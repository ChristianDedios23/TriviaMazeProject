package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.sqlite.SQLiteDataSource;

public class GameSetup {
    // list of short questions
    // list of true and false questions
    // list of multiple choice questions
    private List<TrueAndFalseQuestion> myTFQuestionList;
    private List<MultipleChoiceQuestion> myMCQuestionList;
    private List<ShortAnswerQuestion> myShortAnswerQuestionList;
    // setup questions
    public GameSetup() {
        myTFQuestionList = new ArrayList<>();
        myMCQuestionList = new ArrayList<>();
        myShortAnswerQuestionList = new ArrayList<>();
        tandfquestions(myTFQuestionList);
        shortQuestions(myShortAnswerQuestionList);
        multipleChoiceQuestions(myMCQuestionList);
    }
    // call all setup methods
    //if tandf button is selected, return tandfquestions
    //if shortanswer button is selected return shoranswer
    //if multiplechoice button is selected return mcquestions

    private void tandfquestions(List<TrueAndFalseQuestion> theTFQuestionList) {
        // table true and false list creator
        String query = "SELECT * FROM questionsTF";
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                String hint = rs.getString("hint");
                theTFQuestionList.add(new TrueAndFalseQuestion(question,answer,hint));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //shortanswerquestions
    private void shortQuestions(List<ShortAnswerQuestion> theShortAnswerQuestionList) {
        // table of short questions list creator
        String query = "SELECT * FROM questionsSA";
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                String hint = rs.getString("hint");
                theShortAnswerQuestionList.add(new ShortAnswerQuestion(question,answer,hint));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //mcquestions
    private void multipleChoiceQuestions(List<MultipleChoiceQuestion> theMultipleChoiceQuestionList) {
        // table of multiple choice list creator
        String query = "SELECT * FROM questionsMP";
        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String question = rs.getString("question");
                HashMap<Character,String> map = new HashMap<>();
                map.put('A',rs.getString("option_a"));
                map.put('B',rs.getString("option_b"));
                map.put('C',rs.getString("option_c"));
                map.put('D',rs.getString("option_d"));
                String answer = rs.getString("correct_answer");
                String hint = rs.getString("hint");
                theMultipleChoiceQuestionList.add(new MultipleChoiceQuestion(question,map,answer,hint));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    // tandf list getter
    public List<TrueAndFalseQuestion> getMyTFQuestionList() {
        return myTFQuestionList;
    }
    //  mcquestions list getter
    public List<MultipleChoiceQuestion> getMyMCQuestionList() {
        return myMCQuestionList;
    }
    // shortanswer list getter
    public List<ShortAnswerQuestion> getMyShortAnswerQuestionList() {
        return myShortAnswerQuestionList;
    }

}
