package game.simple.speed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout field;
    TextView score,time;
    Button play;

    int count = 0;


    CountDownTimer timer;

    long timeleft = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field = findViewById(R.id.field);
        score = findViewById(R.id.score);
        time = findViewById(R.id.time);
        play = findViewById(R.id.play);


        score.setText("0");
        time.setText("10");

        field.setEnabled(false);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setVisibility(View.GONE);
                field.setEnabled(true);
            }
        });
        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                score.setText(""+count);
                timeLeft();
            }
        });

    }

    public void timeLeft(){

        timer = new CountDownTimer(timeleft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(MainActivity.this,Result.class);
                intent.putExtra("Score",count);
                startActivity(intent);
                finish();

            }
        }.start();
    }

    public void updateTimer(){
        int left = (int) timeleft/1000;
        time.setText(left+"");
    }
}