package com.example.CountdownSetTime;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class CountdownSetTime extends Activity {
    private int countdownSeconds = 10;  // default value of 10 secs
    private static final int MILLIS_PER_SECOND = 1000;
    private static final int SET_TIME_REQUEST_ID=1234;
    public static final String SET_TIME_ID="set_time";
    private TextView     countdownDisplay;
    private CountDownTimer timer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Context context = this.getApplicationContext();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<Integer> spinnerList = new ArrayList<Integer>();
        for (int i = 1; i <= 30; i++) {
            spinnerList.add(i);
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(context,
                android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                int secondsSet = (Integer)parent.getItemAtPosition(pos);
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing.
            }
        });

        countdownDisplay = (TextView) findViewById(R.id.time_display_box);
        Button startButton = (Button) findViewById(R.id.ok_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    showTimer(countdownSeconds * MILLIS_PER_SECOND);
                } catch (NumberFormatException e) {
                    // method ignores invalid (non-integer) input and waits
                    // for something it can use
                }
            }
        });
    }
    private void showTimer(int countdownMillis) {
        if(timer != null) { timer.cancel(); }
        timer = new CountDownTimer(countdownMillis, MILLIS_PER_SECOND) {
            @Override
            public void onTick(long millisUntilFinished) {
                countdownDisplay.setText("counting down: " +
                        millisUntilFinished / MILLIS_PER_SECOND);
            }

            @Override
            public void onFinish() {
                countdownDisplay.setText("Finish!");
            }
        }.start();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settime, menu);

        return true;
    }
    public boolean onMenuItemSelected(int id, MenuItem item) {
        switch(item.getItemId()) {
            case R.id.set_time:
                setTime();
                return true;
            default:
                // we don't have any other menu items
        }
        return super.onMenuItemSelected(id, item);
    }
    private void setTime() {
        Intent intent = new Intent(getBaseContext(), CountdownActivity.class);
        startActivityForResult(intent, SET_TIME_REQUEST_ID);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        super.onActivityResult(requestCode, resultCode, i);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        //assert resultCode == RESULT_OK;
        switch(requestCode) {
            case SET_TIME_REQUEST_ID:
                Bundle extras = i.getExtras();
                countdownSeconds = extras.getInt(SET_TIME_ID);
                countdownDisplay.setText(Long.toString(countdownSeconds));
                break;
            default:
                // do nothing; we don't expect any other results
        }
    }
}