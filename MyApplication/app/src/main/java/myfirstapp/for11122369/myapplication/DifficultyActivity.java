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

public class DifficultyActivity extends AppCompatActivity {

    // declaring private variables

    private Button btnEasy;
    private Button btnMedium;
    private Button btnHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        // initialising variables
        btnEasy = (Button)findViewById(R.id.btnEasy);
        btnMedium = (Button) findViewById(R.id.btnMedium);
        btnHard = (Button) findViewById(R.id.btnHard);

        Paper.init(this);


// setting onclick listeners
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(DifficultyActivity.this, MainActivity.class);
                startActivity(i);
            }

        });


       btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyActivity.this, MainActivityMedium.class);
                startActivity(i);
            }
        });

        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DifficultyActivity.this, MainActivityHard.class);
                startActivity(i);
            }
        });

    }


}
