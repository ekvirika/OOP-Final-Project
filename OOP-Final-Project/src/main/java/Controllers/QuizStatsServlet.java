package Controllers;

import Models.Managers.QuizManager;
import Models.QuizHistory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "QuizStatsServlet", urlPatterns = {"/QuizStatsServlet"})
public class QuizStatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizHistory quizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");
        QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        int quizId = quizHistory.getQuizId();
        String quizName = quizManager.getQuiz(quizId).getQuizName();
        int score = quizHistory.getQuizScore();
        long startTime = quizHistory.getStartTime().getTime();
        long endTime = quizHistory.getEndTime().getTime();
        long timeTakenSeconds = (endTime - startTime) / 1000;
        String username = quizHistory.getUsermame();


//        List<Map<String, Object>> leaderboard = quizHistory.getLeaderboard(); // Adjust as per your QuizHistory implementation
//        request.setAttribute("leaderboard", leaderboard);


        // Set attributes for JSP
        request.setAttribute("quizName", quizName);
        request.setAttribute("username", username);
        request.setAttribute("score", score);
        request.setAttribute("timeTakenSeconds", timeTakenSeconds);
        request.setAttribute("quizHistory", quizHistory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("QuizStats.jsp");
        dispatcher.forward(request, response);
    }

}
