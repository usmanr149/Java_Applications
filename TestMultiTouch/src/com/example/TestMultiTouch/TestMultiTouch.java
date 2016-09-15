package com.example.TestMultiTouch;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestMultiTouch extends Activity {

    //In this test, handle maximum of 2 pointer
    final int MAX_POINT_CNT = 2;

    String [] pointerAction = new String[MAX_POINT_CNT];
    float[] x = new float[MAX_POINT_CNT];
    float[] y = new float[MAX_POINT_CNT];
    TextView currentPointer;
    TextView pointerStatus_01, pointerStatus_02;
    TextView textDistance;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout MainLayout = (LinearLayout)findViewById(R.id.mainlayout);
        MainLayout.setOnTouchListener(OnTouchListener);

        currentPointer = (TextView)findViewById(R.id.currentpointer);
        pointerStatus_01 = (TextView)findViewById(R.id.pointstatus_01);
        pointerStatus_02 = (TextView)findViewById(R.id.pointstatus_02);
        textDistance = (TextView)findViewById(R.id.distance);
    }

    private OnTouchListener OnTouchListener = new View.OnTouchListener(){

        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            int pointerIndex = ((motionEvent.getAction() & MotionEvent.ACTION_POINTER_ID_MASK)
                    >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            int pointerId = motionEvent.getPointerId(pointerIndex);
            int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
            int pointCnt = motionEvent.getPointerCount();

            if (pointCnt <= MAX_POINT_CNT){
                if (pointerIndex <= MAX_POINT_CNT - 1){

                    for (int i = 0; i < pointCnt; i++) {
                        int id = motionEvent.getPointerId(i);
                        x[id] = (int)motionEvent.getX(i);
                        y[id] = (int)motionEvent.getY(i);
                    }

                    switch (action){
                        case MotionEvent.ACTION_DOWN:
                            pointerAction[pointerId] = "ACTION_DOWN";
                            break;
                        case MotionEvent.ACTION_POINTER_DOWN:
                            pointerAction[pointerId] = "ACTION_POINTER_DOWN";
                            break;
                        case MotionEvent.ACTION_MOVE:
                            pointerAction[pointerId] = "ACTION_MOVE";

                            int distance = (int) Math.sqrt(
                                    (x[0] - x[1]) * (x[0] - x[1]) +
                                            (y[0] - y[1]) * (y[0] - y[1]));
                            textDistance.setText("distance: " + String.valueOf(distance));

                            break;
                        case MotionEvent.ACTION_UP:
                            pointerAction[pointerId] = "ACTION_UP";
                            break;
                        case MotionEvent.ACTION_POINTER_UP:
                            pointerAction[pointerId] = "ACTION_POINTER_UP";
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            pointerAction[pointerId] = "ACTION_CANCEL";
                            break;
                        default:
                            pointerAction[pointerId] = "Unknown!";
                    }

                    currentPointer.setText(
                            "action = " + pointerAction[pointerId] + "\n"
                                    + "pointerIndex = " + String.valueOf(pointerIndex) + "\n"
                                    + "pointerId = " + String.valueOf(pointerId) + "\n"
                                    + "getPointerCount() = " + motionEvent.getPointerCount() + "\n");

                    pointerStatus_01.setText("[0] : "
                            + String.valueOf(x[0]) + " : "
                            + String.valueOf(y[0]));

                    pointerStatus_02.setText("[1] : "
                            + String.valueOf(x[1]) + " : "
                            + String.valueOf(y[1]));
                }
            }

            return true; //means event have been processed
        }};
}