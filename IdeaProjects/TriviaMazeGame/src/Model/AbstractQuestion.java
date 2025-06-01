package Model;


import Model.Enum.QuestionType;

import java.io.Serial;
import java.io.Serializable;


public abstract class AbstractQuestion implements Serializable {
    /** UID for Serialization*/
    @Serial
    private static final long serialVersionUID = 99L;
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
    //public String getMyAnswer() {return myAnswer;}
    public abstract QuestionType getType();
    public boolean checkAnswer(final String theAnswer){

        return myAnswer.equalsIgnoreCase(theAnswer);
    }

}
