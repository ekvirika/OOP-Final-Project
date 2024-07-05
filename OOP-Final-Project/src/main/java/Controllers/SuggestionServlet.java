package Controllers;

import DAO.AccountDAO;
import Models.Account;
import Models.Managers.AccountManager;
import com.google.gson.Gson;
import jdk.jshell.SourceCodeAnalysis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet ("/suggestion")
public class SuggestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String query = request.getParameter("query");
        System.out.println(query);
        List<Account> suggestions = new ArrayList<>();
        System.out.println("SHEMOVEDI");

        try {
            AccountManager accountManager = new AccountManager();
            List<Account> allAccounts = accountManager.getAccounts();
            System.out.println("SHEMOVEDI123");
            for (Account account : allAccounts) {
                if(account.getFirstName().contains(query) || account.getLastName().contains(query) || account.getUserName().contains(query)){
                    suggestions.add(account);
                }
            }
            System.out.println("SHEMOVEDI");
            request.setAttribute("results", suggestions);
            // Forward to the JSP page to display results
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Search.jsp");
            dispatcher.forward(request, response);
            System.out.println(suggestions);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Convert the list of suggestions to JSON
        Gson gson = new Gson();
        String jsonSuggestions = gson.toJson(suggestions);

        // Set the response content type and write the JSON to the response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonSuggestions);
        out.flush();
    }
}
