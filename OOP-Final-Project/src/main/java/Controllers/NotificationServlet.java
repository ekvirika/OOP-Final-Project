package Controllers;

import Models.Enums.NotificationType;
import Models.Managers.NotificationManager;
import Models.Notification;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/notificationServlet")
public class NotificationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loggedInUsername = (String) request.getSession().getAttribute("username");
        List<Notification> notifications = null;
        try {
            NotificationManager notificationManager = (NotificationManager) getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
            notifications = notificationManager.getNotificationsToUser(loggedInUsername);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        RequestData requestData = gson.fromJson(reader, RequestData.class);
        String type = requestData.type;
        ResponseData responseData = new ResponseData();

        String loggedInUsername = (String) request.getSession().getAttribute("username");
        String receiver = requestData.receiver;

        if ("quiz".equals(type)) {
            String quizLink = requestData.quizLink;
            String bestScore = requestData.bestScore;

            // Process the quiz link and best score here
            responseData.status = "success";
            responseData.message = "Quiz data received.";
            responseData.receiver = receiver;

            try {
                System.out.println("aloooooo");
                Notification note = new Notification(loggedInUsername, receiver, NotificationType.CHALLENGE, 1, 0, "");
                NotificationManager notificationManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
                notificationManager.createNotification(note);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if ("note".equals(type)) {
            String message = requestData.message;

            // Process the message here
            responseData.status = "success";
            responseData.message = "Note received.";
            responseData.receiver = receiver;

            try {
                Notification note = new Notification(loggedInUsername, receiver, NotificationType.NOTE, 0, 0, message);
                NotificationManager notificationManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
                notificationManager.createNotification(note);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if ("addFriend".equals(type)) {
            try {
                Notification addFriend = new Notification(loggedInUsername, receiver, NotificationType.FRIEND_REQUEST, 0, 1, "");
                NotificationManager notificationManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
                notificationManager.createNotification(addFriend);
                System.out.println("shevqmeni");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            responseData.status = "error";
            responseData.message = "Invalid request type.";
            responseData.receiver = requestData.receiver;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(responseData));
    }

    private class RequestData {
        String type;
        String quizLink;
        String bestScore;
        String message;
        String receiver;
    }

    private class ResponseData {
        String status;
        String message;
        String receiver;
    }
}