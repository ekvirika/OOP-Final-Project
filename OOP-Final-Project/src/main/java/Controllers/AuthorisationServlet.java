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

@WebServlet(name = "AuthorisationServlet", urlPatterns = {"/AuthorisationServlet"})
public class AuthorisationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username", username);
        try {
            if (accountManager.successfulLogin(username, password)) {
                request.getSession().setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/HomePageServlet");
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("AuthorisationTryAgain.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
