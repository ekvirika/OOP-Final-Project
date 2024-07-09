package Controllers;

import Controllers.Managers.AccountManager;
import Models.Account;
import Models.LeaderboardEntry;
import Controllers.Managers.LeaderboardManager;
import Models.Quiz;
import Controllers.Managers.QuizManager;
import Models.QuizHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "QuizServlet", urlPatterns = {"/QuizServlet"})
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String username = request.getSession().getAttribute("username").toString();
        Account account = accountManager.getAccount(username);
        request.setAttribute("account", account);
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        LeaderboardManager leaderboardManager = (LeaderboardManager) getServletContext().getAttribute(LeaderboardManager.ATTRIBUTE_NAME);
        Quiz quiz = quizManager.getQuiz(quizId);
        request.setAttribute("currentQuiz", quiz);

        request.setAttribute("quizId", quizId);
//        int quizid = request.getSession().getAttribute("quizId").toString();

        boolean isAdmin = accountManager.isAdmin(username);
        request.setAttribute("isAdmin", isAdmin);

        try {
            List<LeaderboardEntry> leaderboard = leaderboardManager.getLeaderboard(quizId);
            request.setAttribute("leaderboard", leaderboard);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to retrieve leaderboard data", e);
        }
        request.getSession().setAttribute("quiz", quiz);
        request.getRequestDispatcher("/QuizDescription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if ("deleteQuiz".equals(action)) {
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);

            quizManager.deleteQuiz(quizId);
            System.out.println(request.getContextPath() + "webapp/HomePage.jsp");
            response.sendRedirect(request.getContextPath() + "/HomePageServlet");
        }
        else if ("editQuiz".equals(action)) {
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
            Quiz quiz = quizManager.getQuiz(quizId);

            request.setAttribute("quiz", quiz);
            response.sendRedirect(request.getContextPath() + "/QuizServlet");
        }
        else {
            int quizId = Integer.parseInt(request.getParameter("quizId"));
            String username = (String) request.getSession().getAttribute("username");

            QuizHistory quizHistory = new QuizHistory(quizId, username);
            quizHistory.setStartTime(new java.sql.Time(System.currentTimeMillis()));

            int questionIndex = 0;
            request.getSession().setAttribute("quizHistory", quizHistory);
            request.getSession().setAttribute("questionIndex", questionIndex);
            response.sendRedirect(request.getContextPath() + "/QuestionServlet?quizId=" + quizId + "&questionIndex=0");
        }
    }
}