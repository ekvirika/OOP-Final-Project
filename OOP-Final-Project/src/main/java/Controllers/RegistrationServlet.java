package Controllers;

import Models.Account;
import Models.Managers.AccountManager;
import Models.PasswordHasher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Registration", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws ServletException, IOException {

        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        String salt = PasswordHasher.generateSalt();
        try {
            password = PasswordHasher.hash(password, salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String email = httpRequest.getParameter("email");
        String name = httpRequest.getParameter("first_name");
        String lastName = httpRequest.getParameter("last_name");

        Account account = new Account(username, name, lastName, password, email, "", salt);
        httpRequest.setAttribute("first_name", name);
        httpRequest.setAttribute("last_name", lastName);
        httpRequest.setAttribute("username", username);
        if (!accountManager.accountExists(username)) {
            System.out.println(account);
            accountManager.createNewUser(account);
            RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("HomePage.jsp");
            requestDispatcher.forward(httpRequest, httpResponse);
        } else {
            RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("RegisterTryAgain.jsp");
            requestDispatcher.forward(httpRequest, httpResponse);
        }
    }
}
