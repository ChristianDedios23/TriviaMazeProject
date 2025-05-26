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
        myQuestionTypeSet.add(SHORT_ANSWER);
        myQuestionTypeSet.add(MULTIPLE_CHOICE);
        myQuestionTypeSet.add(TRUE_OR_FALSE);

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

























        /*
        while (!chosen) {


            int choice = rand.nextInt(3);
            if (choice == 0) {

            } else if (choice == 1) {
                String query = "SELECT * FROM questionsMC ORDER BY RANDOM() LIMIT 1;";
                        AbstractQuestion mcquestion = null;
                        try {
                            Connection connection = DatabaseConnection.getConnection();
                            Statement statement = connection.createStatement();

                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()) {
                                String question = rs.getString("question");
                                HashMap<Character, String> map = new HashMap<>();
                                map.put('A', rs.getString("option_a"));
                                map.put('B', rs.getString("option_b"));
                                map.put('C', rs.getString("option_c"));
                                map.put('D', rs.getString("option_d"));
                                String answer = rs.getString("answer");
                                String hint = rs.getString("hint");
                                mcquestion = new MultipleChoiceQuestion(question, map, answer, hint);
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        return mcquestion;

                } else if (choice == 2) {



                        String query = "SELECT * FROM questionsTOF ORDER BY RANDOM() LIMIT 1;";
                        AbstractQuestion tfquestion = null;
                        try {
                            Connection connection = DatabaseConnection.getConnection();
                            Statement statement = connection.createStatement();

                            ResultSet rs = statement.executeQuery(query);
                            while (rs.next()) {
                                String question = rs.getString("question");
                                String answer = rs.getString("answer");
                                String hint = rs.getString("hint");
                                tfquestion = new TrueAndFalseQuestion(question, answer, hint);
                            }
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        return tfquestion;
                }
            }
        return null;\

         */
    }

    public static void editMyQuestionTypeSet(QuestionType questionType) {
        if (myQuestionTypeSet.contains(questionType)) {
            myQuestionTypeSet.remove(questionType);
        } else {
            myQuestionTypeSet.add(questionType);
        }

    }
}
