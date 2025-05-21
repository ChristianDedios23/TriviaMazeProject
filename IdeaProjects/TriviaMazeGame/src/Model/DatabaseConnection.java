package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Utility class for connecting to the DB
 */
public class DatabaseConnection {
    /** Data source for the class*/
    private static final SQLiteDataSource myDS =  new SQLiteDataSource();
    /** URL for the SQLite*/
    private static final String DB_URL = "jdbc:sqlite:questions.db";

    //On first use, connects the database source to SQLite
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
    /**Dummy Constructor*/
    private DatabaseConnection(){}

    /**
     * @return gets connection to the DB source
     */
    public static Connection getConnection(){
        try{
            return myDS.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}