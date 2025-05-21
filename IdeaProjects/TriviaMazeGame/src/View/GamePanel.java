package View;

import Model.Maze;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel
{
    private MazePanel myMazePanel;

    private Maze myMazeModel;

    //BackgroundPanel.add(MazePanel.add(RoomPanel));
    GamePanel(Maze theMazeModel)
    {
        myMazeModel = theMazeModel;
        myMazePanel = new MazePanel(myMazeModel);
        this.setLayout(null);
        this.add(myMazePanel);
        this.setPreferredSize(new Dimension(1024, 768));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(0, 0, 1024, 768);
    }
}
