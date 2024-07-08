package Controllers;

import Models.Enums.NotificationType;
import Models.Managers.NotificationManager;
import Models.Notification;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        System.out.println("shemovedi");
        Gson gson = new Gson();
        RequestData requestData = gson.fromJson(reader, RequestData.class);
        String type = requestData.type;
        ResponseData responseData = new ResponseData();
        System.out.println(type);

        String loggedInUsername = (String) request.getSession().getAttribute("username");
        System.out.println(loggedInUsername);
        String receiver = requestData.receiver;

        if(type.equals("addFriend")) {System.out.println("shevqmeni");}
        else System.out.println("shevqmeni2");


        if ("quiz".equals(type)) {
            String quizLink = requestData.quizLink;
            String bestScore = requestData.bestScore;

            System.out.println("quizi: " + quizLink);
            System.out.println("score: " + bestScore);
            System.out.println("receiver: " + receiver);

            // Process the quiz link and best score here
            responseData.status = "success";
            System.out.println(responseData.status);
            responseData.message = "Quiz data received.";

            System.out.println(responseData.message);
            responseData.receiver = receiver;
            System.out.println(responseData.receiver);

            System.out.println("aq movedi");
            Notification note = new Notification(loggedInUsername, receiver, NotificationType.CHALLENGE, 1, 0, "");
            System.out.println("aaqac");
            NotificationManager notificationManager = (NotificationManager) request.getServletContext().getAttribute(NotificationManager.ATTRIBUTE_NAME);
            System.out.println("manager arsebobs");
            try {
                System.out.println("vamateb nots");
                notificationManager.createNotification(note);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
//        else if ("note".equals(type)) {
//                String message = requestData.message;
//
//                // Process the message here
//                System.out.println("message: "+ message);
//                System.out.println("receiver: " + receiver);
//                responseData.status = "success";
//                responseData.message = "Note received.";
//                responseData.receiver = receiver;
//
//                Notification note = new Notification(1, loggedInUsername, receiver, NotificationType.NOTE, 0, 0, message);
//                System.out.println("shevqmeni");
//                NotificationManager notificationManager = new NotificationManager();
//                System.out.println("shevqmeni");
//                try {
//                    notificationManager.createNotification(note);
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            else if ("addFriend".equals(type)) {
//                System.out.println("megobrebshi vamateb");
//                Notification addFriend = new Notification(1, loggedInUsername, receiver, NotificationType.NOTE, 0, 1, "");
//                System.out.println("shevqmeni");
//                NotificationManager notificationManager = new NotificationManager();
//                try {
//                    notificationManager.createNotification(addFriend);
//>>>>>>> Stashed changes
//                    System.out.println("shevqmeni");
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//        else if ("note".equals(type)) {
//            String message = requestData.message;
//
//            // Process the message here
//            System.out.println("message: "+ message);
//            System.out.println("receiver: " + receiver);
//            responseData.status = "success";
//            responseData.message = "Note received.";
//            responseData.receiver = receiver;
//
//            Notification note = new Notification(1, loggedInUsername, receiver, NotificationType.NOTE, 0, 0, message);
//            System.out.println("shevqmeni");
//            NotificationManager notificationManager = new NotificationManager();
//            System.out.println("shevqmeni");
//            try {
//                notificationManager.createNotification(note);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        else if ("addFriend".equals(type)) {
//            System.out.println("megobrebshi vamateb");
//            Notification addFriend = new Notification(1, loggedInUsername, receiver, NotificationType.NOTE, 0, 1, "");
//            System.out.println("shevqmeni");
//            NotificationManager notificationManager = new NotificationManager();
//            try {
//                notificationManager.createNotification(addFriend);
//                System.out.println("shevqmeni");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
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