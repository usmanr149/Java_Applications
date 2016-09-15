package com.example.BallBounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.SurfaceHolder;

/**
 * Created by usman on 27/12/14.
 */
public class GameThread extends Thread {

/** Handle to the surface manager object we interact with */
    private SurfaceHolder _surfaceholder;
    private Paint _paint;
    private GameState _state;

    public GameThread (SurfaceHolder surfaceHolder, Context context, Handler handler) {

        _surfaceholder = surfaceHolder;
        _paint = new Paint();
        _state = new GameState();

    }

    @Override
    public void run() {
        while (true) {
            Canvas canvas = _surfaceholder.lockCanvas();
            _state.CheckCollission();
            _state.update();
            _state.draw(canvas, _paint);
            _surfaceholder.unlockCanvasAndPost(canvas);
        }
    }

    public GameState getGameState() {
    return _state;
    }
}