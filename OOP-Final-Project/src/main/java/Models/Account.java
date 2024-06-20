package Models;

public class Account {
    private final int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String imageUrl;
    public Account(int userId, String userName, String firstName, String lastName, String password, String email, String imageUrl) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
    }

}
