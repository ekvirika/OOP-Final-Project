package Controllers;

import Models.LeaderboardEntry;
import Controllers.Managers.LeaderboardManager;
import Controllers.Managers.QuizHistoryManager;
import Controllers.Managers.QuizManager;
import Models.QuizHistory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuizStatsServlet", urlPatterns = {"/QuizStatsServlet"})
public class QuizStatsServlet extends HttpServlet {

    private QuizHistoryManager quizHistoryManager;

    @Override
    public void init() throws ServletException {
        super.init();
        quizHistoryManager = (QuizHistoryManager) getServletContext().getAttribute(QuizHistoryManager.ATTRIBUTE_NAME);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizHistory quizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");
        String loggedIn = (String) request.getSession().getAttribute("username");
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        int quizId = quizHistory.getQuizId();
        request.getSession().setAttribute("quizId", quizId);
        String quizName = quizManager.getQuiz(quizId).getQuizName();
        int score = quizHistory.getQuizScore();

        // Check if quiz history is already stored
        if (!isQuizHistoryStored(request)) {
            quizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
            long startTime = quizHistory.getStartTime().getTime();
            long endTime = quizHistory.getEndTime().getTime();
            long timeTakenSeconds = (endTime - startTime) / 1000;
            quizHistory.setElapsedTime(timeTakenSeconds);
            String username = quizHistory.getUsername();

            // Store quiz history in database
            try {
                quizHistoryManager.createQuizHistory(quizHistory);
                request.getSession().setAttribute("quizHistoryStored", true); // Set flag to indicate history is stored
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Failed to store quiz history in database", e);
            }
        }

        List<QuizHistory> personalHistory = new ArrayList<>();
        try {
            personalHistory = quizHistoryManager.getAllQuizHistoryByUsername(loggedIn);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to retrieve quiz history from database", e);
        }

        LeaderboardManager leaderboardManager = (LeaderboardManager) getServletContext().getAttribute(LeaderboardManager.ATTRIBUTE_NAME);
        try {
            List<LeaderboardEntry> leaderboard = leaderboardManager.getLeaderboard(quizId);
            request.setAttribute("leaderboard", leaderboard);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to retrieve leaderboard data", e);
        }

        quizStatsCounter(personalHistory, request);
        request.setAttribute("quizId", quizId);
        request.setAttribute("personalHistory", personalHistory);
        request.setAttribute("quizName", quizName);
        request.setAttribute("username", loggedIn);
        request.setAttribute("score", score);
        request.setAttribute("timeTakenSeconds", quizHistory.getElapsedTime());
        request.setAttribute("quizHistory", quizHistory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("QuizStats.jsp");
        dispatcher.forward(request, response);
    }

    private boolean isQuizHistoryStored(HttpServletRequest request) {
        Boolean quizHistoryStored = (Boolean) request.getSession().getAttribute("quizHistoryStored");
        return quizHistoryStored != null && quizHistoryStored;
    }

    private void quizStatsCounter(List<QuizHistory> personalHistory, HttpServletRequest request) {
        double totalScore = 0;
        double totalTimeTaken = 0;
        int count = 0;

        for (QuizHistory history : personalHistory) {
            totalScore += history.getQuizScore();
            totalTimeTaken += history.getElapsedTime();
            count++;
        }

        double averageScore = count > 0 ? totalScore / count : 0;
        double averageTimeTaken = count > 0 ? totalTimeTaken / count : 0;

        request.setAttribute("averageScore", averageScore);
        request.setAttribute("averageTimeTaken", averageTimeTaken);
    }
}
