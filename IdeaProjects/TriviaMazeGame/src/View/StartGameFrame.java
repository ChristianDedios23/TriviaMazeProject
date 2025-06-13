package View;

import Model.Enum.Difficulty;
import Model.Enum.QuestionType;
import Model.Maze;
import Model.MazeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/** This class represents the start of the Trivia Maze Game UI where
 * the user is able to start a new game or load a previous save file.
 * It uses a JFrame to contain all the contents of the UI, such as the
 * labels, buttons, and menu items.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed
 * @version 1.0
 */
public class StartGameFrame extends JFrame
{
    /** The Maze object used to handle the game logic.*/
    public static Maze MY_MAZE_MODEL;

    /** The Menu Bar used for the StartGameFrame instance.*/
    private final MenuBar myMenuBar;

    /** This GameSettingsWindow object is used to grab the users desired settings
     * of the maze.*/
    private final GameSettingsWindow myGameSettingsWindow;

    /** This is the frame that holds all the contents of the maze game.*/
    private GameFrame myGameFrame;

    /** This is the button to start a new game*/
    private final JButton myNewGameButton;

    /** This is the button to load a game*/
    private final JButton myLoadGameButton;

    /** This is the label used to display the title*/
    private final JLabel myTitleLabel;

    /** This constructor initializes all the elements of the page, that
     * being the buttons, labels, menu items. As well as adjusting the
     * properties of the frame and the listeners associated to each
     * element.
     */
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

    /** This sets up all the properties of the frame such as its width,
     * height, title, and layout.
     */
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

    /** This method adjusts all the properties of the elements on the page.
     * These elements consist of the buttons and labels.
     */
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

    /** This method adds listeners to all the clickable components
     * on the StartGameFrame, so that specific events happen when
     * each one is clicked.
     */
    private void addListeners()
    {
        //When new game button is pressed, the GameSettingsWindow object
        //appears so the user can select the settings for the game.
        myNewGameButton.addActionListener(theEvent -> {
            myGameSettingsWindow.setLocationRelativeTo(this);
            myGameSettingsWindow.makeVisible(true);
        });

        //When the load game button is pressed, the maze properties
        //are set and the game is loaded.
        myLoadGameButton.addActionListener(theEvent -> {
            try
            {
                FileInputStream file = new FileInputStream("savedGame.ser");
                ObjectInputStream in = new ObjectInputStream (file);
                MY_MAZE_MODEL = (Maze)in.readObject();
                in.close();
                file.close();

            }

            catch (IOException ex)
            {
                System.err.println(ex);
            }

            catch (ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }

            finally
            {
                if(MY_MAZE_MODEL != null){
                    myGameFrame = GameFrame.getInstance();
                    myGameFrame.setVisible(true);
                    this.setVisible(false);
                }
            }
        });

        //When the startGame button is pressed in myGameSettingsWindow,
        //the game settings are sent to the maze model and the game
        //is created with the desired maze.
        myGameSettingsWindow.getStartGameButton().addActionListener(theEvent -> {
            myGameSettingsWindow.makeVisible(false);
            this.setVisible(false);

            if (myGameSettingsWindow.getEasyButton().isSelected()) {
                MY_MAZE_MODEL = MazeFactory.createMaze(Difficulty.EASY);
            }

            else if (myGameSettingsWindow.getMediumButton().isSelected()) {
                MY_MAZE_MODEL = MazeFactory.createMaze(Difficulty.MEDIUM);
            }

            else {
                MY_MAZE_MODEL = MazeFactory.createMaze(Difficulty.HARD);
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

        //When the window is attempted to be closed, the user is asked if they are
        //positive that they want to close the window.
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