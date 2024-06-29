package Controllers;

import Models.Managers.AccountManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "Authorisation", urlPatterns = {"/AuthorisationServlet"})
public class AuthorisationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");
        httpRequest.setAttribute("username", username);

        try {
            if (accountManager.successfulLogin(username, password)) {
                RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("HomePage.jsp");
                requestDispatcher.forward(httpRequest, httpResponse);
            } else {
                RequestDispatcher requestDispatcher = httpRequest.getRequestDispatcher("AuthorisationTryAgain.jsp");
                requestDispatcher.forward(httpRequest, httpResponse);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
