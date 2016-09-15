package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by usman on 04/05/15.
 */
public class Racquet {

    private static final int Y = 330;
    private static final int WIDTH = 60;
    private static final int HEIGHT = 10;

    int x = 0;
    int xa = 0;
    private Game game;

    public Racquet(Game game){
        this.game = game;
    }

    public void move(){
        if(x+xa>0 && x+xa<game.getWidth() - WIDTH)
            x = x + xa;
    }

    public void paint(Graphics2D g){
        g.fillRect(x, Y, WIDTH, HEIGHT);
    }

    public void keyRelease(KeyEvent ev){
        xa = 0;
    }

        public void keyPressed(KeyEvent ev){
            if(ev.getKeyCode() == KeyEvent.VK_RIGHT)
                xa = game.speed;
            if(ev.getKeyCode() == KeyEvent.VK_LEFT)
                xa = -game.speed;
        }

    public Rectangle getBoundsforBat(){
        return new Rectangle(x, Y, WIDTH,HEIGHT);
    }

    public int getTopY(){
        return Y - HEIGHT;
    }

}