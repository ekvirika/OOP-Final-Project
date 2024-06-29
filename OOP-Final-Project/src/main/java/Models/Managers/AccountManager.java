package Models.Managers;

import DAO.AccountDAO;
import Models.Account;
import Models.PasswordHasher;

import java.security.NoSuchAlgorithmException;

/**
 * The AccountManager class manages user accounts, allowing for the creation of new users and
 * verification of login credentials.
 */
public class AccountManager {
    public static final String ATTRIBUTE_NAME = "AccountManager";
    AccountDAO accountDAO;

    /**
     * Constructs an AccountManager with a predefined set of user accounts.
     */
    public AccountManager() {
        accountDAO = new AccountDAO(SQLConnector.dataSource);
    }

    /**
     * Creates a new user account if the username does not already exist.
     *
     * @param account new account
     * @return true if the account was successfully created, false if the username already exists
     */
    public boolean createNewUser(Account account) {
        accountDAO.createAccount(account);
        return true;
    }

    /**
     * Checks if an account with the given username exists.
     *
     * @param username the username to check for existence
     * @return true if the account exists, false otherwise
     */
    public boolean accountExists(String username) {
        return accountDAO.readAccount(username) != null;
    }

    /**
     * Validates the login credentials for a given username and password.
     *
     * @param username the username of the account
     * @param password the password of the account
     * @return true if the username exists and the password matches, false otherwise
     */
    public boolean successfulLogin(String username, String password) throws NoSuchAlgorithmException {
        Account account = accountDAO.readAccount(username);
        String newPassword = PasswordHasher.hash(password, account.getSalt());
        return PasswordHasher.isCorrectPassword(newPassword, account.getSalt(), account.getPassword());
    }
}
