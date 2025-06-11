package Model;


import Model.Enum.QuestionType;

import java.io.Serial;
import java.io.Serializable;

/**
 * Abstract class that represents 3 types of questions and holds the question, answer, and hint.
 */
public abstract class AbstractQuestion implements Serializable {
    /** UID for Serialization*/
    @Serial
    private static final long serialVersionUID = 99L;
    /**
     * the question.
     */
    protected String myQuestion;
    /**
     * the answer.
     */
    protected String myAnswer;
    /**
     * the hint.
     */
    protected String myHint;

    /**
     * constructor initializes the questions components.
     * @param theQuestion the question.
     * @param theAnswer the answer.
     * @param theHint the hint.
     */
    public AbstractQuestion(final String theQuestion, final String theAnswer, final String theHint) {
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myHint = theHint;
    }

    /**
     * Getter for the question.
     * @return the question.
     */
    public String getQuestion(){
        return myQuestion;
    }

    /**
     * Getter for the hint.
     * @return the hint.
     */
    public String getHint(){
        return myHint;
    }




    /**
     * Getter for the question type.
     * @return the question type.
     */

    public abstract QuestionType getType();

    /**
     * Checks the answer given with the correct answer.
     * @param theAnswer the answer the user gave.
     * @return True if correct, False if not.
     */
    public boolean checkAnswer(final String theAnswer){

        return myAnswer.equalsIgnoreCase(theAnswer);
    }

}
