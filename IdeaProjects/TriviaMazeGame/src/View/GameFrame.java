package View;

import Util.SoundClip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static View.StartGameFrame.MY_MAZE_MODEL;

/** GameFrame is the main game window for the Trivia Maze Game.
 * It displays the game panel, board size info, and reacts to game events such as victory or defeat.
 * This class uses a singleton pattern to ensure only one instance is used.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed.
 * @version 1.0
 */
public class GameFrame extends JFrame implements PropertyChangeListener
{
    /** Singleton instance of the GameFrame. */
    private static GameFrame myInstance;

    /** Label that displays the board size information. */
    private JLabel myBoardSizeInfoText;

    /** Private constructor for GameFrame. Sets up the frame and its components.*/
    private GameFrame()
    {
        super();

        this.setVisible(false);
        GamePanel myGamePanel = new GamePanel();
        setUpFrame();
        addListeners();


        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setContentPane(myGamePanel);
        setUpLabels();
        MenuBar menuBar = new MenuBar(this);

        this.setJMenuBar(menuBar);
        this.setLocationRelativeTo(null);
        this.setBoardSizeInfo(MY_MAZE_MODEL.getMyMazeLength());
    }

    /**
     * Returns the singleton instance of GameFrame.
     * Creates it if it doesn't already exist.
     *
     * @return the GameFrame instance.
     */
    public static GameFrame getInstance()
    {
        if (myInstance == null)
        {
            myInstance = new GameFrame();
        }
        return myInstance;
    }

    /** Resets and disposes the current instance of the GameFrame.*/
    public static void resetInstance()
    {
        if (myInstance != null)
        {
            myInstance.dispose();
            myInstance = null;
        }
    }

    /** Sets the visibility of the frame.
     *
     * @param theBoolean true to make the frame visible, false to hide it.
     */
    public void setVisible(final boolean theBoolean) {
        super.setVisible(theBoolean);
    }

    /** Checks if the frame is currently visible.
     *
     * @return true if visible, false otherwise.
     */
    public boolean isVisible()
    {
        return super.isVisible();
    }

    /** Updates the board size info label with the given board size.
     *
     * @param theBoardSizeInfo the length of the board.
     */
    public void setBoardSizeInfo(final int theBoardSizeInfo)
    {
        myBoardSizeInfoText.setText("Board Size: " + theBoardSizeInfo + " x " + theBoardSizeInfo);
    }

    /** Initializes and styles the label that displays board size info.*/
    private void setUpLabels()
    {
        myBoardSizeInfoText = new JLabel("Board Size: ...");
        myBoardSizeInfoText.setFont(new Font("Serif", Font.BOLD, 25));
        myBoardSizeInfoText.setForeground(Color.WHITE);
        myBoardSizeInfoText.setBounds(25, 10, 300, 100);
        this.add(myBoardSizeInfoText);
    }

    /** Sets up general properties of the game window.*/
    private void setUpFrame()
    {
        int WIDTH_OF_FRAME = 1024;
        int HEIGHT_OF_FRAME = 768;
        this.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        this.setTitle("Trivia Maze Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /** Adds window listeners and a confirmation on window closing.*/
    private void addListeners()
    {
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(final WindowEvent theWindowEvent)
            {
                int decision = JOptionPane.showConfirmDialog(myBoardSizeInfoText, "Are you sure you would "
                                + "like to close the program?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION);

                if(decision == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * This method gets called when a bound property is changed. Depending
     * on what this method hears, certain actions will be performed to either
     * enable/disable the buttons or change the text of a label.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {

        if(theEvt.getPropertyName().equals("gameOver"))
        {
            SoundClip.playSound("sound/lose.wav");
            int choice = JOptionPane.showOptionDialog(this, "Looks like there is no possible path to the exit, you lose :( ! Would you like to play again?", "Defeat", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            checkIfPlayAgain(choice);
        }

        else if(theEvt.getPropertyName().equals("victory"))
        {
            SoundClip.playSound("sound/win.wav");
            int choice = JOptionPane.showOptionDialog(this, "Congrats you won! Would you like to play again?", "Victory", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            checkIfPlayAgain(choice);
        }
    }

    /** Checks the player's response and either restarts or exits the game.
     *
     * @param theChoice the user's choice from the dialog (0 for Yes, 1 for No).
     */
    private void checkIfPlayAgain(final int theChoice)
    {
        if(theChoice == 1)
        {
            System.exit(0);
        }
        else
        {
            this.setVisible(false);
            StartGameFrame startGameFrame = new StartGameFrame();
            resetInstance();
        }
    }
}
