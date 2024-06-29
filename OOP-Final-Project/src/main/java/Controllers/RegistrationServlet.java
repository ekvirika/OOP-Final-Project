package Controllers;

import Models.Account;
import Models.Managers.AccountManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Registration", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws ServletException, IOException {

        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        String email = httpRequest.getParameter("email");
        String name = httpRequest.getParameter("first-name");
        String lastName = httpRequest.getParameter("last-name");

        Account account = new Account(username, name, lastName, password, email, "", "");
        httpRequest.setAttribute("first-name", name);
        httpRequest.setAttribute("last-name", lastName);
        if (accountManager.successfulLogin(username, password)) {
            RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("HomePage.jsp");
            requestDispatcher.forward(httpRequest, httpResponse);
        } else {
            RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("RegisterTryAgain.jsp");
            requestDispatcher.forward(httpRequest, httpResponse);
        }
    }
}
