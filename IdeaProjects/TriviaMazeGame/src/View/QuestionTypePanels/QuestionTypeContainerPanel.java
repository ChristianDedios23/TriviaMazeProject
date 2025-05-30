package View.QuestionTypePanels;

import javax.swing.*;
import java.awt.*;

public class QuestionTypeContainerPanel extends JPanel
{
    private TrueFalsePanel myTrueFalsePanel;

    private MultipleChoicePanel myMultipleChoicePanel;

    private ShortAnswerPanel myShortAnswerPanel;

    public QuestionTypeContainerPanel()
    {
        this.setLayout(new GridLayout());
        this.setBorder(BorderFactory.createTitledBorder("Question Types:"));

        myTrueFalsePanel = new TrueFalsePanel();
        //myMultipleChoicePanel = new MultipleChoicePanel();
        //myShortAnswerPanel = new ShortAnswerPanel();

        this.add(myTrueFalsePanel);
        this.add(myMultipleChoicePanel);
        this.add(myShortAnswerPanel);


        setUpComponents();
    }

    private void setUpComponents()
    {

    }
}
