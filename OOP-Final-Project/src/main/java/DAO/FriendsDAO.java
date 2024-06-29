package DAO;

import Models.FriendRequest;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendsDAO {
    private BasicDataSource dataSource;

    public FriendsDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addFriendship(FriendRequest friendRequest) throws SQLException {
        String sql = "INSERT INTO Friends (userName1, userName2, status) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, friendRequest.getFriendIdFrom());
            stmt.setString(2, friendRequest.getFriendIdTo());
            stmt.setString(3, "pending");
            stmt.executeUpdate();
        }
    }

    public void updateFriendshipStatus(String userName1, String userName2, String status) throws SQLException {
        String sql = "UPDATE Friends SET status = ? WHERE userName1 = ? AND userName2 = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, userName1);
            stmt.setString(3, userName2);
            stmt.executeUpdate();
        }
    }

    public FriendRequest getFriendship(String userName1, String userName2) throws SQLException {
        String sql = "SELECT * FROM Friends WHERE userName1 = ? AND userName2 = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userName1);
            stmt.setString(2, userName2);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new FriendRequest(rs.getString("userId1"), rs.getString("userId2"));
            }
            return null;
        }
    }
}