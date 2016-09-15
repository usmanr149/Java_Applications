package com.example.Pong_with_screens;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by usman on 01/02/15.
 */
public class GameState {

    private int x, y;

    //screen width and height
    private int _screenWidth = 480, _screenHeight = 680;

    //The ball
    int _ballX = 100;
    int _ballY = 100;
    final int _ballradius = 30;
    int _ballVelocityX = 4;
    int _ballVelocityY = 4;

    //The bats
    final int _batLength = 75;
    final int _batHeight = 40;
    int _topBatX = (_screenWidth / 2) - (_batLength / 2);
    final int _topBatY = 20;
    int _bottomBatX = (_screenWidth / 2) - (_batLength / 2);
    final int _bottomBatY = _screenHeight - _batHeight;
    final int _batSpeed = 2;

    //The update method
    public void update() {

        /*
        //Hit the bottom paddle?
        if ( ( _ballY + _ballradius >= _bottomBatY  ) )
            if(_ballX - _ballradius >= _bottomBatX || _ballX + _ballradius <= _bottomBatX +_batLength)
        {
            _ballVelocityY = -_ballVelocityY;
            _ballY = _bottomBatY - _ballradius;
        }
        */

        //Hit the top paddle?
        if( (_ballY - _ballradius) < _batHeight  )
            if( (_ballX - _ballradius) >= _topBatX && _ballX <= (_topBatX +_batLength) )
            {
                //_ballY = _batHeight;
                _ballVelocityY = -_ballVelocityY;
                //_ballY = _topBatY - _ballradius;
            }

        //Hit the bottom paddle?
        if( (_ballY + _ballradius) > _bottomBatY  )
            if( (_ballX - _ballradius) >= _bottomBatX && _ballX <= (_bottomBatX +_batLength) )
            {
                //_ballY = _screenHeight - _batHeight;
                _ballVelocityY = -_ballVelocityY;
                //_ballY = _topBatY - _ballradius;
            }

        //Move the paddle
        if(_topBatX + _batLength/2 > _ballX){
            _topBatX -= _batSpeed;
        }

        if(_topBatX + _batLength/2 < _ballX){
            _topBatX += _batSpeed;
        }

        if(_ballY < 0 || _ballY > _screenHeight)
        {
            _ballY = 200;
            _ballX = 200;
        }

        /*
        //Hit the bottom edge?
        if ( ( _ballY + _ballradius >= _screenHeight ) ){
            _ballVelocityY = -_ballVelocityY;
            _ballY = _screenHeight - _ballradius;
        }

        //Hit the top edge?
        if( _ballY - _ballradius <= 0 ) {
            _ballVelocityY = -_ballVelocityY;
            _ballY = _ballradius;
        }
        */

        //Hit the left side?
        if( _ballX - _ballradius <= 0 ) {
            _ballVelocityX = - _ballVelocityX;
            _ballX = _ballradius;
        }

        //Hit the right side?
        if( _ballX + _ballradius >= _screenWidth ) {
            _ballVelocityX = -_ballVelocityX;
            _ballX = _screenWidth - _ballradius;
        }

        _ballX += _ballVelocityX;
        _ballY += _ballVelocityY;

    }


    public void setValues(float xPos, float yPos) {
        x = (int) xPos;
        y = (int) yPos;
        if (x > (_screenWidth - _batLength)) {
            x = _screenWidth - _batLength;
        }
    }

    //the draw method
    public void draw(Canvas canvas, Paint paint) {

        //Clear the screen
        canvas.drawRGB(20, 20, 20);

        //set the colour
        paint.setColor(Color.RED);

        //draw the ball
        canvas.drawCircle(_ballX, _ballY, _ballradius, paint);

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