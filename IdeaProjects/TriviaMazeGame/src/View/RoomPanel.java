package View;

import Model.Room;

import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel
{
    private Room myRoom;

    private int myRow;

    private int myColumn;

    RoomPanel(Room theRoom, int theRow, int theColumn)
    {
        this.setLayout(null);
        this.setOpaque(false);
        this.setBounds(theRow * 75,theColumn * 75, 75,75);
        myRoom = theRoom;
        myRow = theRow;
        myColumn = theColumn;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, 75, 75);
    }
}
