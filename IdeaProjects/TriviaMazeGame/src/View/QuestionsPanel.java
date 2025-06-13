package View;

import Model.AbstractQuestion;

import Model.Player;
import View.QuestionTypePanels.QuestionTypeContainerPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** This QuestionPanel class is used to hold all the question related
 * components. This includes displaying the question, hints,
 * the place to answer questions, and player streaks.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed
 * @version 1.0
 */
public class QuestionsPanel extends JPanel implements PropertyChangeListener
{
    /** This is used to display the question*/
    private JTextArea myQuestionTextArea;

    /** This player object is used to contain the streak and
     * the number of hints available.*/
    private Player myPlayer;

    /** This object contains the different types of questions
     * that could be asked.*/
    private final QuestionTypeContainerPanel myQuestionContainer;

    /** This represents the current type of question being asked*/
    private AbstractQuestion myQuestionObject;

    /** This button is used to receive a hint*/
    private JButton myReceiveHintButton;

    /** This label informs the user of how many hints they have left.*/
    private JLabel myHintsAvailableLabel;

    /** This label informs the user of their current correct question streak*/
    private JLabel myStreakLabel;

    /** This is used to display the hint*/
    private JTextArea myHintTextArea;

    /** This QuestionsPanel constructor initializes the attributes of
     * the panel along with the elements added to it.
     */
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

    /** This method sets up the components of panel.*/
    private void setUpComponents()
    {
        int fontSize = 14;
        int rowSize = 3;
        int columnSize = 10;

        int minSize = 50;
        int prefSize = 100;
        int maxSize = 150;

        myPlayer = StartGameFrame.MY_MAZE_MODEL.getPlayer();

        myStreakLabel = new JLabel("Current Streak: " + myPlayer.getStreak());

        myQuestionTextArea = new JTextArea(rowSize, columnSize);
        myQuestionTextArea.setLineWrap(true);
        myQuestionTextArea.setWrapStyleWord(true);

        myQuestionTextArea.setEditable(false);
        myQuestionTextArea.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
        myQuestionTextArea.setBorder(BorderFactory.createTitledBorder("Question:"));

        myHintTextArea = new JTextArea(rowSize, columnSize);
        myHintTextArea.setLineWrap(true);
        myHintTextArea.setWrapStyleWord(true);

        myHintTextArea.setEditable(false);
        myHintTextArea.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
        myHintTextArea.setBorder(BorderFactory.createTitledBorder("Hint:"));

        myQuestionTextArea.setMinimumSize(new Dimension(this.getWidth(), minSize));
        myQuestionTextArea.setPreferredSize(new Dimension(this.getWidth(), prefSize));
        myQuestionTextArea.setMaximumSize(new Dimension(this.getWidth(), maxSize));

        myHintTextArea.setMinimumSize(new Dimension(this.getWidth(), minSize));
        myHintTextArea.setPreferredSize(new Dimension(this.getWidth(), prefSize));
        myHintTextArea.setMaximumSize(new Dimension(this.getWidth(), maxSize));

        myReceiveHintButton = new JButton("Receive Hint");
        myReceiveHintButton.setEnabled(false);

        //When the player presses the hint button the text area is
        //set with the hint, if there are no more hints available the
        //button is deactivated.
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

        if(currentQuestion != null)
        {
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
     * This method gets called when a bound property is changed. When
     * there is new question, the player gets a question wrong/correct, uses
     * a hint, or has a change in streak. The elements in this panel are changed.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {
        if(theEvt.getPropertyName().equals("newQuestion"))
        {
            myQuestionObject = (AbstractQuestion)theEvt.getNewValue();
            myQuestionContainer.setQuestionType(myQuestionObject.getType());
            myQuestionTextArea.setText(myQuestionObject.getQuestion());
            if(myPlayer.getHints() > 0) myReceiveHintButton.setEnabled(true);
        }


        else if(theEvt.getPropertyName().equals("questionWrong") || theEvt.getPropertyName().equals("questionRight"))
        {
            myReceiveHintButton.setEnabled(false);
            myQuestionTextArea.setText("");
            myHintTextArea.setText("");
            myQuestionContainer.clearComponents();
        }

        else if(theEvt.getPropertyName().equals("useHint") || theEvt.getPropertyName().equals("addHint"))
        {
            myHintsAvailableLabel.setText("Available Hints: " + theEvt.getNewValue());
            if(theEvt.getPropertyName().equals("addHint")){
                JOptionPane.showMessageDialog(null,"Received a Hint!" );
            }
        }

        else if(theEvt.getPropertyName().equals("addStreak") || theEvt.getPropertyName().equals("resetStreak"))
        {
            myStreakLabel.setText("Current Streak: " + theEvt.getNewValue());
        }
    }
}