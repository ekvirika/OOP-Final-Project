package Controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/notificationServlet")
public class NotificationServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        System.out.println("shemovedi");
        Gson gson = new Gson();
        RequestData requestData = gson.fromJson(reader, RequestData.class);
        System.out.println(requestData);
        String type = requestData.type;
        ResponseData responseData = new ResponseData();
        System.out.println(type);

        if ("quiz".equals(type)) {
            String quizLink = requestData.quizLink;
            String bestScore = requestData.bestScore;
            System.out.println("quizi: " + quizLink);
            System.out.println("score: " + bestScore);
            // Process the quiz link and best score here
            responseData.status = "success";
            responseData.message = "Quiz data received.";

        } else if ("note".equals(type)) {
            String message = requestData.message;
            // Process the message here
            System.out.println("message: "+ message);
            responseData.status = "success";
            responseData.message = "Note received.";
        } else {
            responseData.status = "error";
            responseData.message = "Invalid request type.";
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
    }

    private class ResponseData {
        String status;
        String message;
    }
}
