package com.example.truefalseproject;

import java.util.List;

public class Quiz {
    private List<Question> quiz;
    private int questionNum;
    private int score;

    public Quiz(List<Question> quiz) {
        this.quiz = quiz;
        questionNum = 0;
        score = 0;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quiz=" + quiz +
                ", questionNum=" + questionNum +
                ", score=" + score +
                '}';
    }

    public List<Question> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Question> quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return quiz.get(questionNum);
    }

    public Question getNextQuestion(){
        questionNum += 1;
        return quiz.get(questionNum + 1);
    }
    public boolean isThereAnotherQuestion(){
        if (quiz.size() - 1 > questionNum) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addOneToQuestionNum() {questionNum ++;}

    public void setQuestionNum(int i) {questionNum = i;}

    public void setScore(int s) {
        score = s;
    }

    public void addOneToScore() {
        score ++;
    }

    public int getScore() {return score;}

    public int getQuestionNum() {
        return questionNum;
    }

}
