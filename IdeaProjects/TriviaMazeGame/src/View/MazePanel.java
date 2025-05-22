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

    private BufferedImage myBunnyImage;

    private HashMap<Integer, RoomPanel> roomPanelMap;

    private int myCurrentRoom;

    private Maze myMazeModel;

    private int myMazeSize;

    private int myRoomLength;

    MazePanel(Maze theMazeModel)
    {
        super();
        this.setLayout(null);
        this.setOpaque(false);
        theMazeModel.addPropertyChangeListener(this);

        myMazeModel = theMazeModel;
        roomPanelMap = new HashMap<>();
        myMazeSize = myMazeModel.getMyMazeLength();
        myRoomLength = myMazeSize * 75;
        initializeRoomPanelMap();

        this.setPreferredSize(new Dimension(myRoomLength, myRoomLength));
        this.setBounds(25, 100, myRoomLength, myRoomLength);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        try
        {
            myMazeImage = ImageIO.read(new File("tileFloor.png"));
            myBunnyImage = ImageIO.read(new File("bunny.png"));
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
            RoomPanel room = new RoomPanel(myMazeModel.getRoom(i), i / myMazeSize, i % myMazeSize);
            this.add(room);
            roomPanelMap.put(i, room);
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

        if (myBunnyImage != null)
        {
           g.drawImage(myBunnyImage, (myCurrentRoom % myMazeSize) * 75, (myCurrentRoom / myMazeSize) * 75, 75, 75, this);
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
            System.out.println("Reached player move, current room:  " + myCurrentRoom);
            this.repaint();
            this.revalidate();
        }
    }
}