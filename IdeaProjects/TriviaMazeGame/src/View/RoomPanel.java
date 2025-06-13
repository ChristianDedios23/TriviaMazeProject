package View;

import Model.Door;
import Model.Enum.Direction;
import Model.Enum.DoorState;
import Model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** This class represents a room that is display in a small panel.
 * That panel then represents the state of the room by painting \
 * each side of the panel a color that is associated with a door state.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed
 * @version 1.0
 */
public class RoomPanel extends JPanel
{
    /** The room object for this panel*/
    private final Room myRoom;

    /** A boolean representing if this RoomPanel instance
     * is the current room occupied by the user in the maze.*/
    private boolean myIsCurrentRoom;

    /** The avatar used to represent the users location in the maze.*/
    private transient BufferedImage myBunnyImage;

    /** The length of each side of the room*/
    private final int myRoomLength = 75;

    /** This constructor initializes the RoomPanel with its row and column in
     * the maze. Along with the Room object associated with it.
     *
     * @param theRoom The room object associated with this RoomPanel instance.
     * @param theRow The row of the RoomPanel
     * @param theColumn the column of the RoomPanel
     */
    RoomPanel(final Room theRoom, final int theRow, final int theColumn)
    {
        this.setLayout(null);
        this.setOpaque(false);
        this.setBounds(theRow * myRoomLength,theColumn * myRoomLength, 75,75);
        myIsCurrentRoom = false;
        myRoom = theRoom;

        try
        {
            myBunnyImage = ImageIO.read(new File("bunny.png"));
        }
        catch(IOException e)
        {
            System.out.println("Image was not found");
        }
    }

    /** This method is used to set if the specific RoomPanel object
     * is the room that is currently being occupied by the user.
     *
     * @param theIsCurrentRoom a boolean value that sets if the RoomPanel is being occupied
     */
    public void setMyIsCurrentRoom(final boolean theIsCurrentRoom)
    {
        myIsCurrentRoom = theIsCurrentRoom;
        this.repaint();
        this.revalidate();
    }

    /** This method is used to paint the components of the maze.
     * This consists of the rooms with their states and the users
     * avatar.
     *
     * @param theG the component used to paint the panel
     */
    @Override
    protected void paintComponent(final Graphics theG)
    {
        super.paintComponent(theG);
        theG.setColor(Color.WHITE);
        theG.drawRect(0, 0, myRoomLength, myRoomLength);

        if (myBunnyImage != null && myIsCurrentRoom)
        {
            theG.drawImage(myBunnyImage, 0, 0, myRoomLength, myRoomLength, this);
        }

        for (Direction direction: Direction.values())
        {
            Door myCurrentDoor = myRoom.getDoor(direction);
            switch(myCurrentDoor.getDoorState())
            {
                case LOCKED -> theG.setColor(Color.YELLOW);

                case QUESTION -> theG.setColor( new Color(104, 51, 35));
            }

            if(myCurrentDoor.getDoorState() != DoorState.OPEN)
            {
                setDirectionColor(direction, theG);
            }
        }
    }

    /** This method is used to paint a specific side of the panel based
     * on the direction the user wants to go and the state of that door.
     *
     * @param theDirection the direction the user wants to go
     * @param theG the component used to paint the panel
     */
    private void setDirectionColor(final Direction theDirection, final Graphics theG)
    {
        switch (theDirection)
        {
            case UP -> {
                //top of panel
                theG.fillRect(0, 0, myRoomLength, 3);
            }

            case DOWN -> {
                //bottom of panel
                theG.fillRect(0, myRoomLength - 3, myRoomLength, 3);
            }

            case LEFT -> {
                //left side of panel
                theG.fillRect(0, 0, 3, myRoomLength);
            }

            case RIGHT -> {
                //right side of panel
                theG.fillRect(myRoomLength - 3, 0, 3, myRoomLength);
            }
        }
    }
}
