package com.example.PongGameJan87;

import java.util.Random;
import java.util.logging.Handler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by usman on 07/01/15.
 */
public class PongThread extends Thread {

    /*
     * State related constants
     */

    public static final int STATIC_PAUSE = 0;
    public static final int STATE_READY = 1;
    public static final int STATE_RUNNING = 2;
    public static final int STATE_LOSE = 3;
    public static final int STATE_WIN = 4;

    /*
     * Physics constants
     */
    private static final int PHYS_BALL_SPEED = 8;
    private static final int PHYS_PADDLE_SPEED = 8;
    private static final int PHYS_FPS = 60;
    private static final double PHYS_MAX_BOUNCE_ANGLE = Math.PI / 4; // 45 degrees in radians
    private static final int PHYS_COLLISION_FRAMES = 5;

    /*
    * Constants used when game state is saved/restored
    */
    private static final String KEY_HUMAN_PLAYER_DATA = "humanPlayer";
    private static final String KEY_COMPUTER_PLAYER_DATA = "computerPlayer";
    private static final String KEY_BALL_DATA = "ball";

    private static final String TAG = "PongThread";

    /**
     * Handle to the surface manager object we interact with
     */
    private final SurfaceHolder mSurfaceHolder;

    /**
     * Message handler used by thread to interact with status TextView
     */
    private final Handler mStatusHandler;

    /**
     * Message handler used by thread to interact with score TextView
     */
    private final Handler mScoreHandler;

    /**
     * Handle to the application context
     */
    private final Context mContext;

    /**
     * Indicate whether the surface has been created & is ready to draw
     */
    private boolean mRun = false;

    /**
     * The state of the game.
     */
    private int mState;

    /*
     * Game objects
     */
    private Player mHumanPlayer;
    private Player mComputerPlayer;
    private Ball mBall;

    /**
     * Median line paint style.
     */
    private Paint mMedianPaintLine;

    /**
     * Canvas bounds paint.
     */
    private Paint mCanvasBoundsPaint;

    /**
     * Current height of the canvas.
     */
    private int mCanvasHeight = 1;

    /**
     * Current width of the canvas.
     */
    private int mCanvasWidth = 1;

    /**
     * Used to make computer to "forget" to move the paddle in order to behave more like a human opponent.
     */
    private Random mRandonGen;

    /**
     * The probability to move computer paddle.
     */
    private float mComputerMoveProbability;

    PongThread(final SurfaceHolder surfaceHolder,
               final Context context,
               final android.os.Handler statusHandler,
               final android.os.Handler scoreHandler,
               final AttributeSet attributeSet) {
        mSurfaceHolder = surfaceHolder;
        mStatusHandler = statusHandler;
        mScoreHandler = scoreHandler;
        mContext = context;

        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.PongView);

        int paddleHeight = a.getInt(R.styleable.PongView_paddleHeight, 85);
        int paddleWidth = a.getInt(R.styleable.PongView_paddleWidth, 25);
        int ballRadius = a.getInt(R.styleable.PongView_ballRadius, 15);

        a.recycle();

        Paint humanPlayerPaint = new Paint();
        humanPlayerPaint.setAntiAlias(true);
        humanPlayerPaint.setColor(Color.BLUE);

        mHumanPlayer = new Player(paddleWidth, paddleHeight, humanPlayerPaint);

            Paint computerPlayerPaint = new Paint();
            computerPlayerPaint.setAntiAlias(true);
            computerPlayerPaint.setColor(Color.RED);

        mComputerPlayer = new Player(paddleWidth, paddleHeight, computerPlayerPaint);

            Paint ballPaint = new Paint();
            ballPaint.setAntiAlias(true);
            ballPaint.setColor(Color.GREEN);

        mBall = new Ball(ballRadius, ballPaint);

        mMedianPaintLine = new Paint();
        mMedianPaintLine.setAntiAlias(true);
        mMedianPaintLine.setColor(Color.YELLOW);
        mMedianPaintLine.setAlpha(80);
        mMedianPaintLine.setStyle(Style.FILL_AND_STROKE);
        mMedianPaintLine.setStrokeWidth(2.0f);
        mMedianPaintLine.setPathEffect(new DashPathEffect(new float[]{5, 5}, 0));

        mCanvasBoundsPaint = new Paint();
        mCanvasBoundsPaint.setAntiAlias(true);
        mCanvasBoundsPaint.setColor(Color.YELLOW);
        mCanvasBoundsPaint.setStyle(Style.STROKE);
        mCanvasBoundsPaint.setStrokeWidth(1.0f);

        mRandonGen = new Random();
        mComputerMoveProbability = 0.6f;
    }

    /**
     * Game loop.
     */
    @Override
    public void run(){
        long mNextGameTick = SystemClock.uptimeMillis();
        int skipTicks = 1000/PHYS_FPS;
        while (mRun){
            Canvas c = null;
            try{
                c = mSurfaceHolder.lockCanvas(null);
                if( c != null ) {
                    synchronized (mSurfaceHolder) {
                        if (mState == STATE_RUNNING){
                            updatePhysics();
                        }
                        updateDisplay(c);
                    }
                }
            } finally {
                if( c!= null){
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }
            }

            mNextGameTick += skipTicks;
            long sleepTime = mNextGameTick - SystemClock.uptimeMillis();
            if(sleepTime > 0){
                try{
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e){
                    Log.e(TAG, "Interrupted", e);
                }
            }

        }
    }

    /**
     * Used to signal game thread whether it should be running or not.
     *
     * @param running true to run, false to shut down
     */
    void setRunning(boolean running){
        mRun = running;
    }

    /**
     * Save game state to the provided Bundle.
     *
     * @param map bundle to save game state
     */



}
