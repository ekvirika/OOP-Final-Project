package Models.Managers;

import DAO.AccountDAO;
import DAO.LeaderboardDAO;
import Models.LeaderboardEntry;
import org.apache.commons.dbcp2.BasicDataSource;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.List;

public class LeaderboardManager {
    private final LeaderboardDAO leaderboardDAO;
    public static final String ATTRIBUTE_NAME = "LeaderboarManager";

    public LeaderboardManager() {
        SQLConnector sqlConnector = new SQLConnector();
        this.leaderboardDAO = new LeaderboardDAO(sqlConnector.dataSource);

    }

    public List<LeaderboardEntry> getLeaderboard(int quizId) throws SQLException {
        return leaderboardDAO.readLeaderBoard(quizId);
    }
}
