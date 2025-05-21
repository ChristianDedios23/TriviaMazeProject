package Model;

public abstract class AbstractQuestion {
    protected String myQuestion;
    protected String myAnswer;
    protected String myHint;
    public AbstractQuestion(final String theQuestion, final String theAnswer, final String theHint) {
        this.myQuestion = theQuestion;
        this.myAnswer = theAnswer;
        this.myHint = theHint;
    }
    public String getQuestion() {
        return myQuestion;
    }
    public String getHint() {
        return myHint;
    }

    public abstract String getType();

    public boolean checkAnswer(final String theAnswer) {
        return myAnswer.equalsIgnoreCase(theAnswer);
    }

}
