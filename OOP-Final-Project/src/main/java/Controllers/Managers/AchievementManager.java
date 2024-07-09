package Controllers.Managers;

import DAO.AchievementDAO;
import Models.Achievement;
import utils.SQLConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AchievementManager {
    public static final String ATTRIBUTE_NAME = "AchievementManager";
    private AchievementDAO achievementDAO;

    public AchievementManager() {
        SQLConnector sqlConnector = new SQLConnector();
        achievementDAO = new AchievementDAO(sqlConnector.dataSource);
    }

    public Set<Achievement> getAllAchievements() {
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


    public Set<Achievement> getAllAchievemtnsByUser(Set<Integer> achievementIds) throws SQLException {
        Set<Achievement> achievements = new HashSet<>();
        for(Integer id: achievementIds) {
            Achievement achievement = achievementDAO.getAchievement(id);
            achievements.add(achievement);
        }
        return achievements;
    }


}
