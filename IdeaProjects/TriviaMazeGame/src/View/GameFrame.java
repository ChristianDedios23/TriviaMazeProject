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

    private JLabel myQuestionText;

    private JButton myUpButton;

    private JButton myDownButton;

    private JButton myLeftButton;

    private JButton myRightButton;

    private JPanel buttonLocation;

    private final int WIDTH_OF_FRAME = 1024;

    private final int HEIGHT_OF_FRAME = 768;

    //fix this somehow, logically things appear in the correct order
    GameFrame(Maze theMazeModel)
    {
        super();
        myGamePanel = new GamePanel(theMazeModel);

        setUpFrame();
        setUpButtons();

        addListeners();

        //maze container
        this.setContentPane(myGamePanel);
        setUpLabels();
    }

    public void setBoardSizeInfo(final int theBoardSizeInfo)
    {
        myBoardSizeInfoText.setText("Board Size: " + theBoardSizeInfo + " x " + theBoardSizeInfo);
    }

    public void setMazeModel(Maze theMazeModel)
    {
        myGamePanel = new GamePanel(theMazeModel);
    }

    private void setUpLabels()
    {
        myBoardSizeInfoText = new JLabel("Board Size: ...");
        myBoardSizeInfoText.setFont(new Font("Serif", Font.BOLD, 30));
        myBoardSizeInfoText.setForeground(Color.WHITE);
        myBoardSizeInfoText.setBounds(25, 10, 300, 100);
        this.add(myBoardSizeInfoText);

        JLabel questionLabel = new JLabel("Question: ");
        questionLabel.setFont(new Font("Serif", Font.BOLD, 30));
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setBounds(425, 0, 500, 75);
        this.add(questionLabel);

        myQuestionText = new JLabel("<html><body style='width:330px'> Hello I love to eat lots or oranges in my free time and alos blach nwed dnsd adas d   awsd wd f faw aw w</body></html>");
        myQuestionText.setFont(new Font("Serif", Font.BOLD, 20));
        myQuestionText.setForeground(Color.WHITE);
        myQuestionText.setBounds(560, 10, 500, 100);
        this.add(myQuestionText);
    }

    private void setUpFrame()
    {
        this.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        this.setTitle("Trivia Maze Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void setUpButtons()
    {
        buttonLocation = new JPanel(new GridBagLayout());
        buttonLocation.setBackground(Color.RED);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        myUpButton = new JButton("Up");
        myDownButton = new JButton("Down");
        myLeftButton = new JButton("Left");
        myRightButton = new JButton("Right");

        gbc.gridx = 1;
        gbc.gridy = 0;
        buttonLocation.add(myUpButton, gbc);

        gbc.gridy = 2;
        buttonLocation.add(myDownButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonLocation.add(myLeftButton, gbc);

        gbc.gridx = 2;
        buttonLocation.add(myRightButton, gbc);

        buttonLocation.setBounds(660, 450, 250 , 100);

        myGamePanel.add(buttonLocation);
    }

    private void addListeners()
    {
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(final WindowEvent theWindowEvent)
            {
                int decision = JOptionPane.showConfirmDialog(myUpButton, "Are you sure you would "
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
