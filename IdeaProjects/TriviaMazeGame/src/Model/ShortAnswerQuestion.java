package Model;

import Model.Enum.QuestionType;

/**
 * Short answer question.
 */
public class ShortAnswerQuestion extends AbstractQuestion {
    /**
     * Constructor initializes the components of the question.
     * @param theQuestion the question.
     * @param theAnswer the correct answer.
     * @param theHint the hint.
     */
    public ShortAnswerQuestion(final String theQuestion, final String theAnswer, final String theHint) {
        super(theQuestion, theAnswer, theHint);
    }

    /**
     * Getter for the question type.
     * @return enum value SHORT_ANSWER.
     */
    @Override
    public QuestionType getType() {
        return QuestionType.SHORT_ANSWER;
    }

}




