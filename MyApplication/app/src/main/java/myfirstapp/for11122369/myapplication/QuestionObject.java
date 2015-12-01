package myfirstapp.for11122369.myapplication;

/**
 * Created by for11122369 on 07/10/2015.
 */
public class QuestionObject {

    // variables declared

    private String question;
    private int picture;

    // tells the app how the question will be stored

    public QuestionObject(String question, int picture) {
        this.question = question;
        this.picture = picture;
    }

    // returns a question and picture to the app

public String getQuestion() {
    return question;
    }

    public int getPicture() {
        return picture;
    }
}
