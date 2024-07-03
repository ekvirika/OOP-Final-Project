package Controllers;

import Models.Quiz;
import Models.Managers.QuizManager;
import Models.QuizHistory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuizServlet", urlPatterns = {"/QuizServlet"})
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);

        Quiz quiz = quizManager.getQuiz(quizId);
        request.setAttribute("currentQuiz", quiz);

        request.getRequestDispatcher("/QuizDescription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        String username = (String) request.getSession().getAttribute("username");

        QuizHistory quizHistory = new QuizHistory(quizId, username);
        quizHistory.setStartTime(new java.sql.Time(System.currentTimeMillis()));

        int questionIndex=0;
        request.getSession().setAttribute("quizHistory", quizHistory);
        request.getSession().setAttribute("questionIndex", questionIndex);
        response.sendRedirect(request.getContextPath() + "/QuestionServlet?quizId=" + quizId + "&questionIndex=0");
    }
}
