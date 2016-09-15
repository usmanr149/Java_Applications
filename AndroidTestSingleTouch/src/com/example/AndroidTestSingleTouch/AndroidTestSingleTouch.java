package com.example.AndroidTestSingleTouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AndroidTestSingleTouch extends Activity {

    TextView textEvent, textX, textY;
    private float x, y;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        LinearLayout MainLayout = (LinearLayout)findViewById(R.id.mainlayout);
        textEvent = (TextView)  findViewById(R.id.event);
        textX = (TextView) findViewById(R.id.x);
        textY = (TextView) findViewById(R.id.y);

        MainLayout.setOnTouchListener(OnTouchListener);
    }

    private View.OnTouchListener OnTouchListener =
            new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    textX.setText("x = " + String.valueOf(event.getX()));
                    textY.setText("y = " + String.valueOf(event.getY()));

                    int action = event.getAction();

                    switch (action){
                        case MotionEvent.ACTION_DOWN:
                            textEvent.setText("Action Down");
                            x = event.getX();
                            y = event.getY();
                            break;
                        case MotionEvent.ACTION_UP:
                            textEvent.setText("Action Up");
                            break;
                        case MotionEvent.ACTION_MOVE:
                            textEvent.setText("Action Move");
                            x = event.getX();
                            y = event.getY();
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            textEvent.setText("Action Cancel");
                            break;
                        default:
                            textEvent.setText("Unknown!");
                    }

                    return true;
                }
            };

}