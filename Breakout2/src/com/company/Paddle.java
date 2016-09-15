package com.company;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by usman on 13/06/15.
 */
public class Paddle extends Sprite implements Commons {

    String paddle = "/home/usman/Java_Application/Breakout2/src/com/company/Images/paddle.png";

    int dx;

    public Paddle(){

        ImageIcon ii = new ImageIcon(this.getClass().getResource(paddle));
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        resetState();
    }

    public void move(){
        x += dx;
        if( x <= 2)
            x = 2;
        if( x>= Commons.PADDLE_RIGHT)
            x = Commons.PADDLE_RIGHT;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx = -2;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 2;
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            dx = 0;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }
    }

    public void resetState(){
        x = 200;
        y = 360;
    }

}