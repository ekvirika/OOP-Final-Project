package Controllers.Managers;

import DAO.AnnouncementDAO;
import Models.Announcement;
import utils.SQLConnector;

import java.util.List;

public class AnnouncementManager {
    public static final String ATTRIBUTE_NAME = "AnnouncementManager";
    private AnnouncementDAO announcementDAO;
    private SQLConnector sqlConnector;

    public AnnouncementManager() {
        sqlConnector = new SQLConnector();
        announcementDAO = new AnnouncementDAO(sqlConnector.dataSource);
    }

    public List<Announcement> getAnnouncements() {
        try {
            return announcementDAO.getAllAnnouncements();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Announcement getAnnouncement(int id) {
        try {
            return announcementDAO.readAnnouncement(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createAnnouncement(Announcement announcement) {
        try {
            announcementDAO.createAnnouncement(announcement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAnnouncement(Announcement announcement) {
        try {
            announcementDAO.updateAnnouncement(announcement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAnnouncement(int announcementId) {
        try {
            announcementDAO.deleteAnnouncement(announcementId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
