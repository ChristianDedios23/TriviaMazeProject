package View;

import Model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameFrame extends JFrame implements PropertyChangeListener
{
    private GamePanel myGamePanel;

    private JLabel myBoardSizeInfoText;

    private final int WIDTH_OF_FRAME = 1024;

    private final int HEIGHT_OF_FRAME = 768;

    GameFrame()
    {
        super();
        myGamePanel = new GamePanel();
        setUpFrame();
        addListeners();

        //maze container
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        this.setContentPane(myGamePanel);
        setUpLabels();
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
        //maybe give restart button but for now close game button
        if(evt.getPropertyName().equals("gameOver"))
        {
            int choice = JOptionPane.showOptionDialog(this, "Looks like there is no possible path to the exit, you lose :( ! Would you like to play again?", "Defeat", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            checkIfPlayAgain(choice);
        }

        else if(evt.getPropertyName().equals("victory"))
        {
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
        }
    }
}
