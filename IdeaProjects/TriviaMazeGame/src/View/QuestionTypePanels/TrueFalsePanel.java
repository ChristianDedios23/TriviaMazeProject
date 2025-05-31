package View.QuestionTypePanels;

import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import View.StartGameFrame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TrueFalsePanel extends JPanel implements PropertyChangeListener
{
    private AbstractQuestion myCurrentQuestion;

    private JButton myTrueButton;

    private JButton myFalseButton;

    TrueFalsePanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setBorder(BorderFactory.createTitledBorder("True or False Panel:"));
        this.setLayout(new GridLayout());
        setUpComponent();
        setUpListeners();
    }

    private void setUpComponent()
    {
        myTrueButton = new JButton("True");
        myFalseButton = new JButton("False");

        this.add(myTrueButton);
        this.add(myFalseButton);
    }

    private void setUpListeners()
    {
        myTrueButton.addActionListener(theEvent -> {
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("1"));
        });

        myFalseButton.addActionListener(theEvent -> {
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("0"));
        });
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(evt.getPropertyName().equals("newQuestion"))
        {
            AbstractQuestion questionObject = ((AbstractQuestion)evt.getNewValue());
            if(questionObject.getType() == QuestionType.TRUE_OR_FALSE)
            {
                myCurrentQuestion = questionObject;
            }
        }
    }
}
