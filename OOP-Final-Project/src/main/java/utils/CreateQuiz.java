package utils;

import Models.Enums.QuestionType;
import Models.Question;

import java.util.*;

public class CreateQuiz {

    public String generateUI(QuestionType questionType) {
        switch (questionType) {
            case QUESTION_RESPONSE:
                return generateQuesRes();
            case FILL_IN_THE_BLANK:
                return generateFillBlank();
            case MULTIPLE_CHOICE:
                return generateMultiChoice();
            case PICTURE_RESPONSE:
                return generatePictRes();
            case MULTI_ANSWER:
                return generateMultiAns();
            case MULTIPLE_CHOICE_WITH_ANSWERS:
                return generateMultiChoiceAns();
            case MATCHING:
                return generateMatching();
            default:
                System.out.println("Invalid question type");
                return "";
        }
    }

    private String generateQuesRes() {
        return "<form action=\"CreateQuestionServlet\" method=\"post\">"
                + "<input type=\"hidden\" name=\"questionType\" value=\"QUESTION_RESPONSE\">"
                + "<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">"
                + "<input type=\"text\" name=\"answerText\" placeholder=\"Type correct answer here\">"
                + "<button class=\"btn\" type=\"submit\">Save Question</button>"
                + "</form>";
    }

    private String generateFillBlank() {
        return "<form action=\"CreateQuestionServlet\" method=\"post\">"
                + "<input type=\"hidden\" name=\"questionType\" value=\"FILL_IN_THE_BLANK\">"
                + "<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">"
                + "<input type=\"text\" name=\"answerText\" placeholder=\"Type correct answer here\">"
                + "<button class=\"btn\" type=\"submit\">Save Question</button>"
                + "</form>";
    }

    private String generateMultiChoice() {
        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<form action=\"CreateQuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"questionType\" value=\"MULTIPLE_CHOICE\">")
                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">")
                .append("<input type=\"text\" name=\"correctAnswer1\" placeholder=\"Type the correct answer here\">")
                .append("<input type=\"text\" name=\"correctAnswer2\" placeholder=\"Type another correct answer here\">")
                .append("<input type=\"text\" name=\"correctAnswer3\" placeholder=\"Type another correct answer here\">")
                .append("<input type=\"text\" name=\"correctAnswer4\" placeholder=\"Type another correct answer here\">")
                .append("<button type=\"submit\">Save Question</button>")
                .append("</form>");

        return formBuilder.toString();
    }

    private String generatePictRes() {
        return "<form action=\"CreateQuestionServlet\" method=\"post\">"
                + "<input type=\"hidden\" name=\"questionType\" value=\"PICTURE_RESPONSE\">"
                + "<input type=\"text\" name=\"questionText\" placeholder=\"Type your Image URL here\">"
                + "<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">"
                + "<input type=\"text\" name=\"answerText\" placeholder=\"Type correct answer here\">"
                + "<button class=\"btn\" type=\"submit\">Save Question</button>"
                + "</form>";
    }

    private String generateMultiAns() {
        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<form action=\"CreateQuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"questionType\" value=\"MULTI_ANSWER\">")
                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">");

        int maxAnswers = 10;
        for (int i = 1; i <= maxAnswers; i++) {
            formBuilder.append("<input type=\"text\" name=\"correctAnswer").append(i).append("\" placeholder=\"Type correct answer ").append(i).append(" here\">");
        }

        formBuilder.append("<button type=\"submit\">Save Question</button>")
                .append("</form>");

        return formBuilder.toString();
    }

    private String generateMultiChoiceAns() {
        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<form action=\"CreateQuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"questionType\" value=\"MULTIPLE_CHOICE_WITH_ANSWERS\">")
                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">")
                .append("<input type=\"text\" name=\"correctAnswer1\" placeholder=\"Type the correct answer here\">")
                .append("<input type=\"text\" name=\"correctAnswer2\" placeholder=\"Type another correct answer here\">")
                .append("<input type=\"text\" name=\"correctAnswer3\" placeholder=\"Type another correct answer here\">")
                .append("<input type=\"text\" name=\"correctAnswer4\" placeholder=\"Type another correct answer here\">")
                .append("<button type=\"submit\">Save Question</button>")
                .append("</form>");

        return formBuilder.toString();
    }

    private String generateMatching() {
        StringBuilder formBuilder = new StringBuilder();
        formBuilder.append("<form action=\"CreateQuestionServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"questionType\" value=\"MATCHING\">")
                .append("<input type=\"text\" name=\"questionText\" placeholder=\"Type your question here\">")
                .append("<div class=\"quiz\">")
                .append("<div class=\"questions\" id=\"questions\">")
                .append("<div class=\"pair\">")
                .append("<input type=\"text\" name=\"question1\" placeholder=\"Question 1\">")
                .append("<input type=\"text\" name=\"answer1\" placeholder=\"Answer 1\">")
                .append("</div>")
                .append("</div>")
                .append("<button type=\"button\" onclick=\"addPair()\">Add Pair</button>")
                .append("</div>")
                .append("<button class=\"btn\" type=\"submit\">Save Question</button>")
                .append("</form>")
                .append("<script>")
                .append("let pairCount = 1;")
                .append("function addPair() {")
                .append("  pairCount++;")
                .append("  const questionsDiv = document.getElementById('questions');")
                .append("  const newPairDiv = document.createElement('div');")
                .append("  newPairDiv.classList.add('pair');")
                .append("  newPairDiv.innerHTML = `<input type='text' name='question${pairCount}' placeholder='Question ${pairCount}'>")
                .append("  <input type='text' name='answer${pairCount}' placeholder='Answer ${pairCount}'>`;")
                .append("  questionsDiv.appendChild(newPairDiv);")
                .append("}")
                .append("</script>");
        return formBuilder.toString();
    }
}
