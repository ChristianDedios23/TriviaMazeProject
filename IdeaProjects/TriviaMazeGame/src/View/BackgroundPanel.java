package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundPanel extends JPanel
{
    private BufferedImage image;

    BackgroundPanel()
    {
        super();
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
        if (image != null)
            g.drawImage(image, 50, 100, 500, 500, this);
    }
}
