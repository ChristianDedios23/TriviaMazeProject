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

public class RoomPanel extends JPanel
{
    private final Room myRoom;

    private boolean myIsLocked;

    private boolean myIsCurrentRoom;

    private BufferedImage myBunnyImage;

    private final int myRoomLength = 75;

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



    public void setMyIsCurrentRoom(final boolean theCurrentRoom)
    {
        myIsCurrentRoom = theCurrentRoom;
        this.repaint();
        this.revalidate();
    }

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

        //change the color based on door state, and change doors based on direction of door
        // left is open, green
        // right is question, white
        //down is closed, red
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

    private void setDirectionColor(final Direction theDirection, final Graphics theG)
    {
        switch (theDirection)
        {
            case UP -> {
                theG.fillRect(0, 0, myRoomLength, 3);
            }

            case DOWN -> {
                theG.fillRect(0, myRoomLength - 3, myRoomLength, 3);
            }

            case LEFT -> {
                theG.fillRect(0, 0, 3, myRoomLength);
            }

            case RIGHT -> {
                theG.fillRect(myRoomLength - 3, 0, 3, myRoomLength);
            }
        }
    }
}
