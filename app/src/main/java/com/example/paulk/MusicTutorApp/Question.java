package com.example.paulk.MusicTutorApp;

import java.net.PortUnreachableException;

/**
 * Created by paulk on 28/09/2016.
 */
public class Question {

    private int questionID;
    private String question;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String correct;
    private int levelID;
    private byte[] imageResource;

    public Question(String question, String a1, String a2, String a3, String a4,
                    String correct, int levelID){

        this.question = question;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.correct = correct;
        this.levelID = levelID;

    }

    public Question(String question, String a1, String a2, String a3, String a4,
                    String correct, int levelID, byte[] imageResource){

        this.question = question;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.correct = correct;
        this.levelID = levelID;
        this.imageResource = imageResource;

    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public byte[] getImageResource() {
        return imageResource;
    }

    public void setImageResource(byte[] imageResource) {
        this.imageResource = imageResource;
    }
}
