package Model;


import java.util.HashMap;


public class MultipleChoiceQuestion extends AbstractQuestion {
    private final HashMap<Character, String> myOptionList;
    private char correctAnswerIndex;
    public MultipleChoiceQuestion(final String theQuestion, final HashMap<Character, String> theOptionList,
                                  final String theAnswer, final String theHint) {
        super(theQuestion, theAnswer, theHint);
        myOptionList = theOptionList;
        correctAnswerIndex = getCorrectAnswerKey(theAnswer);
    }



    @Override
    public String getType() {
        return "MULTIPLE_CHOICE";
    }

    public HashMap<Character, String> getOptionList() {
        return myOptionList;
    }

    private Character getCorrectAnswerKey(String theAnswer) {
        for (Character key : myOptionList.keySet()) {
            if (myOptionList.get(key).equalsIgnoreCase(theAnswer)) {
                return key;
            }
        }
        return '\0';
    }
}