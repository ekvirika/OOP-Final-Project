package Controllers;

import Models.Question;
import Models.Quiz;
import Controllers.Managers.QuizManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddAnnouncementServlet", urlPatterns = {"/AddAnnouncementServlet"})
public class AddAnnouncementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Optional: Check if the user is authorized to access this servlet
        // For example, ensure user is logged in or has necessary permissions

        // Example code from your original servlet
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        System.out.println("quiz: " + quiz);
        QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        int quizId;

        // If there's no quiz object in the session, create a new one
        if (quiz == null) {
            String username = (String) request.getSession().getAttribute("username");
            quiz = new Quiz();
            quiz.setCreatorUsername(username);
            quizId = quizManager.addQuiz(quiz);
            quiz.setQuizID(quizId);
            System.out.println("New Quiz created with ID: " + quizId);
        } else {
            quizId = quiz.getQuizID();
            System.out.println("Existing Quiz ID: " + quizId);
        }

        // Retrieve questions related to the quiz from the quiz manager
        List<Question> questions = quizManager.getAllQuestionsByQuiz(quizId);

        // Set attributes in the session for later use
        request.getSession().setAttribute("questions", questions);
        request.getSession().setAttribute("quizId", quizId);
        request.getSession().setAttribute("quiz", quiz);

        // Forward the request to a JSP page for rendering
        request.getRequestDispatcher("CreateQuiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String announcementText = request.getParameter("announcement");

        System.out.println("Announcement Text: " + announcementText);

        response.sendRedirect("HomePageServlet");
    }
}
