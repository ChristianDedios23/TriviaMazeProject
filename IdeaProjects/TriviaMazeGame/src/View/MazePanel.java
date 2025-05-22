package View;

import Model.Maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class MazePanel extends JPanel
{
    private BufferedImage myMazeImage;

    private BufferedImage myBunnyImage;

    private HashMap<Integer, RoomPanel> roomPanelMap;

    private Maze myMazeModel;

    private int myMazeSize;

    private int myRoomLength;

    MazePanel(Maze theMazeModel)
    {
        super();
        this.setLayout(null);
        this.setOpaque(false);
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
           g.drawImage(myBunnyImage, 0, 0, 75, 75, this);
        }


    }
}
