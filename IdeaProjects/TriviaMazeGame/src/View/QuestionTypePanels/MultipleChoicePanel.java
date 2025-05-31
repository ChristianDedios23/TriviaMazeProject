package View.QuestionTypePanels;


import Model.Enum.Direction;
import Model.Enum.QuestionType;
import Model.MultipleChoiceQuestion;
import View.StartGameFrame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;


public class MultipleChoicePanel extends JPanel implements PropertyChangeListener
{
    private MultipleChoiceQuestion myCurrentQuestion;

    private final JButton myAChoice;
    private final JButton myBChoice;
    private final JButton myCChoice;
    private final JButton myDChoice;

    public MultipleChoicePanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setLayout(new GridLayout(2,2));
        myAChoice = new JButton("A");
        myBChoice = new JButton("B");
        myCChoice = new JButton("C");
        myDChoice = new JButton("D");
        layoutComponents();
        addListeners();
    }
    private void layoutComponents() {
        this.add(myAChoice);
        this.add(myBChoice);
        this.add(myCChoice);
        this.add(myDChoice);
    }
    private void addListeners(){
        myAChoice.addActionListener(theEvent ->{
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("A"));
        });
        myBChoice.addActionListener(theEvent ->{
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("B"));
        });
        myCChoice.addActionListener(theEvent ->{
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("C"));
        });
        myDChoice.addActionListener(theEvent ->{
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("D"));
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
            MultipleChoiceQuestion questionObject = ((MultipleChoiceQuestion)evt.getNewValue());
            if(questionObject.getType() == QuestionType.MULTIPLE_CHOICE)
            {
                myCurrentQuestion = questionObject;
                HashMap<Character, String> optionList =  myCurrentQuestion.getOptionList();
                for(Character option: optionList.keySet()){
                    switch (option){
                        case 'A':
                            myAChoice.setText(optionList.get(option));
                            break;
                        case 'B':
                            myBChoice.setText(optionList.get(option));
                            break;
                        case 'C':
                            myCChoice.setText(optionList.get(option));
                            break;
                        case 'D':
                            myDChoice.setText(optionList.get(option));
                            break;
                        default:
                            break;
                }
                }
            }
        }
    }
}