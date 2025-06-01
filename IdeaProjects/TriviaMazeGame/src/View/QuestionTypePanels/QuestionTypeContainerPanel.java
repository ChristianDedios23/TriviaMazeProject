package View.QuestionTypePanels;

import Model.Enum.QuestionType;
import View.StartGameFrame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
            case SHORT_ANSWER -> this.add(myShortAnswerPanel);

            case MULTIPLE_CHOICE -> this.add(myMultipleChoicePanel);//maybe add more since we have to adjust for the questions in the buttons

            case TRUE_OR_FALSE -> this.add(myTrueFalsePanel);
        }
        //case switch statements to add type of question
        //if certain type add it
        //when answer is correct or wrong clear the elements from the questionTypeContainer
        //display if correct or wrong with JoptionPane


    }
}
