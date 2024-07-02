package Models.Managers;

import DAO.QuizDAO;
import Models.Quiz;
import utils.SQLConnector;

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
    public void addQuiz(Quiz quiz){
        quizDao.createQuiz(quiz);
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
}
