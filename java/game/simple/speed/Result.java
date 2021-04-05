package game.simple.speed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {


    Button restart;
    TextView score,best;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        restart = findViewById(R.id.restart);
        score = findViewById(R.id.score);
        best = findViewById(R.id.best);

        restart.setEnabled(false);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                restart.setEnabled(true);
            }
        };

        handler.postDelayed(run,2000);

        Intent intent = getIntent();

        int scores = intent.getIntExtra("Score",0);

        score.setText(""+scores);

        SharedPreferences shared = getSharedPreferences("prefs",MODE_PRIVATE);
        int bestscore = shared.getInt("bestscore",0);

        if(scores>bestscore){

            SharedPreferences.Editor edit = getSharedPreferences("prefs",MODE_PRIVATE).edit();
            edit.putInt("bestscore",scores);
            edit.apply();
            best.setText(""+scores);

        }else{
            best.setText(""+bestscore);
        }
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Result.this,MainActivity.class));
                finish();
            }
        });


    }
}