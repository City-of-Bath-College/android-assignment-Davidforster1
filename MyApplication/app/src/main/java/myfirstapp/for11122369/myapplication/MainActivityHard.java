package myfirstapp.for11122369.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;


import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import com.parse.ParseException;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainActivityHard extends AppCompatActivity {

    // variables go here

    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;
    private TextView lblScore;
    private String username = "";
    private boolean expectedAnswer;
    private List<QuestionObject> questions;
    private QuestionObject currentQuestion;
    private int index;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // connect variables to interface items

        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        lblScore = (TextView) findViewById(R.id.lblScore);


        // set questionnaire text
        lblQuestion.setText("Loading Questions!");

        // set image picture

        imgPicture.setImageResource(R.drawable.dave);

        index = 0; // initiating variables
        score = 0;

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(false);

            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

        generateQuestions();
        setUpQuestion();
        Paper.init(this);

    }  //onCreate

    private void generateQuestions() {
        questions = new ArrayList<>(); // list of questions come from parse.com using their api

        questions.add(new QuestionObject("qhVtofppcI", R.drawable.wales));
        questions.add(new QuestionObject("7asIFrVP8c", R.drawable.ireland));
        questions.add(new QuestionObject("nAz71rzLoV", R.drawable.iraq));
        questions.add(new QuestionObject("9GbYw5UUNq", R.drawable.malaysia));
        questions.add(new QuestionObject("6Bfswdg4II", R.drawable.hungary));

    }

    private void setUpQuestion() {

        if (index == questions.size()) {
            // they've used all their questions time to end the game
            Log.d("DAVE_APP", "ended all the questions");
            endGame();
        } else {

            currentQuestion = questions.get(index);

            ///////////////////////////////////////////

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Question");
            query.getInBackground(currentQuestion.getQuestion(), new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        // object will be your game score
                        String textQuestion = object.getString("question");
                        lblQuestion.setText(textQuestion);
                        expectedAnswer = object.getBoolean("answer");
                        imgPicture.setImageResource(currentQuestion.getPicture());
                        index++;
                    } else {
                        // something went wrong
                    }
                }
            });

            //////////////////////////////////////////

        }
    }

    private void determineButtonPress(boolean answer) { // determines whether button press is correct or false
        MediaPlayer player;
        if (answer == expectedAnswer) {
            // you were right!
            score ++;
            lblScore.setText("Score: " + score);
            Toast.makeText(MainActivityHard.this, "Correct!", Toast.LENGTH_SHORT).show();
            player = MediaPlayer.create(MainActivityHard.this,R.raw.correct); // plays correct sound obtained from http://tinyurl.com/glgcak6
            player.start();
        } else {
            // you were wrong!

            Toast.makeText(MainActivityHard.this, "False!", Toast.LENGTH_SHORT).show();
            player = MediaPlayer.create(MainActivityHard.this,R.raw.error); // plays error sound obtained from http://tinyurl.com/nzxjtsc
            player.start();
        }

        setUpQuestion();

    }


    private void endGame() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this); // declares builder which is going to be the prompt for users to type in their high scores
        builder.setTitle("Enter your name here:");

// Set up the input
        final EditText input = new EditText(MainActivityHard.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);
        builder.setCancelable(false);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                username = input.getText().toString(); // user types in their name here

                // new high score!
                HighScoreObject highScoreHard = new HighScoreObject(username, score, new Date().getTime());

                // get user prefs
                List<HighScoreObject> highScoresHard = Paper.book().read("highscoreshard", new ArrayList<HighScoreObject>());

                // add item
                highScoresHard.add(highScoreHard);

                // this is ordering the highscores from highest to lowest
                Collections.sort(highScoresHard, new Comparator<HighScoreObject>() {
                    public int compare(HighScoreObject a, HighScoreObject b) {

                        if (a.getScore() < b.getScore()) {
                            return 1;
                        } else if (a.getScore() > b.getScore()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });


                // save again
                Paper.book().write("highscoreshard",highScoresHard); // saving the highscore then the name the user put in

                finish();


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // takes you back to main menu
            }
        });
        builder.show();
    }
}



