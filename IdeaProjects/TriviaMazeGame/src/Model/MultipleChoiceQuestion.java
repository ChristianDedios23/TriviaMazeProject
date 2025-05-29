package Model;


import Model.Enum.QuestionType;



import java.util.HashMap;



public class MultipleChoiceQuestion extends AbstractQuestion {
    private final HashMap<Character, String> myOptionList;

    public MultipleChoiceQuestion(final String theQuestion, final HashMap<Character, String> theOptionList,
                                  final String theAnswer, final String theHint) {
        super(theQuestion, theAnswer, theHint);
        myOptionList = theOptionList;

    }



    @Override

    public QuestionType getType() {
        return QuestionType.MULTIPLE_CHOICE;
    }


    public HashMap<Character, String> getOptionList() {
        return myOptionList;
    }
}
