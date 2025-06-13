package View.QuestionTypePanels;

import Model.Enum.QuestionType;

import javax.swing.*;
import java.awt.*;

/** This represents a question type container panel. It is used
 * to handle and display the specific question type panel that
 * the user is being asked so, they can answer the question
 * accordingly.
 *
 * @author Christian Dedios, Jason Dinh, Khalid Mohamed.
 * @version 1.0
 */
public class QuestionTypeContainerPanel extends JPanel
{
    /** This represents the panel that holds the true or false objects.*/
    private final TrueFalsePanel myTrueFalsePanel;

    /** This represents the panel that holds the multiple choice objects.*/
    private final MultipleChoicePanel myMultipleChoicePanel;

    /** This represents the panel that holds the short answer objects.*/
    private final ShortAnswerPanel myShortAnswerPanel;

    /** This constructor initializes each of the different question type
     * panels and sets the layout for them.
     */
    public QuestionTypeContainerPanel()
    {
        this.setLayout(new GridLayout());

        myTrueFalsePanel = new TrueFalsePanel();
        myMultipleChoicePanel = new MultipleChoicePanel();
        myShortAnswerPanel = new ShortAnswerPanel();
    }

    /** This method removes all the elements from the QuestionTypeContainerPanel
     * and repaints the panel to display the changes made.
     */
    public void clearComponents()
    {
        this.remove(myTrueFalsePanel);
        this.remove(myMultipleChoicePanel);
        this.remove(myShortAnswerPanel);
        this.revalidate();
        this.repaint();
    }

    /** This method sets the adds the specific question panel to this
     * instance depending on the type of question being asked.
     *
     * @param theType the type of question being asked.
     */
    public void setQuestionType(final QuestionType theType)
    {
        switch(theType)
        {
            case SHORT_ANSWER -> this.add(myShortAnswerPanel);

            case MULTIPLE_CHOICE -> this.add(myMultipleChoicePanel);

            case TRUE_OR_FALSE -> this.add(myTrueFalsePanel);
        }

        this.revalidate();
        this.repaint();
    }
}
