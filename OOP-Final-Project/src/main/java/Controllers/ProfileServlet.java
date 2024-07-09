package Controllers;

import Controllers.Managers.AccountManager;
import Controllers.Managers.FriendManager;
import Controllers.Managers.QuizManager;
import Models.Account;
import Models.Quiz;

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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/ProfileServlet"})
@MultipartConfig
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        FriendManager friendManager = (FriendManager) getServletContext().getAttribute(FriendManager.ATTRIBUTE_NAME);
        String loggedInUsername = (String) request.getSession().getAttribute("username");
        String username = request.getParameter("username");
        System.out.println(username);
        System.out.println(loggedInUsername);

        if (username == null) username = loggedInUsername;
        Account account = accountManager.getAccount(username);
        Account loggedInAccount = accountManager.getAccount(loggedInUsername);
        request.setAttribute("account", account);
        boolean isSelf = username.equals(loggedInUsername);
        request.setAttribute("isSelf", isSelf);
        boolean isAdmin = loggedInAccount.isAdmin();
        request.setAttribute("isAdmin", isAdmin);

        List<Quiz> quizList = quizManager.getQuizzesByUser(username);
        request.setAttribute("quizList", quizList);

        try {
            List<String> friendsList= friendManager.getAcceptedFriendRequests(username);
            request.setAttribute("friendsList", friendsList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Profile.jsp");
        requestDispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountManager accountManager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
        String method = request.getParameter("_method");
        String action = request.getParameter("action");
        if(action != null && action.equals("deleteProfile")){
            String username = request.getParameter("username");
            accountManager.deleteAccount(username);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("HomePageServlet");
            requestDispatcher.forward(request, response);
            return;
        }
        if (method != null && method.equalsIgnoreCase("put")) {
            doPut(request, response);
        } else {
            System.out.println("Changing account information...");
            String username = (String) request.getSession().getAttribute("username");
            if (username != null) {
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
