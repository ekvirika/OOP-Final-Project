package Models;

import Models.Enums.QuestionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents a Question with various types such as multiple choice, fill in the blank, etc.
 */
public class Question {
    private final int questionId;
    private int quizId;
    private QuestionType questionType; /**< The type of the question. */
    private String questionText; /**< The text of the question. */
    private String singleQuestionAnswer; /**< The single correct answer to the question. */
    private HashSet<String> alternativeAnswers; /**< A set of alternative correct answers. */
    private ArrayList<String> multipleChoiceAnswers; /**< A list of multiple choice answers. */
    private ArrayList<Integer> multipleChoiceCorrectIndexes; /**< Indexes of the correct multiple choice answers. */
    private String questionImage; /**< An image associated with the question, if any. */
    private ArrayList<String> multipleAnswerFields; /**< Fields for multiple answers. */
    private HashMap<String, String> matchingPairs;

    public Question(int questionId) {
        this.questionId = questionId;
    }

}
