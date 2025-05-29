package View;

import Model.MultipleChoiceQuestion;

import javax.swing.*;
import java.awt.*;


public class MultipleChoicePanel extends JPanel {
    private final JButton myAChoice;
    private final JButton myBChoice;
    private final JButton myCChoice;
    private final JButton myDChoice;

    private final MultipleChoiceQuestion myQuestion;

    public MultipleChoicePanel(final MultipleChoiceQuestion theQuestion) {
        myAChoice = new JButton("A");
        myBChoice = new JButton("B");
        myCChoice = new JButton("C");
        myDChoice = new JButton("D");
        myQuestion = theQuestion;
        layoutComponents();
        addListeners();
    }
    private void layoutComponents() {
        this.setLayout(new GridLayout());
        this.add(myAChoice);
        this.add(myBChoice);
        this.add(myCChoice);
        this.add(myDChoice);
    }
    private void addListeners(){
        myAChoice.addActionListener(theEvent ->{
            myQuestion.checkAnswer("A");
        });
        myBChoice.addActionListener(theEvent ->{
            myQuestion.checkAnswer("B");
        });
        myCChoice.addActionListener(theEvent ->{
            myQuestion.checkAnswer("C");
        });
        myDChoice.addActionListener(theEvent ->{
            myQuestion.checkAnswer("D");
        });
    }
}
