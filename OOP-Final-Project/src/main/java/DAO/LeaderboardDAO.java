package DAO;

import Models.LeaderboardEntry;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for handling leaderboard operations.
 * This class provides methods to read leaderboard entries from the database.
 */
public class LeaderboardDAO {
    private BasicDataSource dataSource;

    /**
     * Constructs a LeaderboardDAO with the given data source.
     *
     * @param dataSource the BasicDataSource for database connections
     */
    public LeaderboardDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Reads the leaderboard for a specific quiz from the database.
     *
     * @param quizId the ID of the quiz for which to retrieve the leaderboard
     * @return a list of {@link Models.LeaderboardEntry} representing the leaderboard
     * @throws SQLException if a database access error occurs
     */
    public List<LeaderboardEntry> readLeaderBoard(int quizId) throws SQLException {
        List<LeaderboardEntry> leaderBoard = new ArrayList<>();
        String query = "SELECT qh.username, qh.quizScore, qh.elapsedTime " +
                "FROM QUIZ q " +
                "LEFT JOIN QuizHistory qh ON qh.quizId = q.quizId " +
                "WHERE q.quizId = ? " +
                "ORDER BY qh.quizScore DESC, qh.elapsedTime ASC;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the quizId parameter in the query
            statement.setInt(1, quizId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    int quizScore = resultSet.getInt("quizScore");
                    long elapsedTime = resultSet.getLong("elapsedTime");
                    leaderBoard.add(new LeaderboardEntry(username, quizScore, elapsedTime));
                }
            }
        }
        return leaderBoard;
    }
}
