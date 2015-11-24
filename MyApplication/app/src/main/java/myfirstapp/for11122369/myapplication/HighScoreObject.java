package myfirstapp.for11122369.myapplication;

import java.util.Collections;

/**
 * Created by for11122369 on 21/10/2015.
 */
public class HighScoreObject {
    //nothing here!
    private String name;
    private int score;
    private Long timestamp;

    public HighScoreObject(String name, int score, Long timestamp) {
        this.name = name;
        this.score = score;
        this.timestamp = timestamp;
    }

    public HighScoreObject() {
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public Long getTimestamp() {
        return timestamp;
    }
}

