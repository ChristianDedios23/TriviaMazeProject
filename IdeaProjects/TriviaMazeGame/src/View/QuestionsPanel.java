package View;

import javax.swing.*;
import java.awt.*;
import java.util.function.Predicate;

public class QuestionsPanel extends JPanel
{
    private JTextArea myQuestionTextArea;

    private JTextArea myHintTextArea;

    public QuestionsPanel()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(550,50,300,300);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        setUpComponents();
        this.add(new QuestionTypeContainerPanel());
    }

    private void setUpComponents()
    {
        myQuestionTextArea = new JTextArea("This is a very long qwuestion i am going to be hello hello hello asking",3,10);
        myQuestionTextArea.setLineWrap(true);
        myQuestionTextArea.setWrapStyleWord(true);

        myQuestionTextArea.setEditable(false);
        myQuestionTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        myQuestionTextArea.setBorder(BorderFactory.createTitledBorder("Question:"));

        myHintTextArea = new JTextArea("Here is the hint");
        myHintTextArea.setLineWrap(true);
        myHintTextArea.setWrapStyleWord(true);

        myHintTextArea.setEditable(false);
        myHintTextArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        myHintTextArea.setBorder(BorderFactory.createTitledBorder("Hint:"));

        this.add(myQuestionTextArea);
        this.add(myHintTextArea);
    }
}