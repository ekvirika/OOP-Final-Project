package ModelsTest;

import Models.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    private Quiz quiz;

    @BeforeEach
    public void setup() {
        quiz = new Quiz(1, "creator1", "Test Quiz", "This is a test quiz");
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, quiz.getQuizScore(), "Default quiz score should be 0");
        assertFalse(quiz.isSinglePage(), "Default isSinglePage should be false");
        assertFalse(quiz.isRandomizeQuestions(), "Default randomizeQuestions should be false");
        assertFalse(quiz.isImmediateFeedback(), "Default immediateFeedback should be false");

        // Timestamp comparison within a reasonable margin of error (1 second)
        long currentTimeMillis = System.currentTimeMillis();
        assertTrue(Math.abs(currentTimeMillis - quiz.getCreateTime().getTime()) < 1000,
                "Default createTime should be close to current time");
    }

    @Test
    public void testFullConstructor() {
        ArrayList<Integer> questionIds = new ArrayList<>();
        questionIds.add(101);
        questionIds.add(102);

        Timestamp createTime = Timestamp.valueOf("2024-07-06 12:00:00");

        quiz = new Quiz(2, "creator2", "Full Quiz", "This is a full quiz",
                50, questionIds, true, true, true, createTime);

        assertEquals(2, quiz.getQuizID(), "Quiz ID should match");
        assertEquals("creator2", quiz.getCreatorUsername(), "Creator username should match");
        assertEquals("Full Quiz", quiz.getQuizName(), "Quiz name should match");
        assertEquals("This is a full quiz", quiz.getQuizDescription(), "Quiz description should match");
        assertEquals(50, quiz.getQuizScore(), "Quiz score should match");
        assertEquals(questionIds, quiz.getQuestionIds(), "Question IDs should match");
        assertTrue(quiz.isSinglePage(), "isSinglePage should be true");
        assertTrue(quiz.isRandomizeQuestions(), "randomizeQuestions should be true");
        assertTrue(quiz.isImmediateFeedback(), "immediateFeedback should be true");
        assertEquals(createTime, quiz.getCreateTime(), "createTime should match");
    }

    @Test
    public void testIncrementQuizScore() {
        quiz.incrementQuizScore(10);
        assertEquals(10, quiz.getQuizScore(), "Quiz score should be incremented by 10");

        quiz.incrementQuizScore(-5);
        assertEquals(5, quiz.getQuizScore(), "Quiz score should be decremented to 5");
    }

    @Test
    public void testAddRemoveQuestionId() {
        quiz.addQuestionId(101);
        assertTrue(quiz.getQuestionIds().contains(101), "Question ID 101 should be added");

        quiz.removeQuestionId(101);
        assertFalse(quiz.getQuestionIds().contains(101), "Question ID 101 should be removed");
    }

    @Test
    public void testEqualsAndHashCode() {
        Quiz quiz1 = new Quiz(1, "creator1", "Test Quiz", "This is a test quiz");
        Quiz quiz2 = new Quiz(1, "creator2", "Another Quiz", "This is another quiz");

        assertEquals(quiz1, quiz2, "Quizzes should be equal");
        assertEquals(quiz1.hashCode(), quiz2.hashCode(), "Hash codes should be equal");
    }

    @Test
    public void testToString() {
        String expectedToString = "Quiz{quizID=1, username='creator1', quizName='Test Quiz', " +
                "quizDescription='This is a test quiz', quizScore=0, questionIds=[], " +
                "isSinglePage=false, randomizeQuestions=false, immediateFeedback=false, " +
                "createTime=" + quiz.getCreateTime() + "}";
        assertEquals(expectedToString, quiz.toString(), "toString method should produce the expected string");
    }
}
