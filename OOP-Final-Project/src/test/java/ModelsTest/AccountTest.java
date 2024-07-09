package ModelsTest;

import Models.Account;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() {
        account = new Account(
                "johndoe",
                "John",
                "Doe",
                "password123",
                "john.doe@example.com",
                "http://example.com/profile.jpg",
                "randomSalt"
        );
    }

    @Test
    public void testGetUserName() {
        assertEquals("johndoe", account.getUserName());
    }

    @Test
    public void testSetUserName() {
        account.setUserName("janedoe");
        assertEquals("janedoe", account.getUserName());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", account.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        account.setFirstName("Jane");
        assertEquals("Jane", account.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", account.getLastName());
    }

    @Test
    public void testSetLastName() {
        account.setLastName("Smith");
        assertEquals("Smith", account.getLastName());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password123", account.getPassword());
    }

    @Test
    public void testSetPassword() {
        account.setPassword("newpassword");
        assertEquals("newpassword", account.getPassword());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", account.getEmail());
    }

    @Test
    public void testSetEmail() {
        account.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", account.getEmail());
    }

    @Test
    public void testGetImageUrl() {
        assertEquals("http://example.com/profile.jpg", account.getImageUrl());
    }

    @Test
    public void testSetImageUrl() {
        account.setImageUrl("http://example.com/new_profile.jpg");
        assertEquals("http://example.com/new_profile.jpg", account.getImageUrl());
    }

    @Test
    public void testGetSalt() {
        assertEquals("randomSalt", account.getSalt());
    }

    @Test
    public void testSetSalt() {
        account.setSalt("newSalt");
        assertEquals("newSalt", account.getSalt());
    }

    @Test
    public void testGetFriends() {
        List<String> friends = Arrays.asList("alice", "bob");
        account.setFriends(friends);
        assertEquals(friends, account.getFriends());
    }

    @Test
    public void testSetFriends() {
        List<String> friends = Arrays.asList("charlie", "david");
        account.setFriends(friends);
        assertEquals(friends, account.getFriends());
    }

    @Test
    public void testGetAchievementIds() {
        List<Integer> achievements = Arrays.asList(1, 2, 3);
        account.setAchievementIds(achievements);
        assertEquals(achievements, account.getAchievementIds());
    }

    @Test
    public void testSetAchievementIds() {
        List<Integer> achievements = Arrays.asList(4, 5, 6);
        account.setAchievementIds(achievements);
        assertEquals(achievements, account.getAchievementIds());
    }

    @Test
    public void testToString() {
        String expected = "Account{" +
                ", userName='johndoe'" +
                ", firstName='John'" +
                ", lastName='Doe'" +
                ", email='john.doe@example.com'" +
                ", imageUrl='http://example.com/profile.jpg'" +
                ", friends=[]" +
                ", salt=randomSalt" +
                ", password=password123" +
                '}';
        assertEquals(expected, account.toString());
    }
}
