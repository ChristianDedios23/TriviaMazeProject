package test;

import Model.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
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