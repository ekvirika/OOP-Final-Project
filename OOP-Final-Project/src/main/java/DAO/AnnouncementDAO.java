package DAO;

import Models.Announcement;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDAO {
    private BasicDataSource dataSource;

    public AnnouncementDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Create a new announcement
    public void createAnnouncement(Announcement announcement) throws SQLException {
        String query = "INSERT INTO announcements (username, message, announcementTime) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, announcement.getUsername());
            statement.setString(2, announcement.getMessage());
            statement.setTimestamp(3, announcement.getAnnouncementTime());
            statement.executeUpdate();
        }
    }

    // Retrieve an announcement by ID
    public Announcement readAnnouncement(int id) throws SQLException {
        String query = "SELECT * FROM announcements WHERE announcementId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Announcement(
                        resultSet.getInt("announcementId"),
                        resultSet.getString("username"),
                        resultSet.getString("message"),
                        resultSet.getTimestamp("announcementTime")
                );
            }
            return null; // No announcement found with the given ID
        }
    }

    // Retrieve all announcements
    public List<Announcement> getAllAnnouncements() throws SQLException {
        List<Announcement> announcements = new ArrayList<>();
        String query = "SELECT * FROM announcements";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                announcements.add(new Announcement(
                        resultSet.getInt("announcementId"),
                        resultSet.getString("username"),
                        resultSet.getString("message"),
                        resultSet.getTimestamp("announcementTime")
                ));
            }
        }
        return announcements;
    }

    // Update an announcement by ID
    public void updateAnnouncement(Announcement announcement) throws SQLException {
        String query = "UPDATE announcements SET username = ?, message = ?, announcementTime = ? WHERE announcementId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, announcement.getUsername());
            statement.setString(2, announcement.getMessage());
            statement.setTimestamp(3, announcement.getAnnouncementTime());
            statement.setInt(4, announcement.getAnnouncementId());
            statement.executeUpdate();
        }
    }

    // Delete an announcement by ID
    public void deleteAnnouncement(int announcementId) throws SQLException {
        String query = "DELETE FROM announcements WHERE announcementId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, announcementId);
            statement.executeUpdate();
        }
    }
}
