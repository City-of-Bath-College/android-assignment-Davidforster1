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


import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    // variables go here

    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;
    private TextView lblScore;
    private String m_Text = "";

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
        lblQuestion.setText("Is my name Dave?");

        // set image picture

        imgPicture.setImageResource(R.drawable.dave);

        index = 0;
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
        questions = new ArrayList<>();

        questions.add(new QuestionObject("Is the capital of England London?", true, R.drawable.england));
        questions.add(new QuestionObject("Is the capital of Egypt Cairo?", true, R.drawable.egypt));
        questions.add(new QuestionObject("Is the capital of Germany Berlin?", true, R.drawable.germany));
        questions.add(new QuestionObject("Is the capital of Finland Helsinki?", true, R.drawable.finland));
        questions.add(new QuestionObject("Is the capital of Spain France?", false, R.drawable.spain));
        questions.add(new QuestionObject("Is the capital of Italy Rome?", true, R.drawable.italy));
        questions.add(new QuestionObject("Is the capital of Romania Paris?", false, R.drawable.romania));
        questions.add(new QuestionObject("Is the capital of Switzerland Bath?", false, R.drawable.switzerland));
        questions.add(new QuestionObject("Is the capital of Nigeria Abuja?", true, R.drawable.nigeria));
        questions.add(new QuestionObject("Is the capital of Australia Sydney?", false, R.drawable.australia));

    }

    private void setUpQuestion() {

        if (index == questions.size()) {
            // they've used all their questions time to end the game
            Log.d("DANIELLE_APP", "ended all the questions");
            endGame();
        } else {


            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());
            index++;
        }
    }

    private void determineButtonPress(boolean answer) {
        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer) {
            // you were right!
        score ++;
            lblScore.setText("Score: " + score );
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            // you were wrong!

            Toast.makeText(MainActivity.this, "False!", Toast.LENGTH_SHORT).show();
        }

        setUpQuestion();

    }

   /* @Override
    protected void onPause() {
        super.onPause();
        Log.d("Daves app", "reached onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Daves app", "reached onStop");
    }
*/

    private void endGame() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your name here:");

// Set up the input
        final EditText input = new EditText(MainActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString(); // user types in their name here

                // new high score!
                HighScoreObject highScore = new HighScoreObject( "Dave", score, new Date().getTime());

                // get user prefs
              //  List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

                // add item
               // highScores.add(highScore);

                // save again
             //   Paper.book().write("highscores", highScores); // saving the highscore then the name the user put in
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

} // end activity

       /*final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Congratulations")
                .setMessage("You scored " + score + " points this round!")
                .setNeutralButton("ok", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        // return back to the intro screen

                        // new high score!
                        HighScoreObject highScore = new HighScoreObject( "Dave", score, new Date().getTime());

                        // get user prefs
                        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

                        // add item
                        highScores.add(highScore);

                        // save again
                        Paper.book().write("highscores", highScores);

                        // return back to the intro screen


                    }
                })
    .create();
        alertDialog.show();

*/



