package View;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import Model.Enum.QuestionType;

import static View.StartGameFrame.MY_MAZE_MODEL;

public class GameSettingsWindow extends JDialog implements PropertyChangeListener
{
    private final JPanel myQuestionsPanel;

    private final JPanel myDifficultyPanel;

    private final JPanel myBoardPanel;

    private JRadioButton myEasyButton;

    private JRadioButton myMediumButton;

    private JRadioButton myHardButton;

    private final JButton myStartGameButton;

    GameSettingsWindow(final JFrame theParent)
    {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Game settings:");
        int SETTINGS_WINDOW_WIDTH = 300;
        int SETTINGS_WINDOW_HEIGHT = 300;
        this.setSize(SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOW_HEIGHT);
        this.setResizable(false);

        myQuestionsPanel = new JPanel();
        myDifficultyPanel = new JPanel();
        myBoardPanel = new JPanel();

        setUpQuestionPanel();
        setUpDifficultyPanel();
        setUpBoardPanel();

        myStartGameButton = new JButton("Start Game");
        this.add(myStartGameButton);
        this.pack();
    }

    private void setUpQuestionPanel()
    {
        JCheckBox myMultipleChoiceBox = new JCheckBox("Multiple Choice");
        JCheckBox myTrueFalseCheckBox = new JCheckBox("True or False");
        JCheckBox myShortAnswerCheckBox = new JCheckBox("Short Answer");
        myQuestionsPanel.setBorder(BorderFactory.createTitledBorder("Types of Questions:"));
        myQuestionsPanel.add(myMultipleChoiceBox);
        myQuestionsPanel.add(myTrueFalseCheckBox);
        myQuestionsPanel.add(myShortAnswerCheckBox);
        this.add(myQuestionsPanel);
    }

    private void setUpDifficultyPanel()
    {
        ButtonGroup myDifficultyButtons = new ButtonGroup();
        myDifficultyPanel.setBorder(BorderFactory.createTitledBorder("Difficulty:"));
        myEasyButton = new JRadioButton("Easy (5x5 board)");
        myMediumButton = new JRadioButton("Medium (6x6 board)");
        myHardButton = new JRadioButton("Hard (7x7 board)");
        myEasyButton.setSelected(true);
        myDifficultyButtons.add(myEasyButton);
        myDifficultyButtons.add(myMediumButton);
        myDifficultyButtons.add(myHardButton);
        myDifficultyPanel.add(myEasyButton);
        myDifficultyPanel.add(myMediumButton);
        myDifficultyPanel.add(myHardButton);
        this.add(myDifficultyPanel);
    }

    private void setUpBoardPanel()
    {
        myBoardPanel.setBorder(BorderFactory.createTitledBorder("Other Options:"));
        JCheckBox myBoardCheckBox = new JCheckBox("Fully Visible Board:");
        myBoardPanel.add(myBoardCheckBox);
        this.add(myBoardPanel);
    }

    private void addListeners() {
        myQuestionsPanel.addPropertyChangeListener(this);
        myMultipleChoiceBox.addActionListener(theEvent -> {
            if (myMultipleChoiceBox.isSelected()) {
                MY_MAZE_MODEL.setQuestionType(QuestionType.MULTIPLE_CHOICE);
            }

        });
        myTrueFalseCheckBox.addActionListener(theEvent -> {
            if (myMultipleChoiceBox.isSelected()) {
                MY_MAZE_MODEL.setQuestionType(QuestionType.TRUE_OR_FALSE);
            }

        });
        myShortAnswerCheckBox.addActionListener(theEvent -> {
            if (myMultipleChoiceBox.isSelected()) {
                MY_MAZE_MODEL.setQuestionType(QuestionType.SHORT_ANSWER);
            }
        });

    }

    public void makeVisible(boolean isVisible)
    {
        this.setVisible(isVisible);
    }

    public JButton getStartGameButton()
    {
        return myStartGameButton;
    }

    public JRadioButton getEasyButton() { return myEasyButton;}

    public JRadioButton getMediumButton() { return myMediumButton;}




    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {

    }
}