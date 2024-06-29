package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a user account with basic information such as user ID, username, first name, last name,
 * password, email, and profile image URL.
 */

public class Account {
    private String salt;
    private final int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String imageUrl;
    private List<Integer> friends;

    /**
     * Constructs a new Account object with the specified user details.
     *
     * @param userId    the unique identifier for the user
     * @param userName  the username chosen by the user
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param password  the user's password
     * @param email     the user's email address
     * @param imageUrl  the URL of the user's profile image
     */
    public Account(int userId, String userName, String firstName, String lastName,
                   String password, String email, String imageUrl, String salt) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.friends = new ArrayList<>();
        this.salt = salt;
    }


    /**
     * Constructs a new Account object with the specified user details.
     *
     * @param userName  the username chosen by the user
     * @param firstName the user's first name
     * @param lastName  the user's last name
     * @param password  the user's password
     * @param email     the user's email address
     * @param imageUrl  the URL of the user's profile image
     */
    public Account(String userName, String firstName, String lastName,
                   String password, String email, String imageUrl, String salt) {
        this.userId = -1;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.friends = new ArrayList<>();
        this.salt = salt;
    }


    /**
     * Retrieves the user ID of the account.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Retrieves the username of the account.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the username of the account.
     *
     * @param userName the new username to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Retrieves the first name of the account holder.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the account holder.
     *
     * @param firstName the new first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the account holder.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the account holder.
     *
     * @param lastName the new last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the password of the account.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the account.
     *
     * @param password the new password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the email address associated with the account.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address associated with the account.
     *
     * @param email the new email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the URL of the profile image associated with the account.
     *
     * @return the URL of the profile image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the URL of the profile image associated with the account.
     *
     * @param imageUrl the new URL of the profile image to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Integer> getFriends() {
        return Collections.unmodifiableList(friends);
    }

    public void setFriends(List<Integer> friends) {
        this.friends = friends;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


}
