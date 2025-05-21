package Model;

import java.util.Random;

public class QuestionFactory {
    private static boolean mySA = false;
    private static boolean myMC = false;
    private static boolean myTF = false;
    private QuestionFactory() {
    }
    public static AbstractQuestion getQuestion() {
        GameSetup gameSetup = new GameSetup();
        Random rand = new Random();
        boolean chosen = false;
        while(!chosen) {
            int choice = rand.nextInt(3);
            if (choice == 0) {
                if (mySA) {

                    String query = "SELECT * FROM questionSA ORDER BY RANDOM() LIMIT  num_records_to_return;";
                    return gameSetup.getMyShortAnswerQuestionList().get(rand.nextInt(gameSetup.getMyShortAnswerQuestionList().size()));
                }
            } else if (choice == 1) {
                if (myMC) {
                    chosen = true;
                    return gameSetup.getMyMCQuestionList().get(rand.nextInt(gameSetup.getMyMCQuestionList().size()));
                }
            } else if (choice == 2) {
                if (myTF) {
                    chosen = true;
                    return gameSetup.getMyTFQuestionList().get(rand.nextInt(gameSetup.getMyTFQuestionList().size()));
                }
            }
        }
        return null;
    }
    public static void setMySA(boolean mySA) {
        QuestionFactory.mySA = mySA;
    }
    public static void setMyMC(boolean myMC) {
        QuestionFactory.myMC = myMC;
    }
    public static void setMyTF(boolean myTF) {
        QuestionFactory.myTF = myTF;
    }

}
