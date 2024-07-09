package Controllers.Managers;

import DAO.AchievementDAO;
import Models.Achievement;
import org.apache.commons.dbcp2.BasicDataSource;
import utils.SQLConnector;

import java.util.List;

public class AchievementManager {
    public static final String ATTRIBUTE_NAME = "AchievementManager";
    private AchievementDAO achievementDAO;

    public AchievementManager() {
        SQLConnector sqlConnector = new SQLConnector();
        achievementDAO = new AchievementDAO(sqlConnector.dataSource);
    }

    public List<Achievement> getAllAchievements() {
        try {
            return achievementDAO.getAllAchievements();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Achievement getAchievement(int id) {
        try {
            return achievementDAO.getAchievement(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createAchievement(Achievement achievement) {
        try {
            achievementDAO.createAchievement(achievement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAchievement(Achievement achievement) {
        try {
            achievementDAO.updateAchievement(achievement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAchievement(int achievementId) {
        try {
            achievementDAO.deleteAchievement(achievementId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
