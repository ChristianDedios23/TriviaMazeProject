package View;

import Model.Maze;

import javax.swing.*;

public class MazeFrame extends JFrame
{
    private final int WIDTH_OF_FRAME = 1024;

    private final int HEIGHT_OF_FRAME = 768;

    MazeFrame()
    {
        super();
        BackgroundPanel test = new BackgroundPanel();
        setUpFrame();
        this.add(test);
        test.repaint();
    }
    private void setUpFrame()
    {
        this.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        this.setTitle("Trivia Maze Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public JFrame getMazeFrame()
    {
        return this;
    }
}
