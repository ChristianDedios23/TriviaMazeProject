package Model;


import Model.Enum.QuestionType;



import java.util.HashMap;


/**
 * Multiple choices question.
 */
public class MultipleChoiceQuestion extends AbstractQuestion {
    /**
     * Map to hold the options for the multiple choice question.
     */
    private final HashMap<Character, String> myOptionList;

    /**
     * Constructor initializes the components of the question.
     * @param theQuestion the question.
     * @param theOptionList the list of options to choose from.
     * @param theAnswer the correct answer.
     * @param theHint the hint.
     */
    public MultipleChoiceQuestion(final String theQuestion, final HashMap<Character, String> theOptionList,
                                  final String theAnswer, final String theHint) {
        super(theQuestion, theAnswer, theHint);
        myOptionList = theOptionList;

    }



    @Override
    /**
     * Getter for the question type.
     * @returns enum value MULTIPLE_CHOICE.
     */
    public QuestionType getType() {
        return QuestionType.MULTIPLE_CHOICE;
    }

    /**
     * Getter for the options.
     * @return myOptionList the list of options.
     */
    public HashMap<Character, String> getOptionList() {
        return myOptionList;
    }
}
