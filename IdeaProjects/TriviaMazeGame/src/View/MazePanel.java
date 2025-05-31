package View;

import Model.Maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class MazePanel extends JPanel implements PropertyChangeListener
{
    private BufferedImage myMazeImage;

    private HashMap<Integer, RoomPanel> myRoomPanelMap;

    private int myCurrentRoom;

    private int myMazeSize;

    private int myRoomLength;

    MazePanel()
    {
        super();
        this.setLayout(null);
        this.setOpaque(false);
        StartGameFrame.MY_MAZE_MODEL.addPropertyChangeListener(this);

        myRoomPanelMap = new HashMap<>();
        myMazeSize = StartGameFrame.MY_MAZE_MODEL.getMyMazeLength();
        myRoomLength = myMazeSize * 75;
        initializeRoomPanelMap();
        myCurrentRoom = 0;
        myRoomPanelMap.get(myCurrentRoom).setMyIsCurrentRoom(true);

        this.setPreferredSize(new Dimension(myRoomLength, myRoomLength));
        this.setBounds(25, 100, myRoomLength, myRoomLength);
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

    private void initializeRoomPanelMap()
    {
        for (int i = 0; i < myMazeSize * myMazeSize; i++)
        {
            RoomPanel room = new RoomPanel(StartGameFrame.MY_MAZE_MODEL.getRoom(i), i % myMazeSize, i / myMazeSize);
            this.add(room);
            myRoomPanelMap.put(i, room);
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (myMazeImage != null)
        {
            g.drawImage(myMazeImage, 0, 0, myRoomLength, myRoomLength, this);
        }

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
        if (evt.getPropertyName().equals("playerMove"))
        {
            myCurrentRoom = (int)evt.getNewValue();
            int myOldRoom = (int)evt.getOldValue();
            System.out.println("Reached player move, current room:  " + myCurrentRoom);
            myRoomPanelMap.get(myOldRoom).setMyIsCurrentRoom(false);
            myRoomPanelMap.get(myCurrentRoom).setMyIsCurrentRoom(true);
        }
    }
}