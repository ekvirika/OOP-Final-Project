//package Controllers;
//
//import Models.Question;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "QuestionServlet", urlPatterns = {"/question"})
//public class QuestionServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int questionId = Integer.parseInt(request.getParameter("questionId"));
//        Question question = fetchQuestion(questionId);
//
//        // Set question attribute in request
//        request.setAttribute("question", question);
//
//        // Forward to singleQuestionPage.jsp to display the question
//        request.getRequestDispatcher("/singleQuestionPage.jsp").forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int questionId = Integer.parseInt(request.getParameter("questionId"));
//        String userAnswer = request.getParameter("userAnswer");
//
//        boolean isCorrect = checkAnswer(questionId, userAnswer); // Implement checkAnswer method
//        redirectToNextQuestion(response);
//    }
//
//    private void redirectToNextQuestion(HttpServletResponse response) throws IOException {
//        // Redirect to the next question based on application logic
//        response.sendRedirect("question?questionId=nextQuestionId"); // Implement logic to determine next question ID
//    }
//}
