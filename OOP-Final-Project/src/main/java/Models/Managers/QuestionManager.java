package Models.Managers;

import DAO.QuestionDAO;
import Models.Enums.QuestionType;
import Models.Question;
import utils.SQLConnector;

import java.util.*;

/**
 * Manages operations related to Question objects, including retrieval, creation, update, and deletion.
 */
public class QuestionManager {
    public static final String ATTRIBUTE_NAME = "QuestionManager";
    private QuestionDAO questionDAO;

    /**
     * Constructs a QuestionManager instance.
     * Initializes the QuestionDAO using the provided SQLConnector.
     */
    public QuestionManager() {
        SQLConnector sqlConnector = new SQLConnector();
        this.questionDAO = new QuestionDAO();
        this.questionDAO.setDataSource(sqlConnector.dataSource);
    }

    /**
     * Retrieves a Question object by its ID.
     *
     * @param questionId The ID of the question to retrieve.
     * @return The Question object corresponding to the given ID, or null if not found.
     */
    public Question getQuestion(int questionId) {
        return questionDAO.ReadQuestion(questionId);
    }

    /**
     * Creates a new Question in the database.
     *
     * @param question The Question object to create.
     * @return
     */
    public int createQuestion(Question question) {
        return questionDAO.CreateQuestion(question);
    }

    /**
     * Updates an existing Question in the database.
     *
     * @param question The updated Question object.
     */
    public void updateQuestion(Question question) {
        questionDAO.updateQuestion(question);
    }

    /**
     * Deletes a Question from the database by its ID.
     *
     * @param questionId The ID of the Question to delete.
     */
    public void deleteQuestion(int questionId) {
        questionDAO.deleteQuestion(questionId);
    }


    public boolean isAnswerCorrect(Question question, String answer, ArrayList<String> answers,
                                   HashMap<String, String> matchingAnswers) {
        QuestionType questionType = question.getQuestionType();
        System.out.println("Ques: " + questionType + " " + question);
        System.out.println("Ans: " + answer + " " + answers);
        System.out.println("Match: " + matchingAnswers);
        if (questionType.equals(QuestionType.MATCHING)) {
            HashMap<String, String> correctAnswers = question.getMatchingPairs();
            return matchingAnswers.equals(correctAnswers);
        } else if (questionType.equals(QuestionType.QUESTION_RESPONSE) ||
                questionType.equals(QuestionType.FILL_IN_THE_BLANK) ||
                questionType.equals(QuestionType.PICTURE_RESPONSE)) {
            return question.getSingleQuestionAnswer().equalsIgnoreCase(answer.trim());
        } else if (questionType.equals(QuestionType.MULTI_ANSWER)) {
            // Case-insensitive comparison for multi-answer
            List<String> correctAnswers = new ArrayList<>();
            for (String correctAnswer : question.getMultipleAnswerFields()) {
                correctAnswers.add(correctAnswer.toLowerCase().trim());
            }
            List<String> userAnswers = new ArrayList<>();
            for (String userAnswer : answers) {
                userAnswers.add(userAnswer.toLowerCase().trim());
            }
            return new HashSet<>(userAnswers).containsAll(correctAnswers);
        } else if (questionType.equals(QuestionType.MULTIPLE_CHOICE) ||
                questionType.equals(QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS)) {
            ArrayList<String> correctAnswers = new ArrayList<>();
            ArrayList<String> allAnswers = question.getMultipleChoiceAnswers();
            System.out.println("All answers: " + allAnswers);
            ArrayList<Integer> indices = question.getMultipleChoiceCorrectIndexes();
            for (int index : indices) {
                correctAnswers.add(allAnswers.get(index).toLowerCase().trim());
            }
            ArrayList<String> userAnswers = new ArrayList<>();
            for (String userAnswer : answers) {
                userAnswers.add(userAnswer.toLowerCase().trim());
            }
            System.out.println("Correst: " + correctAnswers);
            System.out.println("User: " + userAnswers);
            return userAnswers.containsAll(correctAnswers);
        }
        return false;
    }

}
