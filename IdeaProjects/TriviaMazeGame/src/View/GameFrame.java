package View;

import Model.Maze;
import Util.SoundClip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static View.StartGameFrame.MY_MAZE_MODEL;

public class GameFrame extends JFrame implements PropertyChangeListener
{
    private static GameFrame myInstance;

    private GamePanel myGamePanel;

    private JLabel myBoardSizeInfoText;

    private final int WIDTH_OF_FRAME = 1024;

    private final int HEIGHT_OF_FRAME = 768;

    private GameFrame()
    {
        super();

        this.setVisible(false);
        myGamePanel = new GamePanel();
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
    public static GameFrame getInstance() {
        if (myInstance == null) {
            myInstance = new GameFrame();
        }
        return myInstance;
    }


    public static void resetInstance() {
        if (myInstance != null) {
            myInstance.dispose();
            myInstance = null;
        }
    }
    public void setVisible(boolean theBoolean) {
        super.setVisible(theBoolean);
    }
    public boolean isVisible() {
        return super.isVisible();
    }
    public void setBoardSizeInfo(final int theBoardSizeInfo)
    {
        myBoardSizeInfoText.setText("Board Size: " + theBoardSizeInfo + " x " + theBoardSizeInfo);
    }

    private void setUpLabels()
    {
        myBoardSizeInfoText = new JLabel("Board Size: ...");
        myBoardSizeInfoText.setFont(new Font("Serif", Font.BOLD, 25));
        myBoardSizeInfoText.setForeground(Color.WHITE);
        myBoardSizeInfoText.setBounds(25, 10, 300, 100);
        this.add(myBoardSizeInfoText);
    }

    private void setUpFrame()
    {
        this.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        this.setTitle("Trivia Maze Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

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
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {

        if(evt.getPropertyName().equals("gameOver"))
        {
            SoundClip.playSound("sound/lose.wav");
            int choice = JOptionPane.showOptionDialog(this, "Looks like there is no possible path to the exit, you lose :( ! Would you like to play again?", "Defeat", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            checkIfPlayAgain(choice);
        }

        else if(evt.getPropertyName().equals("victory"))
        {
            SoundClip.playSound("sound/win.wav");
            int choice = JOptionPane.showOptionDialog(this, "Congrats you won! Would you like to play again?", "Victory", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            checkIfPlayAgain(choice);
        }
    }

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
