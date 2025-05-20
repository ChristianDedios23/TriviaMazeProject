package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseConnection {
    private static final SQLiteDataSource myDS =  new SQLiteDataSource();
    private static final String DB_URL = "jdbc:sqlite:questions.db";

    static {
        try {
            myDS.setUrl(DB_URL);
            System.out.println("SQLite database connected.");
        } catch (Exception e) {
            System.err.println("ailed to connect to SQLite database.");
            e.printStackTrace();
            System.exit(1);
        }
    }
    private DatabaseConnection(){

    }
    public static Connection getConnection(){
        try{
           return myDS.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
