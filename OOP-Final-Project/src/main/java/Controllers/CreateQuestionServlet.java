package Controllers;

import Models.Enums.QuestionType;
import Models.Managers.QuestionManager;
import Models.Question;
import Models.Quiz;
import utils.CreateQuiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "CreateQuestionServlet", urlPatterns = {"/CreateQuestionServlet"})
public class CreateQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        QuestionType questionType = QuestionType.valueOf(httpServletRequest.getParameter("hiddenQuestionType"));
        System.out.println(questionType);
        CreateQuiz createQuiz = new CreateQuiz();
        String html = createQuiz.generateUI(questionType);
        System.out.println(html);

        httpServletRequest.setAttribute("html", html);
        httpServletRequest.getRequestDispatcher("CreateQuestion.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionText = request.getParameter("questionText");
        QuestionType questionType = QuestionType.valueOf(request.getParameter("questionType"));
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        QuestionManager questionManager = (QuestionManager) request.getSession().getAttribute(QuestionManager.ATTRIBUTE_NAME);

        // Create a new Question object
        Question question = new Question();
        question.setQuestionText(questionText);
        question.setQuestionType(questionType);
        question.setQuizId(quiz.getQuizID());

        // Handle question type-specific fields
        switch (questionType) {
            case QUESTION_RESPONSE:
            case FILL_IN_THE_BLANK:
            case PICTURE_RESPONSE:
                String answerText = request.getParameter("answerText");
                question.setSingleQuestionAnswer(answerText);
                break;

            case MULTIPLE_CHOICE:
            case MULTIPLE_CHOICE_WITH_ANSWERS:
                ArrayList<String> multipleChoiceAnswers = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                    String answer = request.getParameter("correctAnswer" + i);
                    if (answer != null && !answer.trim().isEmpty()) {
                        multipleChoiceAnswers.add(answer);
                    }
                }
                question.setMultipleChoiceAnswers(multipleChoiceAnswers);

                ArrayList<Integer> correctIndexes = new ArrayList<>();
                for (int i = 0; i < multipleChoiceAnswers.size(); i++) {
                    if (request.getParameter("isCorrect" + i) != null) {
                        correctIndexes.add(i);
                    }
                }
                question.setMultipleChoiceCorrectIndexes(correctIndexes);
                break;

            case MULTI_ANSWER:
                ArrayList<String> multiAnswers = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    String answer = request.getParameter("correctAnswer" + i);
                    if (answer != null && !answer.trim().isEmpty()) {
                        multiAnswers.add(answer);
                    }
                }
                question.setMultipleAnswerFields(multiAnswers);
                break;

            case MATCHING:
                HashMap<String, String> matchingPairs = new HashMap<>();
                int pairCount = Integer.parseInt(request.getParameter("pairCount"));
                for (int i = 1; i <= pairCount; i++) {
                    String questiontxt = request.getParameter("question" + i);
                    String answer = request.getParameter("answer" + i);
                    if (questiontxt != null && answer != null) {
                        matchingPairs.put(questiontxt, answer);
                    }
                }
                question.setMatchingPairs(matchingPairs);
                break;

            default:
                throw new IllegalArgumentException("Unexpected question type: " + questionType);
        }

        questionManager.createQuestion(question);

        response.sendRedirect("CreateQuestionServlet");
    }

}
