package utils;

import Models.Enums.QuestionType;
import Models.Question;

import java.util.*;

public class TakeSinglePageQuiz {

    public String generateUI(QuestionType questionType, Question question, boolean isEditable) {
        switch (questionType) {
            case QUESTION_RESPONSE:
                return generateQuesRes(question, isEditable);
            case FILL_IN_THE_BLANK:
                return generateFillBlank(question, isEditable);
            case MULTIPLE_CHOICE:
                return generateMultiChoice(question, isEditable);
            case PICTURE_RESPONSE:
                return generatePictRes(question, isEditable);
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

    private String generateQuesRes(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\"><input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
                .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" placeholder=\"Type your answer here\"></div>");

        if (isEditable) {
            html.append(generateEditButtons(question));
        }
        return html.toString();
    }

    private String generateFillBlank(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\"><input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
                .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" placeholder=\"Type your answer here\"></div>");

        if (isEditable) {
            html.append(generateEditButtons(question));
        }
        return html.toString();
    }

    private String generateMultiChoice(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        for (int i = 0; i < answers.size(); i++) {
            String answerId = "answer" + (char) ('A' + i);
            String answerLabel = answers.get(i);

            html.append("<li><input type=\"radio\" id=\"").append(answerId).append("_").append(question.getQuestionId())
                    .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" value=\"").append(answerLabel).append("\">")
                    .append("<label for=\"").append(answerId).append("_").append(question.getQuestionId()).append("\">").append(answerLabel).append("</label></li>");
        }
        html.append("</ul>");

        if (isEditable) {
            html.append(generateEditButtons(question));
        }
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String generatePictRes(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();
        html.append("<div class=\"image-question\"><img src=\"").append(question.getQuestionImage()).append("\" alt=\"Question Image\"></div>")
                .append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\"><input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
                .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" placeholder=\"Type your answer here\"></div>");

        if (isEditable) {
            html.append(generateEditButtons(question));
        }
        return html.toString();
    }

    private String generateMultiAns(Question question, boolean isEditable) {
        List<String> correctAnswers = question.getMultipleAnswerFields();
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\">");

        for (int i = 0; i < correctAnswers.size(); i++) {
            html.append("<input type=\"text\" id=\"userAnswer_").append(question.getQuestionId())
                    .append("\" name=\"userAnswer_").append(question.getQuestionId())
                    .append("\" placeholder=\"Answer Here\">");
        }

        html.append("</div>");

        if (isEditable) {
            html.append(generateEditButtons(question));
        }
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String generateMultiChoiceAns(Question question, boolean isEditable) {
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"question\"><h3>").append(question.getQuestionText()).append("</h3></div>")
                .append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        for (int i = 0; i < answers.size(); i++) {
            char answerId = (char) ('A' + i);
            html.append("<li><input type=\"checkbox\" id=\"answer").append(answerId).append("_").append(question.getQuestionId())
                    .append("\" name=\"userAnswer_").append(question.getQuestionId()).append("\" value=\"").append(answerId).append("\">")
                    .append("<label for=\"answer").append(answerId).append("_").append(question.getQuestionId())
                    .append("\">").append(answers.get(i)).append("</label></li>");
        }

        html.append("</ul>");
        if (isEditable) {
            html.append(generateEditButtons(question));
        }
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String generateMatching(Question question, boolean isEditable) {
        HashMap<String, String> matchingPairs = question.getMatchingPairs();

        List<String> questions = new ArrayList<>(matchingPairs.keySet());
        List<String> answers = new ArrayList<>(matchingPairs.values());

        Collections.shuffle(questions);
        Collections.shuffle(answers);

        StringBuilder html = new StringBuilder();
        html.append("<h1>").append(question.getQuestionText()).append("</h1>")
                .append("<div class=\"quiz\"><div class=\"questions\">");

        for (int i = 0; i < questions.size(); i++) {
            html.append("<div class=\"marcxena\" id=\"question").append(i + 1).append("_").append(question.getQuestionId())
                    .append("\" onclick=\"selectQuestion('question").append(i + 1).append("_").append(question.getQuestionId()).append("')\">")
                    .append(questions.get(i)).append("</div>");
        }

        html.append("</div><div class=\"answers\">");

        for (int i = 0; i < answers.size(); i++) {
            html.append("<div class=\"answer\" id=\"answer").append(i + 1).append("_").append(question.getQuestionId())
                    .append("\" onclick=\"selectAnswer('answer").append(i + 1).append("_").append(question.getQuestionId()).append("')\">")
                    .append(answers.get(i)).append("</div>");
        }

        html.append("</div></div>");

        if (isEditable) {
            html.append(generateEditButtons(question));
        }
        html.append("<input type=\"hidden\" name=\"userAnswers_").append(question.getQuestionId()).append("\" value=\"\">");
        return html.toString();
    }

    private String generateEditButtons(Question question) {
        return "<div class=\"edit-buttons\" id=\"question-" + question.getQuestionId() + "\">"
                + "<button type=\"button\" class=\"deleteBtn\" onclick=\"deleteQuestion('" + question.getQuestionId() + "')\">Delete</button>"
                + "<input type=\"hidden\" name=\"questionId\" value=\"" + question.getQuestionId() + "\">"
                + "</div>";
    }
}
