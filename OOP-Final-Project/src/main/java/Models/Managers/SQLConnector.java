package Models.Managers;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/OOP_QUIZ";
    private final static String USERNAME = "root";

    private final static String PASSWORD = "1234";

    public static BasicDataSource dataSource;

    public SQLConnector(){
        dataSource = new BasicDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
