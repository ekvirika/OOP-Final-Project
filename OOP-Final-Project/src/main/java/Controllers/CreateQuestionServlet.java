package Controllers;

import Models.Enums.QuestionType;
import Controllers.Managers.QuestionManager;
import Controllers.Managers.QuizManager;
import Models.Question;
import Models.Quiz;
import utils.CreateQuiz;

import javax.servlet.ServletContext;
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
        String type = httpServletRequest.getParameter("questionType");
        System.out.println("Received questionType in GET: " + type);

        if (type != null) {
            type = type.trim();
        }

        try {
            QuestionType questionType = QuestionType.fromString(type);
            System.out.println("Converted questionType in GET: " + questionType);

            CreateQuiz createQuiz = new CreateQuiz();
            String html = createQuiz.generateUI(questionType);
            System.out.println("Generated HTML: " + html);

            httpServletRequest.setAttribute("html", html);
            httpServletRequest.getRequestDispatcher("CreateQuestion.jsp").forward(httpServletRequest, httpServletResponse);
        } catch (IllegalArgumentException e) {
            System.err.println("Error converting questionType in GET: " + e.getMessage());
            httpServletRequest.setAttribute("error", "Invalid question type: " + type);
            httpServletRequest.getRequestDispatcher("errorPage.jsp").forward(httpServletRequest, httpServletResponse);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("questionType").trim();
        System.out.println("Received questionType in POST: " + type);

        try {
            QuizManager quizManager = (QuizManager) request.getServletContext().getAttribute(QuizManager.ATTRIBUTE_NAME);
            QuestionType questionType = QuestionType.fromString(type);
            System.out.println("Converted questionType in POST: " + questionType);

            String questionText = request.getParameter("questionText").trim();
            System.out.println("Question Text: " + questionText);

            Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
            int quizId = (int) request.getSession().getAttribute("quizId");

            ServletContext servletContext = request.getServletContext();
            QuestionManager questionManager = (QuestionManager) servletContext.getAttribute(QuestionManager.ATTRIBUTE_NAME);

            if (questionManager == null) {
                throw new ServletException("QuestionManager not found in servlet context.");
            }

            Question question = new Question();
            question.setQuizId(quizId);
            question.setQuestionText(questionText);
            question.setQuestionType(questionType);

            switch (questionType) {
                case QUESTION_RESPONSE:
                case FILL_IN_THE_BLANK:
                    String answerText = request.getParameter("answerText").trim();
                    question.setSingleQuestionAnswer(answerText);
                    break;
                case PICTURE_RESPONSE:
                    String questionImage = request.getParameter("questionImage").trim();
                    String singleAns = request.getParameter("answerText").trim();
                    question.setSingleQuestionAnswer(singleAns);
                    question.setQuestionImage(questionImage);
                    break;

                case MULTIPLE_CHOICE:
                case MULTIPLE_CHOICE_WITH_ANSWERS:
                    ArrayList<String> multipleChoiceAnswers = new ArrayList<>();
                    for (int i = 1; i <= 4; i++) {
                        String answer = request.getParameter("answer" + i);
                        if (answer != null && !answer.trim().isEmpty()) {
                            multipleChoiceAnswers.add(answer.trim());
                        }
                    }
                    question.setMultipleChoiceAnswers(multipleChoiceAnswers);

                    ArrayList<Integer> correctIndexes = new ArrayList<>();
                    String[] correctIndexArray = request.getParameter("correctIndexes").split(",");
                    for (String index : correctIndexArray) {
                        try {
                            correctIndexes.add(Integer.parseInt(index.trim()));
                        } catch (NumberFormatException e) {
                            e.printStackTrace(); // handle error or log it
                        }
                    }
                    question.setMultipleChoiceCorrectIndexes(correctIndexes);
                    break;

                case MULTI_ANSWER:
                    ArrayList<String> multiAnswers = new ArrayList<>();
                    for (int i = 1; i <= 10; i++) {
                        String answer = request.getParameter("answer" + i);
                        if (answer != null && !answer.trim().isEmpty()) {
                            multiAnswers.add(answer.trim());
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
                            matchingPairs.put(questiontxt.trim(), answer.trim());
                        }
                    }
                    question.setMatchingPairs(matchingPairs);
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected question type: " + questionType);
            }
            int questionId = questionManager.createQuestion(question);
            quiz.getQuestionIds().add(questionId);
            quizManager.updateQuiz(quiz);
            System.out.println(quiz);
            request.getSession().setAttribute("quiz", quiz);
            response.sendRedirect("CreateQuizServlet");
        } catch (IllegalArgumentException e) {
            System.err.println("Error converting questionType in POST: " + e.getMessage());
            request.setAttribute("error", "Invalid question type: " + type);
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}

