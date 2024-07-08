package Models;

import java.sql.Time;
import java.sql.Timestamp;

public class Announcement {
    private int announcementId;
    private String username;
    private String message;
    private Timestamp announcementTime;

    public Announcement(String username, String message) {
        announcementId = -1;
        this.username = username;
        this.message = message;
        this.announcementTime = new Timestamp(System.currentTimeMillis());
    }

    public Announcement(int announcementId, String username, String message, Timestamp announcementTime) {
        this.announcementId = announcementId;
        this.username = username;
        this.message = message;
        this.announcementTime = announcementTime;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for announcementTime
    public Timestamp getAnnouncementTime() {
        return announcementTime;
    }

    // Setter for announcementTime
    public void setAnnouncementTime(Timestamp announcementTime) {
        this.announcementTime = announcementTime;
    }

    // Getter for message
    public String getMessage() {
        return message;
    }

    // Setter for message
    public void setMessage(String message) {
        this.message = message;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }
}
