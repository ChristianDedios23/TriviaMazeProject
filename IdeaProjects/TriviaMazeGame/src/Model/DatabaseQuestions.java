package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseQuestions {
    private SQLiteDataSource myDS;
    private DatabaseQuestions(){
        try {
            myDS = new SQLiteDataSource();
            myDS.setUrl("jdbc:sqlite:questions.db");
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Successfully connected to DB");

        String multipleChoiceTable = new StringBuilder().append("CREATE TABLE IF NOT EXISTS questionsMP ( ")
                .append("question TEXT NOT NULL, ")
                .append("option_a TEXT NOT NULL )")
                .append("option_b TEXT NOT NULL )")
                .append("option_c TEXT NOT NULL )")
                .append("option_d TEXT NOT NULL )")
                .append("correct_answer TEXT NOT NULL )")
                .append("PRIMARY KEY question;")
                .toString();
        try (Connection conn = myDS.getConnection();
             Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( multipleChoiceTable );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created questions table successfully" );
    }

}
