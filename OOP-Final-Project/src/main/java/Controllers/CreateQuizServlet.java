package Controllers;

import Models.Managers.QuestionManager;
import Models.Managers.QuizManager;
import Models.Question;
import Models.Quiz;

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
//        System.out.println("quizi1: " + quiz);
        QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        int quizId;
        if (quiz == null) {
            String username = request.getSession().getAttribute("username").toString();
            quiz = new Quiz();
            quizId = quizManager.addQuiz(quiz);
            quiz.setQuizID(quizId);
            quiz.setCreatorUsername(username);
        } else {
            quizId = quiz.getQuizID();
        }
//        System.out.println(quiz);
        List<Question> questions = quizManager.getAllQuestionsByQuiz(quizId);
//        System.out.println("questions: -------" + questions);
        request.getSession().setAttribute("questions", questions);
        request.getSession().setAttribute("quizId", quizId);
        request.getSession().setAttribute("quiz", quiz);
        request.getRequestDispatcher("CreateQuiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizAction = request.getParameter("quizAction");
        QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");

        if (quiz == null) {
            response.sendRedirect("HomePageServlet");
            return;
        }
        if ("save".equals(quizAction)) {
            quiz.setSinglePage(request.getParameter("isSinglePage") != null);
            quiz.setRandomizeQuestions(request.getParameter("randomizeQuestions") != null);
            quiz.setImmediateFeedback(request.getParameter("immediateFeedback") != null);
            quiz.setQuizDescription(request.getParameter("quizDescription"));
            quiz.setQuizName(request.getParameter("quizName"));

            quizManager.updateQuiz(quiz);
            request.getSession().removeAttribute("quiz");
            request.getSession().removeAttribute("questions");
            response.sendRedirect("HomePageServlet");
        } else if ("delete".equals(quizAction)) {
            if (quiz != null) {
                QuestionManager questionManager = (QuestionManager) request.getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
                List<Integer> questionIds = quiz.getQuestionIds();
                for(int id : questionIds) {
                    questionManager.deleteQuestion(id);
                }
                quizManager.deleteQuiz(quiz.getQuizID());
            }
            request.getSession().removeAttribute("quiz");
            request.getSession().removeAttribute("questions");
            response.sendRedirect("HomePageServlet");
        }
    }
}
