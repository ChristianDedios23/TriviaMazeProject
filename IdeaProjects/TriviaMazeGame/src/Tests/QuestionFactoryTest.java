package Tests;
import Model.AbstractQuestion;
import Model.QuestionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionFactoryTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        QuestionFactory.setupQuestions();
    }
    @Test
    public void testCreateQuestion() {
        AbstractQuestion question = QuestionFactory.getQuestion2();
        int check = 1;
        while(check != 3) {
           // if()
        }
        assertNotNull(question);
    }



}
