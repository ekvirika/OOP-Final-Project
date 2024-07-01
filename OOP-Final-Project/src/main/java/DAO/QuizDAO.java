package DAO;

import Models.Quiz;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.dbcp2.BasicDataSource;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class for managing Quiz entities in the database.
 */
public class QuizDAO {

    private final BasicDataSource dataSource;
    private final Gson gson;

    public QuizDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
        this.gson = new Gson();
    }

    /**
     * Creates a new quiz record in the database.
     *
     * @param quiz the Quiz object to be created
     */
    public void createQuiz(Quiz quiz) {
        String query = "INSERT INTO Quiz (username, quizName, quizDescription, quizScore, questionIds, isSinglePage, " +
                "randomizeQuestions, immediateFeedback, createTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setStatementParams(quiz, statement);
            statement.setTimestamp(9, quiz.getCreateTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating quiz: " + e.getMessage(), e);
        }
    }

    private void setStatementParams(Quiz quiz, PreparedStatement statement) throws SQLException {
        statement.setString(1, quiz.getCreatorUsername());
        statement.setString(2, quiz.getQuizName());
        statement.setString(3, quiz.getQuizDescription());
        statement.setInt(4, quiz.getQuizScore());
        statement.setString(5, gson.toJson(quiz.getQuestionIds()));
        statement.setBoolean(6, quiz.isSinglePage());
        statement.setBoolean(7, quiz.isRandomizeQuestions());
        statement.setBoolean(8, quiz.isImmediateFeedback());
    }

    /**
     * Retrieves a quiz by its ID.
     *
     * @param quizID the ID of the quiz to be retrieved
     * @return the Quiz object, or null if not found
     */
    public Quiz readQuiz(int quizID) {
        String query = "SELECT * FROM Quiz WHERE quizID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, quizID);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return extractQuizFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading quiz: " + e.getMessage(), e);
        }
        return null;
    }

    private Quiz extractQuizFromResultSet(ResultSet rs) throws SQLException {
        String questionIdsJson = rs.getString("questionIds");
        List<Integer> questionIds = new ArrayList<>();
        try {
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            questionIds = gson.fromJson(questionIdsJson, listType);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Error parsing questionIds JSON: " + e.getMessage(), e);
        }

        return new Quiz(
                rs.getInt("quizID"),
                rs.getString("username"),
                rs.getString("quizName"),
                rs.getString("quizDescription"),
                rs.getInt("quizScore"),
                (ArrayList<Integer>) questionIds,
                rs.getBoolean("isSinglePage"),
                rs.getBoolean("randomizeQuestions"),
                rs.getBoolean("immediateFeedback"),
                rs.getTimestamp("createTime")
        );
    }

    /**
     * Updates an existing quiz record in the database.
     *
     * @param quiz the Quiz object with updated values
     */
    public void updateQuiz(Quiz quiz) {
        String query = "UPDATE Quiz SET username = ?, quizName = ?, quizDescription = ?, quizScore = ?, " +
                "questionIds = ?, isSinglePage = ?, randomizeQuestions = ?, immediateFeedback = ? WHERE quizID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setStatementParams(quiz, statement);
            statement.setInt(9, quiz.getQuizID());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating quiz: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a quiz by its ID.
     *
     * @param quizID the ID of the quiz to be deleted
     */
    public void deleteQuiz(int quizID) {
        String query = "DELETE FROM Quiz WHERE quizID = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, quizID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting quiz: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves all quizzes from the database.
     *
     * @return a list of all Quiz objects
     */
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT * FROM Quiz";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Quiz quiz = extractQuizFromResultSet(resultSet);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all quizzes: " + e.getMessage(), e);
        }
        return quizzes;
    }
}
