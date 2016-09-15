package com.example.BallBounce;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;

/**
 * Created by usman on 27/12/14.
 */
public class GameState {

    private int radius = 60;   // the radius of the ball
    private int ball2_pos_x=100, ball2_pos_y=250,ball_pos_x =300, ball_pos_y=200;
    private int speedX=4, speedY=2;
    private int speedX2=-4, speedY2=-5;
    private boolean ballballCollssion = true;
    private int width = 480, height = 680;

    //the draw method
    public void draw(Canvas canvas, Paint paint) {

        //Clear the screen
        canvas.drawRGB(20, 20, 20);

        //set the colour
        paint.setColor(Color.GREEN);
        //draw the ball
        canvas.drawCircle(ball_pos_x, ball_pos_y, radius, paint);

        //set the colour
        paint.setColor(Color.RED);
        //draw the ball
        canvas.drawCircle(ball2_pos_x, ball2_pos_y, radius, paint);

    }

    public void CheckCollission(){

        double speedholdx1, speedholdy1, speedholdx2, speedholdy2;
        double phi=0, deltaX, deltaY, delta1,delta2;
        double finalspeedX1, finalspeedY1, finalspeedX2, finalspeedY2;

        //This code shouldn't be executed if balls are already colliding
        if( ballballCollssion == true && ( (Math.pow(ball_pos_x-ball2_pos_x,2)
                + Math.pow(ball_pos_y-ball2_pos_y,2) ) <= Math.pow(2*radius, 2) ) )
        {
            speedholdx1 = speedX;
            speedholdy1 = speedY;
            speedholdx2 = speedX2;
            speedholdy2 = speedY2;

            deltaX = ball2_pos_x - ball_pos_x;
            deltaY = ball_pos_y - ball2_pos_y;
            phi = Math.atan(deltaY / deltaX);

            if (deltaX == 0) phi = 90;

            //speedX = (int) (Math.sqrt(speedholdx2*speedholdx2 + speedholdy2*speedholdy2) * Math.cos(angle2-phi));
            //speedY = (int) (Math.sqrt(speedholdx1*speedholdx1 + speedholdy1*speedholdy1) * Math.sin(angle1 - phi));

            //speedX2 = (int) (Math.sqrt(speedholdx1*speedholdx1 + speedholdy1*speedholdy1) * Math.cos(angle1-phi));
            //speedY2 = (int) (Math.sqrt(speedholdx2*speedholdx2 + speedholdy2*speedholdy2) * Math.sin(angle2-phi));


            finalspeedX1 = ((speedholdx2 * Math.cos(phi) - speedholdy2 * Math.sin(phi)) * Math.cos(phi))
                    + ((speedholdx1 * Math.sin(phi) + speedholdy1 * Math.cos(phi)) * Math.sin(phi));

            finalspeedY1 = -((speedholdx2 * Math.cos(phi) - speedholdy2 * Math.sin(phi)) * Math.sin(phi))
                    + ((speedholdx1 * Math.sin(phi) + speedholdy1 * Math.cos(phi)) * Math.cos(phi));

            finalspeedX2 = ((speedholdx1 * Math.cos(phi) - speedholdy1 * Math.sin(phi)) * Math.cos(phi))
                    + ((speedholdx2 * Math.sin(phi) + speedholdy2 * Math.cos(phi)) * Math.sin(phi));

            finalspeedY2 = -((speedholdx1 * Math.cos(phi) - speedholdy1 * Math.sin(phi)) * Math.sin(phi))
                    + ((speedholdx2 * Math.sin(phi) + speedholdy2 * Math.cos(phi)) * Math.cos(phi));



            speedX = round(finalspeedX1);
            speedY = round(finalspeedY1);


            speedX2 = round(finalspeedX2);
            speedY2 = round(finalspeedY2);


            ball_pos_x+=speedX;
            ball_pos_y+=speedY;
            ball2_pos_x+=speedX2;
            ball2_pos_y+=speedY2;

            ballballCollssion = false;

        }

        //Once the ball collision calculations are done ballballcollision is set to false and it can only be set to
        //true again by the following statement.
        if( (Math.pow(ball_pos_x-ball2_pos_x,2) + Math.pow(ball_pos_y-ball2_pos_y,2)) >= Math.pow(2*radius, 2) )
            ballballCollssion = true;


    }

    private int round (double d)
    {
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;
        }else{
            return d<0 ? -(i+1) : i+1;
        }
    }


    public void update() {

        // May cross both x and y bounds

        //Ball 1: Hit the bottom edge?
        if ( ( ball_pos_y + radius >= height ) ){
            speedY = -speedY;
            ball_pos_y = height - radius ;
        }

        //Ball 1: Hit the top edge?
        if( ball_pos_y - radius <= 0 ) {
            speedY = -speedY;
            ball_pos_y = radius;
        }

        //Ball 1: Hit the left side?
        if( ball_pos_x - radius <= 0 ) {
            speedX = -speedX;
            ball_pos_x = radius;
        }

        //Ball 1: Hit the right side?
        if( ball_pos_x + radius >= width ) {
            speedX = -speedX;
            ball_pos_x = width - radius;
        }


        //Ball 2: Hit the bottom edge?
        if ( ( ball2_pos_y + radius >= height ) ){
            speedY2 = -speedY2;
            ball2_pos_y = height - radius ;
        }

        //Ball 2: Hit the top edge?
        if( ball2_pos_y - radius <= 0 ) {
            speedY2 = -speedY2;
            ball2_pos_y = radius;
        }

        //Ball 2: Hit the left side?
        if( ball2_pos_x - radius <= 0 ) {
            speedX2 = -speedX2;
            ball2_pos_x = radius;
        }

        //Ball 2: Hit the right side?
        if( ball2_pos_x + radius >= width ) {
            speedX2 = -speedX2;
            ball2_pos_x = width - radius;
        }

        ball_pos_x+=speedX;
        ball_pos_y+=speedY;
        ball2_pos_x+=speedX2;
        ball2_pos_y+=speedY2;

    }



    public boolean keyPressed(int keyCode, KeyEvent msg)
    {
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) //left
        {
        }

        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) //right
        {
        }

        return true;
    }

}