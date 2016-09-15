package com.kilobolt.robotgame;

import java.math.RoundingMode;

import android.graphics.Rect;

/**
 * Created by usman on 26/11/14.
 */
public class Enemy {

    private int power, centerX, speedX, centerY;
    private Background bg = GameScreen.getBg1();
    private Robot robot = GameScreen.getRobot();

    public Rect r = new Rect(0, 0, 0, 0);
    public int health = 5;

    private int movementSpeed;

    //Behavioral methods
    public void update(){
        follow();
        centerX+=speedX;
        speedX = bg.getSpeedX() * 5 + movementSpeed; //get the moving speed from funnction background.
        //set(int left, top, right, bottom)
        r.set(centerX - 25, centerY - 25, centerX + 25, centerY + 25);

        if(Rect.intersects(r, Robot.yellowRed)){
            checkCollision();
        }
    }

    private void checkCollision() {
        if (Rect.intersects(r, Robot.rect) || Rect.intersects(r, Robot.rect2)
            || Rect.intersects(r, Robot.rect3) || Rect.intersects(r, Robot.rect4) ){

        }
    }

    public void follow(){

        if (centerX < -95 || centerX > 180){
            movementSpeed = 0;
        }

        else if ( Math.abs(robot.getCenterX() - centerX ) ){
            movementSpeed = 0;
        }

        else {
            if( robot.getCenterX() >= centerX ){
                movementSpeed = 1;
            } else {
                movementSpeed = -1;
            }
        }
    }

    public void die(){

    }

    public void attack(){

    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public Rect getR() {
        return r;
    }

    public void setR(Rect r) {
        this.r = r;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
