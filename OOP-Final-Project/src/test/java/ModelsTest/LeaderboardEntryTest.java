package ModelsTest;

import Models.LeaderboardEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardEntryTest {

    private LeaderboardEntry entry;

    @BeforeEach
    public void setup() {
        entry = new LeaderboardEntry("player1", 100, 5000);
    }

    @Test
    public void testConstructorAndGetters() {
        // Test getters
        assertEquals("player1", entry.getUsername(), "Username should be 'player1'");
        assertEquals(100, entry.getScore(), "Score should be 100");
        assertEquals(5000, entry.getElapsedTime(), "Elapsed time should be 5000 milliseconds");
    }

    @Test
    public void testSetters() {
        // Test setters
        entry.setUsername("player2");
        entry.setScore(150);
        entry.setElapsedTime(6000);

        assertEquals("player2", entry.getUsername(), "Username should be 'player2' after setter");
        assertEquals(150, entry.getScore(), "Score should be 150 after setter");
        assertEquals(6000, entry.getElapsedTime(), "Elapsed time should be 6000 milliseconds after setter");
    }

    @Test
    public void testToString() {
        // Test toString method
        String expectedToString = "LeaderboardEntry{username='player1', score=100, elapsedTime=5000}";
        assertEquals(expectedToString, entry.toString(), "toString method should produce the expected string");
    }
}
