package Controllers;

import Models.Account;
import Controllers.Managers.AccountManager;

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

@WebServlet(name = "SuggestionServlet", urlPatterns = {"/SuggestionServlet"})
public class SuggestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String query = request.getParameter("query");
        System.out.println(query);
        List<Account> suggestions = new ArrayList<>();

        String loggedInUsername = (String) request.getSession().getAttribute("username");

        try {
            AccountManager accountManager = new AccountManager();
            List<Account> allAccounts = accountManager.getAccounts();
            for (Account account : allAccounts) {
                if((account.getFirstName().startsWith(query) ||
                    account.getLastName().startsWith(query) ||
                    account.getUserName().startsWith(query) ||
                    account.getEmail().startsWith(query)) &&
                    !account.getUserName().equals(loggedInUsername)){
                    suggestions.add(account);
                }
            }

            request.setAttribute("results", suggestions);
            System.out.println(suggestions);
            // Forward to the JSP page to display results
            RequestDispatcher dispatcher = request.getRequestDispatcher("/SearchResult.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
