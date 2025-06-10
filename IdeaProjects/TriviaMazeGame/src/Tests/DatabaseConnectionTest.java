package Tests;

import Model.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for DatabaseConnection class
 */
class DatabaseConnectionTest {
    /**
     * Tests if the connection is not null
     */
    @Test
    void testConnection() {
        Connection connection = null;
        try{
            connection = DatabaseConnection.getConnection();
        }catch(Exception e){
            fail("Failed to connect to DB");
        }
        assertNotNull(connection);
    }
}