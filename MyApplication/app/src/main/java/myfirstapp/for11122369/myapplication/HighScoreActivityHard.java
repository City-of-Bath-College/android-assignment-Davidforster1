package myfirstapp.for11122369.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class HighScoreActivityHard extends AppCompatActivity {

    // declaring private variables

    private List<HighScoreObject> highscoresHard;
    private ListView listview;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // loads highscores during oncreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_hard); // setes content to high score activity
        Paper.init(this); // initialises paper to allow highscores to function
        highscoresHard = Paper.book().read("highscoreshard", new ArrayList<HighScoreObject>());
        listview = (ListView) findViewById(R.id.listview);
        HighscoreAdapter adapter = new HighscoreAdapter(highscoresHard);
        listview.setAdapter(adapter);

        btnReset = (Button) findViewById(R.id.btnReset); // Tells the app where the reset button is located

        // onclick listener for reset button, it will delete all records in paper

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player;
                Paper.book().delete("highscoreshard"); // resets highscores sound obtained from http://tinyurl.com/pwo9afz
                player = MediaPlayer.create(HighScoreActivityHard.this, R.raw.reset);
                player.start();
                setContentView(R.layout.activity_high_score_hard);
            }
        });
    }

    // tells paper how to lay out the highscores

    private class HighscoreAdapter extends ArrayAdapter<HighScoreObject> {

        public HighscoreAdapter(List<HighScoreObject> items) {
            super(HighScoreActivityHard.this, 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.activity_row_highscore, null);
            }

            //get the highscore object for the row we're looking at
            HighScoreObject highscore = highscoresHard.get(position);
            Date date = new Date(highscore.getTimestamp());
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd-MM-yyyy");
            TextView lblTitle = (TextView) convertView.findViewById(R.id.lblTitle);
            lblTitle.setText(highscore.getScore() + " - " + highscore.getName() + " - " + fmtOut.format(date));
            return convertView;
        }// end get view

    }// end adapter class
}
