package Model;


public class ShortAnswerQuestion extends AbstractQuestion {
    public ShortAnswerQuestion(final String theQuestion, final String theAnswer, final String theHint) {
        super(theQuestion, theAnswer, theHint);
    }

    @Override
    public String getType() {
        return "SHORT_ANSWER";
    }

}