package Models.Managers;

import DAO.QuestionDAO;
import Models.Enums.QuestionType;
import Models.Question;
import utils.SQLConnector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

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
     */
    public void createQuestion(Question question) {
        questionDAO.CreateQuestion(question);
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
        if(questionType.equals(QuestionType.MATCHING)){
            HashMap<String, String> correctAnswers = question.getMatchingPairs();
            return matchingAnswers.equals(correctAnswers);
        } else if(questionType.equals(QuestionType.QUESTION_RESPONSE) || questionType.equals(QuestionType.FILL_IN_THE_BLANK) ||
                    questionType.equals(QuestionType.PICTURE_RESPONSE)){
            return question.getQuestionText().equals(answer);
        } else if(questionType.equals(QuestionType.MULTI_ANSWER)){
            // TODO
            return answers.containsAll(question.getMultipleAnswerFields());
        } else if(questionType.equals(QuestionType.MULTIPLE_CHOICE) || questionType.equals(QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS)){
            ArrayList<String> correctAnswers = new ArrayList<>();
            ArrayList<String> allAnswers = question.getMultipleChoiceAnswers();
            ArrayList<Integer> indices = question.getMultipleChoiceCorrectIndexes();
            for(int i = 0; i < indices.size(); i++){
                correctAnswers.add(allAnswers.get(indices.get(i)));
            }
            return answers.containsAll(correctAnswers);
        }
        return false;
    }
}
