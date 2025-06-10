package View;

import Model.Enum.Difficulty;
import Model.Enum.QuestionType;
import Model.Maze;
import Model.MazeFactory;
import Model.QuestionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class StartGameFrame extends JFrame
{


    public static Maze MY_MAZE_MODEL;

    private final MenuBar myMenuBar;
    private final GameSettingsWindow myGameSettingsWindow;

    private GameFrame myGameFrame;

    private final JButton myNewGameButton;
    private final JButton myLoadGameButton;
    private final JLabel myTitleLabel;

    public StartGameFrame()
    {
        super();
        myNewGameButton = new JButton();
        myLoadGameButton = new JButton();
        myTitleLabel = new JLabel();
        myGameSettingsWindow = new GameSettingsWindow();
        myMenuBar = new MenuBar(this);

        setUpFrame();
        setUpPageElements();
        addListeners();

        this.setVisible(true);
    }

    private void setUpFrame()
    {
        int WIDTH_OF_FRAME = 700;
        int HEIGHT_OF_FRAME = 500;

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
        int WIDTH_OF_BUTTON = 100;
        int HEIGHT_OF_BUTTON = 50;

        myNewGameButton.setText("New Game");
        myNewGameButton.setBounds(290, 225, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        myNewGameButton.setMnemonic(KeyEvent.VK_N);

        myLoadGameButton.setText("Load Game");
        myLoadGameButton.setBounds(290, 290, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        myLoadGameButton.setMnemonic(KeyEvent.VK_L);

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
            try {
                FileInputStream file = new FileInputStream("savedGame.ser");
                ObjectInputStream in = new ObjectInputStream (file);
                MY_MAZE_MODEL = (Maze)in.readObject();
                in.close();
                file.close();

            }catch (IOException ex) {
                System.err.println(ex);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            finally {
                if(MY_MAZE_MODEL != null){
                    myGameFrame = GameFrame.getInstance();
                    myGameFrame.setVisible(true);
                    this.setVisible(false);
                }
            }

        });

        myGameSettingsWindow.getStartGameButton().addActionListener(theEvent -> {
            myGameSettingsWindow.makeVisible(false);
            this.setVisible(false);
            int mazeSize;

            if (myGameSettingsWindow.getEasyButton().isSelected()) {
                MY_MAZE_MODEL = MazeFactory.createMaze(Difficulty.EASY);
                mazeSize = 5;
            }

            else if (myGameSettingsWindow.getMediumButton().isSelected()) {
                MY_MAZE_MODEL = MazeFactory.createMaze(Difficulty.MEDIUM);
                mazeSize = 6;
            }

            else {
                MY_MAZE_MODEL = MazeFactory.createMaze(Difficulty.HARD);
                mazeSize = 7;
            }

            if (myGameSettingsWindow.getMultipleChoiceBox().isSelected())
            {
                MY_MAZE_MODEL.editMyQuestionTypeSet(QuestionType.MULTIPLE_CHOICE);
            }

            if (myGameSettingsWindow.getShortAnswerCheckBox().isSelected())
            {
                MY_MAZE_MODEL.editMyQuestionTypeSet(QuestionType.SHORT_ANSWER);
            }

            if (myGameSettingsWindow.getTrueFalseCheckBox().isSelected())
            {
                MY_MAZE_MODEL.editMyQuestionTypeSet(QuestionType.TRUE_OR_FALSE);
            }

            myGameFrame = GameFrame.getInstance();
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
}