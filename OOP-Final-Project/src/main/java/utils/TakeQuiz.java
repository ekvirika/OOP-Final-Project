package utils;

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

    private String generateQuesRes(Question question){
        return  "<form action=\"QuestionServlet\" method=\"post\">" +
                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
                "<div class=\"question\">" + question.getQuestionText() + "</div>" +
                "<div class=\"response\">" +
                "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" +
                "</div>" +
                "<button class=\"btn\" type=\"submit\">Submit</button>" +
                "</form>";
    }

    private String generateFillBlank(Question question){
        return  "<form action=\"QuestionServlet\" method=\"post\">" +
                "<input type=\"hidden\" name=\"quizId\" value=\"" + question.getQuizId() + "\">" +
                "<div class=\"question\">" + question.getQuestionText() + "</div>" +
                "<div class=\"response\">" +
                "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" +
                "</div>" +
                "<button class=\"btn\" type=\"submit\">Submit</button>" +
                "</form>";
    }


    private String generateMultiChoice(Question question) {
        StringBuilder html = new StringBuilder();
        html.append("<form action=\"QuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"quizId\" value=\"").append(question.getQuizId()).append("\">")
                .append("<div class=\"question\">").append(question.getQuestionText()).append("</div>")
                .append("<ul class=\"answers\">");

        List<String> answers = question.getMultipleChoiceAnswers();
        if (answers.size() > 0) {
            html.append("<li><input type=\"radio\" id=\"answerA\" name=\"userAnswer\" value=\"A\">")
                    .append("<label for=\"answerA\">A. ").append(answers.get(0)).append("</label></li>");
        }
        if (answers.size() > 1) {
            html.append("<li><input type=\"radio\" id=\"answerB\" name=\"userAnswer\" value=\"B\">")
                    .append("<label for=\"answerB\">B. ").append(answers.get(1)).append("</label></li>");
        }
        if (answers.size() > 2) {
            html.append("<li><input type=\"radio\" id=\"answerC\" name=\"userAnswer\" value=\"C\">")
                    .append("<label for=\"answerC\">C. ").append(answers.get(2)).append("</label></li>");
        }
        if (answers.size() > 3) {
            html.append("<li><input type=\"radio\" id=\"answerD\" name=\"userAnswer\" value=\"D\">")
                    .append("<label for=\"answerD\">D. ").append(answers.get(3)).append("</label></li>");
        }

        html.append("</ul>")
                .append("<button class=\"btn\" type=\"submit\">Submit</button>")
                .append("</form>");

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