package Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for hashing passwords and generating salts using SHA-256.
 */
public class PasswordHasher {

    /**
     * Generates a random salt.
     *
     * @return a Base64 encoded string representation of the generated salt.
     */
    public static String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[10];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Hashes a password using the provided salt and SHA-256 algorithm.
     *
     * @param password the password to hash.
     * @param salt     the salt to use for hashing.
     * @return a Base64 encoded string representation of the hashed password.
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available.
     */
    private static String hash(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        byte[] hashed = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashed);
    }

    /**
     * Generates a hashed password with a new salt.
     *
     * @param password the password to hash.
     * @return the hashed password with the generated salt.
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available.
     */
    public String hashedPassword(String password) throws NoSuchAlgorithmException {
        String salt = generateSalt();
        return hash(password, salt);
    }

    /**
     * Checks if the provided password matches the hashed password.
     *
     * @param newPassword   the new password to check.
     * @param hashedPassword the stored hashed password to compare against.
     * @return true if the hashed new password matches the stored hashed password, false otherwise.
     * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available.
     */
    public boolean isCorrectPassword(String newPassword, String salt,String hashedPassword) throws NoSuchAlgorithmException {
        String newHashed = hash(newPassword, salt);
        return hashedPassword.equals(newHashed);
    }

}
