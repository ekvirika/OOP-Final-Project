package ModelsTest;

import Models.Enums.QuestionType;
import Models.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    private Question question;

    @BeforeEach
    public void setup() {
        question = new Question(1);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1, question.getQuestionId(), "Question ID should match");
        assertEquals(0, question.getQuizId(), "Quiz ID should initially be 0");
        assertNull(question.getQuestionType(), "Question type should initially be null");
        assertNull(question.getQuestionText(), "Question text should initially be null");
        assertNull(question.getSingleQuestionAnswer(), "Single question answer should initially be null");
        assertTrue(question.getAlternativeAnswers().isEmpty(), "Alternative answers should initially be empty");
        assertTrue(question.getMultipleChoiceAnswers().isEmpty(), "Multiple choice answers should initially be empty");
        assertTrue(question.getMultipleChoiceCorrectIndexes().isEmpty(), "Multiple choice correct indexes should initially be empty");
        assertNull(question.getQuestionImage(), "Question image should initially be null");
        assertTrue(question.getMultipleAnswerFields().isEmpty(), "Multiple answer fields should initially be empty");
        assertTrue(question.getMatchingPairs().isEmpty(), "Matching pairs should initially be empty");
    }

    @Test
    public void testSetters() {
        question.setQuizId(2);
        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        question.setQuestionText("What is 2 + 2?");
        question.setSingleQuestionAnswer("4");
        question.setQuestionImage("image.jpg");

        HashSet<String> altAnswers = new HashSet<>();
        altAnswers.add("Four");
        altAnswers.add("4");
        question.setAlternativeAnswers(altAnswers);

        ArrayList<String> mcAnswers = new ArrayList<>();
        mcAnswers.add("1");
        mcAnswers.add("2");
        mcAnswers.add("3");
        mcAnswers.add("4");
        question.setMultipleChoiceAnswers(mcAnswers);

        ArrayList<Integer> mcCorrectIndexes = new ArrayList<>();
        mcCorrectIndexes.add(3); // Index of "4"
        question.setMultipleChoiceCorrectIndexes(mcCorrectIndexes);

        HashMap<String, String> matchingPairs = new HashMap<>();
        matchingPairs.put("A", "1");
        matchingPairs.put("B", "2");
        question.setMatchingPairs(matchingPairs);

        assertEquals(2, question.getQuizId(), "Quiz ID should be updated");
        assertEquals(QuestionType.MULTIPLE_CHOICE, question.getQuestionType(), "Question type should be updated");
        assertEquals("What is 2 + 2?", question.getQuestionText(), "Question text should be updated");
        assertEquals("4", question.getSingleQuestionAnswer(), "Single question answer should be updated");
        assertEquals("image.jpg", question.getQuestionImage(), "Question image should be updated");

        assertEquals(altAnswers, question.getAlternativeAnswers(), "Alternative answers should be updated");
        assertEquals(mcAnswers, question.getMultipleChoiceAnswers(), "Multiple choice answers should be updated");
        assertEquals(mcCorrectIndexes, question.getMultipleChoiceCorrectIndexes(), "Multiple choice correct indexes should be updated");
        assertEquals(matchingPairs, question.getMatchingPairs(), "Matching pairs should be updated");
    }

    @Test
    public void testAddingMethods() {
        question.addAlternativeAnswer("Four");
        question.addAlternativeAnswer("4");

        question.addMultipleChoiceAnswer("1", false);
        question.addMultipleChoiceAnswer("2", false);
        question.addMultipleChoiceAnswer("3", false);
        question.addMultipleChoiceAnswer("4", true);

        question.addMatchingPair("A", "1");
        question.addMatchingPair("B", "2");

        assertEquals(2, question.getAlternativeAnswers().size(), "Should have added two alternative answers");
        assertEquals(4, question.getMultipleChoiceAnswers().size(), "Should have added four multiple choice answers");
        assertEquals(1, question.getMultipleChoiceCorrectIndexes().size(), "Should have added one correct multiple choice index");
        assertEquals(2, question.getMatchingPairs().size(), "Should have added two matching pairs");
    }

    @Test
    public void testToString() {
        String expectedToString = "Question{" +
                "questionId=1, quizId=0, questionType=null, questionText='null', " +
                "singleQuestionAnswer='null', alternativeAnswers=[], multipleChoiceAnswers=[], " +
                "multipleChoiceCorrectIndexes=[], questionImage='null', multipleAnswerFields=[], " +
                "matchingPairs={}}";
        assertEquals(expectedToString, question.toString(), "toString method should produce the expected string");
    }

    @Test
    public void testIsMultipleChoice() {
        question.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        assertTrue(question.isMultipleChoice(), "Now, question type is set to multiple choice");
    }
}
