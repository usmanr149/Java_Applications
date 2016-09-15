package com.example.AndroigTouchView;

import android.app.Activity;
import android.os.Bundle;

public class AndroigTouchView extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AndroidSurfaceView(this));

    }
}