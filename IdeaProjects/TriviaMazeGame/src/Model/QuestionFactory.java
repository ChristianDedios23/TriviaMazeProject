package Model;

import Model.Enum.QuestionType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static Model.Enum.QuestionType.*;

public class QuestionFactory {

    private static int mySACurrentIndex = 0;
    private static int myMCCurrentIndex = 0;
    private static int myTFCurrentIndex = 0;

    private static List<TrueAndFalseQuestion> myTFQuestionList = new ArrayList<>();
    private static List<MultipleChoiceQuestion> myMCQuestionList = new ArrayList<>();
    private static List<ShortAnswerQuestion> myShortAnswerQuestionList = new ArrayList<>();

    private static Set<QuestionType> myQuestionTypeSet = new HashSet<QuestionType>();

    private QuestionFactory() {
    }

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
        myQuestionTypeSet.add(TRUE_OR_FALSE);

    }
    public static AbstractQuestion getQuestion2() {
        Random rand = new Random();
        String type = myQuestionTypeSet.toArray()[rand.nextInt(myQuestionTypeSet.size())].toString();
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



    public static AbstractQuestion getQuestion() {
        Random rand = new Random();
        String type = myQuestionTypeSet.toArray()[rand.nextInt(myQuestionTypeSet.size())].toString();
        String query = "SELECT * FROM " + type + " ORDER BY RANDOM() LIMIT 1;";
        AbstractQuestion returnQuestion = null;

            try {
                Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();

                ResultSet rs = statement.executeQuery(query);
                if(type.equals("MULTIPLE_CHOICE")) {
                    while (rs.next()) {
                        String question = rs.getString("question");
                        HashMap<Character, String> map = new HashMap<>();
                        map.put('A', rs.getString("option_a"));
                        map.put('B', rs.getString("option_b"));
                        map.put('C', rs.getString("option_c"));
                        map.put('D', rs.getString("option_d"));
                        String answer = rs.getString("answer");
                        String hint = rs.getString("hint");
                        returnQuestion = new MultipleChoiceQuestion(question, map, answer, hint);
                    }
                } else {
                    while (rs.next()) {
                        String question = rs.getString("question");
                        String answer = rs.getString("answer");
                        String hint = rs.getString("hint");
                        returnQuestion = type.equals("SHORT_ANSWER")
                                ? new ShortAnswerQuestion(question, answer, hint)
                                : new TrueAndFalseQuestion(question, answer, hint);


                    }
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return returnQuestion;
    }

    public static void editMyQuestionTypeSet(QuestionType questionType) {
        if (myQuestionTypeSet.contains(questionType)) {
            myQuestionTypeSet.remove(questionType);
        } else {
            myQuestionTypeSet.add(questionType);
        }
        shuffleList(questionType);
    }
    private static void shuffleList(final QuestionType questionType) {
        if(questionType == SHORT_ANSWER) {
            Collections.shuffle(myShortAnswerQuestionList);
        }
        else if(questionType == MULTIPLE_CHOICE) {
            Collections.shuffle(myMCQuestionList);
        }
        else if(questionType == TRUE_OR_FALSE) {
            Collections.shuffle(myTFQuestionList);
        }
    }
}
