package com.example.EventHandling;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Event_Handling extends Activity {
    private View mColorRegion;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mColorRegion = findViewById(R.id.color_region);

        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);

        RadioButton r1 = (RadioButton) findViewById(R.id.radioButton);
        RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton r3 = (RadioButton) findViewById(R.id.radioButton3);

    }

        public void buttonClicked (View v){
            switch (v.getId()) {
                case R.id.button1:
                    Toast.makeText(getApplicationContext(),
                            "Red button clicked", Toast.LENGTH_LONG).show();

                    break;
                case R.id.button2:
                    Toast.makeText(getApplicationContext(),
                            "Blue button clicked", Toast.LENGTH_LONG).show();
                    break;
                case R.id.button3:
                    Toast.makeText(getApplicationContext(),
                            "Yellow button clicked", Toast.LENGTH_LONG).show();
                    break;
            }
        }
}