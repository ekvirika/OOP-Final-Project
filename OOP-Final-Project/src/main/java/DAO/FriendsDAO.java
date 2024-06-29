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
        String sql = "INSERT INTO Friends (userId1, userId2, status) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, friendRequest.getFriendIdFrom());
            stmt.setInt(2, friendRequest.getFriendIdTo());
            stmt.setString(3, "pending");
            stmt.executeUpdate();
        }
    }

    public void updateFriendshipStatus(int userId1, int userId2, String status) throws SQLException {
        String sql = "UPDATE Friends SET status = ? WHERE userId1 = ? AND userId2 = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, userId1);
            stmt.setInt(3, userId2);
            stmt.executeUpdate();
        }
    }

    public FriendRequest getFriendship(int userId1, int userId2) throws SQLException {
        String sql = "SELECT * FROM Friends WHERE userId1 = ? AND userId2 = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId1);
            stmt.setInt(2, userId2);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new FriendRequest(rs.getInt("userId1"), rs.getInt("userId2"));
            }
            return null;
        }
    }
}