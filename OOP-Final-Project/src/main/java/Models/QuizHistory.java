package Models;

import java.sql.Time;
import java.util.HashMap;

/**
 * Represents the history of a quiz attempt by a user, including details such as quiz ID, user ID,
 * quiz score, start time, end time, and elapsed time.
 */
public class QuizHistory {

    private int quizId;
    private int userId;
    private int quizScore;
    private Time startTime;
    private Time endTime;
    private long elapsedTime;

    /**
     * Constructs a new QuizHistory instance with the specified quiz ID, user ID, and initial values
     * for quiz score, start time, end time, and elapsed time.
     *
     * @param quizId the ID of the quiz
     * @param userId the ID of the user who attempted the quiz
     */
    public QuizHistory(int quizId, int userId) {
        this.quizId = quizId;
        this.userId = userId;
        this.quizScore = 0; // Initialize quiz score to 0
        this.startTime = null;
        this.endTime = null;
        this.elapsedTime = 0;
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

    /**
     * Retrieves the ID of the user who attempted the quiz.
     *
     * @return the ID of the user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who attempted the quiz.
     *
     * @param userId the ID of the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
}
