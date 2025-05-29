package View.QuestionTypePanels;

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
        //set invisible later
        //this.setVisible(false);
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

        });

        myFalseButton.addActionListener(theEvent -> {

        });
    }
}
