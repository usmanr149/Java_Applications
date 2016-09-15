package com.example.PongGameJan87;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

/**
 * Created by usman on 07/01/15.
 */
public class PongView extends SurfaceView implements SurfaceHolder.Callback {

    /**
     * The game thread that actually draws the animation and handles user input.
     */
    private PongThread mPongThread;

    /**
     * Text view to display game status (Win, Lose, Paused etc.).
     */
    private TextView mStatusView;

    /**
     * Text view to display game score.
     */
    private TextView mScoreView;

    public PongView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        mPongThread = new PongThread(holder, context,
                handlemessage(m)-> {
                    mStatusView.setVisibility(m.getData().getInt("vis"));
                    mStatusView.setText(m.getData().getString("text"));
            },
            handlemessage(m) -> {
            mScoreView.setText(m.getData().getString("text"));
        },
        attributeSet
                );
        setFocusable(true);
    }

    /**
     * @param textView to be used for status messages
     */
    public void setStatusView(TextView textView){
        mScoreView = textView;
    }

    /**
     * @param textView to be used to display score
     */
    public void setmScoreView(TextView textView){
        mStatusView = textView;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if(!hasWindowFocus){
            mPongThread.pause;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        mPongThread.setSurfaceSize(width, height);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        mPongThread.setRunning(true);
        mPongThread.start();
    }



}
