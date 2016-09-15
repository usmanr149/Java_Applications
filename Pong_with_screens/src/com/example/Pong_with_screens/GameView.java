package com.example.Pong_with_screens;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by usman on 01/02/15.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread _thread;

    public GameView(Context context, AttributeSet attrs){
        super(context, attrs);

        //So we can listen for events...
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);

        //and instantiate the thread
        _thread = new GameThread(holder, context, new Handler());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        _thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        _thread.stop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x, y;

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                _thread.setBallPos(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                _thread.setBallPos(x,y);
                break;
        }
        return true;
    }



}