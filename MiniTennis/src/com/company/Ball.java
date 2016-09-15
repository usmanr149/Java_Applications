package com.company;

import java.awt.*;

/**
 * Created by usman on 04/05/15.
 */
public class Ball {

    private static final int DIAMETER = 30;
    int x = 0;
    int y = 0;
    int xa = 1;
    int ya = 1;

    // Ball sprite needs the reference to the Game object to obtain the borders of the canvas
    private Game game;

    public Ball(Game game){
        this.game = game;
    }

    void move() {
        boolean changeDirection = true;
        if (x + xa < 0)
            xa = game.speed;
        if (x + xa > game.getWidth() - DIAMETER)
            xa = -game.speed;
        if (y + ya < 0)
            ya = game.speed;
        if (y + ya > game.getHeight() - DIAMETER)
            game.gameOver();
        if(collision()){
            ya = -game.speed;
            y = game.racquet.getTopY() - DIAMETER; //getTopY is the location of bat
            game.speed++;
        }else changeDirection = false;

        if(changeDirection) Sound.BALL.play();

        x = x + xa;
        y = y + ya;
    }

    private boolean collision(){
        return game.racquet.getBoundsforBat().intersects(getBoundsforBall()); //returns location of bat and
        //compares it to location of ball. If intersects return true.
    }

    public void paint(Graphics g) {
        g.fillOval(x, y, 30, 30);

    }

    public Rectangle getBoundsforBall(){
        return new Rectangle(x,y,DIAMETER,DIAMETER);
    }


}
