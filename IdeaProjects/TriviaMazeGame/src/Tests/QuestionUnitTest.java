package Tests;



import java.util.HashMap;

import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import Model.MultipleChoiceQuestion;
import Model.TrueAndFalseQuestion;
import Model.ShortAnswerQuestion;
import com.sun.jdi.ShortType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the three question types
 * True and False, Multiple Choice, and Short answer questions.
 */
public class QuestionUnitTest {

    @Test
    /**
     * Tests the initialization of true and false questions.
     */
    public void testTFQuestionCreation() {
        AbstractQuestion question1 = new TrueAndFalseQuestion("Is the sky blue?", "true","look outside");
        AbstractQuestion question2 = new TrueAndFalseQuestion("Is grass green?", "true","look outside");
        AbstractQuestion question3 = new TrueAndFalseQuestion("2 + 2 = 5?", "false","count your fingers");

        assertNotNull(question1);
        assertNotNull(question2);
        assertNotNull(question3);
    }

    @Test
    /**
     * Tests the get question method.
     */
    public void testTFGetQuestion() {
        AbstractQuestion question1 = new TrueAndFalseQuestion("Is the sky blue?", "true","look outside");
        AbstractQuestion question2 = new TrueAndFalseQuestion("Is grass green?", "true","look outside");
        AbstractQuestion question3 = new TrueAndFalseQuestion("2 + 2 = 5?", "false","count your fingers");

        assertEquals("Is the sky blue?", question1.getQuestion());
        assertEquals("Is grass green?", question2.getQuestion());
        assertEquals("2 + 2 = 5?", question3.getQuestion());
        assertNotEquals("Is the sky green?", question1.getQuestion());
    }

    @Test
    /**
     * Tests the check answer method.
     */
    public void testTFCheckAnswer() {
        AbstractQuestion question1 = new TrueAndFalseQuestion("Is the sky blue?", "true","look outside");
        AbstractQuestion question2 = new TrueAndFalseQuestion("Is grass green?", "true","look outside");
        AbstractQuestion question3 = new TrueAndFalseQuestion("2 + 2 = 5?", "false","count your fingers");

        assertTrue(question1.checkAnswer("true"));
        assertTrue(question2.checkAnswer("true"));
        assertTrue(question3.checkAnswer("false"));
        assertFalse(question1.checkAnswer("false"));
        assertFalse(question2.checkAnswer("false"));
        assertFalse(question3.checkAnswer("true"));
    }

    @Test
    /**
     * Tests the get hint method.
     */
    public void testTFGetHint() {
        AbstractQuestion question1 = new TrueAndFalseQuestion("Is the sky blue?", "true","look outside");
        AbstractQuestion question2 = new TrueAndFalseQuestion("Is grass green?", "true","look outside");
        AbstractQuestion question3 = new TrueAndFalseQuestion("2 + 2 = 5?", "false","count your fingers");

        assertEquals("look outside", question1.getHint());
        assertEquals("look outside", question2.getHint());
        assertEquals("count your fingers", question3.getHint());
    }




    @Test
    /**
     * Tests the initialization of multiple choice questions.
     */
    public void testMultipleChoiceQuestionCreation() {
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('A',"Blue");
        map.put('B',"Red");
        map.put('C',"Yellow");
        map.put('D',"Green");
        String theQuestion1 = "What color is the sky";
        String theQuestion2 = "What color is grass";
        String theQuestion3 = "What color is the sun";
        String theQuestion4 = "What color is blood";
        AbstractQuestion question1 = new MultipleChoiceQuestion(theQuestion1,map,"Blue","look outside");
        AbstractQuestion question2 = new MultipleChoiceQuestion(theQuestion2,map,"green","look outside");
        AbstractQuestion question3 = new MultipleChoiceQuestion(theQuestion3,map,"yellow","look outside");
        AbstractQuestion question4 = new MultipleChoiceQuestion(theQuestion4,map,"red","poke yourself");

        assertNotNull(question1);
        assertNotNull(question2);
        assertNotNull(question3);
        assertNotNull(question4);

    }

    @Test
    /**
     * Tests the get question method.
     */
    public void testMCGetQuestion() {
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('A',"Blue");
        map.put('B',"Red");
        map.put('C',"Yellow");
        map.put('D',"Green");
        String theQuestion1 = "What color is the sky";
        String theQuestion2 = "What color is grass";
        String theQuestion3 = "What color is the sun";
        String theQuestion4 = "What color is blood";
        AbstractQuestion question1 = new MultipleChoiceQuestion(theQuestion1,map,"Blue","look outside");
        AbstractQuestion question2 = new MultipleChoiceQuestion(theQuestion2,map,"green","look outside");
        AbstractQuestion question3 = new MultipleChoiceQuestion(theQuestion3,map,"yellow","look outside");
        AbstractQuestion question4 = new MultipleChoiceQuestion(theQuestion4,map,"red","poke yourself");

        assertEquals(theQuestion1,question1.getQuestion());
        assertEquals(theQuestion2,question2.getQuestion());
        assertEquals(theQuestion3,question3.getQuestion());
        assertEquals(theQuestion4,question4.getQuestion());
    }

    @Test
    /**
     * Tests the check answer method.
     */
    public void testMCCheckAnswer() {
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('A',"Blue");
        map.put('B',"Red");
        map.put('C',"Yellow");
        map.put('D',"Green");
        String theQuestion1 = "What color is the sky";
        String theQuestion2 = "What color is grass";
        String theQuestion3 = "What color is the sun";
        String theQuestion4 = "What color is blood";
        AbstractQuestion question1 = new MultipleChoiceQuestion(theQuestion1,map,"Blue","look outside");
        AbstractQuestion question2 = new MultipleChoiceQuestion(theQuestion2,map,"green","look outside");
        AbstractQuestion question3 = new MultipleChoiceQuestion(theQuestion3,map,"yellow","look outside");
        AbstractQuestion question4 = new MultipleChoiceQuestion(theQuestion4,map,"red","poke yourself");

        assertTrue(question1.checkAnswer("blue"));
        assertTrue(question2.checkAnswer("green"));
        assertTrue(question3.checkAnswer("yellow"));
        assertTrue(question4.checkAnswer("red"));
        assertFalse(question1.checkAnswer("blood"));
        assertFalse(question2.checkAnswer("yellow"));
        assertFalse(question3.checkAnswer("red"));
        assertFalse(question4.checkAnswer("blue"));
    }

    @Test
    /**
     * Tests the get hint method.
     */
    public void testMCGetHint() {
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('A',"Blue");
        map.put('B',"Red");
        map.put('C',"Yellow");
        map.put('D',"Green");
        String theQuestion1 = "What color is the sky";
        String theQuestion2 = "What color is grass";
        String theQuestion3 = "What color is the sun";
        String theQuestion4 = "What color is blood";
        AbstractQuestion question1 = new MultipleChoiceQuestion(theQuestion1,map,"Blue","look outside");
        AbstractQuestion question2 = new MultipleChoiceQuestion(theQuestion2,map,"green","look outside");
        AbstractQuestion question3 = new MultipleChoiceQuestion(theQuestion3,map,"yellow","look outside");
        AbstractQuestion question4 = new MultipleChoiceQuestion(theQuestion4,map,"red","poke yourself");

        assertEquals("look outside",question1.getHint());
        assertEquals("look outside",question2.getHint());
        assertEquals("look outside",question3.getHint());
        assertEquals("poke yourself",question4.getHint());
    }

    @Test
    /**
     * Tests the initialization of short answer questions.
     */
    public void testShortAnswerQuestionCreation() {
        String theQuestion1 = "What color is the sky";
        String theQuestion2 = "What color is grass";
        String theQuestion3 = "What color is the sun";
        String theQuestion4 = "What color is blood";
        String answer1 = "blue";
        String answer2 = "green";
        String answer3 = "yellow";
        String answer4 = "red";
        AbstractQuestion question1 = new ShortAnswerQuestion(theQuestion1,answer1,"look outside");
        AbstractQuestion question2 = new ShortAnswerQuestion(theQuestion2,answer2,"look outside");
        AbstractQuestion question3 = new ShortAnswerQuestion(theQuestion3,answer3,"look outside");
        AbstractQuestion question4 = new ShortAnswerQuestion(theQuestion4,answer4,"poke yourself");


        assertNotNull(question1);
        assertNotNull(question2);
        assertNotNull(question3);
        assertNotNull(question4);
    }

    @Test
    /**
     * Tests the get question method.
     */
    public void testSAGetQuestion() {
        String theQuestion1 = "What color is the sky";
        String theQuestion2 = "What color is grass";
        String theQuestion3 = "What color is the sun";
        String theQuestion4 = "What color is blood";
        String answer1 = "blue";
        String answer2 = "green";
        String answer3 = "yellow";
        String answer4 = "red";
        AbstractQuestion question1 = new ShortAnswerQuestion(theQuestion1,answer1,"look outside");
        AbstractQuestion question2 = new ShortAnswerQuestion(theQuestion2,answer2,"look outside");
        AbstractQuestion question3 = new ShortAnswerQuestion(theQuestion3,answer3,"look outside");
        AbstractQuestion question4 = new ShortAnswerQuestion(theQuestion4,answer4,"poke yourself");

        assertEquals(theQuestion1,question1.getQuestion());
        assertEquals(theQuestion2,question2.getQuestion());
        assertEquals(theQuestion3,question3.getQuestion());
        assertEquals(theQuestion4,question4.getQuestion());

    }

    @Test
    /**
     * Tests the check answer method.
     */
    public void testSACheckAnswer() {
        String theQuestion1 = "What color is the sky";
        String theQuestion2 = "What color is grass";
        String theQuestion3 = "What color is the sun";
        String theQuestion4 = "What color is blood";
        String answer1 = "blue";
        String answer2 = "green";
        String answer3 = "yellow";
        String answer4 = "red";
        AbstractQuestion question1 = new ShortAnswerQuestion(theQuestion1,answer1,"look outside");
        AbstractQuestion question2 = new ShortAnswerQuestion(theQuestion2,answer2,"look outside");
        AbstractQuestion question3 = new ShortAnswerQuestion(theQuestion3,answer3,"look outside");
        AbstractQuestion question4 = new ShortAnswerQuestion(theQuestion4,answer4,"poke yourself");

        assertTrue(question1.checkAnswer("blue"));
        assertTrue(question2.checkAnswer("green"));
        assertTrue(question3.checkAnswer("yellow"));
        assertTrue(question4.checkAnswer("red"));
        assertFalse(question1.checkAnswer("red"));
        assertFalse(question2.checkAnswer("yellow"));
        assertFalse(question3.checkAnswer("red"));
        assertFalse(question4.checkAnswer("blue"));

    }

    @Test
    /**
     * Tests the get hint method.
     */
    public void testSAGetHint() {
        String theQuestion1 = "What color is the sky";
        String theQuestion2 = "What color is grass";
        String theQuestion3 = "What color is the sun";
        String theQuestion4 = "What color is blood";
        String answer1 = "blue";
        String answer2 = "green";
        String answer3 = "yellow";
        String answer4 = "red";
        AbstractQuestion question1 = new ShortAnswerQuestion(theQuestion1,answer1,"look outside");
        AbstractQuestion question2 = new ShortAnswerQuestion(theQuestion2,answer2,"look outside");
        AbstractQuestion question3 = new ShortAnswerQuestion(theQuestion3,answer3,"look outside");
        AbstractQuestion question4 = new ShortAnswerQuestion(theQuestion4,answer4,"poke yourself");

        assertEquals("look outside",question1.getHint());
        assertEquals("look outside",question2.getHint());
        assertEquals("look outside",question3.getHint());
        assertEquals("poke yourself",question4.getHint());
    }

    @Test
    public void testALlQuestionTypes() {
        AbstractQuestion tfQuestion = new TrueAndFalseQuestion("Do goldfish live underwater?","true","Where do fish live");
        AbstractQuestion saQuestion = new ShortAnswerQuestion("Who lives in a pineapple under the sea?","Spongebob","He has square pants.");
        HashMap<Character, String> map = new HashMap<Character, String>();
        map.put('A',"Blue");
        map.put('B',"Red");
        map.put('C',"Yellow");
        map.put('D',"Green");
        String theQuestion1 = "What color is the sky";
        AbstractQuestion mcQuestion = new MultipleChoiceQuestion("What color is grass?",map,"green","look outside");

        assertEquals(QuestionType.MULTIPLE_CHOICE,mcQuestion.getType());
        assertEquals(QuestionType.TRUE_OR_FALSE,tfQuestion.getType());
        assertEquals(QuestionType.SHORT_ANSWER,saQuestion.getType());
    }
}