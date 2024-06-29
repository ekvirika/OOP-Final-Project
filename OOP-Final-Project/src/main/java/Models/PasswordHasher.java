package Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {

    public static  String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[10];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private static  String hash(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt.getBytes());
        byte[] hashed = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashed);
    }

    public  String hashedPassword(String password) throws NoSuchAlgorithmException {
        String salt = generateSalt();
        return hash(password, salt);
    }

    public boolean isCorrectPassword(String newPassword, String hashedPassword) throws NoSuchAlgorithmException {
        String newHashed = hash(newPassword, generateSalt());
        return hashedPassword.equals(newHashed);
    }

}
