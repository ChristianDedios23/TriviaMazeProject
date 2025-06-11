package Model;

import Model.Enum.QuestionType;


/**
 * True or false question.
 */
public class TrueAndFalseQuestion extends AbstractQuestion {


    /**
     * Constructor initializes the components of the question.
     * @param theQuestion the question.
     * @param theAnswer the correct answer.
     * @param theHint the hint.
     */
    public TrueAndFalseQuestion(final String theQuestion, final String theAnswer, final String theHint) {
        super(theQuestion, theAnswer, theHint);
    }

    @Override
    public QuestionType getType() {
        return QuestionType.TRUE_OR_FALSE;
    }
    


}
