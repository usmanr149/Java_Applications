package com.example.AndroigTouchView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by usman on 04/01/15.
 */
    public class AndroidSurfaceView extends View{

    //In this test, handle maximum of 2 pointer
    final int MAX_POINT_CNT = 2;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    float[] x = new float[MAX_POINT_CNT];
    float[] y = new float[MAX_POINT_CNT];
    boolean[] isTouch = new boolean[MAX_POINT_CNT];

    public AndroidSurfaceView(Context context) {
        super(context);

        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        if(isTouch[0]){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1);
            paint.setColor(Color.RED);
            canvas.drawCircle(x[0], y[0], 50f, paint);
        }
        if(isTouch[1]){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(x[1], y[1], 50f, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // TODO Auto-generated method stub

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
                        isTouch[pointerId] = true;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        isTouch[pointerId] = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isTouch[pointerId] = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isTouch[pointerId] = false;
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        isTouch[pointerId] = false;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        isTouch[pointerId] = false;
                        break;
                    default:
                        isTouch[pointerId] = false;
                }
            }
        }

        invalidate();
        return true;
    }

}