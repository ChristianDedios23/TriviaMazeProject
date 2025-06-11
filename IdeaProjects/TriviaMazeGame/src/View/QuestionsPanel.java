package View;

import Model.AbstractQuestion;

import Model.Player;
import View.QuestionTypePanels.QuestionTypeContainerPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QuestionsPanel extends JPanel implements PropertyChangeListener
{
    private JTextArea myQuestionTextArea;

    private Player myPlayer;

    private QuestionTypeContainerPanel myQuestionContainer;

    private AbstractQuestion myQuestionObject;

    private JButton myReceiveHintButton;

    private JLabel myHintsAvailableLabel;

    private JLabel myStreakLabel;

    private JTextArea myHintTextArea;

    public QuestionsPanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(600,50,400,300);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        myQuestionContainer = new QuestionTypeContainerPanel();

        setUpComponents();
        this.add(myQuestionContainer);
    }

    private void setUpComponents()
    {
        myPlayer = StartGameFrame.MY_MAZE_MODEL.getPlayer();

        myStreakLabel = new JLabel("Current Streak: " + myPlayer.getStreak());

        myQuestionTextArea = new JTextArea(3,10);
        myQuestionTextArea.setLineWrap(true);
        myQuestionTextArea.setWrapStyleWord(true);

        myQuestionTextArea.setEditable(false);
        myQuestionTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        myQuestionTextArea.setBorder(BorderFactory.createTitledBorder("Question:"));

        myHintTextArea = new JTextArea(3,10);
        myHintTextArea.setLineWrap(true);
        myHintTextArea.setWrapStyleWord(true);

        myHintTextArea.setEditable(false);
        myHintTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        myHintTextArea.setBorder(BorderFactory.createTitledBorder("Hint:"));

        myQuestionTextArea.setMinimumSize(new Dimension(this.getWidth(),50));
        myQuestionTextArea.setPreferredSize(new Dimension(this.getWidth(), 100));
        myQuestionTextArea.setMaximumSize(new Dimension(this.getWidth(),150));

        myHintTextArea.setMinimumSize(new Dimension(this.getWidth(),50));
        myHintTextArea.setPreferredSize(new Dimension(this.getWidth(), 100));
        myHintTextArea.setMaximumSize(new Dimension(this.getWidth(),150));

        myReceiveHintButton = new JButton("Receive Hint");
        myReceiveHintButton.setEnabled(false);
        myReceiveHintButton.addActionListener(theEvent -> {

            myHintTextArea.setText(myQuestionObject.getHint());
            myReceiveHintButton.setEnabled(false);
            try {
                myPlayer.useHint();
            }
            catch(IllegalArgumentException e)
            {
                myReceiveHintButton.setEnabled(false);
            }
        });

        myHintsAvailableLabel = new JLabel("Available Hints: " + myPlayer.getHints());
        AbstractQuestion currentQuestion = StartGameFrame.MY_MAZE_MODEL.getMyCurrentQuestion();
        if(currentQuestion != null){
            myQuestionObject = currentQuestion;
            myQuestionContainer.setQuestionType(myQuestionObject.getType());
            myQuestionTextArea.setText(myQuestionObject.getQuestion());
            if(myPlayer.getHints() > 0) myReceiveHintButton.setEnabled(true);
        }
        this.add(myQuestionTextArea);
        this.add(myHintTextArea);
        this.add(myHintsAvailableLabel);
        this.add(myReceiveHintButton);
        this.add(myStreakLabel);
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
            myQuestionObject = (AbstractQuestion)evt.getNewValue();
            myQuestionContainer.setQuestionType(myQuestionObject.getType());
            myQuestionTextArea.setText(myQuestionObject.getQuestion());
            if(myPlayer.getHints() > 0) myReceiveHintButton.setEnabled(true);
        }

        else if(evt.getPropertyName().equals("questionWrong") || evt.getPropertyName().equals("questionRight"))
        {
            myReceiveHintButton.setEnabled(false);
            myQuestionTextArea.setText("");
            myHintTextArea.setText("");
            myQuestionContainer.clearComponents();
        }

        else if(evt.getPropertyName().equals("useHint") || evt.getPropertyName().equals("addHint"))
        {
            myHintsAvailableLabel.setText("Available Hints: " + evt.getNewValue());
            if(evt.getPropertyName().equals("addHint")){
                JOptionPane.showMessageDialog(null,"Received a Hint!" );
            }
        }

        else if(evt.getPropertyName().equals("addStreak") || evt.getPropertyName().equals("resetStreak"))
        {
            myStreakLabel.setText("Current Streak: " + evt.getNewValue());
        }
    }
}