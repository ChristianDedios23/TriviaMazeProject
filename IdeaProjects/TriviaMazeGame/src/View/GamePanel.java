package View;

import Model.Enum.Direction;
import Model.Maze;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//To-D0 - Make it so when the player cant move in certain direction, deactivate button or give error to user
public class GamePanel extends JPanel implements PropertyChangeListener
{
    private MazePanel myMazePanel;

    private JButton myUpButton;

    private JButton myDownButton;

    private JButton myLeftButton;

    private JButton myRightButton;

    private JPanel buttonLocation;

    private Maze myMazeModel;

    //BackgroundPanel.add(MazePanel.add(RoomPanel));
    GamePanel(Maze theMazeModel)
    {
        myMazeModel = theMazeModel;
        myMazePanel = new MazePanel(myMazeModel);
        setUpButtons();
        addListeners();
        this.setLayout(null);
        this.add(myMazePanel);
        this.setPreferredSize(new Dimension(1024, 768));

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

        this.add(buttonLocation);
    }

    private void addListeners()
    {
        //temp, change when khalid decides to do his job
        myUpButton.addActionListener(theEvent -> {
            //myMazeModel.attemptMove(Direction.UP);
            myMazeModel.move(Direction.UP, true);
        });

        myDownButton.addActionListener(theEvent -> {
            myMazeModel.move(Direction.DOWN, true);
        });

        myLeftButton.addActionListener(theEvent -> {
            myMazeModel.move(Direction.LEFT, true);
        });

        myRightButton.addActionListener(theEvent -> {
            myMazeModel.move(Direction.RIGHT, true);
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