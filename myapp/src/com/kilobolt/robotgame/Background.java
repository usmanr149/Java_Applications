package com.kilobolt.robotgame;

/**
 * Created by usman on 26/11/14.
 */
public class Background {

    private int bgX, bgY, speedX;
    
    public Background(int x, int y){

        //x and y is essetially speed, it can be passed to this function to move the background.
        bgX = x;
        bgY = y;
        speedX = 0;
    }

    public void update(){
        bgX += speedX;
        //the length of the image in pixels is 4320, so when the character moves the background is scrolled
        if(bgX <= -2160){
            bgX += 4320;
        }
    }

    public int getBgX() {
        return bgX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
}
