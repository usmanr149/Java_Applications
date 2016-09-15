package com.example.Pong_with_screens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyActivity extends Activity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    private Button Plbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //retrieve the button
        Plbutton = (Button)findViewById(R.id.Plbutton);

        //listen for clicks
        Plbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.Plbutton){
            //high scores button
            Intent pongIntent = new Intent(this, Pong.class);
            this.startActivity(pongIntent);
        }
    }

}
