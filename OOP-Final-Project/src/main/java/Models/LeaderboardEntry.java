package Models;

public class LeaderboardEntry {
    private int rank;
    private String username;
    private int score;

    // Constructor
    public LeaderboardEntry(int rank, String username, int score) {
        this.rank = rank;
        this.username = username;
        this.score = score;
    }

    // Getters and Setters
    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Override toString() method for debugging or logging purposes
    @Override
    public String toString() {
        return "LeaderboardEntry{" +
                "rank=" + rank +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }
}
