package utilsTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.SQLConnector;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SQLConnectorTest {
    private SQLConnector sqlConnector;

    @BeforeEach
    public void setUp() {
        sqlConnector = new SQLConnector();
    }

    @AfterEach
    public void tearDown() {
        // Closing all connections and the datasource
        try {
            SQLConnector.dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDataSourceInitialization() {
        assertNotNull(SQLConnector.dataSource, "DataSource should be initialized");
        assertEquals("jdbc:mysql://localhost:3306/OOP_QUIZ", SQLConnector.dataSource.getUrl(), "URL should match");
        assertEquals("root", SQLConnector.dataSource.getUsername(), "Username should match");
        assertEquals("1234", SQLConnector.dataSource.getPassword(), "Password should match");
    }

    @Test
    public void testGetConnection() throws SQLException {
        try (Connection connection = SQLConnector.getConnection()) {
            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");
        }
    }

    @Test
    public void testMultipleConnections() throws SQLException {
        Connection connection1 = null;
        Connection connection2 = null;

        try {
            connection1 = SQLConnector.getConnection();
            connection2 = SQLConnector.getConnection();
            assertNotNull(connection1, "First connection should not be null");
            assertNotNull(connection2, "Second connection should not be null");
            assertNotSame(connection1, connection2, "Connections should be different");
        } finally {
            if (connection1 != null) connection1.close();
            if (connection2 != null) connection2.close();
        }
    }
}
