package Models.Enums;

/**
 * Enum representing the various types of questions.
 */
public enum QuestionType {
    FILL_IN_THE_BLANK,        /**< A question with a blank that needs to be filled in. */
    QUESTION_RESPONSE,        /**< A question that requires a textual response. */
    MULTIPLE_CHOICE,          /**< A question with multiple choice answers. */
    PICTURE_RESPONSE,         /**< A question that requires a response based on a picture. */
    MULTI_ANSWER,             /**< A question with multiple fields to answer. */
    MULTIPLE_CHOICE_WITH_ANSWERS, /**< A multiple choice question with multiple correct answers. */
    MATCHING                 /**< A question that requires matching pairs. */
}

