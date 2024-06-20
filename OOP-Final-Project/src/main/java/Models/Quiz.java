package Models;

import java.util.ArrayList;

/**
 * Represents a quiz entity, containing details such as quiz ID, creator ID, name, description,
 * score, associated question IDs, history, and various settings.
 */
public class Quiz {

    private final int quizID;
    private Integer creatorId;
    private String quizName;
    private String quizDescription;
    private int quizScore;
    private ArrayList<Integer> questionIds;
    private QuizHistory quizHistory;
    private boolean isSinglePage;
    private boolean randomizeQuestions;
    private boolean immediateFeedback;

    /**
     * Constructs a new Quiz instance with the specified quiz ID.
     *
     * @param quizID the unique ID of the quiz
     */
    public Quiz(int quizID) {
        this.quizID = quizID;
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
     * Retrieves the ID of the creator of the quiz.
     *
     * @return the creator ID of the quiz
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * Sets the ID of the creator of the quiz.
     *
     * @param creatorId the creator ID of the quiz
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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
     * Retrieves the list of question IDs associated with the quiz.
     *
     * @return the list of question IDs
     */
    public ArrayList<Integer> getQuestionIds() {
        return questionIds;
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
     * Retrieves the history of the quiz, including past attempts and scores.
     *
     * @return the quiz history object
     */
    public QuizHistory getQuizHistory() {
        return quizHistory;
    }

    /**
     * Sets the history of the quiz.
     *
     * @param quizHistory the quiz history object
     */
    public void setQuizHistory(QuizHistory quizHistory) {
        this.quizHistory = quizHistory;
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
}
