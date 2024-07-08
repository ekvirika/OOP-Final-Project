package Controllers;

import Controllers.Managers.FriendManager;
import Models.Enums.NotificationType;
import Controllers.Managers.NotificationManager;
import Models.FriendRequest;
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

            responseData.status = "success";
            responseData.message = "Quiz data received.";
            responseData.receiver = receiver;

            try {
                Notification note = new Notification(loggedInUsername, receiver, NotificationType.CHALLENGE, quizLink, 0, "");
                NotificationManager notificationManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
                notificationManager.createNotification(note);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if ("note".equals(type)) {
            String message = requestData.message;

            responseData.status = "success";
            responseData.message = "Note received.";
            responseData.receiver = receiver;

            try {
                Notification note = new Notification(loggedInUsername, receiver, NotificationType.NOTE, "", 0, message);
                NotificationManager notificationManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
                notificationManager.createNotification(note);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if ("addFriend".equals(type)) {
            try {
                FriendRequest friendRequest = new FriendRequest(loggedInUsername, receiver);
                FriendManager friendManager = (FriendManager) request.getServletContext().getAttribute(FriendManager.ATTRIBUTE_NAME);
                friendManager.sendFriendRequest(loggedInUsername, receiver);

                int requestId = friendManager.getFriendRequestID(loggedInUsername, receiver);
                Notification addFriend = new Notification(loggedInUsername, receiver, NotificationType.FRIEND_REQUEST, "", requestId, "");
                NotificationManager notificationManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
                notificationManager.createNotification(addFriend);

                responseData.status = "success";
                responseData.message = "Friend request sent and notification created.";
                responseData.receiver = receiver;
            } catch (SQLException e) {
                responseData.status = "error";
                responseData.message = e.getMessage();
                responseData.receiver = receiver;
                e.printStackTrace();
            }
        }
        else if ("pending".equals(type)){
            System.out.println("PENDING");
            boolean answer = requestData.answer;
            System.out.println("answer: " + answer);

            FriendManager friendManager = (FriendManager) request.getServletContext().getAttribute(FriendManager.ATTRIBUTE_NAME);

            NotificationManager notificationManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
//            notificationManager.getNotificationById()

            if(answer){
                System.out.println("aqvaar");
                try {
                    System.out.println("shemovedi tryshi");
                    friendManager.acceptFriendRequest(loggedInUsername, requestData.receiver);
                    System.out.println("gamovedy manageridan");
//                    notificationManager.deleteNotification(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else{
                try {
                    friendManager.rejectFriendRequest(requestData.receiver, loggedInUsername);
//                    notificationManager.deleteNotification(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
        boolean answer;
    }

    private class ResponseData {
        String status;
        String message;
        String receiver;
    }
}