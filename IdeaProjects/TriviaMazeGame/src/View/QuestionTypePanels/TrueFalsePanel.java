package View.QuestionTypePanels;

import View.StartGameFrame;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TrueFalsePanel extends JPanel
{
    private JButton myTrueButton;
    private JButton myFalseButton;

    TrueFalsePanel()
    {
        this.setBorder(BorderFactory.createTitledBorder("True or False Panel:"));
        this.setLayout(new GridLayout());
        setUpComponent();
        setUpListeners();
    }

    private void setUpComponent()
    {
        myTrueButton = new JButton("True");
        myFalseButton = new JButton("False");

        this.add(myTrueButton);
        this.add(myFalseButton);
    }

    private void setUpListeners()
    {
        myTrueButton.addActionListener(theEvent -> {
            //StartGameFrame.MY_MAZE_MODEL.move()
        });

        myFalseButton.addActionListener(theEvent -> {

        });
    }
}
