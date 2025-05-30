package View;

import View.QuestionTypePanels.QuestionTypeContainerPanel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QuestionsPanel extends JPanel implements PropertyChangeListener
{
    private JTextArea myQuestionTextArea;

    private JTextArea myHintTextArea;

    public QuestionsPanel()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(600,50,400,300);
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

        myQuestionTextArea.setMinimumSize(new Dimension(this.getWidth(),50));
        myQuestionTextArea.setPreferredSize(new Dimension(this.getWidth(), 100));
        myQuestionTextArea.setMaximumSize(new Dimension(this.getWidth(),150));

        myHintTextArea.setMinimumSize(new Dimension(this.getWidth(),50));
        myHintTextArea.setPreferredSize(new Dimension(this.getWidth(), 100));
        myHintTextArea.setMaximumSize(new Dimension(this.getWidth(),150));

        this.add(myQuestionTextArea);
        this.add(myHintTextArea);
    }


    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        if(evt.getPropertyName().equals("newQuestion"))
        {
            myQuestionTextArea.setText((String)evt.getNewValue());
        }
    }
}