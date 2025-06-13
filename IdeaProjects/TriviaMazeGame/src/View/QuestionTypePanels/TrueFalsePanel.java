package View.QuestionTypePanels;

import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import Model.TrueAndFalseQuestion;
import View.StartGameFrame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** This class represents a panel that holds the true or false
 * components that are used to answer a given question.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed.
 * @version 1.0
 */
public class TrueFalsePanel extends JPanel implements PropertyChangeListener
{
    /** This represents the question being asked.*/
    private TrueAndFalseQuestion myCurrentQuestion;

    /** This represents the true button option.*/
    private JButton myTrueButton;

    /** This represents the false button option.*/
    private JButton myFalseButton;

    /** This constructor initializes the components of the panel that
     * are used to answer the given question.
     */
    TrueFalsePanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setLayout(new GridLayout());
        AbstractQuestion question = StartGameFrame.MY_MAZE_MODEL.getMyCurrentQuestion();
        if(question instanceof TrueAndFalseQuestion){
            myCurrentQuestion = (TrueAndFalseQuestion) question;
        }
        setUpComponent();
        setUpListeners();
    }

    /** This constructor initializes the true and false buttons,
     * then adds them to the panel.
     */
    private void setUpComponent()
    {
        myTrueButton = new JButton("True");
        myFalseButton = new JButton("False");

        this.add(myTrueButton);
        this.add(myFalseButton);
    }

    /** This method adds listeners to the buttons.*/
    private void setUpListeners()
    {
        //Checks if the answer is true
        myTrueButton.addActionListener(theEvent -> {
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("1"));
        });

        //Checks if the answer is false
        myFalseButton.addActionListener(theEvent -> {
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer("0"));
        });
    }

    /**
     * This method gets called when a bound property is changed. When
     * a new questions is asked and, it is a true or false type question
     * then the components in this panel are adjusted accordingly.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {
        if(theEvt.getPropertyName().equals("newQuestion"))
        {
            AbstractQuestion questionObject = ((AbstractQuestion)theEvt.getNewValue());
            if(questionObject.getType() == QuestionType.TRUE_OR_FALSE)
            {

                myCurrentQuestion = (TrueAndFalseQuestion)questionObject;
            }
        }
    }
}
