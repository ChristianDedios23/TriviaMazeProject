package View;

import Util.SoundClip;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/** This class represents a JPanel that holds all the contents used to
 * represent the maze to the user.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed
 * @version 1.0
 */
public class MazePanel extends JPanel implements PropertyChangeListener
{
    /** This represents the background image of the maze.*/
    private transient BufferedImage myMazeImage;

    /** This represents a hashMap of RoomPanel objects to paint each room, with
     * the keys being their room number in the maze.*/
    private final HashMap<Integer, RoomPanel> myRoomPanelMap;

    /** This represents the current room number of the player*/
    private int myCurrentRoom;

    /** This represents the size of the maze.*/
    private final int myMazeSize;

    /** This represents the length of the maze panel and the maze image.*/
    private final int myMazeLength;

    /** This constructor initializes the contents of the room panel map,
     * background image of maze, and properties of the maze panel itself.
     */
    MazePanel()
    {
        super();
        this.setLayout(null);
        this.setOpaque(false);

        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);

        myRoomPanelMap = new HashMap<>();
        myMazeSize = StartGameFrame.MY_MAZE_MODEL.getMyMazeLength();
        myMazeLength = myMazeSize * 75;

        initializeRoomPanelMap();

        if(StartGameFrame.MY_MAZE_MODEL != null)
        {
            myCurrentRoom = StartGameFrame.MY_MAZE_MODEL.getMyCurrentRoom();
        }
        else
        {
            myCurrentRoom = 0;
        }

        myRoomPanelMap.get(myCurrentRoom).setMyIsCurrentRoom(true);

        this.setPreferredSize(new Dimension(myMazeLength, myMazeLength));
        this.setBounds(25, 100, myMazeLength, myMazeLength);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        try
        {
            myMazeImage = ImageIO.read(new File("tileFloor.png"));
        }
        catch(IOException e)
        {
            System.out.println("Image was not found");
        }
    }

    /** This method initializes the hashmap with all the rooms in the maze.*/
    private void initializeRoomPanelMap()
    {
        for (int i = 0; i < myMazeSize * myMazeSize; i++)
        {
            RoomPanel room = new RoomPanel(StartGameFrame.MY_MAZE_MODEL.getRoom(i), i % myMazeSize, i / myMazeSize);
            this.add(room);
            myRoomPanelMap.put(i, room);
        }
    }

    /** This method paints the components in the maze panel.
     *
     * @param theG This is used to paint the background image of the maze.
     */
    @Override
    protected void paintComponent(final Graphics theG)
    {
        super.paintComponent(theG);

        if (myMazeImage != null)
        {
            theG.drawImage(myMazeImage, 0, 0, myMazeLength, myMazeLength, this);
        }

    }

    /** This method listens for changes made in the maze,
     * such as the player moving or getting a question wrong/correct.
     * Based on what it hears certain actions are performed.
     *
     * @param theEvt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvt)
    {
        if(theEvt.getPropertyName().equals("playerMove"))
        {
            myCurrentRoom = (int)theEvt.getNewValue();
            int myOldRoom = (int)theEvt.getOldValue();
            System.out.println("Reached player move, current room:  " + myCurrentRoom);
            myRoomPanelMap.get(myOldRoom).setMyIsCurrentRoom(false);
            myRoomPanelMap.get(myCurrentRoom).setMyIsCurrentRoom(true);
        }

        else if(theEvt.getPropertyName().equals("questionWrong"))
        {
            myRoomPanelMap.get(myCurrentRoom).setMyIsCurrentRoom(true);
            SoundClip.playSound("sound/wrong.wav");
            JOptionPane.showMessageDialog(this, "Boo hoo! You got it wrong.", "Incorrect Answer", JOptionPane.ERROR_MESSAGE);

        }

        else if(theEvt.getPropertyName().equals("questionRight"))
        {
            SoundClip.playSound("sound/correct.wav");
            JOptionPane.showMessageDialog(this, "Congrats! You got it correct!");

        }
    }
}