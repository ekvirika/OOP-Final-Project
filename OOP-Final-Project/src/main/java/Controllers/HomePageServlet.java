package Controllers;

import Models.Account;
import Models.LeaderboardEntry;
import Models.Managers.AccountManager;
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
        String username = (String) request.getSession().getAttribute("username");
        Account account = (Account) accountManager.getAccount(username);

//        request.setAttribute("leaderboard", accountManager.getLeaderboard());
//        request.setAttribute("newQuizzes", accountManager.getNewQuizzes());
        request.setAttribute("username", username);
        request.setAttribute("account", account);
        // Assuming you have retrieved the leaderboard list from somewhere
        List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) accountManager.getLeaderboard();
        List<Quiz> newQuizzes = accountManager.getNewQuizzes();
        request.setAttribute("leaderboard", leaderboard);
        request.setAttribute("newQuizzes", newQuizzes);
        request.getSession().setAttribute("account", account);
        request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
    }
}
