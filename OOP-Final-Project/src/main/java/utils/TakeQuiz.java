package utils;

import Models.Question;



public class TakeQuiz {

    public String generateUI(int questionType, Question question) {

        switch (questionType) {
            case 1:
                return generateQuesRes(question);
//            break;
//            case 2:
//                return generateFillBlank(question);
//            break;
//            case 3:
//                return generateMultiChoice(question);
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
        return "<div class=\"question\">" + question.getQuestionText() + "</div>" +
                "<div class=\"response\">" +
                "<input type=\"text\" id=\"userAnswer\" name=\"userAnswer\" placeholder=\"Type your answer here\">" +
                "</div>" +
                "<button class=\"btn\">Submit</button>";
    }

//    private String generateFillBlank(Question question){
//
//    }
//
//    private String generateMultiChoice(Question question){
//
//    }
//
//    private String generatePictRes(Question question){
//
//    }
//
//    private String generateMultiAns(Question question){
//
//    }
//
//    private String generateMultiChoiceAns(Question question){
//
//    }
//
//    private String generateMatching(Question question){
//
//    }

}