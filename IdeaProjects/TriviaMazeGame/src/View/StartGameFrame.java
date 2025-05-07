package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//for options use Jcheckbox, Jradiobutton, Buttongroup, Jbutton, JDialog, Jpanel
// perhaps make new class to handle all of this
public class StartGameFrame extends JFrame implements PropertyChangeListener
{

    private MenuBar myMenuBar;
    private GameSettingsWindow myGameSettingsWindow;

    private JButton myNewGameButton;
    private JButton myLoadGameButton;
    private JLabel myTitleLabel;
    private JPopupMenu d;

    private final int WIDTH_OF_FRAME = 700;
    private final int HEIGHT_OF_FRAME = 500;

    private final int WIDTH_OF_BUTTON = 100;
    private final int HEIGHT_OF_BUTTON = 50;

    public StartGameFrame()
    {
        super();
        myMenuBar = new MenuBar();
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
            myGameSettingsWindow.makeVisible(true);
        });

        myGameSettingsWindow.getStartGameButton().addActionListener(theEvent -> {
            myGameSettingsWindow.makeVisible(false);
        });

        myMenuBar.getExitGameItem().addActionListener(theEvent -> {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        myMenuBar.getGameInstructions().addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(this, "This is how to play the game");
        });

        //add buttons
        myMenuBar.getShortCutsItem().addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(this, "New Game - ALT + N\n"
                    + "Load Game - CTRL + L \nSave Game - CTRL + S \nExit Game - CTRL + E \nAbout - CTRL + A\n"
                    + "How-To-Play - CTRL + H \nShortcuts - CTRL + O \nMove Up - Alt + U \nMove Right - ALT + P\n"
                    + "Move Left - ALT + L \nMove Down - ALT + D");
        });

        myMenuBar.getAboutItem().addActionListener(theEvent -> {
            JOptionPane.showMessageDialog(this, "Authors :\n     Christian Dedios \n     Jason Dinh " +
                    "\n     Khalid Mohamed \n\nVersion: 1.0   JDK 23.0.1");
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