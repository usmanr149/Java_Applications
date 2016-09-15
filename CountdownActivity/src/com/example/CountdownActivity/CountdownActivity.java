package com.example.CountdownActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CountdownActivity extends Activity {

    private static final int MILLIS_PER_SECOND = 1000;
    private static final int SECOND_TO_COUNTDOWN = 30;
    private TextView countdownDisplay;
    private CountDownTimer timer;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        countdownDisplay = (TextView) findViewById(R.id.time_display_box);
        Button startButton = (Button) findViewById(R.id.startbutton);
        Button stopButton = (Button) findViewById(R.id.stopbutton);

        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showTimer(SECOND_TO_COUNTDOWN * MILLIS_PER_SECOND);
                } catch (NumberFormatException e) {
                    // method ignores invalid (non-integer) input and waits
                    // for something it can use
                }
            }
        });
    }

    private void showTimer(int countdownMillis) {
        if (timer != null) {
            timer.cancel();
        }
        timer = new CountDownTimer(countdownMillis, MILLIS_PER_SECOND) {

            @Override
            public void onTick(long millisUntilFinished) {
                countdownDisplay.setText("counting down: " +
                        millisUntilFinished / MILLIS_PER_SECOND);
            }

            @Override
            public void onFinish() {
                countdownDisplay.setText("KABOOM!");
            }
        }.start();
    }
}