package DAO;

import Models.Quiz;
import com.google.gson.Gson;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO class for managing Quiz entities in the database.
 */
public class QuizDAO {

    private BasicDataSource dataSource;

    public QuizDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new quiz record in the database.
     *
     * @param quiz the Quiz object to be created
     * @throws SQLException if any SQL error occurs
     */
    public void createQuiz(Quiz quiz) throws SQLException {
        String query = "INSERT INTO Quiz (username, quizName, quizDescription, quizScore, quiestionIds ,isSinglePage, " +
                "randomizeQuestions, immediateFeedback, createTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setStatement(quiz, statement);
            statement.setTimestamp(9, quiz.getCreateTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setStatement(Quiz quiz, PreparedStatement statement) throws SQLException {
        statement.setString(1, quiz.getCreatorUsername());
        statement.setString(2, quiz.getQuizName());
        statement.setString(3, quiz.getQuizDescription());
        statement.setInt(4, quiz.getQuizScore());
        statement.setString(5, new Gson().toJson(quiz.getQuestionIds()));
        statement.setBoolean(6, quiz.isSinglePage());
        statement.setBoolean(7, quiz.isRandomizeQuestions());
        statement.setBoolean(8, quiz.isImmediateFeedback());
    }

    /**
     * Retrieves a quiz by its ID.
     *
     * @param quizID the ID of the quiz to be retrieved
     * @return the Quiz object, or null if not found
     * @throws SQLException if any SQL error occurs
     */
    public Quiz ReadQuiz(int quizID) throws SQLException {
        String query = "SELECT * FROM Quiz WHERE quizID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, quizID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Quiz(
                            rs.getInt("quizID"),
                            rs.getString("username"),
                            rs.getString("quizName"),
                            rs.getString("quizDescription"),
                            rs.getInt("quizScore"),
                            new Gson().fromJson(rs.getString("questionIds"), ArrayList.class), // questionIds should be handled separately
                            rs.getBoolean("isSinglePage"),
                            rs.getBoolean("randomizeQuestions"),
                            rs.getBoolean("immediateFeedback"),
                            rs.getTimestamp("createTime")
                    );
                }
            }
        }
        return null;
    }

    /**
     * Updates an existing quiz record in the database.
     *
     * @param quiz the Quiz object with updated values
     * @throws SQLException if any SQL error occurs
     */
    public void updateQuiz(Quiz quiz) throws SQLException {
        String query = "UPDATE Quiz SET username = ?, quizName = ?, quizDescription = ?, quizScore = ?, " +
                "quiestionIds = ?, isSinglePage = ?, randomizeQuestions = ?, immediateFeedback = ? WHERE quizID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatement(quiz, statement);
            statement.setInt(8, quiz.getQuizID());
            statement.executeUpdate();
        }
    }

    /**
     * Deletes a quiz by its ID.
     *
     * @param quizID the ID of the quiz to be deleted
     * @throws SQLException if any SQL error occurs
     */
    public void deleteQuiz(int quizID) throws SQLException {
        String sql = "DELETE FROM Quiz WHERE quizID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, quizID);
            statement.executeUpdate();
        }
    }

}
