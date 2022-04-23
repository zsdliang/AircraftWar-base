package edu.hitsz.data;

import java.io.Serializable;

public class Data implements Serializable {
    private int score;
    private String name;
    public Data(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
