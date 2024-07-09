package Controllers;

import Controllers.Managers.*;
import Models.Account;
import Models.LeaderboardEntry;
import Models.QuizHistory;
import javafx.util.Pair;

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
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        AchievementManager achievementManager = (AchievementManager) getServletContext().getAttribute(AchievementManager.ATTRIBUTE_NAME);
        Account acc = accountManager.getAccount(loggedIn);
        int quizId = quizHistory.getQuizId();
        try {
            if (quizHistoryManager.getAllQuizHistoryByUsername(loggedIn).size() == 5) {
                acc.getAchievementIds().add(4);
            } else if (quizHistoryManager.getAllQuizHistoryByUsername(loggedIn).size() == 10) {
                acc.getAchievementIds().add(6);
            }
            accountManager.updateAccount(acc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getSession().setAttribute("quizId", quizId);
        String quizName = quizManager.getQuiz(quizId).getQuizName();
        int score = quizHistory.getQuizScore();

        quizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
        long startTime = quizHistory.getStartTime().getTime();
        long endTime = quizHistory.getEndTime().getTime();
        long timeTakenSeconds = (endTime - startTime) / 1000;
        quizHistory.setElapsedTime(timeTakenSeconds);
        String username = quizHistory.getUsername();

        try {
            quizHistoryManager.createQuizHistory(quizHistory);
            if (quizHistoryManager.getHighestScoreUserNameByQuizId(quizId).equals(acc.getUserName())
                    && quizHistory.getQuizScore() > 0) {
                acc.getAchievementIds().add(5);
            }
            accountManager.updateAccount(acc);
            request.getSession().setAttribute("quizHistoryStored", true); // Set flag to indicate history is stored
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to store quiz history in database", e);
        }


        List<QuizHistory> personalHistory = new ArrayList<>();
        personalHistory = quizHistoryManager.getAllQuizHistoryByUsername(username, quizId);
//            personalHistory = quizHistoryManager.getAllQuizHistoryByUsername(loggedIn);

        LeaderboardManager leaderboardManager = (LeaderboardManager) getServletContext().getAttribute(LeaderboardManager.ATTRIBUTE_NAME);
        try {
            List<LeaderboardEntry> leaderboard = leaderboardManager.getLeaderboard(quizId);
            request.setAttribute("leaderboard", leaderboard);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to retrieve leaderboard data", e);
        }

//        quizStatsCounter(personalHistory, request);
        try {
            Pair<Long, Long> avgs = quizHistoryManager.getAverageScoreAndTimeByQuizId(quizId);
            request.setAttribute("avgScore", avgs.getKey());
            request.setAttribute("avgTime", avgs.getKey());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

}
