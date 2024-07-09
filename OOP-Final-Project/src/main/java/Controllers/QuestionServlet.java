package Controllers;

import Models.Enums.QuestionType;
import Controllers.Managers.QuestionManager;
import Controllers.Managers.QuizManager;
import Models.Question;
import Models.Quiz;
import Models.QuizHistory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import utils.TakeQuiz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "QuestionServlet", urlPatterns = {"/QuestionServlet"})
public class QuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        QuizManager quizManager = (QuizManager) getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        request.getSession().setAttribute("qid", quizId);
        Quiz quiz = quizManager.getQuiz(quizId);
        TakeQuiz takeQuiz = new TakeQuiz();

        if (quiz.isSinglePage()) {
            StringBuilder questionsHtml = new StringBuilder();
            List<Question> questions = quizManager.getAllQuestionsByQuiz(quizId);
            for (Question question : questions) {
                questionsHtml.append(takeQuiz.generateUI(question.getQuestionType(), question));
            }
            request.setAttribute("questionsHtml", questionsHtml.toString());
            request.setAttribute("quizId", quizId);
            request.setAttribute("questions", questions); // Ensure questions are set here
            request.setAttribute("currentQuiz", quiz);

            RequestDispatcher dispatcher = request.getRequestDispatcher("SinglePageQuiz.jsp");
            dispatcher.forward(request, response);
            return;
        }

        ArrayList<Integer> questionIds = quiz.getQuestionIds();
        Integer questionIndex = (Integer) request.getSession().getAttribute("questionIndex");

        if (questionIndex == null) {
            questionIndex = 0;
            request.getSession().setAttribute("questionIndex", questionIndex);
        }

        if (questionIndex >= questionIds.size()) {
            doPost(request, response);
            return;
        }

        int questionId = questionIds.get(questionIndex);
        Question question = questionManager.getQuestion(questionId);
        System.out.println(question);
        String questionHtml = takeQuiz.generateUI(question.getQuestionType(), question);

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

        if (quiz.isSinglePage()) {
            handleSinglePageQuiz(request, response, quiz);
            return;
        } else {
            handleMultiPageQuiz(request, response, quiz, questionIndex);
            return;
        }
    }

    private void handleSinglePageQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz)
            throws ServletException, IOException {
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
        List<Integer> questionIds = quiz.getQuestionIds();
        QuizHistory quizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");
        String username = (String) request.getSession().getAttribute("username");

        for (Integer questionId : questionIds) {
            Question question = questionManager.getQuestion(questionId);
            String userAnswer = request.getParameter("userAnswer_" + questionId);
            String userAnswersList = request.getParameter("userAnswers_" + questionId);
            System.out.println("ans: " + userAnswer);
            System.out.println("anss: " + userAnswersList);
            Gson gson = new Gson();

            List<String> userAnswers = new ArrayList<>();
            HashMap<String, String> hashmapAnswer = new HashMap<>();

            if (question.getQuestionType().equals(QuestionType.MULTIPLE_CHOICE) ||
                    question.getQuestionType().equals(QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS) ||
                    question.getQuestionType().equals(QuestionType.MULTI_ANSWER)) {
                Type listType = new TypeToken<ArrayList<String>>() {}.getType();
                userAnswers = gson.fromJson(userAnswersList, listType);
            } else if (question.getQuestionType().equals(QuestionType.MATCHING)) {
                Type hashmapType = new TypeToken<HashMap<String, String>>() {}.getType();
                hashmapAnswer = gson.fromJson(userAnswersList, hashmapType);
            }

            if (questionManager.isAnswerCorrect(question, userAnswer, (ArrayList<String>) userAnswers, hashmapAnswer)) {
                quizHistory.setQuizScore(quizHistory.getQuizScore() + 1);
            }
        }

        request.getSession().setAttribute("quizHistory", quizHistory);
        request.getSession().setAttribute("username", quizHistory.getUsername());
        response.sendRedirect("/QuizStatsServlet");
    }

    private void handleMultiPageQuiz(HttpServletRequest request, HttpServletResponse response, Quiz quiz, Integer questionIndex)
            throws ServletException, IOException {
        QuestionManager questionManager = (QuestionManager) getServletContext().getAttribute(QuestionManager.ATTRIBUTE_NAME);
        int quizId = quiz.getQuizID();

        List<Integer> questionIds = quiz.getQuestionIds();
        QuizHistory quizHistory = (QuizHistory) request.getSession().getAttribute("quizHistory");

        if (questionIndex >= questionIds.size()) {
            quizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
            request.getSession().setAttribute("quizHistory", quizHistory);
            request.getSession().setAttribute("username", quizHistory.getUsername());
            response.sendRedirect("/QuizStatsServlet");
            return;
        }

        int questionId = questionIds.get(questionIndex);
        Question question = questionManager.getQuestion(questionId);
        String userAnswer = request.getParameter("userAnswer");
        System.out.println(question);
        String userAnswersList = request.getParameter("userAnswers");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        List<String> userAnswers = new ArrayList<>();
        if (question.getQuestionType().equals(QuestionType.MULTIPLE_CHOICE) ||
                question.getQuestionType().equals(QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS) ||
                question.getQuestionType().equals(QuestionType.MULTI_ANSWER)) {
            userAnswers = gson.fromJson(userAnswersList, listType);
        }
        Type hashmapType = new TypeToken<HashMap<String, String>>() {}.getType();
        HashMap<String, String> hashmapAnswer = new HashMap<>();
        if (question.getQuestionType().equals(QuestionType.MATCHING)) {
            hashmapAnswer = gson.fromJson(userAnswersList, hashmapType);
        }
        System.out.println("list: " + hashmapAnswer);
        if (questionManager.isAnswerCorrect(question, userAnswer, (ArrayList<String>) userAnswers, hashmapAnswer)) {
            quizHistory.setQuizScore(quizHistory.getQuizScore() + 1);
        }
        System.out.println("Score: " + quizHistory.getQuizScore());

        request.getSession().setAttribute("questionIndex", questionIndex + 1);

        if (questionIndex + 1 >= questionIds.size()) {
            quizHistory.setEndTime(new java.sql.Time(System.currentTimeMillis()));
            request.getSession().setAttribute("quizHistory", quizHistory);
            request.getSession().setAttribute("username", quizHistory.getUsername());
            response.sendRedirect("/QuizStatsServlet");
        } else {
            response.sendRedirect("/QuestionServlet?quizId=" + quizId);
        }
    }
}
