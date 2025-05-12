package Tests;

import Model.MultipleChoiceQuestion;
import Model.QuestionFactory;
import Model.TrueAndFalseQuestion;

import java.util.Arrays;
import java.util.List;

public class QuestionTest {
    public static void main(String[] args) {
        String[] s = new String[4];
        for(int i = 0; i < s.length; i++) {
            s[i] = "Option " + (i + 1);
        }
        List<String> options = Arrays.stream(s).toList();
        QuestionFactory questionFactory = new MultipleChoiceQuestion("What is the third option?",options,"Option 3");
        questionFactory.ask();
        if(questionFactory.checkAnswer("3")) {
            System.out.println("Correct");
        } else {
            System.out.println("Incorrect");
        }
        String correct = String.valueOf(true);
        QuestionFactory trueOrFalse = new TrueAndFalseQuestion("Is this true?" , correct);
        trueOrFalse.ask();
        if(trueOrFalse.checkAnswer(correct)) {
            System.out.println("Correct");
        } else {
            System.out.println("Incorrect");
        }

    }
}
