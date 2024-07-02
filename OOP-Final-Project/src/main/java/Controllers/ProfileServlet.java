package Controllers;

import Models.Account;
import Models.Managers.AccountManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
@MultipartConfig
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String loggedInUsername = (String) request.getSession().getAttribute("username");
        String username = request.getParameter("username");

        if (username == null) username = loggedInUsername;
        Account account = accountManager.getAccount(username);
        request.setAttribute("account", account);
        boolean isSelf = username.equals(loggedInUsername);
        request.setAttribute("isSelf", isSelf);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Profile.jsp");
        requestDispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Changing account information...");
        String method = request.getParameter("_method");
        if (method != null && method.equalsIgnoreCase("put")) {
            doPut(request, response);
        } else {
            String username = (String) request.getSession().getAttribute("username");
            if (username != null) {
                AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
                Account account = accountManager.getAccount(username);
                request.setAttribute("account", account);
                request.setAttribute("isSelf", true);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditProfile.jsp");
                requestDispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in to edit your profile.");
            }
        }
    }


    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String username = (String) request.getSession().getAttribute("username");
        Account account = accountManager.getAccount(username);
        String loggedInUser = (String) request.getSession().getAttribute("username");

        account.setFirstName(request.getParameter("firstName"));
        account.setLastName(request.getParameter("lastName"));
        account.setEmail(request.getParameter("email"));
        request.setAttribute("account", account);
        request.setAttribute("isSelf", username.equals(loggedInUser));
        System.out.println(account);

        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getSubmittedFileName(filePart);
            String uploadDir = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdirs();
            }
            filePart.write(uploadDir + File.separator + fileName);
            account.setImageUrl("uploads/" + fileName);
        }

        accountManager.updateAccount(account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditProfile.jsp");
        requestDispatcher.forward(request, response);    }

    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
