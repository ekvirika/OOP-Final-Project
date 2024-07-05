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
//            case 4:
//                return generatePictRes(question);
//            break;
//            case 5:
//                return generateMultiAns(question);
//            break;
//            case 6:
//                return generateMultiChoiceAns(question);
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


//    private String generatePictRes(Question question){
//        return  "<form action=\"QuestionServlet\" method=\"post\">" +
//                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
//                "<div class=\"image-question\">" +
//                "<img src=\"https://physics.itmo.ru/sites/default/files/styles/seminar_speaker_full/public/speaker-photo/12244.jpg?itok=_XsYjE4D\" alt=\"Question Image\">" +
//                "</div>" +
//                "<div class=\"question\">\"Who is on the photo?\"</div>" +
//                "<div class=\"response\">" +
//                "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" +
//                "</div>" +
//                "<button class=\"btn\" type=\"submit\">Submit</button>" +
//                "</form>";
//    }

//    private String generateMultiAns(Question question){
//         return  "<form action=\"QuestionServlet\" method=\"post\">" +
//                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
//                "<div class=\"response\">" +
//                "<div class=\"row\">" +
//                "<input type=\"text\" id=\"userAnswer1\" name=\"userAnswer1\" placeholder=\"#7\">" +
//                "<input type=\"text\" id=\"userAnswer2\" name=\"userAnswer2\" placeholder=\"#22\">" +
//                "</div>" +
//                "<div class=\"row\">" +
//                "<input type=\"text\" id=\"userAnswer3\" name=\"userAnswer3\" placeholder=\"#6\">" +
//                "<input type=\"text\" id=\"userAnswer4\" name=\"userAnswer4\" placeholder=\"#17\">" +
//                 "<input type=\"text\" id=\"userAnswer10\" name=\"userAnswer10\" placeholder=\"#10\">" +
//                "</div>" +
//                "<div class=\"row\">" +
//                 "<input type=\"text\" id=\"userAnswer18\" name=\"userAnswer18\" placeholder=\"#18\">" +
//                 "<input type=\"text\" id=\"userAnswer16\" name=\"userAnswer16\" placeholder=\"#16\">" +
//                 "<input type=\"text\" id=\"userAnswer15\" name=\"userAnswer15\" placeholder=\"#15\">" +
//                 "<input type=\"text\" id=\"userAnswer2\" name=\"userAnswer2\" placeholder=\"#2\">" +
//                "</div>" +
//                "</div>" +
//                "<button class=\"btn\" type=\"submit\">Submit</button>" +
//                "</form>";
//    }

//    private String generateMultiChoiceAns(Question question){
//        return  "<form action=\"QuestionServlet\" method=\"post\">" +
//                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
//                <div class="question"><h3>Please mark each statement below which is true:</h3></div>
//                <ul class="answers">
//                <li>
//                <input type="checkbox" id="answerA" name="answers" value="A">
//                <label for="answerA">Stanford was established in 1891.</label>
//                </li>
//                <li>
//                <input type="checkbox" id="answerB" name="answers" value="B">
//                <label for="answerB">Stanford has the best computer science department in the world.</label>
//                </li>
//                <li>
//                <input type="checkbox" id="answerC" name="answers" value="C">
//                <label for="answerC">Stanford will be going to a bowl game this year.</label>
//                </li>
//                </ul>
//                "<button class=\"btn\" type=\"submit\">Submit</button>" +
//                "</form>";
//    }

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