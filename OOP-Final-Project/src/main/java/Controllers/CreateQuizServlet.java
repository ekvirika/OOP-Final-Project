package Controllers;

import Models.Managers.QuizManager;
import Models.Question;
import Models.Quiz;
import utils.TakeSinglePageQuiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateQuizServlet", urlPatterns = {"/CreateQuizServlet"})
public class CreateQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Quiz quiz = (Quiz) request.getServletContext().getAttribute("quiz");
        QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        int quizId;
        if(quiz == null) {
            quiz = new Quiz();
            quizId = quizManager.addQuiz(quiz);
        } else quizId = quiz.getQuizID();
        System.out.println(quizId);
        List<Question> questions = quizManager.getAllQuestionsByQuiz(quizId);
        TakeSinglePageQuiz uihelper = new TakeSinglePageQuiz();
        String html = "";
        for(Question question : questions) {
            html += uihelper.generateUI(question.getQuestionType(), question);
        }

        request.getSession().setAttribute("quizId", quizId);
        request.setAttribute("html", html);
        request.getSession().setAttribute("quiz", quiz);
        response.sendRedirect("CreateQuiz.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }
}
