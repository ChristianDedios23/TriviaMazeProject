package Model;

public abstract class QuestionFactory {
    protected String question;
    protected String answer;

    public QuestionFactory(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public abstract void ask();
    public abstract boolean checkAnswer(String answer);

}
