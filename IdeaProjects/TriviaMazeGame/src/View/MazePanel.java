package View;

import Model.Maze;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    private transient BufferedImage myMazeImage;

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
        if(StartGameFrame.MY_MAZE_MODEL != null){
            myCurrentRoom = StartGameFrame.MY_MAZE_MODEL.getMyCurrentRoom();
        }else{
            myCurrentRoom = 0;
        }
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
        if(evt.getPropertyName().equals("playerMove"))
        {
            myCurrentRoom = (int)evt.getNewValue();
            int myOldRoom = (int)evt.getOldValue();
            System.out.println("Reached player move, current room:  " + myCurrentRoom);
            myRoomPanelMap.get(myOldRoom).setMyIsCurrentRoom(false);
            myRoomPanelMap.get(myCurrentRoom).setMyIsCurrentRoom(true);
        }

        else if(evt.getPropertyName().equals("questionWrong"))
        {
            myRoomPanelMap.get(myCurrentRoom).setMyIsCurrentRoom(true);
            try {
                File soundFile = new File("sound/wrong.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Get your money up not your funny up", "Incorrect Answer", JOptionPane.ERROR_MESSAGE);

        }

        else if(evt.getPropertyName().equals("questionRight"))
        {
            try {
                File soundFile = new File("sound/correct.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Congrats! You got it correct!");

        }

        //maybe give restart button but for now close game button
        else if(evt.getPropertyName().equals("gameOver"))
        {
            JOptionPane.showMessageDialog(this, "Looks like there is no possible path to the exit, you lose :(");
            System.exit(0);
        }
    }
}