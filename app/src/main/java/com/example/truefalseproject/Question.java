package com.example.truefalseproject;

public class Question {

    private String question;
    private boolean answer;

    public Question() {
        question = "";
        answer = false;
    }

    public Question(String q, boolean a) {
        question = q;
        answer = a;
    }

    public String getQuestionQ() {
        return question;
    }

    public void setQuestion(String q) {
        question = q;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean a) {
        answer = a;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer=" + answer +
                '}';
    }
}
