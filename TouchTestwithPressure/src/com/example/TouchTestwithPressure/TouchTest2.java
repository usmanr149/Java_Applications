package com.example.TouchTestwithPressure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TouchTest2 extends Activity {
    /**
     * Called when the activity is first created.
     */
    public class TouchView extends View {

        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float x, y;
        private float touchMajor, touchMinor;
        boolean touching = false;

        float pressure = 0; //Touch pressure
        float size = 0;  //Touch size
        final static float PRESET_PRESSURE = 0xFF;
        final static float PRESET_SIZE = 300;

        public TouchView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            if(touching){

                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(1);

                paint.setColor(Color.WHITE);
                RectF ovalTouch = new RectF(x-touchMajor/2, y-touchMinor/2, x+touchMajor/2, y+touchMinor/2);

                canvas.drawOval(ovalTouch, paint);
            }
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // TODO Auto-generated method stub
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                    MeasureSpec.getSize(heightMeasureSpec));
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            // TODO Auto-generated method stub

            pressure = event.getPressure();
            if(pressure > 1){
                pressure = 1;
            }

            size =event.getSize();

            String act;

            int action = event.getAction();

            switch(action){
                case MotionEvent.ACTION_MOVE:
                    act = "ACTION_MOVE\n";
                    x = event.getX();
                    y = event.getY();
                    touchMajor = event.getTouchMajor();
                    touchMinor = event.getTouchMinor();
                    touching = true;
                    break;
                case MotionEvent.ACTION_DOWN:
                    act = "ACTION_DOWN\n";
                    x = event.getX();
                    y = event.getY();
                    touchMajor = event.getTouchMajor();
                    touchMinor = event.getTouchMinor();
                    touching = true;
                    break;
                case MotionEvent.ACTION_UP:
                    act = "ACTION_UP\n";
                    touching = false;
                    break;
                default:
                    act = "XXX\n";
                    touching = false;
            }

            act += event.toString();
            textView.setText(act);

            invalidate();
            return true;
        }
    }

    TextView textView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new TouchView(this));
        textView = new TextView(this);
        textView.setText("Waiting");
        TouchView touchView = new TouchView(this);
        LinearLayout mainScreen = new LinearLayout(this);
        mainScreen.setOrientation(LinearLayout.VERTICAL);
        mainScreen.addView(textView);
        mainScreen.addView(touchView);
        setContentView(mainScreen);
    }
}
