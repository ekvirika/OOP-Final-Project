package Controllers;

import Models.Managers.QuestionManager;
import Models.Managers.QuizManager;
import Models.Question;
import Models.Quiz;
import Models.QuizHistory;
import utils.TakeQuiz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QuestionServlet", urlPatterns = {"/QuestionServlet"})
public class QuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Quiz quiz = quizManager.getQuiz(quizId);
        System.out.println(quiz);
        ArrayList<Integer> questionIds = quiz.getQuestionIds();
        int questionIndex = (int) request.getSession().getAttribute("questionIndex");

        if (questionIndex >= questionIds.size()) {
            doPost(request, response);
            return;
        }

        int questionId = questionIds.get(questionIndex);
        Question question = questionManager.getQuestion(questionId);

        TakeQuiz takeQuiz = new TakeQuiz();
        String questionHtml = takeQuiz.generateUI(question.getQuestionType().ordinal(), question);

        request.setAttribute("currentQuiz", quiz);
        request.setAttribute("questionHtml", questionHtml);
        request.setAttribute("quizId", quizId);
        request.setAttribute("questionIndex", questionIndex);

        RequestDispatcher dispatcher = request.getRequestDispatcher("SinglePageQuestion.jsp");
        dispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        Integer questionIndex = (Integer) request.getSession().getAttribute("questionIndex");
        Quiz quiz = quizManager.getQuiz(quizId);
        List<Integer> questionIds = quiz.getQuestionIds();
        QuizHistory quizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");
        request.getSession().setAttribute("quizHistory", quizHistory);

        if (questionIndex >= questionIds.size()) {
            response.sendRedirect("/QuizStatsServlet");
            quizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
            request.getSession().setAttribute("quizHistory", quizHistory);
            request.getSession().setAttribute("username", quizHistory.getUsermame());
            response.sendRedirect("/QuizStatsServlet");
            return;
        }

        int questionId = questionIds.get(questionIndex);
        Question question = questionManager.getQuestion(questionId);

        String userAnswer = request.getParameter("userAnswer");
        if (quizHistory != null) {
            if (isAnswerCorrect(question, userAnswer)) {
                quizHistory.setQuizScore(quizHistory.getQuizScore() + 1);
            }
        }

        request.getSession().setAttribute("questionIndex", questionIndex + 1);
        if (questionIndex + 1 >= questionIds.size()) {
            assert quizHistory != null;
            quizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
            request.getSession().setAttribute("quizHistory", quizHistory);
            request.getSession().setAttribute("username", quizHistory.getUsermame());
            response.sendRedirect("/QuizStatsServlet");
        } else {
            response.sendRedirect("/QuestionServlet?quizId=" + quizId);
        }
    }

    private boolean isAnswerCorrect(Question question, String userAnswer) {
        return question.getSingleQuestionAnswer().equalsIgnoreCase(userAnswer.trim());
    }
}