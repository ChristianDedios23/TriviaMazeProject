package View;

import javax.swing.*;

/** This class represents the game settings window that the
 * user interacts with to affect the state of the maze.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed
 * @version 1.0
 */
public class GameSettingsWindow extends JDialog
{
    /** This panel asks the user the type of questions they want*/
    private final JPanel myQuestionsPanel;

    /** This panel asks the user the type of difficulty they want.*/
    private final JPanel myDifficultyPanel;

    /** This represents multiple choice questions.*/
    private JCheckBox myMultipleChoiceBox;

    /** This represents true or false questions.*/
    private JCheckBox myTrueFalseCheckBox;

    /** This represents short answer questions.*/
    private JCheckBox myShortAnswerCheckBox;

    /** This represents the easy mode.*/
    private JRadioButton myEasyButton;

    /** This represents the medium mode.*/
    private JRadioButton myMediumButton;

    /** This represents the hard mode.*/
    private JRadioButton myHardButton;

    /** This buttons creates the game with the desired settings.*/
    private final JButton myStartGameButton;

    /** This constructor initializes the properties of the GameSettingsWindow,
     * and both the difficulty and question panel properties.
     */
    GameSettingsWindow()
    {
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setTitle("Game settings:");
        int SETTINGS_WINDOW_WIDTH = 300;
        int SETTINGS_WINDOW_HEIGHT = 300;
        this.setSize(SETTINGS_WINDOW_WIDTH, SETTINGS_WINDOW_HEIGHT);
        this.setResizable(false);

        myQuestionsPanel = new JPanel();
        myDifficultyPanel = new JPanel();

        setUpQuestionPanel();
        setUpDifficultyPanel();

        myStartGameButton = new JButton("Start Game");
        this.add(myStartGameButton);
        this.pack();
    }

    /** This method adds all the contents necessary for the
     * question panel to be interactive with the user.
     */
    private void setUpQuestionPanel()
    {
        myMultipleChoiceBox = new JCheckBox("Multiple Choice");
        myTrueFalseCheckBox = new JCheckBox("True or False");
        myShortAnswerCheckBox = new JCheckBox("Short Answer");
        myQuestionsPanel.setBorder(BorderFactory.createTitledBorder("Types of Questions:"));
        myQuestionsPanel.add(myMultipleChoiceBox);
        myQuestionsPanel.add(myTrueFalseCheckBox);
        myQuestionsPanel.add(myShortAnswerCheckBox);
        myMultipleChoiceBox.setSelected(true);
        this.add(myQuestionsPanel);
    }

    /** This method adds all the contents necessary for the
     * difficulty panel to be interactive with the user.
     */
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

    /** This method sets the visible of the GameSettingsWindow.
     *
     * @param isVisible the visibility of the GameSettingsWindow.
     */
    public void makeVisible(boolean isVisible)
    {
        this.setVisible(isVisible);
    }

    /** This method retrieves the start game button.
     *
     * @return the start game button.
     */
    public JButton getStartGameButton()
    {
        return myStartGameButton;
    }

    /** This method retrieves the easy mode button.
     *
     * @return the easy mode button.
     */
    public JRadioButton getEasyButton() { return myEasyButton;}

    /** This method retrieves the medium mode button.
     *
     * @return the medium mode button.
     */
    public JRadioButton getMediumButton() { return myMediumButton;}

    /** This method retrieves the multiple choice question box.
     *
     * @return the multiple choice question box.
     */
    public JCheckBox getMultipleChoiceBox(){ return myMultipleChoiceBox;}

    /** This method retrieves the true or false question box.
     *
     * @return the true or false question box.
     */
    public JCheckBox getTrueFalseCheckBox() {return myTrueFalseCheckBox;}

    /** This method retrieves short answer question box.
     *
     * @return the short answer question box.
     */
    public JCheckBox getShortAnswerCheckBox(){return myShortAnswerCheckBox;}
}