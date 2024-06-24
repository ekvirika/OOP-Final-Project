package DAO;

import Models.Account;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for the Account model.
 * Provides methods to perform CRUD operations on the Account table.
 */
public class AccountDAO {
    private final DataSource dataSource;

    /**
     * Constructs an AccountDAO with the specified DataSource.
     *
     * @param dataSource the DataSource to be used for database connections.
     */
    public AccountDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new account in the database.
     *
     * @param account the Account object containing the account details.
     */
    public void createAccount(Account account) {
        String query = "INSERT INTO Accounts (userName, firstName, lastName, password, email, imageUrl) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatement(account, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the parameters of a PreparedStatement using the provided Account object.
     *
     * @param account   the Account object containing the account details.
     * @param statement the PreparedStatement to be set with account details.
     * @throws SQLException if a database access error occurs.
     */
    private void setStatement(Account account, PreparedStatement statement) throws SQLException {
        statement.setString(1, account.getUserName());
        statement.setString(2, account.getFirstName());
        statement.setString(3, account.getLastName());
        statement.setString(4, account.getPassword());
        statement.setString(5, account.getEmail());
        statement.setString(6, account.getImageUrl());
    }

    /**
     * Reads an account from the database by its userId.
     *
     * @param userId the ID of the account to be read.
     * @return the Account object containing the account details, or null if no account is found.
     */
    public Account readAccount(int userId) {
        String query = "SELECT * FROM Accounts WHERE userId = ?";
        Account account = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    account = new Account(
                            resultSet.getInt("userId"),
                            resultSet.getString("userName"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getString("imageUrl")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    /**
     * Updates an existing account in the database.
     *
     * @param account the Account object containing the updated account details.
     */
    public void updateAccount(Account account) {
        String query = "UPDATE Accounts SET userName = ?, firstName = ?, lastName = ?, password = ?, email = ?, imageUrl = ? WHERE userId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatement(account, statement);
            statement.setInt(7, account.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an account from the database by its userId.
     *
     * @param userId the ID of the account to be deleted.
     */
    public void deleteAccount(int userId) {
        String query = "DELETE FROM Accounts WHERE userId = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
