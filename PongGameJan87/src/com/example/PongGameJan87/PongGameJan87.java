package com.example.PongGameJan87;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PongGameJan87 extends Activity {

    private static final int MENU_NEW_GAME = 1;
    private static final int MENU_RESUME = 1;
    private static final int MENU_EXIT = 1;

    private PongThread mPongThread;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        final PongView mPongView = (PongView) findViewById(R.id.main);
        mPongView.setStatusView((TextView)findViewById(R.id.status));
        mPongView.setScoreView((TextView)findViewById(R.id.score));

        mPongThread = mPongView.getGameThread();
        if (savedInstanceState == null){
            mPongThread.setState(PongThread.STATE_READY);
        } else {
            mPongThread.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        mPongThread.pause();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(outState);
        mPongThread.saveState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_NEW_GAME, 0, "New Game");
        menu.add(0, MENU_RESUME, 0, "Resume");
        menu.add(0, MENU_EXIT, 0, "Exit");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case MENU_NEW_GAME:
                mPongThread.startNewGame();
                return true;
            case MENU_EXIT:
                finish();
                return true;
            case MENU_RESUME:
                mPongThread.unPause();
                return true;
        }
        return false;
    }

}
