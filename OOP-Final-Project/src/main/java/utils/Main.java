package utils;

import Models.Enums.QuestionType;

public class Main {
    public static void main(String[] args) {
        String questionTypeStr = "QUESTION_RESPONSE";

        try {
            QuestionType questionType = QuestionType.fromString(questionTypeStr);
            System.out.println("Question type: " + questionType);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
