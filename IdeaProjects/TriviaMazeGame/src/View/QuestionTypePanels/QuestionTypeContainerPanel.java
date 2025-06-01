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
        this.revalidate();
        this.repaint();
    }

    public void setQuestionType(QuestionType type)
    {
        switch(type)
        {
            case SHORT_ANSWER -> this.add(myShortAnswerPanel);

            case MULTIPLE_CHOICE -> this.add(myMultipleChoicePanel);

            case TRUE_OR_FALSE -> this.add(myTrueFalsePanel);
        }

        this.revalidate();
        this.repaint();
    }
}
