package Controllers.Managers;

import DAO.NotificationDAO;
import Models.Notification;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.List;

public class NotificationManager {
    public static final String ATTRIBUTE_NAME = "NotificationManager";
    private final NotificationDAO notificationDAO;
    private final SQLConnector sqlConnector;

    /**
     * Constructs a NotificationManager with a predefined set of notifications.
     */
    public NotificationManager() {
        sqlConnector = new SQLConnector();
        notificationDAO = new NotificationDAO(sqlConnector.dataSource);
    }

    /**
     * Creates a new notification.
     *
     * @param notification the notification to create
     * @throws SQLException if a database access error occurs
     */
    public void createNotification(Notification notification) throws SQLException {
        System.out.println("managershi var");
        notificationDAO.CreateNotification(notification);
    }

    /**
     * Retrieves a notification by its ID.
     *
     * @param notificationId the ID of the notification to retrieve
     * @return the notification with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Notification getNotificationById(int notificationId) throws SQLException {
        return notificationDAO.getNotificationById(notificationId);
    }

    /**
     * Updates an existing notification.
     *
     * @param notification the notification to update
     * @throws SQLException if a database access error occurs
     */
    public void updateNotification(Notification notification) throws SQLException {
        notificationDAO.updateNotification(notification);
    }

    /**
     * Deletes a notification by its ID.
     *
     * @param notificationId the ID of the notification to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteNotification(int notificationId) throws SQLException {
        notificationDAO.deleteNotification(notificationId);
    }

    /**
     * Retrieves all notifications.
     *
     * @return a list of all notifications
     * @throws SQLException if a database access error occurs
     */
    public List<Notification> getAllNotifications() throws SQLException {
        return notificationDAO.getAllNotifications();
    }

    /**
     * Retrieves notifications for a specific user.
     *
     * @param username the username to retrieve notifications for
     * @return a list of notifications to the specified user
     * @throws SQLException if a database access error occurs
     */
    public List<Notification> getNotificationsToUser(String username) throws SQLException {
        return notificationDAO.getAllNotificationsTo(username);
    }


    /**
     * Retrieves notifications for a specific user.
     *
     * @param username the username to retrieve notifications for
     * @return a list of notifications from the specified user
     * @throws SQLException if a database access error occurs
     */
    public List<Notification> getNotificationsFromUser(String username) throws SQLException {
        return notificationDAO.getAllNotificationsFrom(username);
    }
}