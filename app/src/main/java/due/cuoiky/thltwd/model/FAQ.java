package due.cuoiky.thltwd.model;

import java.util.List;

public class FAQ {
    private String question;
    private String date;
    private List<String> answers;

    public FAQ() {
    }

    public FAQ(String question, String date, List<String> answers) {
        this.question = question;
        this.date = date;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}