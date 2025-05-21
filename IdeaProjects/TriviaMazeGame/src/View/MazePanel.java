package View;

import Model.Maze;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class MazePanel extends JPanel
{
    private BufferedImage image;

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

        try{
            image = ImageIO.read(new File("tileFloor.png"));
            this.setPreferredSize(new Dimension(myRoomLength, myRoomLength)); // Set size from image
            this.setBounds(25, 100, myRoomLength, myRoomLength);
            this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        }
        catch(IOException e)
        {
            System.out.println("error");
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
        //25, 100
        if (image != null) g.drawImage(image, 0, 0, myRoomLength, myRoomLength, this);
    }
}
