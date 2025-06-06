package Model;

import Model.Enum.QuestionType;



public class TrueAndFalseQuestion extends AbstractQuestion {


    public TrueAndFalseQuestion(final String theQuestion, final String theAnswer, final String theHint) {
        super(theQuestion, theAnswer, theHint);
    }

    @Override
    public QuestionType getType() {
        return QuestionType.TRUE_OR_FALSE;
    }
    


}
