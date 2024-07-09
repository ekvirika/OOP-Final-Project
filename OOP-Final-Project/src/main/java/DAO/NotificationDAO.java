package DAO;

import Models.FriendRequest;
import Models.Notification;
import Models.Enums.NotificationType;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    private final BasicDataSource dataSource;

    // Constructor
    public NotificationDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Add a new notification
    public void CreateNotification(Notification notification) throws SQLException {
        String query = "INSERT INTO Notifications (usernameFrom, usernameTo, notificationType, quizLink, highScore,friendRequestId, message) VALUES (?, ?, ? , ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatement(notification, statement);
            statement.executeUpdate();
        }
    }

    private void setStatement(Notification notification, PreparedStatement statement) throws SQLException {
        // Set non-null values
        statement.setString(1, notification.getUsernameFrom());
        statement.setString(2, notification.getUsernameTo());
        statement.setInt(3, notification.getNotificationType().ordinal());

        // Initialize all fields to null in case they need to be reset
        statement.setNull(4, java.sql.Types.VARCHAR); // quizLink
        statement.setNull(5, Types.INTEGER); // highscore
        statement.setNull(6, java.sql.Types.INTEGER); // requestId
        statement.setNull(7, java.sql.Types.VARCHAR); // message

        // Set fields based on notification type
        switch (notification.getNotificationType()) {
            case CHALLENGE: // Assume case 0
                statement.setString(4, notification.getQuizLink());
                statement.setInt(5, notification.getHighScore());
                break;
            case FRIEND_REQUEST: // Assume case 1
                statement.setInt(6, notification.getRequestId());
                break;
            case NOTE: // Assume case 2
                statement.setString(7, notification.getMessage());
                break;
            default:
                throw new IllegalArgumentException("Unknown notification type: " + notification.getNotificationType());
        }
    }


    // Get notification by ID
    public Notification getNotificationById(int notificationId) throws SQLException {
        String query = "SELECT * FROM Notifications WHERE notificationId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, notificationId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Notification(
                            resultSet.getInt("notificationId"),
                            resultSet.getString("usernameFrom"),
                            resultSet.getString("usernameTo"),
                            NotificationType.values()[resultSet.getInt("notificationType")],
                            resultSet.getString("quizLink"),
                            resultSet.getInt("highScore"),
                            resultSet.getInt("friendRequestId"),
                            resultSet.getString("message")
                    );
                }
            }
        }
        return null;
    }

    // Update a notification
    public void updateNotification(Notification notification) throws SQLException {
        String query = "UPDATE Notifications SET usernameFrom = ?, usernameTo = ?, notificationType = ?, quizLink = ?, highScore = ? ,friendRequestId = ?, message = ? WHERE notificationId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatement(notification, statement);
            statement.setInt(8, notification.getNotificationId());

            statement.executeUpdate();
        }
    }

    // Delete a notification
    public void deleteNotification(int notificationId) throws SQLException {
        String query = "DELETE FROM Notifications WHERE notificationId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, notificationId);
            statement.executeUpdate();
        }
    }

    // Get all notifications
    public List<Notification> getAllNotifications() throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String query = "SELECT * FROM Notifications";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                notifications.add(new Notification(
                        resultSet.getInt("notificationId"),
                        resultSet.getString("usernameFrom"),
                        resultSet.getString("usernameTo"),
                        NotificationType.values()[resultSet.getInt("notificationType")],
                        resultSet.getString("quizLink"),
                        resultSet.getInt("highScore"),
                        resultSet.getInt("friendRequestId"),
                        resultSet.getString("message")
                ));
            }
        }
        return notifications;
    }

    public ArrayList<Notification> getAllNotificationsFrom(String usernameFrom) throws SQLException {
        ArrayList<Notification> notifications = new ArrayList<>();
        String query = "SELECT * FROM Notifications WHERE usernameFrom = ?";
        return getNotifications(usernameFrom, notifications, query);
    }

    public ArrayList<Notification> getAllNotificationsTo(String usernameTo) throws SQLException {
        ArrayList<Notification> notifications = new ArrayList<>();
        String query = "SELECT * FROM Notifications WHERE usernameTo = ?";
        return getNotifications(usernameTo, notifications, query);
    }

    private ArrayList<Notification> getNotifications(String username, ArrayList<Notification> notifications, String query) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                notifications.add(getNotificationById(resultSet.getInt("notificationId")));
            }
            return notifications;
        }
    }
}
