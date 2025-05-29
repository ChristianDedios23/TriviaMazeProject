package View;

import javax.swing.*;
import java.awt.*;

public class QuestionTypeContainerPanel extends JPanel
{

    public QuestionTypeContainerPanel()
    {
        this.setLayout(new GridLayout());
        this.add(new JLabel("Types of Questions:"));

        setUpComponents();
    }

    private void setUpComponents()
    {

    }
}
