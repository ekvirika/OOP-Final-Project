package Controllers.Managers;

import DAO.QuizDAO;
import Models.Question;
import Models.Quiz;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class QuizManager {
    public static final String ATTRIBUTE_NAME = "QuizManager";
    private final QuizDAO quizDao;

    public QuizManager( ) {
        SQLConnector sqlConnector = new SQLConnector();
        this.quizDao = new QuizDAO(sqlConnector.dataSource);
    }
    /**
     * Retrieves a quiz by its ID.
     *
     * @param quizId the ID of the quiz
     * @return the quiz with the specified ID
     */
    public Quiz getQuiz(int quizId)  {
        Quiz quiz = quizDao.readQuiz(quizId);
        if(quiz.isRandomizeQuestions()) Collections.shuffle(quiz.getQuestionIds());
        return quiz;
    }

    /**
     * Retrieves all quizzes.
     *
     * @return a list of all quizzes
     */
    public List<Quiz> getAllQuizzes(){
        return quizDao.getAllQuizzes();
    }

    /**
     * Adds a new quiz.
     *
     * @param quiz the quiz to add
     */
    public int addQuiz(Quiz quiz){
        return quizDao.createQuiz(quiz);
    }

    /**
     * Updates an existing quiz.
     *
     * @param quiz the quiz with updated information
     */
    public void updateQuiz(Quiz quiz) {
        quizDao.updateQuiz(quiz);
    }

    /**
     * Deletes a quiz by its ID.
     *
     * @param quizId the ID of the quiz to delete
     */
    public void deleteQuiz(int quizId) {
        quizDao.deleteQuiz(quizId);
    }


    public List<Question> getAllQuestionsByQuiz(int quizId) {
        return quizDao.getAllQuestionsByQuizId(quizId);
    }

    public List<Quiz> getNewlyAddedQuizzes() throws SQLException {
        return quizDao.getAllQuizzesByCreationTime();
    }

    public List<Quiz> getQuizzesByUser(String username){
        return quizDao.getAllQuizzesByUser(username);
    }
}
