package model;

public class Questions {
    private String question;
    private String[] options;
    private int answerIndex;
    private int mark = 5;

    public Questions(String question, String[] options, int answerIndex) {
        this.question = question;
        this.options = options;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] newOptions) {
        this.options = newOptions;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int newIndex) {
        this.answerIndex = newIndex;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}