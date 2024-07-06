package ModelsTest;

import Models.Achievement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AchievementTest {

    private Achievement achievement;

    @Before
    public void setUp() {
        achievement = new Achievement(
                1,
                "First Steps",
                "http://example.com/achievements/first_steps",
                "Awarded for taking the first steps."
        );
    }

    @Test
    public void testGetAchievementId() {
        assertEquals(1, achievement.getAchievementId());
    }

    @Test
    public void testSetAchievementId() {
        achievement.setAchievementId(2);
        assertEquals(2, achievement.getAchievementId());
    }

    @Test
    public void testGetAchievementName() {
        assertEquals("First Steps", achievement.getAchievementName());
    }

    @Test
    public void testSetAchievementName() {
        achievement.setAchievementName("Next Level");
        assertEquals("Next Level", achievement.getAchievementName());
    }

    @Test
    public void testGetAchievementUrl() {
        assertEquals("http://example.com/achievements/first_steps", achievement.getAchievementUrl());
    }

    @Test
    public void testSetAchievementUrl() {
        achievement.setAchievementUrl("http://example.com/achievements/next_level");
        assertEquals("http://example.com/achievements/next_level", achievement.getAchievementUrl());
    }

    @Test
    public void testGetAchievementDescription() {
        assertEquals("Awarded for taking the first steps.", achievement.getAchievementDescription());
    }

    @Test
    public void testSetAchievementDescription() {
        achievement.setAchievementDescription("Awarded for reaching the next level.");
        assertEquals("Awarded for reaching the next level.", achievement.getAchievementDescription());
    }
}
