package utils;

import Models.Enums.QuestionType;
import Models.Question;

import java.util.List;


public class TakeQuiz {

    public String generateUI(int questionType, Question question) {

        switch (questionType) {
            case 1:
                return generateQuesRes(question);
//            break;
            case 2:
                return generateFillBlank(question);
//            break;
            case 3:
                return generateMultiChoice(question);
//            break;
            case 4:
                return generatePictRes(question);
//            break;
            case 5:
                return generateMultiAns(question);
//            break;
            case 6:
                return generateMultiChoiceAns(question);
//            break;
//            case 7:
//                return generateMatching(question);
//            break;
            default:
                System.out.println("Invalid question type");
                return "";
        }
    }

    private String generateQuesRes(Question question) {
        return "<form action=\"QuestionServlet\" method=\"post\">" + "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" + "<div class=\"question\">" + question.getQuestionText() + "</div>" + "<div class=\"response\">" + "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" + "</div>" + "<button class=\"btn\" type=\"submit\">Submit</button>" + "</form>";
    }

    private String generateFillBlank(Question question) {
        return "<form action=\"QuestionServlet\" method=\"post\">" + "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" + "<div class=\"question\">" + question.getQuestionText() + "</div>" + "<div class=\"response\">" + "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" + "</div>" + "<button class=\"btn\" type=\"submit\">Submit</button>" + "</form>";
    }

    private String generateMultiChoice(Question question) {
        StringBuilder html = new StringBuilder();
        html.append("<form action=\"QuestionServlet\" method=\"post\" onsubmit=\"collectAnswers(event)\">").append("<input type=\"hidden\" name=\"quizId\" value=\"").append(question.getQuizId()).append("\">").append("<input type=\"hidden\" name=\"userAnswers\" id=\"userAnswers\">").append("<div class=\"question\">").append(question.getQuestionText()).append("</div>").append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        boolean isMultipleAnswers = question.getQuestionType() == QuestionType.MULTIPLE_CHOICE_WITH_ANSWERS;

        for (int i = 0; i < answers.size(); i++) {
            String answerId = "answer" + (char) ('A' + i);
            String answerLabel = (char) ('A' + i) + ". " + answers.get(i);
            if (isMultipleAnswers) {
                html.append("<li><input type=\"checkbox\" id=\"").append(answerId).append("\" name=\"userAnswer\" value=\"").append(answerLabel).append("\">").append("<label for=\"").append(answerId).append("\">").append(answerLabel).append("</label></li>");
            } else {
                html.append("<li><input type=\"radio\" id=\"").append(answerId).append("\" name=\"userAnswer\" value=\"").append(answerLabel).append("\">").append("<label for=\"").append(answerId).append("\">").append(answerLabel).append("</label></li>");
            }
        }

        html.append("</ul>").append("<button class=\"btn\" type=\"submit\">Submit</button>").append("</form>").append("<script>").append("function collectAnswers(event) {").append("event.preventDefault();").append("const selectedAnswers = [];").append("const checkboxes = document.querySelectorAll('input[name=\"userAnswer\"]:checked');").append("checkboxes.forEach((checkbox) => { selectedAnswers.push(checkbox.value); });").append("document.getElementById('userAnswers').value = JSON.stringify(selectedAnswers);").append("event.target.submit();").append("}").append("</script>");
        return html.toString();
    }


    private String generatePictRes(Question question){
        return  "<form action=\"QuestionServlet\" method=\"post\">" +
                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
                "<div class=\"image-question\">" +
                "<img src=\"" + question.getQuestionImage() + "\" alt=\"Question Image\">" +
                "</div>" +
                "<div class=\"question\">\"" + question.getQuestionText() + "\"</div>" +
                "<div class=\"response\">" +
                "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" +
                "</div>" +
                "<button class=\"btn\" type=\"submit\">Submit</button>" +
                "</form>";
    }

    private String generateMultiAns(Question question) {
        List<String> correctAnswers = question.getMultipleAnswerFields();
        StringBuilder formBuilder = new StringBuilder();

        formBuilder.append("<form action=\"QuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"quizId\" value=\"").append(question.getQuizId()).append("\">")
                .append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<div class=\"response\">");

        for (int i = 0; i < correctAnswers.size(); i++) {
            if (i % 4 == 0) {
                formBuilder.append("<div class=\"row\">");
            }

            formBuilder.append("<input type=\"text\" id=\"userAnswer").append(i + 1).append("\" name=\"userAnswer").append(i + 1)
                    .append("\" placeholder=\"Answer Here\">");

            if ((i + 1) % 4 == 0 || i == correctAnswers.size() - 1) {
                formBuilder.append("</div>");
            }
        }

        formBuilder.append("</div>")
                .append("<button class=\"btn\" type=\"submit\">Submit</button>")
                .append("</form>");

        return formBuilder.toString();
    }


    private String generateMultiChoiceAns(Question question) {
        StringBuilder formBuilder = new StringBuilder();

        formBuilder.append("<form action=\"QuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"quizId\" value=\"").append(question.getQuizId()).append("\">")
                .append("<div class=\"question\"><h3>").append(question.getQuestionText()).append("</h3></div>")
                .append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        for (int i = 0; i < answers.size(); i++) {
            char answerId = (char) ('A' + i);
            formBuilder.append("<li>")
                    .append("<input type=\"checkbox\" id=\"answer").append(answerId).append("\" name=\"answers\" value=\"").append(answerId).append("\">")
                    .append("<label for=\"answer").append(answerId).append("\">").append(answers.get(i)).append("</label>")
                    .append("</li>");
        }

        formBuilder.append("</ul>")
                .append("<button class=\"btn\" type=\"submit\">Submit</button>")
                .append("</form>");

        return formBuilder.toString();
    }


//    private String generateMatching(Question question){
//         return  "<form action=\"QuestionServlet\" method=\"post\">" +
//                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
//                "<div class=\"question\">" + question.getQuestionText() + "</div>" +
//                "<div class=\"response\">" +
//                "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" +
//                "</div>" +
//                "<button class=\"btn\" type=\"submit\">Submit</button>" +
//                "</form>";
//    }

}