package Model;

import Model.Enum.QuestionType;

class ShortAnswerQuestion extends AbstractQuestion {



    public ShortAnswerQuestion(final String theQuestion, final String theAnswer, final String theHint) {
        super(theQuestion, theAnswer, theHint);
    }

    @Override

    public QuestionType getType() {
        return QuestionType.SHORT_ANSWER;
    }

}




