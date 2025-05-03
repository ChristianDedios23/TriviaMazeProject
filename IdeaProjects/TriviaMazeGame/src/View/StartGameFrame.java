package View;

import javax.swing.*;
import java.awt.*;

//for options use Jcheckbox, Jradiobutton, Buttongroup, Jbutton, JDialog, Jpanel
// perhaps make new class to handle all of this
public class StartGameFrame extends JFrame
{

    private MenuBar myMenuBar;
    private GameSettingsWindow myGameSettingsWindow;

    private JButton myNewGameButton;
    private JButton myLoadGameButton;
    private JLabel myTitleLabel;

    private final int WIDTH_OF_FRAME = 700;
    private final int HEIGHT_OF_FRAME = 500;

    private final int WIDTH_OF_BUTTON = 100;
    private final int HEIGHT_OF_BUTTON = 50;

    public StartGameFrame()
    {
        super();
        myGameSettingsWindow = new GameSettingsWindow();
        myMenuBar = new MenuBar();
        myNewGameButton = new JButton();
        myLoadGameButton = new JButton();
        myTitleLabel = new JLabel();

        setUpFrame();
        setUpPageElements();
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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setUpPageElements()
    {
        myNewGameButton.setText("New Game");
        myNewGameButton.setBounds(290, 225, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);

        myLoadGameButton.setText("Load Game");
        myLoadGameButton.setBounds(290, 290, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);

        myTitleLabel.setText("<html>The Great Trivia Escape!<br>Think Your Way Out!</html>");
        myTitleLabel.setBounds(200,50, 325, 200);
        myTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));

        this.add(myNewGameButton);
        this.add(myLoadGameButton);
        this.add(myTitleLabel);
    }
}
