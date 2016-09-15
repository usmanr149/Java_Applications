package com.company;

import javax.swing.*;

/**
 * Created by usman on 13/06/15.
 */
//The destroyed variable keeps the state of the brick.
public class Brick extends Sprite {
    String brickie = "/home/usman/Java_Application/Breakout2/src/com/company/Images/brickie.png";

    boolean destroyed;

    public Brick(int x, int y){
        this.x = x;
        this.y = y;

        ImageIcon ii = new ImageIcon(this.getClass().getResource(brickie));
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        destroyed = false;

    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }
}