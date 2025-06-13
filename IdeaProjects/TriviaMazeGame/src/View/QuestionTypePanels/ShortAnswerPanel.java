package View.QuestionTypePanels;

import Model.AbstractQuestion;
import Model.Enum.QuestionType;
import Model.ShortAnswerQuestion;
import View.StartGameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** This class represents a panel that hold the short answer components
 * that are given to the user to answer the given question.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed.
 */
public class ShortAnswerPanel extends JPanel implements PropertyChangeListener
{
    /** This is used by the user to answer the short answer question.*/
    private final JTextField myShortAnswerTextField;

    /** This button is used to submit their answer.*/
    private final JButton mySubmitButton;

    /** This is the current short answer question being asked.*/
    private ShortAnswerQuestion myCurrentQuestion;

    /** This constructor initializes the components of the panel that
     * are used to answer the given question.
     */
    ShortAnswerPanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setLayout(new GridLayout());
        myShortAnswerTextField = new JTextField("Enter your answer...");
        AbstractQuestion question = StartGameFrame.MY_MAZE_MODEL.getMyCurrentQuestion();

        if(question instanceof ShortAnswerQuestion)
        {
            myCurrentQuestion = (ShortAnswerQuestion) question;
        }

        mySubmitButton = new JButton("Submit");
        this.add(myShortAnswerTextField);
        this.add(mySubmitButton);
        addListeners();
    }

    /** This method is used to add listeners to each on the components in
     * this panel so, they can be interactive with the users actions.
     */
    private void addListeners()
    {
        //Adjusts the short answer text field to tell the user what to do
        //based on the current text in the text field.
        myShortAnswerTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(final FocusEvent theEvent)
            {
                if (myShortAnswerTextField.getText().equals("Enter your answer..."))
                {
                    myShortAnswerTextField.setText("");
                    myShortAnswerTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(final FocusEvent theEvent) {
                if (myShortAnswerTextField.getText().isEmpty()) {
                    myShortAnswerTextField.setForeground(Color.GRAY);
                    myShortAnswerTextField.setText("Enter your answer...");
                }
            }
        });

        //Checks if the answer is correct
        mySubmitButton.addActionListener(theEvent -> {
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer(myShortAnswerTextField.getText()));
            myShortAnswerTextField.setText("");
        });
    }

    /**
     * This method gets called when a bound property is changed. When there
     * is a new question and, it's a short answer type question then
     * the components in this panel will be adjusted.
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
            if(questionObject.getType() == QuestionType.SHORT_ANSWER)
            {
                myCurrentQuestion = (ShortAnswerQuestion) questionObject;
            }
        }
    }
}