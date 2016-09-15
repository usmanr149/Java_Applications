package com.company;

import java.awt.*;

/**
 * Created by usman on 13/06/15.
 */
//The sprite class is a base class for all objects on board.
public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    public void setX(int X){
        this.x = x;
    }

    public int getX(){
        return x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    Image getImage(){
        return image;
    }

    Rectangle getRect(){
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

}
