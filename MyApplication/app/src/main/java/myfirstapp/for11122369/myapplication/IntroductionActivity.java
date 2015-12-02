package myfirstapp.for11122369.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.paperdb.Paper;

public class IntroductionActivity extends AppCompatActivity {

    // declaring private variables

    private Button btnAbout;
    private Button btnPlay;
    private Button btnHighscore;
    private TextView lblHighscore;
    private TextView lblHighscoreMedium;
    private TextView lblHighscoreHard;
    private int highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.enableLocalDatastore(this);
        setContentView(R.layout.activity_introduction);
        Parse.initialize(this, "tFIzdzMxCCR5pf2dNM92oUYTwNxLNVSyEFBrfkO9", "gj7gDSreN4VM4gtX9TKNTjOUlhYyi86TBF5TfUIm");

        ParseObject testObject = new ParseObject("TestObject"); // tests that the connection is there between api and app
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        // initialising variables
        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnHighscore = (Button) findViewById(R.id.btnHighscore);
        lblHighscore = (TextView) findViewById(R.id.lblHighscore);
        lblHighscoreMedium = (TextView) findViewById(R.id.lblHighscoreMedium);
        lblHighscoreHard = (TextView) findViewById(R.id.lblHighscoreHard);


        Paper.init(this);


// setting onclick listeners
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(IntroductionActivity.this, ProfileActivity.class);
                startActivity(i);
            }

        });


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, DifficultyActivity.class);
                startActivity(i);
            }
        });


        btnHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, DifficultyHighscore.class);
                startActivity(i);
            }
        });

    }


    // orders the highscores from highest to lowest

    @Override
    protected void onResume() {
        super.onResume();
        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());
        List<HighScoreObject> highscoresmedium = Paper.book().read("highscoresmedium", new ArrayList<HighScoreObject>());
        List<HighScoreObject> highscoreshard = Paper.book().read("highscoreshard", new ArrayList<HighScoreObject>());

        highscore = 0;

            // if no highscores are found, set the value to 0
        if (highScores.size() == 0 )
        {

            lblHighscore.setText("Easy high score: 0");
        }
        else
        {
            lblHighscore.setText("Easy high score: " + highScores.get(0).getName() +  (" - ") + highScores.get(0).getScore());
        }

        if (highscoresmedium.size() == 0 )
        {

            lblHighscoreMedium.setText("Medium high score: 0");
        }
        else
        {
            lblHighscoreMedium.setText("Medium high score: " + highscoresmedium.get(0).getName() +  (" - ") + highscoresmedium.get(0).getScore());
        }

        if (highscoreshard.size() == 0 )
        {

            lblHighscoreHard.setText("Hard high score: 0");
        }
        else
        {
            lblHighscoreHard.setText("Hard high score: " + highscoreshard.get(0).getName() +  (" - ") + highscoreshard.get(0).getScore());
        }
    }


}

