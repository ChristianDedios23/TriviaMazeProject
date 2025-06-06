package Tests;
import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import Model.QuestionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

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

    }
    @Test
    /**
     * Tests 100 random questions to see if they are made.
     */
    public void testCreateQuestion() {
        AbstractQuestion question = QuestionFactory.getQuestion(
                new HashSet<>(List.of(QuestionType.MULTIPLE_CHOICE, QuestionType.TRUE_OR_FALSE, QuestionType.SHORT_ANSWER)));
        for(int i=0; i<100; i++) {
            assertNotNull(question);
            question = QuestionFactory.getQuestion(new HashSet<>(List.of(QuestionType.MULTIPLE_CHOICE, QuestionType.TRUE_OR_FALSE, QuestionType.SHORT_ANSWER)));
        }
    }

    @Test
    /**
     * Tests to see if when all questions of a certain type are asked, it reshuffles the questions.
     */
    public void testShuffleQuestion() {

        AbstractQuestion question = QuestionFactory.getQuestion(new HashSet<>(List.of( QuestionType.TRUE_OR_FALSE, QuestionType.SHORT_ANSWER)));
        for(int i=0; i<100; i++) {
            assertNotNull(question);
            question = QuestionFactory.getQuestion(new HashSet<>(List.of( QuestionType.TRUE_OR_FALSE, QuestionType.SHORT_ANSWER)));
        }
    }
}
