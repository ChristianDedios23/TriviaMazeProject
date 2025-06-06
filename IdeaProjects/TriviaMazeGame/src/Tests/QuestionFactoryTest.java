package Tests;
import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import Model.QuestionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test for question factory questions.
 */
public class QuestionFactoryTest {

    @BeforeAll
    /**
     * Sets up the questionFactory to contain all types of questions.
     */
    static void setUpBeforeClass() throws Exception {
        QuestionFactory.setupQuestions();
        if(!QuestionFactory.checkQuestionTypes().contains(QuestionType.SHORT_ANSWER)) {
            QuestionFactory.editMyQuestionTypeSet(QuestionType.SHORT_ANSWER);
        }
        if(!QuestionFactory.checkQuestionTypes().contains(QuestionType.TRUE_OR_FALSE)) {
            QuestionFactory.editMyQuestionTypeSet(QuestionType.TRUE_OR_FALSE);
        }
        if(!QuestionFactory.checkQuestionTypes().contains(QuestionType.MULTIPLE_CHOICE)) {
            QuestionFactory.editMyQuestionTypeSet(QuestionType.MULTIPLE_CHOICE);
        }
    }
    @Test
    /**
     * Tests 100 random questions to see if they are made.
     */
    public void testCreateQuestion() {
        AbstractQuestion question = QuestionFactory.getQuestion();
        for(int i=0; i<100; i++) {
            assertNotNull(question);
            question = QuestionFactory.getQuestion();
        }
    }

    @Test
    /**
     * Tests to see if when all questions of a certain type are asked, it reshuffles the questions.
     */
    public void testShuffleQuestion() {
        QuestionFactory.editMyQuestionTypeSet(QuestionType.SHORT_ANSWER);
        QuestionFactory.editMyQuestionTypeSet(QuestionType.TRUE_OR_FALSE);
        AbstractQuestion question = QuestionFactory.getQuestion();
        for(int i=0; i<100; i++) {
            assertNotNull(question);
            question = QuestionFactory.getQuestion();
        }
    }
}
