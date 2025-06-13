package View;

import Model.Enum.Direction;
import Model.Enum.DoorState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** This class represents a panel that holds the buttons that the player
 * uses to move directions, labels that give the user information, and
 * both the question and maze panel.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed.
 * @version 1.0
 */
public class GamePanel extends JPanel implements PropertyChangeListener
{
    /** This is the up button.*/
    private JButton myUpButton;

    /** This is the down button.*/
    private JButton myDownButton;

    /** This is the left button.*/
    private JButton myLeftButton;

    /** This is the right button.*/
    private JButton myRightButton;

    /** This is the current task that is displayed to the player.*/
    private final JLabel myCurrentTask;

    /** This represents the current location of the player.*/
    private final JLabel myLocation;

    /** This constructor initializes all the labels that are used to inform the
     * user about the state of the game as well as adding the necessary components
     * to display the questions and maze.
     */
    GamePanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        MazePanel myMazePanel = new MazePanel();
        setUpButtons();
        addListeners();
        this.setLayout(null);
        this.add(myMazePanel);

        myLocation = new JLabel("Current Position: 0");
        myLocation.setFont(new Font("Serif", Font.BOLD, 25));
        myLocation.setForeground(Color.WHITE);
        myLocation.setBounds(300, 10, 300, 100);
        this.add(myLocation);

        JLabel myObjectiveLabel = new JLabel("Objective: Find the exit!");
        myObjectiveLabel.setFont(new Font("Serif", Font.BOLD, 25));
        myObjectiveLabel.setForeground(Color.WHITE);
        myObjectiveLabel.setBounds(575, 575, 300, 100);
        this.add(myObjectiveLabel);

        myCurrentTask = new JLabel("Current Task:");
        myCurrentTask.setFont(new Font("Serif", Font.BOLD, 25));
        myCurrentTask.setForeground(Color.WHITE);
        myCurrentTask.setBounds(575, 525, 450, 100);
        this.add(myCurrentTask);

        QuestionsPanel myQuestionPanel = new QuestionsPanel();
        this.add(myQuestionPanel);
        this.setPreferredSize(new Dimension(1024, 768));


    }

    /** This method initializes and sets the location of the buttons.*/
    private void setUpButtons()
    {
        JPanel myButtonLocation = new JPanel(new GridBagLayout());
        myButtonLocation.setBackground(new Color(101, 140, 225));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        myUpButton = new JButton("Up");
        myDownButton = new JButton("Down");
        myLeftButton = new JButton("Left");
        myRightButton = new JButton("Right");

        //only works on windows
        myUpButton.setMnemonic(KeyEvent.VK_W);
        myDownButton.setMnemonic(KeyEvent.VK_S);
        myLeftButton.setMnemonic(KeyEvent.VK_A);
        myRightButton.setMnemonic(KeyEvent.VK_D);

        if(StartGameFrame.MY_MAZE_MODEL.getMyCurrentQuestion() != null)
        {
            myUpButton.setEnabled(false);
            myDownButton.setEnabled(false);
            myLeftButton.setEnabled(false);
            myRightButton.setEnabled(false);
        }

        gbc.gridx = 1;
        gbc.gridy = 0;
        myButtonLocation.add(myUpButton, gbc);

        gbc.gridy = 2;
        myButtonLocation.add(myDownButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        myButtonLocation.add(myLeftButton, gbc);

        gbc.gridx = 2;
        myButtonLocation.add(myRightButton, gbc);

        myButtonLocation.setBounds(660, 450, 250 , 100);

        this.add(myButtonLocation);
    }

    /** This method adds listeners to all the buttons in the panel.*/
    private void addListeners()
    {
        //Checks if going up is a valid move or not.
        myUpButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.UP) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.UP) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);

            else{ JOptionPane.showMessageDialog(this, "You can not access this area, try another direction!");}
        });

        //Checks if going down is a valid move or not.
        myDownButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.DOWN) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.DOWN) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);

            else{ JOptionPane.showMessageDialog(this, "You can not access this area, try another direction!");}
        });

        //Checks if going left is a valid move or not.
        myLeftButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.LEFT) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.LEFT) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);

            else{ JOptionPane.showMessageDialog(this, "You can not access this area, try another direction!");}
        });

        //Checks if going right is a valid move or not.
        myRightButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.RIGHT) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.RIGHT) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);

            else{ JOptionPane.showMessageDialog(this, "You can not access this area, try another direction!");}
        });
    }

    /** This method sets all the buttons either enabled or disabled,
     * it depends on the argument passed in.
     *
     * @param theIsEnabled Tells the buttons if they are enabled or disabled.
     */
    public void enableButtons(final boolean theIsEnabled)
    {
        myUpButton.setEnabled(theIsEnabled);
        myRightButton.setEnabled(theIsEnabled);
        myLeftButton.setEnabled(theIsEnabled);
        myDownButton.setEnabled(theIsEnabled);
    }

    /**
     * This method gets called when a bound property is changed. Depending
     * on what this method hears, certain actions will be performed to either
     * enable/disable the buttons or change the text of a label.
     *
     * @param myEvt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent myEvt)
    {
        if(myEvt.getPropertyName().equals("playerMove"))
        {
            myLocation.setText("Current Position: " + myEvt.getNewValue());
        }

        if(myEvt.getPropertyName().equals("newQuestion"))
        {
            enableButtons(false);
            myCurrentTask.setText("Current Task: Answer the question");
        }

        else if(myEvt.getPropertyName().equals("questionWrong") || myEvt.getPropertyName().equals("questionRight"))
        {
            enableButtons(true);
            myCurrentTask.setText("Current Task: Choose a valid direction");
        }
    }

    /** This method paints the background of the game light blue.
     *
     * @param theG Paints the background of the game.
     */
    @Override
    protected void paintComponent(final Graphics theG)
    {
        super.paintComponent(theG);
        theG.setColor(new Color(101, 140, 225));
        theG.fillRect(0, 0, 1024, 768);
    }
}