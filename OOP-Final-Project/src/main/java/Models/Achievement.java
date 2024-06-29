package Models;

/**
 * The Achievement class represents an achievement that can be awarded to a user.
 * Each achievement has an ID, name, URL, and description.
 */
public class Achievement {
    private int achievementId;
    private String achievementName;
    private String achievementUrl;
    private String achievementDescription;

    /**
     * Constructs a new Achievement with the specified details.
     *
     * @param achievementId          the unique ID of the achievement
     * @param achievementName        the name of the achievement
     * @param achievementUrl         the URL associated with the achievement
     * @param achievementDescription a description of the achievement
     */
    public Achievement(int achievementId, String achievementName, String achievementUrl, String achievementDescription) {
        this.achievementId = achievementId;
        this.achievementName = achievementName;
        this.achievementUrl = achievementUrl;
        this.achievementDescription = achievementDescription;
    }

    /**
     * Gets the unique ID of the achievement.
     *
     * @return the achievement ID
     */
    public int getAchievementId() {
        return achievementId;
    }

    /**
     * Sets the unique ID of the achievement.
     *
     * @param achievementId the new achievement ID
     */
    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    /**
     * Gets the name of the achievement.
     *
     * @return the achievement name
     */
    public String getAchievementName() {
        return achievementName;
    }

    /**
     * Sets the name of the achievement.
     *
     * @param achievementName the new achievement name
     */
    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    /**
     * Gets the URL associated with the achievement.
     *
     * @return the achievement URL
     */
    public String getAchievementUrl() {
        return achievementUrl;
    }

    /**
     * Sets the URL associated with the achievement.
     *
     * @param achievementUrl the new achievement URL
     */
    public void setAchievementUrl(String achievementUrl) {
        this.achievementUrl = achievementUrl;
    }

    /**
     * Gets the description of the achievement.
     *
     * @return the achievement description
     */
    public String getAchievementDescription() {
        return achievementDescription;
    }

    /**
     * Sets the description of the achievement.
     *
     * @param achievementDescription the new achievement description
     */
    public void setAchievementDescription(String achievementDescription) {
        this.achievementDescription = achievementDescription;
    }
}
