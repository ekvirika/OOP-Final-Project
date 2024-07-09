package Models.Enums;

public enum AchievementType {

    AMATEUR_AUTHOR,
    PROLIFIC_AUTHOR,
    PRODIGIOUS_AUTHOR,
    QUIZ_MACHINE,
    I_AM_THE_GREATEST,
    PRACTICE_MAKES_PERFECT;

    public static QuestionType fromString(String type) {
        if (type == null) {
            return null;
        }
        try {
            return QuestionType.valueOf(type.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
