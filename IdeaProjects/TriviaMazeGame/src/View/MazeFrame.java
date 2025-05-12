package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MazeFrame extends JFrame
{
    private final Color myMazeFrameBackgroundColor;

    private JButton myUpButton;

    private JButton myDownButton;

    private JButton myLeftButton;

    private JButton myRightButton;

    private JPanel buttonLocation;

    private final int WIDTH_OF_FRAME = 1024;

    private final int HEIGHT_OF_FRAME = 768;

    MazeFrame()
    {
        super();

        myMazeFrameBackgroundColor = Color.BLUE;
        BackgroundPanel test = new BackgroundPanel(myMazeFrameBackgroundColor);

        setUpFrame();
        setUpButtons();

        addListeners();
        this.add(test);
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
        buttonLocation.setBackground(myMazeFrameBackgroundColor);

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

        buttonLocation.setBounds(675, 175, 250 , 100);

        this.add(buttonLocation);
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
}
