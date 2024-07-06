package DAO;

import Models.Account;
import Models.Quiz;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.dbcp2.BasicDataSource;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for the Account model.
 * Provides methods to perform CRUD operations on the Account table.
 */
public class AccountDAO {
    private final BasicDataSource dataSource;

    /**
     * Constructs an AccountDAO with the specified DataSource.
     *
     * @param dataSource the DataSource to be used for database connections.
     */
    public AccountDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new account in the database.
     *
     * @param account the Account object containing the account details.
     */
    public void createAccount(Account account) {
        String query = "INSERT INTO Accounts (username, firstName, lastName, password, email, imageUrl, salt) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            setStatement(account, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void setStatement(Account account, PreparedStatement statement) throws SQLException {
        statement.setString(1, account.getUserName());
        statement.setString(2, account.getFirstName());
        statement.setString(3, account.getLastName());
        statement.setString(4, account.getPassword());
        statement.setString(5, account.getEmail());
        statement.setString(6, account.getImageUrl());
        statement.setString(7, account.getSalt());
    }


    public Account readAccount(String username) {
        List<String> friends = new ArrayList<>();
        String query = "SELECT * FROM Accounts WHERE username = ?";
        String queryFriends = "SELECT usernameFrom, usernameTo FROM Friends WHERE (usernameFrom = ? OR usernameTo = ?) AND isAccepted = TRUE";
        Account account = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             PreparedStatement statementFriends = connection.prepareStatement(queryFriends)) {

            statement.setString(1, username);
            statementFriends.setString(1, username);
            statementFriends.setString(2, username);
            try (ResultSet resultSet = statement.executeQuery();
                 ResultSet resultSetFriends = statementFriends.executeQuery()) {
                if (resultSet.next()) {
                    account = new Account(
                            resultSet.getString("username"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                            resultSet.getString("imageUrl"),
                            resultSet.getString("salt")
                    );
                } else return null;

                while (resultSetFriends.next()) {
                    String usernameFrom = resultSetFriends.getString("usernameFrom");
                    String usernameTo = resultSetFriends.getString("usernameTo");

                    if (!usernameFrom.equals(username)) {
                        friends.add(usernameFrom);
                    } else {
                        friends.add(usernameTo);
                    }
                }
                account.setFriends(friends);

                setGson(resultSet, account);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }


    public void updateAccount(Account account) {
        String query = "UPDATE Accounts SET username = ?, firstName = ?, lastName = ?, password = ?, email = ?, imageUrl = ?, salt = ?, achievementIds = ?, QuizIds = ? WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setStatement(account, statement);
            statement.setString(10, new Gson().toJson(account.getQuizIds()));
            statement.setString(9, new Gson().toJson(account.getAchievementIds()));
            statement.setString(8, account.getUserName());
            statement.setString(7, account.getSalt());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(String username) {
        String query = "DELETE FROM Accounts WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Quiz> getAllQuizzes(String username) {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        ArrayList<Integer> quizIds = (ArrayList<Integer>) readAccount(username).getQuizIds();
        QuizDAO quizDAO = new QuizDAO(dataSource);
        for (Integer quizId : quizIds) {
            quizzes.add(quizDAO.readQuiz(quizId));
        }
        return quizzes;
    }


    public List<Account> getAllAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String query = "SELECT * FROM Accounts";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getString("username"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("imageUrl"),
                        resultSet.getString("salt")
                );

                // Deserialize JSON strings to ArrayLists
//                setGson(resultSet, account);
                accounts.add(account);
            }
        }
        return accounts;
    }

    private void setGson(ResultSet resultSet, Account account) throws SQLException {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> achievementIds = gson.fromJson(resultSet.getString("achievementIds"), listType);
        ArrayList<Integer> quizIds = gson.fromJson(resultSet.getString("quizIds"), listType);
        account.setAchievementIds(achievementIds);
        account.setQuizIds(quizIds);
    }

}
