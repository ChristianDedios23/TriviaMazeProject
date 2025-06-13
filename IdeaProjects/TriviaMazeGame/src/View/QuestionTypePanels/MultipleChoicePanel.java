package View.QuestionTypePanels;


import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import Model.MultipleChoiceQuestion;
import View.StartGameFrame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

/** This class represents a panel that holds all the multiple choice
 * options given to the user to answer the given question.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed.
 * @version 1.0
 */
public class MultipleChoicePanel extends JPanel implements PropertyChangeListener
{
    /** This is the current question being asked*/
    private MultipleChoiceQuestion myCurrentQuestion;

    /** This is option A.*/
    private final JButton myAChoice;

    /** This is option B.*/
    private final JButton myBChoice;

    /** This is option C.*/
    private final JButton myCChoice;

    /** This is option D.*/
    private final JButton myDChoice;

    /** This constructor creates the layout of the possible answers of the
     * multiple choice question the user is given.
     */
    MultipleChoicePanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setLayout(new GridLayout(2,2));
        AbstractQuestion question = StartGameFrame.MY_MAZE_MODEL.getMyCurrentQuestion();
        myAChoice = new JButton("A");
        myBChoice = new JButton("B");
        myCChoice = new JButton("C");
        myDChoice = new JButton("D");
        if(question instanceof MultipleChoiceQuestion){
            myCurrentQuestion = (MultipleChoiceQuestion) question;
            setChoices();
        }
        layoutComponents();
        addListeners();
    }

    /** This method is used to add all the choices to this panel to be displayed.*/
    private void layoutComponents()
    {
        this.add(myAChoice);
        this.add(myBChoice);
        this.add(myCChoice);
        this.add(myDChoice);
    }

    /** This method adds listeners to each option so their action is reflected in
     * the maze UI and maze logic in the model.
     */
    private void addListeners()
    {
        //checks if answer is option A.
        myAChoice.addActionListener(theEvent ->{
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("A"));
        });

        //checks if answer is option B.
        myBChoice.addActionListener(theEvent ->{
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("B"));
        });

        //checks if answer is option C.
        myCChoice.addActionListener(theEvent ->{
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("C"));
        });

        //checks if answer is option D.
        myDChoice.addActionListener(theEvent ->{
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("D"));
        });
    }

    /** This method assigns each JButton with the specific answer tied to it.*/
    private void setChoices()
    {
        HashMap<Character, String> optionList = myCurrentQuestion.getOptionList();
        for (Character option : optionList.keySet())
        {
            switch (option)
            {
                case 'A':
                    assert myAChoice != null;
                    myAChoice.setText(optionList.get(option));
                    break;
                case 'B':
                    assert myBChoice != null;
                    myBChoice.setText(optionList.get(option));
                    break;
                case 'C':
                    assert myCChoice != null;
                    myCChoice.setText(optionList.get(option));
                    break;
                case 'D':
                    assert myDChoice != null;
                    myDChoice.setText(optionList.get(option));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * This method gets called when a bound property is changed. When there
     * is a new question being asked this method checks if it's a multiple
     * choice question. If this is ture then it sets all the contents in this
     * panel accordingly.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange (final PropertyChangeEvent theEvt)
    {
        if (theEvt.getPropertyName().equals("newQuestion")) {
            if (((AbstractQuestion) theEvt.getNewValue()).getType() == QuestionType.MULTIPLE_CHOICE) {
                myCurrentQuestion = ((MultipleChoiceQuestion) theEvt.getNewValue());
                setChoices();
            }
        }
    }
}





