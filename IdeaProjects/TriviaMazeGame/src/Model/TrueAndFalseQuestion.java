package Model;

public class TrueAndFalseQuestion extends AbstractQuestion {

    private boolean correctAnswer;

    public TrueAndFalseQuestion(String question, String answer,String theHint) {
        super(question, answer,theHint);
        correctAnswer = answer.equals("true");
    }

    @Override
    public String getType() {
        return "TRUE_OR_FALSE";
    }
}
