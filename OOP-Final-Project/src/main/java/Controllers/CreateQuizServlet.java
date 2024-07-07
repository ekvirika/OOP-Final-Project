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
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        System.out.println("quizi1: " + quiz);
        QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        int quizId;
        if(quiz == null) {
            String username = request.getSession().getAttribute("username").toString();
            quiz = new Quiz();
            quizId = quizManager.addQuiz(quiz);
            quiz.setQuizID(quizId);
            quiz.setCreatorUsername(username);
        } else quizId = quiz.getQuizID();
        System.out.println("aidio: " + quizId);
        System.out.println(quiz);
        List<Question> questions = quizManager.getAllQuestionsByQuiz(quizId);
        TakeSinglePageQuiz uihelper = new TakeSinglePageQuiz();
        StringBuilder html = new StringBuilder();
        for(Question question : questions) {
            html.append(uihelper.generateUI(question.getQuestionType(), question));
        }
        System.out.println(html.toString());

        request.getSession().setAttribute("quizId", quizId);
        request.setAttribute("html", html.toString());
        request.getSession().setAttribute("quiz", quiz);
        request.getRequestDispatcher("CreateQuiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }
}
