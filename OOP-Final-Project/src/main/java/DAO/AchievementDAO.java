package DAO;

import Models.Achievement;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AchievementDAO {

    private BasicDataSource dataSource;

    public AchievementDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Create a new achievement record
    public void createAchievement(Achievement achievement) throws SQLException {
        String query = "INSERT INTO Achievement (achievementName, achievementUrl, achievementDescription) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, achievement.getAchievementName());
            statement.setString(2, achievement.getAchievementUrl());
            statement.setString(3, achievement.getAchievementDescription());
            statement.executeUpdate();
        }
    }

    // Retrieve an achievement record by achievementId
    public Achievement getAchievement(int achievementId) throws SQLException {
        String query = "SELECT * FROM Achievement WHERE achievementId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, achievementId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Achievement(
                            resultSet.getInt("achievementId"),
                            resultSet.getString("achievementName"),
                            resultSet.getString("achievementUrl"),
                            resultSet.getString("achievementDescription")
                    );
                }
            }
        }
        return null;
    }

    // Update an achievement record
    public void updateAchievement(Achievement achievement) throws SQLException {
        String query = "UPDATE Achievement SET achievementName = ?, achievementUrl = ?, achievementDescription = ? WHERE achievementId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, achievement.getAchievementName());
            statement.setString(2, achievement.getAchievementUrl());
            statement.setString(3, achievement.getAchievementDescription());
            statement.setInt(4, achievement.getAchievementId());
            statement.executeUpdate();
        }
    }

    // Delete an achievement record
    public void deleteAchievement(int achievementId) throws SQLException {
        String query = "DELETE FROM Achievement WHERE achievementId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, achievementId);
            statement.executeUpdate();
        }
    }

    // Retrieve all achievement records
    public List<Achievement> getAllAchievements() throws SQLException {
        String query = "SELECT * FROM Achievement";
        List<Achievement> achievements = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Achievement achievement = new Achievement(
                        resultSet.getInt("achievementId"),
                        resultSet.getString("achievementName"),
                        resultSet.getString("achievementUrl"),
                        resultSet.getString("achievementDescription")
                );
                achievements.add(achievement);
            }
        }
        return achievements;
    }
}
