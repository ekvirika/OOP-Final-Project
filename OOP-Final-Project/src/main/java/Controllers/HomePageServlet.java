package Controllers;

import Models.Account;
import Models.LeaderboardEntry;
import Models.Managers.AccountManager;
import Models.Managers.QuizManager;
import Models.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePageServlet", urlPatterns = {"/HomePageServlet"})
public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        
        String username = (String) request.getSession().getAttribute("username");
        Account account = (Account) accountManager.getAccount(username);
        List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) accountManager.getLeaderboard();
        List<Quiz> quizzes = quizManager.getAllQuizzes();
        
        
        request.setAttribute("username", username);
        request.setAttribute("account", account);
        request.setAttribute("quizzes", quizzes);
        request.setAttribute("leaderboard", leaderboard);
        request.setAttribute("quizzes", quizzes);
        request.getSession().setAttribute("account", account);
        request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
    }
}
