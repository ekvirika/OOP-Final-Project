package Controllers;

import Controllers.Managers.AccountManager;
import Controllers.Managers.QuestionManager;
import Controllers.Managers.QuizManager;
import Models.Account;
import Models.Question;
import Models.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "CreateQuizServlet", urlPatterns = {"/CreateQuizServlet"})
public class CreateQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        System.out.println("quizi1: " + quiz);
        QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        int quizId;
        if (quiz == null) {
            String username = request.getSession().getAttribute("username").toString();
            quiz = new Quiz();
            quiz.setCreatorUsername(username);
            quizId = quizManager.addQuiz(quiz);
            quiz.setQuizID(quizId);

            System.out.println(quizId);
        } else {
            quizId = quiz.getQuizID();
            System.out.println("ucnauri: " + quizId);
        }
        System.out.println(quiz);
        List<Question> questions = quizManager.getAllQuestionsByQuiz(quizId);
        System.out.println("questions: -------" + questions);
        request.getSession().setAttribute("questions", questions);
        request.getSession().setAttribute("quizId", quizId);
        request.getSession().setAttribute("quiz", quiz);
        request.getRequestDispatcher("CreateQuiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizAction = request.getParameter("quizAction");
        String username = request.getSession().getAttribute("username").toString();
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
            /* Achievement handling logic */
            AccountManager manager = (AccountManager) getServletContext().getAttribute(AccountManager.ATTRIBUTE_NAME);
            Account account = manager.getAccount(username);
            Set<Integer> ids = (HashSet<Integer>) account.getAchievementIds();
            if (ids == null) {
                ids = new HashSet<>();
            }
            if (quizManager.getQuizzesByUser(username).size() == 1) { ids.add(1);
            } else if(quizManager.getQuizzesByUser(username).size() == 5) { ids.add(2); }
            else if(quizManager.getQuizzesByUser(username).size() == 10) { ids.add(3); }
            manager.updateAccount(account);
            request.getSession().removeAttribute("quiz");
            request.getSession().removeAttribute("questions");
            response.sendRedirect("HomePageServlet");
        } else if ("delete".equals(quizAction)) {
            if (quiz != null) {
                QuestionManager questionManager = (QuestionManager) request.getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
                List<Integer> questionIds = quiz.getQuestionIds();
                for (int id : questionIds) {
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
