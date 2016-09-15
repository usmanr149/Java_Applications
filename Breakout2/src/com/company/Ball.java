package com.company;

import javax.swing.*;

/**
 * Created by usman on 13/06/15.
 */
public class Ball extends Sprite implements Commons {

    private int xdir;
    private int ydir;

    protected String ball = "/home/usman/Java_Application/Breakout2/src/com/company/Images/ball.png";

    public Ball(){
        xdir = 1;
        ydir = -1;

        ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        resetState();
    }

    public void move(){
        x+=xdir;
        y+=ydir;

        if( x == 0){
            setXDir(1);
        }

        if( x == BALL_RIGHT){
            setXDir(-1);
        }

        if( y == 0){
            setYDir(1);
        }
    }

    public void resetState(){
        x = 250;
        y = 350;
    }

    public void setXDir(int x){
        xdir = x;
    }

    public void setYDir(int y){
        ydir = y;
    }

    public int getYDir(){
        return ydir;
    }

}