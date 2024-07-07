package DAO;

import Models.FriendRequest;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendsDAO {
    private BasicDataSource dataSource;

    public FriendsDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addFriendship(FriendRequest friendRequest) throws SQLException {
        String sql = "INSERT INTO Friends (usernameFrom, usernameTo, isAccepted) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, friendRequest.getUsernameFrom());
            statement.setString(2, friendRequest.getUsernameTo());
            statement.setBoolean(3, false);
            statement.executeUpdate();
        }
    }

    public void updateFriendshipStatus(String usernameFrom, String usernameTo, Boolean isAccepted) throws SQLException {
        String query = "UPDATE Friends SET isAccepted = ? WHERE usernameFrom = ? AND usernameTo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, isAccepted);
            statement.setString(2, usernameFrom);
            statement.setString(3, usernameTo);
            statement.executeUpdate();
        }
    }

    public FriendRequest getFriendRequest(String usernameFrom, String usernameTo) throws SQLException {
        String query = "SELECT * FROM Friends WHERE usernameFrom = ? AND usernameTo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usernameFrom);
            statement.setString(2, usernameTo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new FriendRequest(resultSet.getInt("friendRequestId"), resultSet.getString("usernameFrom"), resultSet.getString("usernameTo"), resultSet.getBoolean("isAccepted"));
            }
            return null;
        }
    }

    public ArrayList<FriendRequest> getAllFriendRequestsFrom(String usernameFrom) throws SQLException {
        ArrayList<FriendRequest> friendRequests = new ArrayList<>();
        String query = "SELECT * FROM Friends WHERE usernameFrom = ?";
        return getFriendRequests(usernameFrom, friendRequests, query);
    }

    public ArrayList<FriendRequest> getAllFriendRequestsTo(String usernameTo) throws SQLException {
        ArrayList<FriendRequest> friendRequests = new ArrayList<>();
        String query = "SELECT * FROM Friends WHERE usernameTo = ?";
        return getFriendRequests(usernameTo, friendRequests, query);
    }

    private ArrayList<FriendRequest> getFriendRequests(String username, ArrayList<FriendRequest> friendRequests, String query) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                friendRequests.add(getFriendRequest(resultSet.getString("usernameFrom"),resultSet.getString("usernameTo")));
            }
            return friendRequests;
        }
    }
}