package Models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a quiz entity, containing details such as quiz ID, creator ID, name, description,
 * score, associated question IDs, history, and various settings.
 */
public class Quiz {

    private int quizID;
    private String username;
    private String quizName;
    private String quizDescription;
    private int quizScore;
    private ArrayList<Integer> questionIds;
    private boolean isSinglePage;
    private boolean randomizeQuestions;
    private boolean immediateFeedback;
    private Timestamp createTime;


    public Quiz() {
        this.username = "";
        this.quizName = "";
        this.quizDescription = "";
        this.quizScore = 0;
        this.questionIds = new ArrayList<>();
        this.isSinglePage = false;
        this.randomizeQuestions = false;
        this.immediateFeedback = false;
        this.createTime = new Timestamp(System.currentTimeMillis());
    }
    /**
     * Default constructor that initializes a new Quiz instance with default values.
     *
     * @param quizID the unique ID of the quiz
     */
    public Quiz(int quizID) {
        this.quizID = quizID;
        this.username = "";
        this.quizName = "New Quiz";
        this.quizDescription = "";
        this.quizScore = 0;
        this.questionIds = new ArrayList<>();
        this.isSinglePage = false;
        this.randomizeQuestions = false;
        this.immediateFeedback = false;
        this.createTime = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Constructs a new Quiz instance with the specified quiz ID and basic details.
     *
     * @param quizID          the unique ID of the quiz
     * @param username        the username of the creator
     * @param quizName        the name of the quiz
     * @param quizDescription the description of the quiz
     */
    public Quiz(int quizID, String username, String quizName, String quizDescription) {
        this.quizID = quizID;
        this.username = username;
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizScore = 0;
        this.questionIds = new ArrayList<>();
        this.isSinglePage = false;
        this.randomizeQuestions = false;
        this.immediateFeedback = false;
        this.createTime = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Constructs a new Quiz instance with the specified details.
     *
     * @param quizID             the unique ID of the quiz
     * @param username           the username of the creator
     * @param quizName           the name of the quiz
     * @param quizDescription    the description of the quiz
     * @param quizScore          the score of the quiz
     * @param questionIds        the list of question IDs associated with the quiz
     * @param isSinglePage       whether the quiz is displayed on a single page
     * @param randomizeQuestions whether questions in the quiz are randomized
     * @param immediateFeedback  whether immediate feedback is enabled for the quiz
     * @param createTime         the creation time of the quiz
     */
    public Quiz(int quizID, String username, String quizName, String quizDescription, int quizScore,
                ArrayList<Integer> questionIds, boolean isSinglePage, boolean randomizeQuestions,
                boolean immediateFeedback, Timestamp createTime) {
        this.quizID = quizID;
        this.username = username;
        this.quizName = quizName;
        this.quizDescription = quizDescription;
        this.quizScore = quizScore;
        this.questionIds = questionIds != null ? new ArrayList<>(questionIds) : new ArrayList<>();
        this.isSinglePage = isSinglePage;
        this.randomizeQuestions = randomizeQuestions;
        this.immediateFeedback = immediateFeedback;
        this.createTime = createTime != null ? createTime : new Timestamp(System.currentTimeMillis());
    }


    /**
     * Retrieves the unique ID of the quiz.
     *
     * @return the quiz ID
     */
    public int getQuizID() {
        return quizID;
    }

    /**
     * Retrieves the username of the creator of the quiz.
     *
     * @return the username of the creator of the quiz
     */
    public String getCreatorUsername() {
        return username;
    }

    /**
     * Sets the username of the creator of the quiz.
     *
     * @param username the username of the creator of the quiz
     */
    public void setCreatorUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the name of the quiz.
     *
     * @return the name of the quiz
     */
    public String getQuizName() {
        return quizName;
    }

    /**
     * Sets the name of the quiz.
     *
     * @param quizName the name of the quiz
     */
    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    /**
     * Retrieves the description of the quiz.
     *
     * @return the description of the quiz
     */
    public String getQuizDescription() {
        return quizDescription;
    }

    /**
     * Sets the description of the quiz.
     *
     * @param quizDescription the description of the quiz
     */
    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    /**
     * Retrieves the score of the quiz.
     *
     * @return the score of the quiz
     */
    public int getQuizScore() {
        return quizScore;
    }

    /**
     * Sets the score of the quiz.
     *
     * @param quizScore the score of the quiz
     */
    public void setQuizScore(int quizScore) {
        this.quizScore = quizScore;
    }

    /**
     * Increments the score of the quiz by a specified amount.
     *
     * @param increment the amount to increment the score by
     */
    public void incrementQuizScore(int increment) {
        this.quizScore += increment;
    }

    /**
     * Resets the score of the quiz to zero.
     */
    public void resetQuizScore() {
        this.quizScore = 0;
    }

    /**
     * Retrieves the list of question IDs associated with the quiz.
     *
     * @return the list of question IDs
     */
    public ArrayList<Integer> getQuestionIds() {
        return (ArrayList<Integer>) questionIds;
    }

    /**
     * Sets the list of question IDs associated with the quiz.
     *
     * @param questionIds the list of question IDs
     */
    public void setQuestionIds(ArrayList<Integer> questionIds) {
        this.questionIds = questionIds;
    }

    /**
     * Adds a question ID to the quiz.
     *
     * @param questionId the ID of the question to add
     */
    public void addQuestionId(int questionId) {
        this.questionIds.add(questionId);
    }

    /**
     * Removes a question ID from the quiz.
     *
     * @param questionId the ID of the question to remove
     */
    public void removeQuestionId(int questionId) {
        this.questionIds.remove(Integer.valueOf(questionId));
    }

    /**
     * Checks if the quiz is displayed on a single page.
     *
     * @return true if the quiz is displayed on a single page; false otherwise
     */
    public boolean isSinglePage() {
        return isSinglePage;
    }

    /**
     * Sets whether the quiz is displayed on a single page or not.
     *
     * @param singlePage true if the quiz should be displayed on a single page; false otherwise
     */
    public void setSinglePage(boolean singlePage) {
        isSinglePage = singlePage;
    }

    /**
     * Checks if questions in the quiz are randomized.
     *
     * @return true if questions are randomized; false otherwise
     */
    public boolean isRandomizeQuestions() {
        return randomizeQuestions;
    }

    /**
     * Sets whether questions in the quiz should be randomized.
     *
     * @param randomizeQuestions true to randomize questions; false otherwise
     */
    public void setRandomizeQuestions(boolean randomizeQuestions) {
        this.randomizeQuestions = randomizeQuestions;
    }

    /**
     * Checks if immediate feedback is enabled for the quiz.
     *
     * @return true if immediate feedback is enabled; false otherwise
     */
    public boolean isImmediateFeedback() {
        return immediateFeedback;
    }

    /**
     * Sets whether immediate feedback should be enabled for the quiz.
     *
     * @param immediateFeedback true to enable immediate feedback; false otherwise
     */
    public void setImmediateFeedback(boolean immediateFeedback) {
        this.immediateFeedback = immediateFeedback;
    }

    /**
     * Retrieves the creation time of the quiz.
     *
     * @return the creation time of the quiz
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * Sets the creation time of the quiz.
     *
     * @param createTime the creation time of the quiz
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return quizID == quiz.quizID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quizID);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizID=" + quizID +
                ", username='" + username + '\'' +
                ", quizName='" + quizName + '\'' +
                ", quizDescription='" + quizDescription + '\'' +
                ", quizScore=" + quizScore +
                ", questionIds=" + questionIds +
                ", isSinglePage=" + isSinglePage +
                ", randomizeQuestions=" + randomizeQuestions +
                ", immediateFeedback=" + immediateFeedback +
                ", createTime=" + createTime +
                '}';
    }
}
