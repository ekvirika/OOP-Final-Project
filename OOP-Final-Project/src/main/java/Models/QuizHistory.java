package Models;

import java.sql.Time;
import java.util.Date;

/**
 * Represents the history of a quiz attempt by a user, including details such as quiz ID, user ID,
 * quiz score, start time, end time, and elapsed time.
 */
public class QuizHistory {

    private int quizId;
    private String username;
    private int quizScore;
    private Time startTime;
    private Time endTime;
    private Date endDate;
    private long elapsedTime;

    /**
     * Constructs a new QuizHistory instance with the specified quiz ID, user ID, and initial values
     * for quiz score, start time, end time, and elapsed time.
     *
     * @param quizId the ID of the quiz
     * @param username the ID of the user who attempted the quiz
     */
    public QuizHistory(int quizId, String username) {
        this.quizId = quizId;
        this.username = username;
        this.quizScore = 0;
        this.startTime = null;
        this.endTime = null;
        this.elapsedTime = 0;
    }

    public QuizHistory(int quizId, String username, int quizScore, Time startTime, Time endTime, long elapsedTime) {
        this.quizId = quizId;
        this.username = username;
        this.quizScore = quizScore;
        this.startTime = startTime;
        this.endTime = endTime;
        this.elapsedTime = elapsedTime;
    }

    /**
     * Retrieves the ID of the quiz.
     *
     * @return the ID of the quiz
     */
    public int getQuizId() {
        return quizId;
    }

    /**
     * Sets the ID of the quiz.
     *
     * @param quizId the ID of the quiz
     */
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the ID of the user who attempted the quiz.
     *
     * @return the ID of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the ID of the user who attempted the quiz.
     *
     * @param username the ID of the user
     */
    public void setUserId(String username) {
        this.username = username;
    }

    /**
     * Retrieves the score achieved in the quiz.
     *
     * @return the score achieved in the quiz
     */
    public int getQuizScore() {
        return quizScore;
    }

    /**
     * Sets the score achieved in the quiz.
     *
     * @param quizScore the score achieved in the quiz
     */
    public void setQuizScore(int quizScore) {
        this.quizScore = quizScore;
    }

    /**
     * Retrieves the start time of the quiz attempt.
     *
     * @return the start time of the quiz attempt
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the quiz attempt.
     *
     * @param startTime the start time of the quiz attempt
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the end time of the quiz attempt.
     *
     * @return the end time of the quiz attempt
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the quiz attempt.
     *
     * @param endTime the end time of the quiz attempt
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * Retrieves the elapsed time of the quiz attempt.
     *
     * @return the elapsed time of the quiz attempt in milliseconds
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Sets the elapsed time of the quiz attempt.
     *
     * @param elapsedTime the elapsed time of the quiz attempt in milliseconds
     */
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "QuizHistory{" +
                "quizId=" + quizId +
                ", username='" + username + '\'' +
                ", quizScore=" + quizScore +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
