package Model;

import java.util.List;

public class MultipleChoiceQuestion extends QuestionFactory {
    private List<String> optionsList;
    private int correctAnswerIndex;
    public MultipleChoiceQuestion(String question, List<String> options, String answer) {
        super(question, answer);
        optionsList = options;
        correctAnswerIndex = optionsList.indexOf(answer);
    }

    @Override
    public void ask() {
        System.out.println(question);
        for(int i=0; i<optionsList.size(); i++){
            System.out.println( (i+1) + ". " + optionsList.get(i));
        }
    }

    @Override
    public boolean checkAnswer(String answerIndex) {
        return optionsList.get(Integer.parseInt(answerIndex)-1).equals(optionsList.get(correctAnswerIndex));
    }
}
