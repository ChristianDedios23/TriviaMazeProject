package Model;

import Model.Enum.QuestionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static Model.Enum.QuestionType.*;

/**
 * Question factory method to get questions.
 */
public class QuestionFactory {
    /**
     * Index tracker to keep track of the current question index.
     */
    private static int mySACurrentIndex = 0;
    /**
     * Index tracker to keep track of the current question index.
     */
    private static int myMCCurrentIndex = 0;
    /**
     * Index tracker to keep track of the current question index.
     */
    private static int myTFCurrentIndex = 0;

    /**
     * List to store true and false questions.
     */
    private static List<TrueAndFalseQuestion> myTFQuestionList = new ArrayList<>();
    /**
     * List to store multiple choice questions.
     */
    private static List<MultipleChoiceQuestion> myMCQuestionList = new ArrayList<>();
    /**
     * List to store short answer questions.
     */
    private static List<ShortAnswerQuestion> myShortAnswerQuestionList = new ArrayList<>();

    private QuestionFactory() {
    }

    /**
     * Method to setup and initialize the question lists from the database.
     */
    public static void setupQuestions() {

        String queryTF = "SELECT * FROM TRUE_OR_FALSE";
        String queryMC = "SELECT * FROM MULTIPLE_CHOICE";
        String querySA = "SELECT * FROM SHORT_ANSWER";

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(queryTF);
            while (rs.next()) {
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                String hint = rs.getString("hint");
                myTFQuestionList.add(new TrueAndFalseQuestion(question,answer,hint));
            }
            rs = statement.executeQuery(queryMC);
            while (rs.next()) {
                String question = rs.getString("question");
                HashMap<Character,String> map = new HashMap<>();
                map.put('A',rs.getString("option_a"));
                map.put('B',rs.getString("option_b"));
                map.put('C',rs.getString("option_c"));
                map.put('D',rs.getString("option_d"));
                String answer = rs.getString("answer");
                String hint = rs.getString("hint");
                myMCQuestionList.add(new MultipleChoiceQuestion(question,map,answer,hint));
            }
            rs = statement.executeQuery(querySA);
            while (rs.next()) {
                String question = rs.getString("question");
                String answer = rs.getString("answer");
                String hint = rs.getString("hint");
                myShortAnswerQuestionList.add(new ShortAnswerQuestion(question,answer,hint));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method gets a random question from the lists depending on what question types are available
     * in the question type set and returns it.
     * @return question A random question based on the settings of the game.
     */
    public static AbstractQuestion getQuestion(final Set<QuestionType> theQuestionTypes) {
        Random rand = new Random();
        String type = theQuestionTypes.toArray()[rand.nextInt(theQuestionTypes.size())].toString();
        AbstractQuestion question = null;
        if(type.equals("MULTIPLE_CHOICE")) {
            if(myMCCurrentIndex >= myMCQuestionList.size()) {
                myMCCurrentIndex = 0;
                Collections.shuffle(myMCQuestionList);
            }
            question = myMCQuestionList.get(myMCCurrentIndex);
            myMCCurrentIndex++;
        }
        else if(type.equals("TRUE_OR_FALSE")) {
            if(myTFCurrentIndex >= myTFQuestionList.size()) {
                myTFCurrentIndex = 0;
                Collections.shuffle(myTFQuestionList);
            }
            question = myTFQuestionList.get(myTFCurrentIndex);
            myTFCurrentIndex++;
        }
        else {
            if(mySACurrentIndex >= myShortAnswerQuestionList.size()) {
                mySACurrentIndex = 0;
                Collections.shuffle(myShortAnswerQuestionList);
            }
            question = myShortAnswerQuestionList.get(mySACurrentIndex);
            mySACurrentIndex++;
        }
        return question;
    }



    /**
     * Shuffles the list after the questions have all been asked.
     * @param theQuestionType the question type to be added or removed
     */
    static void shuffleList(final QuestionType theQuestionType) {
        if(theQuestionType == SHORT_ANSWER) {
            Collections.shuffle(myShortAnswerQuestionList);
        }
        else if(theQuestionType == MULTIPLE_CHOICE) {
            Collections.shuffle(myMCQuestionList);
        }
        else if(theQuestionType == TRUE_OR_FALSE) {
            Collections.shuffle(myTFQuestionList);
        }
    }

}
