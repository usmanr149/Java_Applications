package com.example.AnimatedView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;

/**
 * Created by usman on 06/01/15.
 */
public class GameState {

    private int x, y;

    //screen width and height
    private int _screenWidth = 480, _screenHeight = 680;

    //The ball
    final int _ballSize = 10;
    int _ballX = 100; 	int _ballY = 100;
    int _ballradius = 20;
    int _ballVelocityX = 4; 	int _ballVelocityY = 4;

    //The bats
    final int _batLength = 75;	final int _batHeight = 20;
    int _topBatX = (_screenWidth/2) - (_batLength / 2);
    final int _topBatY = 20;
    int _bottomBatX = (_screenWidth/2) - (_batLength / 2);
    final int _bottomBatY = _screenHeight - _batHeight;
    final int _batSpeed = 3;

    //The update method
    public void update() {

        _ballX += _ballVelocityX;
        _ballY += _ballVelocityY;

        if(_topBatX + _batLength/2 < _ballX){
            _topBatX+=_batSpeed;
        }

        if(_topBatX + _batLength/2 > _ballX){
            _topBatX-=_batSpeed;
        }

        //DEATH!
        if( (_ballY - _ballradius) > _screenHeight || (_ballY + _ballradius) < 0)
        {_ballX = _screenWidth/2; 	_ballY = _screenHeight/2;}  	//Collisions with the sides

        if( (_ballX + _ballradius) > _screenWidth || (_ballX - _ballradius) < 0)
            _ballVelocityX *= -1; 	//Collisions with the bats

        if( (_ballX  > _topBatX && _ballX < _topBatX+_batLength &&
                (_ballY  +_ballradius) < (_screenHeight - _batHeight) ) ) {
            _ballVelocityY *= -1;  //Collisions with the bats
        }

        if(_ballX > _bottomBatX && _ballX < _bottomBatX+_batLength
                && (_ballY + _ballradius > _batHeight) )
            _ballVelocityY *= -1;

    }

    public void setValues( float xPos, float yPos){
        x = (int) xPos;
        y = (int) yPos;
        if (x > (_screenWidth - _batLength )){
            x = _screenWidth - _batLength;
        }
    }

    public boolean keyPressed(int keyCode, KeyEvent msg)
    {
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) //left
        {
            _topBatX += _batSpeed; _bottomBatX -= _batSpeed;
        }

        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) //right
        {
            _topBatX -= _batSpeed; _bottomBatX += _batSpeed;
        }

        return true;
    }

    //the draw method
    public void draw(Canvas canvas, Paint paint) {

        //Clear the screen
        canvas.drawRGB(20, 20, 20);

        //set the colour
        paint.setColor(Color.RED);

        //draw the ball
        canvas.drawCircle(_ballX,_ballY,_ballradius, paint);

        //set the colour
        paint.setColor(Color.GREEN);
        //draw the bats
        canvas.drawRect(new Rect(_topBatX, _topBatY, _topBatX + _batLength,
                _topBatY + _batHeight), paint); //top bat
        canvas.drawRect(new Rect(x, _bottomBatY, x + _batLength,
                _bottomBatY + _batHeight), paint); //bottom bat

//        //set the colour
//        paint.setColor(Color.RED);
//        //draw the ball
//        canvas.drawCircle(x, y, 20, paint);

    }

}