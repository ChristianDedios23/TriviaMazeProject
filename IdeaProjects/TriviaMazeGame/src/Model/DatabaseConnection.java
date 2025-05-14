package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseConnection {
    private static SQLiteDataSource myDS;
    private static final String DB_URL = "jdbc:sqlite:questions.db";

    private DatabaseConnection(){
        try {
            myDS = new SQLiteDataSource();
            myDS.setUrl(DB_URL);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static Connection getConnection(){
        try{
           return myDS.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
