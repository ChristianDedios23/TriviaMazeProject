package View.QuestionTypePanels;

import javax.swing.*;
import java.awt.*;

public class ShortAnswerPanel extends JPanel
{
    private JTextField myShortAnswerTextField;

    ShortAnswerPanel()
    {
        this.setLayout(new GridLayout());
        this.setBorder(BorderFactory.createTitledBorder("Short Answer Panel:(Press Enter to Submit Answer)"));
        myShortAnswerTextField = new JTextField("Type answer here");
        this.add(myShortAnswerTextField);
    }
}