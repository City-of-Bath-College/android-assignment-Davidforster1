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

public class IntroductionActivity extends AppCompatActivity {

    // declaring private variables

    private Button btnAbout;
    private Button btnPlay;
    private Button btnHighscore;
    private TextView lblHighscore;
    private int highscore;
   /* private Button btnHighscore;
    private Button btnPlay;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.enableLocalDatastore(this);
        setContentView(R.layout.activity_introduction);
        Parse.initialize(this, "tFIzdzMxCCR5pf2dNM92oUYTwNxLNVSyEFBrfkO9", "gj7gDSreN4VM4gtX9TKNTjOUlhYyi86TBF5TfUIm");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();

        // initialising variables
        btnAbout = (Button)findViewById(R.id.btnAbout);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnHighscore = (Button) findViewById(R.id.btnHighscore);
        lblHighscore = (TextView) findViewById(R.id.lblHighscore);
        highscore = 2;
        lblHighscore.setText("Highscore: " + highscore );
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

        /*btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(IntroductionActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_introduction, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
