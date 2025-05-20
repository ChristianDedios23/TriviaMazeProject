package Model;

import Model.Enum.QuestionType;

public abstract class AbstractQuestion {
    protected String myQuestion;
    protected String myAnswer;
    protected String myHint;

    public AbstractQuestion(final String theQuestion, final String theAnswer, final String theHint) {
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myHint = theHint;
    }

    public String getQuestion(){
        return myQuestion;
    }
    public String getHint(){
        return myHint;
    }
    public abstract QuestionType getType();
    public boolean checkAnswer(final String theAnswer){
        return myAnswer.equalsIgnoreCase(theAnswer);
    }

}
