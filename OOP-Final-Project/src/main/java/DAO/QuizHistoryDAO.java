package DAO;

import javafx.util.Pair;
import Models.Quiz;
import Models.QuizHistory;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuizHistoryDAO {

    private BasicDataSource dataSource;

    public QuizHistoryDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Create a new quiz history record
    public void createQuizHistory(QuizHistory quizHistory) throws SQLException {
        String query = "INSERT INTO QuizHistory (quizId, username, quizScore, startTime, endTime, endDate, elapsedTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizHistory.getQuizId());
            statement.setString(2, quizHistory.getUsername());
            statement.setInt(3, quizHistory.getQuizScore());
            statement.setTime(4, quizHistory.getStartTime());
            statement.setTime(5, quizHistory.getEndTime());
            statement.setDate(6, (Date) quizHistory.getEndDate());
            statement.setLong(7, quizHistory.getElapsedTime());
            statement.executeUpdate();
        }
    }

    // Retrieve a quiz history record by quizId and username
    public QuizHistory getQuizHistoryByQuizId(int quizId) throws SQLException {
        String query = "SELECT * FROM QuizHistory WHERE quizId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            QuizHistory quizHistory = getQuizHistory(statement);
            if (quizHistory != null) return quizHistory;
        }
        return null;
    }

    private QuizHistory getQuizHistory(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                QuizHistory quizHistory = new QuizHistory(
                        resultSet.getInt("quizId"),
                        resultSet.getString("username"),
                        resultSet.getInt("quizScore"),
                        resultSet.getTime("startTime"),
                        resultSet.getTime("endTime"),
                        resultSet.getLong("elapsedTime")
                );
                Calendar calendar = Calendar.getInstance();
                quizHistory.setEndDate(calendar.getTime());
                return quizHistory;
            }
        }
        return null;
    }

    public List<QuizHistory> getAllQuizHistoryByUsername(String username) throws SQLException {
        String query = "SELECT * FROM QuizHistory WHERE username = ?";
        List<QuizHistory> quizHistories = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                getHistoryWithWhile(quizHistories, resultSet);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return quizHistories;
    }


    public QuizHistory getQuizHistoryByUsername(String username) throws SQLException {
        String query = "SELECT * FROM QuizHistory WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            QuizHistory quizHistory = getQuizHistory(statement);
            if (quizHistory != null) return quizHistory;
        }
        return null;
    }

    // Update a quiz history record
    public void updateQuizHistory(QuizHistory quizHistory) throws SQLException {
        String query = "UPDATE QuizHistory SET quizScore = ?, startTime = ?, endTime = ?, elapsedTime = ? WHERE quizId = ? AND username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizHistory.getQuizScore());
            statement.setTime(2, quizHistory.getStartTime());
            statement.setTime(3, quizHistory.getEndTime());
            statement.setLong(4, quizHistory.getElapsedTime());
            statement.setInt(5, quizHistory.getQuizId());
            statement.setString(6, quizHistory.getUsername());
            statement.executeUpdate();
        }
    }

    // Delete a quiz history record
    public void deleteQuizHistory(int quizId, String username) throws SQLException {
        String sql = "DELETE FROM QuizHistory WHERE quizId = ? AND username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quizId);
            statement.setString(2, username);
            statement.executeUpdate();
        }
    }

    // Retrieve all quiz history records
    public List<QuizHistory> getAllQuizHistories() throws SQLException {
        String query = "SELECT * FROM QuizHistory";
        List<QuizHistory> quizHistories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            getHistoryWithWhile(quizHistories, resultSet);
        }
        return quizHistories;
    }

    public List<Quiz> getAllQuizzesByPopularity() throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT quizId " +
                "FROM QuizHistory " +
                "GROUP BY quizId " +
                "ORDER BY COUNT(quizId) DESC";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            QuizDAO quizDAO = new QuizDAO(dataSource);
            while (resultSet.next()) {
                quizzes.add(quizDAO.readQuiz(resultSet.getInt("quizId")));
            }
        }
        return quizzes;
    }

    public List<Quiz> getQuizzesForUserByTakingTime(String username) throws SQLException {
        List<Quiz> quizzes = new ArrayList<>();
        String query = "SELECT quizId " +
                "FROM quizHistory " +
                "WHERE username = ? " +
                "ORDER BY DATE_FORMAT(startTime, '%Y-%m-%d %H:%i:%s') DESC";
        QuizDAO quizDAO = new QuizDAO(dataSource);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    quizzes.add(quizDAO.readQuiz(resultSet.getInt("quizId")));
                }
            }
        }
        return quizzes;
    }

    public void deleteAllQuizzesById(int quizId) throws SQLException {
        String query = "DELETE FROM QuizHistory WHERE quizId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quizId);
            statement.executeUpdate();
        }
    }

    public List<QuizHistory> getUsersFriendsRecentActivities(String username) throws SQLException {
        List<QuizHistory> activities = new ArrayList<>();
        String query = "SELECT * FROM QuizHistory qh " +
                "WHERE qh.username IN ( " +
                "    SELECT a.userName " +
                "    FROM Accounts a " +
                "    JOIN Friends f ON (a.userName = f.usernameFrom OR a.userName = f.usernameTo) " +
                "    WHERE (f.usernameFrom = ? OR f.usernameTo = ?) " +
                "      AND f.isAccepted = TRUE " +
                "      AND a.userName != ? " +
                ") " +
                "ORDER BY DATE_FORMAT(startTime, '%Y-%m-%d %H:%i:%s') DESC;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, username);
            statement.setString(3, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                getHistoryWithWhile(activities, resultSet);
            }
            return activities;
        }
    }

    private void getHistoryWithWhile(List<QuizHistory> activities, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            QuizHistory quizHistory = new QuizHistory(
                    resultSet.getInt("quizId"),
                    resultSet.getString("username"),
                    resultSet.getInt("quizScore"),
                    resultSet.getTime("startTime"),
                    resultSet.getTime("endTime"),
                    resultSet.getLong("elapsedTime")
            );
            Calendar calendar = Calendar.getInstance();
            quizHistory.setEndDate(calendar.getTime());
            activities.add(quizHistory);
        }
    }

    public Pair<Long, Long> getAverageScoreAndTimeByQuizId(String quizId) throws SQLException {
        String query = "SELECT avg(quizScore), avg(elapsedTime) FROM QuizHistory WHERE quizId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, quizId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return new Pair<>(resultSet.getLong("avg(quizScore)"), resultSet.getLong("avg(elapsedTime)"));
            }
        }
    }
}
