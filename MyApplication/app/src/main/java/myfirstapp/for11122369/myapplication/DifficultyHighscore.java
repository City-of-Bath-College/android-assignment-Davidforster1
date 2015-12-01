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

public class DifficultyHighscore extends AppCompatActivity {

    // declaring private variables

    private Button btnHighscoreEasy;
    private Button btnHighscoreMedium;
    private Button btnHighscoreHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_difficulty);

        // initialising variables
        btnHighscoreEasy = (Button)findViewById(R.id.btnHighscoreEasy);
        btnHighscoreMedium = (Button) findViewById(R.id.btnHighscoreMedium);
        btnHighscoreHard = (Button) findViewById(R.id.btnHighscoreHard);

        Paper.init(this);


// setting onclick listeners
        btnHighscoreEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(DifficultyHighscore.this, HighScoreActivity.class);
                startActivity(i);
            }

        });


       btnHighscoreMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyHighscore.this, HighScoreActivityMedium.class);
                startActivity(i);
            }
        });

        btnHighscoreHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyHighscore.this, HighScoreActivityHard.class);
                startActivity(i);
            }
        });

    }


}
