package Controller;


import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import Model.QuestionFactory;
import View.StartGameFrame;
import java.util.*;

import static Model.Enum.QuestionType.SHORT_ANSWER;

public class Main
{
    public static void main(String[] args)
    {

        //StartGameFrame frame = new StartGameFrame();

         ///

        //*/question factory tester
        QuestionFactory.setupQuestions();
         QuestionFactory.editMyQuestionTypeSet(SHORT_ANSWER);
         QuestionFactory.editMyQuestionTypeSet(SHORT_ANSWER);
        AbstractQuestion tf = QuestionFactory.getQuestion();
        System.out.println(tf.getQuestion());
        System.out.println(tf.getHint());
        System.out.println(tf.getType());
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();
        System.out.println(tf.checkAnswer(answer));

         //*/

    }
}