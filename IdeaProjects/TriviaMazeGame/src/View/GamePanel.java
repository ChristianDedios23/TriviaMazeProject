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

    private JLabel myCurrentTask;

    private JLabel myObjectiveLabel;

    GamePanel()
    {
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);
        MazePanel myMazePanel = new MazePanel();
        setUpButtons();
        addListeners();
        this.setLayout(null);
        this.add(myMazePanel);

        myObjectiveLabel = new JLabel("Objective: Find the exit!");
        myObjectiveLabel.setFont(new Font("Serif", Font.BOLD, 25));
        myObjectiveLabel.setForeground(Color.WHITE);
        myObjectiveLabel.setBounds(575, 575, 300, 100);
        this.add(myObjectiveLabel);

        myCurrentTask = new JLabel("Current Task:");
        myCurrentTask.setFont(new Font("Serif", Font.BOLD, 25));
        myCurrentTask.setForeground(Color.WHITE);
        myCurrentTask.setBounds(575, 525, 450, 100);
        this.add(myCurrentTask);

        QuestionsPanel myQuestionPanel = new QuestionsPanel();
        this.add(myQuestionPanel);
        this.setPreferredSize(new Dimension(1024, 768));


    }

    private void setUpButtons()
    {
        myButtonLocation = new JPanel(new GridBagLayout());
        myButtonLocation.setBackground(new Color(101, 140, 225));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        myUpButton = new JButton("Up");
        myDownButton = new JButton("Down");
        myLeftButton = new JButton("Left");
        myRightButton = new JButton("Right");

        //only works on windows
        myUpButton.setMnemonic(KeyEvent.VK_W);
        myDownButton.setMnemonic(KeyEvent.VK_S);
        myLeftButton.setMnemonic(KeyEvent.VK_A);
        myRightButton.setMnemonic(KeyEvent.VK_D);
        if(StartGameFrame.MY_MAZE_MODEL.getMyCurrentQuestion() != null){
            myUpButton.setEnabled(false);
            myDownButton.setEnabled(false);
            myLeftButton.setEnabled(false);
            myRightButton.setEnabled(false);
        }
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

            else{ JOptionPane.showMessageDialog(this, "You can not access this area, try another direction!");}
        });

        myDownButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.DOWN) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.DOWN) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);

            else{ JOptionPane.showMessageDialog(this, "You can not access this area, try another direction!");}
        });

        myLeftButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.LEFT) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.LEFT) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);

            else{ JOptionPane.showMessageDialog(this, "You can not access this area, try another direction!");}
        });

        myRightButton.addActionListener(theEvent -> {
            if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.RIGHT) == DoorState.QUESTION) StartGameFrame.MY_MAZE_MODEL.getQuestion();

            else if(StartGameFrame.MY_MAZE_MODEL.checkDoorState(Direction.RIGHT) == DoorState.OPEN) StartGameFrame.MY_MAZE_MODEL.move(true);

            else{ JOptionPane.showMessageDialog(this, "You can not access this area, try another direction!");}
        });
    }

    public void enableButtons(final boolean theIsEnabled)
    {
        myUpButton.setEnabled(theIsEnabled);
        myRightButton.setEnabled(theIsEnabled);
        myLeftButton.setEnabled(theIsEnabled);
        myDownButton.setEnabled(theIsEnabled);
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
        if(evt.getPropertyName().equals("playerMove"))
        {
            myObjectiveLabel.setText("Current Position: " + evt.getNewValue());
        }

        if(evt.getPropertyName().equals("newQuestion"))
        {
            enableButtons(false);
            myCurrentTask.setText("Current Task: Answer the question");
        }

        else if(evt.getPropertyName().equals("questionWrong") || evt.getPropertyName().equals("questionRight"))
        {
            enableButtons(true);
            myCurrentTask.setText("Current Task: Choose a valid direction");
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(new Color(101, 140, 225));
        g.fillRect(0, 0, 1024, 768);
    }
}