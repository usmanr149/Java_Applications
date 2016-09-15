package com.example.AnimatedView;

import android.app.Activity;
import android.os.Bundle;

public class AnimatedView extends Activity
{
    private GameView pongGameView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
