package Models.Managers;

import DAO.QuestionDAO;
import Models.Question;
import utils.SQLConnector;

/**
 * Manages operations related to Question objects, including retrieval, creation, update, and deletion.
 */
public class QuestionManager {
    public static  final String ATTRIBUTE_NAME = "QuestionManager";
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
}
