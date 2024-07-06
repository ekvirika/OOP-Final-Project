package Models.Managers;

import DAO.QuizHistoryDAO;
import Models.QuizHistory;
import org.apache.commons.dbcp2.BasicDataSource;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.List;

public class QuizHistoryManager {
    private QuizHistoryDAO quizHistoryDAO;
    public static final String ATTRIBUTE_NAME = "QuizHistoryManager";

    public QuizHistoryManager() {
        SQLConnector sqlConnector = new SQLConnector();
        this.quizHistoryDAO = new QuizHistoryDAO(sqlConnector.dataSource);
    }

    public void createQuizHistory(QuizHistory quizHistory) throws SQLException {
        quizHistoryDAO.createQuizHistory(quizHistory);
    }

    public QuizHistory getQuizHistoryByQuizId(int quizId) throws SQLException {
        return quizHistoryDAO.getQuizHistoryByQuizId(quizId);
    }

    public QuizHistory getQuizHistoryByUsername(String username) throws SQLException {
        return quizHistoryDAO.getQuizHistoryByUsername(username);
    }

    public void updateQuizHistory(QuizHistory quizHistory) throws SQLException {
        quizHistoryDAO.updateQuizHistory(quizHistory);
    }

    public void deleteQuizHistory(int quizId, String username) throws SQLException {
        quizHistoryDAO.deleteQuizHistory(quizId, username);
    }

    public List<QuizHistory> getAllQuizHistories() throws SQLException {
        return quizHistoryDAO.getAllQuizHistories();
    }

    public List<QuizHistory> getAllQuizHistoryByUsername(String username) throws SQLException {
        return quizHistoryDAO.getAllQuizHistoryByUsername(username);
    }
}
