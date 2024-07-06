package Models;

/**
 * Represents an entry in a leaderboard with a username, score, and elapsed time.
 */
public class LeaderboardEntry {
    private String username;
    private int score;
    private long elapsedTime;

    /**
     * Constructs a new LeaderboardEntry.
     *
     * @param username    the username of the player
     * @param score       the score achieved by the player
     * @param elapsedTime the elapsed time in milliseconds
     */
    public LeaderboardEntry(String username, int score, long elapsedTime) {
        this.username = username;
        this.score = score;
        this.elapsedTime = elapsedTime;
    }

    /**
     * Returns the username of the player.
     *
     * @return the username of the player
     */
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the score achieved by the player.
     *
     * @return the score achieved by the player
     */
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Returns the elapsed time in milliseconds.
     *
     * @return the elapsed time in milliseconds
     */
    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Returns a string representation of the LeaderboardEntry.
     *
     * @return a string representation of the LeaderboardEntry
     */
    @Override
    public String toString() {
        return "LeaderboardEntry{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
