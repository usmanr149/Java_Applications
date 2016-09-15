package com.example.AndroidProgressDialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class AndroidProgressDialog extends Activity {
    /**
     * Called when the activity is first created.
     */

    Button buttonStart;
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buttonStart = (Button)findViewById(R.id.start);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        buttonStart.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                buttonStart.setClickable(false);
                new asyncTaskUpdateProgress().execute();
            }

        });
    }

    public class asyncTaskUpdateProgress extends AsyncTask<Void, Integer, Void>
    {

        int progress;
        @Override
        protected void onPostExecute(Void result){
            buttonStart.setClickable(true);
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            progress = 0;
            progressDialog = ProgressDialog.show(AndroidProgressDialog.this, "ProgressDialog", "Uploading");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {
            while(progress<100){
                progress++;
                publishProgress(progress);
                SystemClock.sleep(100);
            }
            return null;
        }
    }

}