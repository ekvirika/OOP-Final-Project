package DAO;

import Models.FriendRequest;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void updateFriendshipStatus(String usernameFrom, String usernameTo, Boolean isAccepted){
        System.out.println("aq var");
        String query = "UPDATE Friends SET friends.isAccepted = ? WHERE friends.usernameFrom = ? AND friends.usernameTo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, isAccepted);
            statement.setString(3, usernameFrom);
            statement.setString(2, usernameTo);
            System.out.println(statement);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
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

    public int getFriendRequestId(String usernameFrom, String usernameTo) throws SQLException {
        String query = "SELECT friendRequestId FROM Friends WHERE usernameFrom = ? AND usernameTo = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usernameFrom);
            statement.setString(2, usernameTo);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("friendRequestId");
            } else {
                throw new SQLException("Friend request not found.");
            }
        }
    }

    public List<String> getAcceptedFriends(String username) throws SQLException {
        List<String> friends = new ArrayList<>();
        String query = "SELECT usernameFrom, usernameTo FROM Friends WHERE (usernameFrom = ? OR usernameTo = ?) AND isAccepted = 1";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String usernameFrom = resultSet.getString("usernameFrom");
                String usernameTo = resultSet.getString("usernameTo");
                if (!usernameFrom.equals(username)) {
                    friends.add(usernameFrom);
                } else {
                    friends.add(usernameTo);
                }
            }
        }
        return friends;
    }
}