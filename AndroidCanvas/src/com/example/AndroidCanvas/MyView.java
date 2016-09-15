package com.example.AndroidCanvas;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by usman on 28/12/14.
 */
public class MyView extends View {

    boolean freeTouched = false;
    Path freePath;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);

        if(freeTouched){
            canvas.drawPath(freePath, paint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_UP:
                // finger leaves the screen
                freeTouched = false;
                break;
            case MotionEvent.ACTION_DOWN:
                // finger touches the screen
                freeTouched = true;
                freePath = new Path();
                freePath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                // finger moves on the screen
                freePath.lineTo(event.getX(), event.getY());
                invalidate();
                break;
        }

        // tell the system that we handled the event and no further processing is required
        return true;
    }

}