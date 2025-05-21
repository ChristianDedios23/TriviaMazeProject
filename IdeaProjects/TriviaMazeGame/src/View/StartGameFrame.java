package View;

import Model.Maze;
import Model.MazeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StartGameFrame extends JFrame implements PropertyChangeListener
{
    private Maze myMazeModel;

    private MenuBar myMenuBar;
    private GameSettingsWindow myGameSettingsWindow;
    private GameFrame myGameFrame;

    private JButton myNewGameButton;
    private JButton myLoadGameButton;
    private JLabel myTitleLabel;

    private final int WIDTH_OF_FRAME = 700;
    private final int HEIGHT_OF_FRAME = 500;

    private final int WIDTH_OF_BUTTON = 100;
    private final int HEIGHT_OF_BUTTON = 50;

    //maybe make variable for view to check if all of maze will
    //be shown or not

    //use the question factory for checking the questions
    public StartGameFrame()
    {
        super();
        myMenuBar = new MenuBar(this);
        myNewGameButton = new JButton();
        myLoadGameButton = new JButton();
        myTitleLabel = new JLabel();
        myGameSettingsWindow = new GameSettingsWindow(this);

        setUpFrame();
        setUpPageElements();
        addListeners();

        this.setVisible(true);
    }

    private void setUpFrame()
    {
        this.getContentPane().setBackground(Color.PINK);
        this.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        this.setTitle("Trivia Maze Game");
        this.setLayout(null);
        this.setResizable(false);
        this.setJMenuBar(myMenuBar.getMenuBar());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void setUpPageElements()
    {
        myNewGameButton.setText("New Game");
        myNewGameButton.setBounds(290, 225, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        myNewGameButton.setMnemonic(KeyEvent.VK_N);

        myLoadGameButton.setText("Load Game");
        myLoadGameButton.setBounds(290, 290, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);

        myTitleLabel.setText("<html>The Great Trivia Escape!<br>Think Your Way Out!</html>");
        myTitleLabel.setBounds(200, 50, 325, 200);
        myTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));

        this.add(myNewGameButton);
        this.add(myLoadGameButton);
        this.add(myTitleLabel);
    }

    private void addListeners()
    {
        //MazeModel.addPropertyChangeListener(this);

        myNewGameButton.addActionListener(theEvent -> {
            myGameSettingsWindow.setLocationRelativeTo(this);
            myGameSettingsWindow.makeVisible(true);
        });

        //make method so both do the same
        myLoadGameButton.addActionListener(theEvent -> {
            //this.dispatchEvent(new KeyEvent());
        });

        myGameSettingsWindow.getStartGameButton().addActionListener(theEvent -> {
            myGameSettingsWindow.makeVisible(false);
            this.setVisible(false);
            int mazeSize;

            if (myGameSettingsWindow.getEasyButton().isSelected()) {
                myGameFrame = new GameFrame(MazeFactory.create5x5Maze());
                mazeSize = 5;
            }

            else if (myGameSettingsWindow.getMediumButton().isSelected()) {
                myGameFrame = new GameFrame(MazeFactory.create6x6Maze());
                mazeSize = 6;
            }

            else {
                myGameFrame = new GameFrame(MazeFactory.create7x7Maze());
                mazeSize = 7;
            }
            myGameFrame.setJMenuBar(new MenuBar(myGameFrame));
            myGameFrame.setLocationRelativeTo(this);
            myGameFrame.setBoardSizeInfo(mazeSize);
            myGameFrame.setVisible(true);
            this.setVisible(false);
        });


        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(final WindowEvent theWindowEvent)
            {
                int decision = JOptionPane.showConfirmDialog(myTitleLabel, "Are you sure you would "
                                + "like to close the program?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION);

                if(decision == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {

    }
}