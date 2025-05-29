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

    //fix this somehow, logically things appear in the correct order
    GameFrame(Maze theMazeModel)
    {
        super();
        myGamePanel = new GamePanel(theMazeModel);
        setUpFrame();
        addListeners();

        //maze container
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
        myBoardSizeInfoText.setFont(new Font("Serif", Font.BOLD, 30));
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

    }
}
