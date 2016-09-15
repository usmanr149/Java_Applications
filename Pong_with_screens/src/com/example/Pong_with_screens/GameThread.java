package com.example.Pong_with_screens;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by usman on 01/02/15.
 */
public class GameThread extends Thread {

    private float xPos, yPos;
    private android.os.Handler handler;

    /** Handle to the surface manager object we interact with */
    private SurfaceHolder _surfaceHolder;
    private Paint paint;
    private GameState _state;

    public GameThread (SurfaceHolder surfaceHolder, Context context, android.os.Handler handler){
        _surfaceHolder = surfaceHolder;
        paint = new Paint();
        _state =    new GameState();
        this.handler = handler;
    }

    public void setBallPos( float x, float y){
        xPos = x;
        yPos = y;
    }

    @Override
    public void run(){
        while (true)
        {
            Canvas canvas = _surfaceHolder.lockCanvas();
            _state.update();
            _state.setValues(xPos, yPos);
            _state.draw(canvas, paint);
            _surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public GameState getGameState(){
        return _state;
    }

}