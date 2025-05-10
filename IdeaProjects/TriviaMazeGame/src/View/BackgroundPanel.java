package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundPanel extends JPanel
{
    private Color myBackgroundColor;

    private BufferedImage image;

    BackgroundPanel(final Color theBackgroundColor)
    {
        super();
        myBackgroundColor = theBackgroundColor;

        try{
            image = ImageIO.read(new File("tileFloor.png"));
        }
        catch(IOException e)
        {
            System.out.println("error");
        }


    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(myBackgroundColor);
        g.fillRect(0,0, 1024, 768);
        if (image != null)
            g.drawImage(image, 50, 100, 500, 500, this);
    }
}
