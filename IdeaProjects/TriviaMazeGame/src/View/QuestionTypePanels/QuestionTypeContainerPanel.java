package View.QuestionTypePanels;

import Model.Enum.QuestionType;

import javax.swing.*;
import java.awt.*;

public class QuestionTypeContainerPanel extends JPanel
{
    private TrueFalsePanel myTrueFalsePanel;

    private MultipleChoicePanel myMultipleChoicePanel;

    private ShortAnswerPanel myShortAnswerPanel;

    public QuestionTypeContainerPanel()
    {
        this.setLayout(new GridLayout());
        this.setBorder(BorderFactory.createTitledBorder("Question Types:"));

        myTrueFalsePanel = new TrueFalsePanel();
        myMultipleChoicePanel = new MultipleChoicePanel();
        myShortAnswerPanel = new ShortAnswerPanel();

        setUpComponents();
    }

    private void setUpComponents()
    {

    }

    public void clearComponents()
    {
        this.remove(myTrueFalsePanel);
        this.remove(myMultipleChoicePanel);
        this.remove(myShortAnswerPanel);
    }

    public void setQuestionType(QuestionType type)
    {
        switch(type)
        {
            case SHORT_ANSWER -> {
                clearComponents();
                this.add(myShortAnswerPanel);
            }

            case MULTIPLE_CHOICE -> {
                clearComponents();
                this.add(myMultipleChoicePanel);//maybe add more since we have to adjust for the questions in the buttons
            }

            case TRUE_OR_FALSE -> {
                clearComponents();
                this.add(myTrueFalsePanel);
            }
        }
        //case switch statements to add type of question
        //if certain type add it
        //when answer is correct or wrong clear the elements from the questionTypeContainer
        //display if correct or wrong with JoptionPane
    }
}
