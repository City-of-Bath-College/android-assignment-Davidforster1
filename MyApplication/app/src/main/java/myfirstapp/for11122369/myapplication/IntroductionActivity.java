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
                Intent i = new Intent(IntroductionActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


        btnHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, HighScoreActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        List<HighScoreObject> highScores = Paper.book().read("highscores", new ArrayList<HighScoreObject>());

        highscore = 0;

        if (highScores.size() == 0 )
        {

            lblHighscore.setText("Highscore: 0");
        }
        else
        {
             lblHighscore.setText("Highscore: " + highScores.get(0).getScore());
        }
    }
}
