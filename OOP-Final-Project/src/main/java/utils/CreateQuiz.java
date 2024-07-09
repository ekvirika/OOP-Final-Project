package utils;

import Models.Enums.QuestionType;
import Models.Question;

import java.util.*;

public class CreateQuiz {

    public String generateUI(QuestionType questionType, Question question, boolean isEditable) {
        switch (questionType) {
            case QUESTION_RESPONSE:
                return generateQuesRes(question);
//                        + generateCorrectResponse(question) + generateEditButtons(question, isEditable);
            case FILL_IN_THE_BLANK:
                return generateFillBlank(question);
//                        + generateCorrectResponse(question) + generateEditButtons(question, isEditable);
            case MULTIPLE_CHOICE:
                return generateMultiChoice(question, isEditable);
            case PICTURE_RESPONSE:
                return generatePictRes(question);
//                        + generateCorrectResponse(question) + generateEditButtons(question, isEditable);
            case MULTI_ANSWER:
                return generateMultiAns(question, isEditable);
            case MULTIPLE_CHOICE_WITH_ANSWERS:
                return generateMultiChoiceAns(question, isEditable);
            case MATCHING:
                return generateMatching(question, isEditable);
            default:
                System.out.println("Invalid question type");
                return "";
        }
    }

    private String generateQuesRes(Question question) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"questiontxt\">").append(question.getQuestionText()).append("</div>");
        html.append("<div class=\"correct-response\">Correct Answer: ");
        html.append("<p>").append(question.getSingleQuestionAnswer())
                .append("</p>");

        html.append("</div>");
        return html.toString();
    }

    private String generateFillBlank(Question question) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"questiontxt\">").append(question.getQuestionText()).append("</div>");

        html.append("<div class=\"correct-response\">Correct Answer: ");
        html.append("<p>").append(question.getSingleQuestionAnswer())
                .append("</p>");

        html.append("</div>");
        return html.toString();
    }

    private String generateMultiChoice(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"questiontxt\">").append(question.getQuestionText()).append("</div>")
                .append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        for (int i = 0; i < answers.size(); i++) {
            String answerId = "answer" + (char) ('A' + i);
            String answerLabel = answers.get(i);

            // Check if the current answer is a correct answer
            boolean isCorrect = question.getMultipleChoiceCorrectIndexes().contains(i);

            html.append("<li><input type=\"radio\" id=\"").append(answerId).append("_").append(question.getQuestionId())
                    .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" value=\"").append(answerLabel).append("\"");

            // If the answer is correct, mark it as checked
            if (isCorrect) {
                html.append(" checked");
            }

            html.append(">")
                    .append("<label for=\"").append(answerId).append("_").append(question.getQuestionId()).append("\">").append(answerLabel).append("</label></li>");
        }
        html.append("</ul>");

//        html.append("<div class=\"correct-response\">Correct Answer: ");
//        for (int index : question.getMultipleChoiceCorrectIndexes()) {
//            html.append("<p>").append(question.getMultipleChoiceAnswers().get(index))
//                    .append("</p>");
//        }
//        html.append("</div>");
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }


    private String generatePictRes(Question question) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"image-question\"><img src=\"").append(question.getQuestionImage()).append("\" alt=\"Question Image\"></div>")
                .append("<div class=\"questiontxt\">").append(question.getQuestionText()).append("</div>");
        html.append("<div class=\"correct-response\">Correct Answer: ");
        html.append("<p>").append(question.getSingleQuestionAnswer())
                .append("</p>");

        html.append("</div>");
        return html.toString();
    }

    private String generateMultiAns(Question question, boolean isEditable) {
        List<String> correctAnswers = question.getMultipleAnswerFields();
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"questiontxt\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\">");
//
//        for (int i = 0; i < correctAnswers.size(); i++) {
//            html.append("<input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
//                    .append("\" name=\"userAnswer_").append(question.getQuestionId())
//                    .append("\" placeholder=\"Answer Here\">");
//        }

        html.append("</div>");

        html.append("<div class=\"correct-response\">Correct Answer: ");
        for (String ans : question.getMultipleAnswerFields()) {
            html.append("<p>").append(ans)
                    .append("</p>");
        }
        html.append("</div>");
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String generateMultiChoiceAns(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"questiontxt\"><h3>").append(question.getQuestionText()).append("</h3></div>")
                .append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        List<Integer> correctIndexes = question.getMultipleChoiceCorrectIndexes();

        for (int i = 0; i < answers.size(); i++) {
            char answerId = (char) ('A' + i);
            boolean isCorrect = correctIndexes.contains(i);  // Check if this answer is correct

            html.append("<li><input type=\"checkbox\" id=\"answer").append(answerId).append("_").append(question.getQuestionId())
                    .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" value=\"").append(answerId).append("\"")
                    .append(isCorrect ? " checked" : "")  // Mark the checkbox as checked if it's a correct answer
                    .append(">")
                    .append("<label for=\"answer").append(answerId).append("_").append(question.getQuestionId())
                    .append("\">").append(answers.get(i)).append("</label></li>");
        }

        html.append("</ul>");
//        html.append("<div class=\"correct-response\">Correct Answer: ");
//        for (int index : question.getMultipleChoiceCorrectIndexes()) {
//            html.append("<p>").append(question.getMultipleChoiceAnswers().get(index))
//                    .append("</p>");
//        }
//        html.append("</div>");

        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String generateMatching(Question question, boolean isEditable) {
        HashMap<String, String> matchingPairs = question.getMatchingPairs();

        List<String> questions = new ArrayList<>(matchingPairs.keySet());
        List<String> answers = new ArrayList<>(matchingPairs.values());

        StringBuilder html = new StringBuilder();
        html.append("<div class=\"matching-container\">")
                .append("<h1>").append(question.getQuestionText()).append("</h1>")
                .append("<div class=\"matching-content\">")
                .append("<div class=\"matching-questions\">");

        for (int i = 0; i < questions.size(); i++) {
            html.append("<div class=\"matching-item\" id=\"question").append(i + 1).append("_").append(question.getQuestionId())
                    .append("\" onclick=\"selectQuestion('question").append(i + 1).append("_").append(question.getQuestionId()).append("')\">")
                    .append(questions.get(i)).append("</div>");
        }

        html.append("</div><div class=\"matching-answers\">");

        for (int i = 0; i < answers.size(); i++) {
            html.append("<div class=\"matching-item\" id=\"answer").append(i + 1).append("_").append(question.getQuestionId())
                    .append("\" onclick=\"selectAnswer('answer").append(i + 1).append("_").append(question.getQuestionId()).append("')\">")
                    .append(answers.get(i)).append("</div>");
        }

        html.append("</div></div>")
                .append("<div class=\"correct-response\">Correct Answer: <p>").append(question.getMatchingPairs().toString()).append("</p></div>")
                .append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">")
                .append("<br>")
                .append("</div>");
        return html.toString();
    }
}