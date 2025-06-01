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

public class ShortAnswerPanel extends JPanel implements PropertyChangeListener
{
    private final JTextField myShortAnswerTextField;
    private final JButton mySubmitButton;
    private ShortAnswerQuestion myCurrentQuestion;

    ShortAnswerPanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setLayout(new GridLayout());
        myShortAnswerTextField = new JTextField("Enter your answer...");

        mySubmitButton = new JButton("Submit");
        this.add(myShortAnswerTextField);
        this.add(mySubmitButton);
        addListeners();
    }

    private void addListeners() {
        myShortAnswerTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (myShortAnswerTextField.getText().equals("Enter your answer...")) {
                    myShortAnswerTextField.setText("");
                    myShortAnswerTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (myShortAnswerTextField.getText().isEmpty()) {
                    myShortAnswerTextField.setForeground(Color.GRAY);
                    myShortAnswerTextField.setText("Enter your answer...");
                }
            }
        });
        mySubmitButton.addActionListener(theEvent -> {
            StartGameFrame.MY_MAZE_MODEL.move(myCurrentQuestion.checkAnswer(myShortAnswerTextField.getText()));
            myShortAnswerTextField.setText("");
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
            if(questionObject.getType() == QuestionType.SHORT_ANSWER)
            {

                myCurrentQuestion = (ShortAnswerQuestion) questionObject;
            }
        }
    }
}