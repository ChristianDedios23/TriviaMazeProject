package View;

import Model.Enum.Direction;
import Model.Enum.DoorState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//To-D0 - Make it so when the player cant move in certain direction, deactivate button or give error to user
public class GamePanel extends JPanel implements PropertyChangeListener
{
    private JButton myUpButton;

    private JButton myDownButton;

    private JButton myLeftButton;

    private JButton myRightButton;

    private JPanel myButtonLocation;

    GamePanel()
    {
        MazePanel myMazePanel = new MazePanel();
        setUpButtons();
        addListeners();
        this.setLayout(null);
        this.add(myMazePanel);

        //see if works, it works!
        QuestionsPanel myQuestionPanel = new QuestionsPanel();
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(myQuestionPanel);
        this.add(myQuestionPanel);
        this.setPreferredSize(new Dimension(1024, 768));

    }

    private void setUpButtons()
    {
        myButtonLocation = new JPanel(new GridBagLayout());
        myButtonLocation.setBackground(Color.RED);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        myUpButton = new JButton("Up");
        myDownButton = new JButton("Down");
        myLeftButton = new JButton("Left");
        myRightButton = new JButton("Right");

        myUpButton.setMnemonic(KeyEvent.VK_W);
        myDownButton.setMnemonic(KeyEvent.VK_S);
        myLeftButton.setMnemonic(KeyEvent.VK_A);
        myRightButton.setMnemonic(KeyEvent.VK_D);

        gbc.gridx = 1;
        gbc.gridy = 0;
        myButtonLocation.add(myUpButton, gbc);

        gbc.gridy = 2;
        myButtonLocation.add(myDownButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        myButtonLocation.add(myLeftButton, gbc);

        gbc.gridx = 2;
        myButtonLocation.add(myRightButton, gbc);

        myButtonLocation.setBounds(660, 450, 250 , 100);

        this.add(myButtonLocation);
    }

    private void addListeners()
    {
        //temp, change
        myUpButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.UP) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.UP) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);
        });

        myDownButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.DOWN) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.DOWN) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);
        });

        myLeftButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.LEFT) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.LEFT) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);
        });

        myRightButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.RIGHT) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.RIGHT) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);
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

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, 1024, 768);
    }
}