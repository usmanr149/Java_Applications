package com.example.recipesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by usman on 16/10/15.
 */
public class Splash extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        beginAnimation();
    }

    private void beginAnimation(){
        // header animation
        TextView header = (TextView) findViewById(R.id.SplashHeader);
        Animation headerAnim = AnimationUtils.loadAnimation(this, R.anim.splash_text);
        header.startAnimation(headerAnim);

        // Footer Animation
        TextView footer = (TextView) findViewById(R.id.SplashFooter);
        Animation footerAnim = AnimationUtils.loadAnimation(this, R.anim.splash_text);
        footer.startAnimation(footerAnim);

        //Image Animation
        ImageView image = (ImageView) findViewById(R.id.SplashImageCenter);
        Animation imageAnim = AnimationUtils.loadAnimation(this, R.anim.splash_image);
        image.startAnimation(imageAnim);

        //Prepare end of animation event
        imageAnim.setAnimationListener(new AnimationListener (){
            public void onAnimationEnd(Animation animation){
                //All done, open the Main Menu
                startActivity(new Intent(Splash.this, MainMenu.class));
            }
            // Required method, nothing to do here
            public void onAnimationRepeat(Animation animation){}

            // Required method, nothing to do here
            public void onAnimationStart(Animation animation){}
        });
    }
    @Override
    protected void onPause(){
        super.onPause();

        //clear the animation. Start fresh on resume.
        TextView header = (TextView) findViewById(R.id.SplashHeader);
        header.clearAnimation();

        TextView footer = (TextView) findViewById(R.id.SplashFooter);
        footer.clearAnimation();

        ImageView image = (ImageView) findViewById(R.id.SplashImageCenter);
        image.clearAnimation();

    }

    @Override
    protected void onResume(){
        super.onResume();
        // Start the animation from the begining
        beginAnimation();
    }
}
