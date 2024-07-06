package ModelsTest;

import Models.PasswordHasher;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordHasherTest {

    @Test
    public void testGenerateSalt() {
        String salt1 = PasswordHasher.generateSalt();
        String salt2 = PasswordHasher.generateSalt();

        assertNotNull(salt1, "Generated salt should not be null");
        assertNotNull(salt2, "Generated salt should not be null");
        assertNotEquals(salt1, salt2, "Generated salts should be different");
    }

    @Test
    public void testHashAndVerifyPassword() throws NoSuchAlgorithmException {
        String password = "Password123!";
        String salt = PasswordHasher.generateSalt();

        String hashedPassword = PasswordHasher.hash(password, salt);

        assertNotNull(hashedPassword, "Hashed password should not be null");

        assertTrue(PasswordHasher.isCorrectPassword(password, salt, hashedPassword),
                "Password should match hashed password");

        String wrongPassword = "WrongPassword456!";
        assertFalse(PasswordHasher.isCorrectPassword(wrongPassword, salt, hashedPassword),
                "Incorrect password should not match hashed password");
    }
}
