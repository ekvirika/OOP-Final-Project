package Models.Enums;

/**
 * Enum representing the various types of questions.
 */
public enum QuestionType {
    QUESTION_RESPONSE,        /**< A question that requires a textual response. */
    FILL_IN_THE_BLANK,        /**< A question with a blank that needs to be filled in. */
    MULTIPLE_CHOICE,          /**< A question with multiple choice answers. */
    PICTURE_RESPONSE,         /**< A question that requires a response based on a picture. */
    MULTI_ANSWER,             /**< A question with multiple fields to answer. */
    MULTIPLE_CHOICE_WITH_ANSWERS, /**< A multiple choice question with multiple correct answers. */
    MATCHING;                 /**< A question that requires matching pairs. */


    /**
     * Converts a string to the corresponding QuestionType enum constant.
     *
     * @param type the string representing the question type
     * @return the corresponding QuestionType enum constant
     * @throws IllegalArgumentException if the string does not match any enum constant
     */
    public static QuestionType fromString(String type) {
        for (QuestionType questionType : QuestionType.values()) {
            if (questionType.name().equalsIgnoreCase(type)) {
                return questionType;
            }
        }
        throw new IllegalArgumentException("Unknown question type: " + type);
    }
}


