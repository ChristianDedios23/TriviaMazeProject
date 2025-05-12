package Model;

public class TrueAndFalseQuestion extends QuestionFactory{

    private boolean correctAnswer;

    public TrueAndFalseQuestion(String question, String answer) {
        super(question, answer);
        correctAnswer = answer.equals("true");
    }

    @Override
    public void ask() {
        System.out.println(question);
        System.out.println("True or False");
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.equals(String.valueOf(correctAnswer));
    }
}
