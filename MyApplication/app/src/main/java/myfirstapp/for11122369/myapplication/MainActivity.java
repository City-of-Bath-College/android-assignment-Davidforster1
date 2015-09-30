package myfirstapp.for11122369.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // variables go here

    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // connect variables to interface items

        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        // set questionnaire text
        lblQuestion.setText("Is my name Dave?");

        // set image picture

        imgPicture.setImageResource(R.drawable.dave);

        //onclick listeners

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Toast.makeText(MainActivity.this, "false!", Toast.LENGTH_SHORT).show();
            }
        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                Toast.makeText(MainActivity.this, "correct!!", Toast.LENGTH_SHORT).show();
            }

        });

    }
}