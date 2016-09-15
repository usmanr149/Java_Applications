package com.example.BallBounce;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class BallBounce extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Toast.makeText(getApplicationContext(),
                "Watch the balls bounce", Toast.LENGTH_LONG).show();
    }

}