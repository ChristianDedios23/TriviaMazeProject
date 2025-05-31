package View.QuestionTypePanels;

import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import Model.MultipleChoiceQuestion;
import View.StartGameFrame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class MultipleChoicePanel extends JPanel implements PropertyChangeListener
{
    private AbstractQuestion myCurrentQuestion;

    private final JButton myAChoice;
    private final JButton myBChoice;
    private final JButton myCChoice;
    private final JButton myDChoice;

    public MultipleChoicePanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        myAChoice = new JButton("A");
        myBChoice = new JButton("B");
        myCChoice = new JButton("C");
        myDChoice = new JButton("D");
        layoutComponents();
        addListeners();
    }
    private void layoutComponents() {
        this.setLayout(new GridLayout());
        this.add(myAChoice);
        this.add(myBChoice);
        this.add(myCChoice);
        this.add(myDChoice);
    }
    private void addListeners(){
        myAChoice.addActionListener(theEvent ->{
            myCurrentQuestion.checkAnswer("A");
        });
        myBChoice.addActionListener(theEvent ->{
            myCurrentQuestion.checkAnswer("B");
        });
        myCChoice.addActionListener(theEvent ->{
            myCurrentQuestion.checkAnswer("C");
        });
        myDChoice.addActionListener(theEvent ->{
            myCurrentQuestion.checkAnswer("D");
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
            if(questionObject.getType() == QuestionType.MULTIPLE_CHOICE)
            {
                myCurrentQuestion = questionObject;
            }
        }
    }
}