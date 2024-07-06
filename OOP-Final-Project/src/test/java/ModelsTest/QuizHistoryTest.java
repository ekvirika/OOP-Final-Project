package ModelsTest;

import Models.QuizHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Time;
import static org.junit.jupiter.api.Assertions.*;

public class QuizHistoryTest {

    private QuizHistory quizHistory;

    @BeforeEach
    public void setup() {
        quizHistory = new QuizHistory(1, "user1");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1, quizHistory.getQuizId(), "Quiz ID should match");
        assertEquals("user1", quizHistory.getUsername(), "Username should match");
        assertEquals(0, quizHistory.getQuizScore(), "Quiz score should initially be 0");
        assertNull(quizHistory.getStartTime(), "Start time should initially be null");
        assertNull(quizHistory.getEndTime(), "End time should initially be null");
        assertEquals(0, quizHistory.getElapsedTime(), "Elapsed time should initially be 0");
    }

    @Test
    public void testSetters() {
        quizHistory.setQuizId(2);
        quizHistory.setUsername("user2");
        quizHistory.setQuizScore(50);

        Time startTime = new Time(System.currentTimeMillis());
        quizHistory.setStartTime(startTime);

        Time endTime = new Time(System.currentTimeMillis() + 10000);
        quizHistory.setEndTime(endTime);

        quizHistory.setElapsedTime(15000);

        assertEquals(2, quizHistory.getQuizId(), "Quiz ID should be updated");
        assertEquals("user2", quizHistory.getUsername(), "Username should be updated");
        assertEquals(50, quizHistory.getQuizScore(), "Quiz score should be updated");
        assertEquals(startTime, quizHistory.getStartTime(), "Start time should be updated");
        assertEquals(endTime, quizHistory.getEndTime(), "End time should be updated");
        assertEquals(15000, quizHistory.getElapsedTime(), "Elapsed time should be updated");
    }

    @Test
    public void testToString() {
        String expectedToString = "QuizHistory{" +
                "quizId=1, username='user1', quizScore=0, startTime=null, endTime=null, elapsedTime=0}";
        assertEquals(expectedToString, quizHistory.toString(), "toString method should produce the expected string");
    }
}
