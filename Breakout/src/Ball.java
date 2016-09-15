import javax.swing.*;

/**
 * Created by usman on 05/08/15.
 */
public class Ball extends Sprite implements Commons{

    private int xdir = -1;
    private int ydir = -1;

    public Ball(){

        xdir = -1;
        ydir = -1;

        ImageIcon ii = new ImageIcon("/home/usman/Java_Application/Breakout/src/ball.png");
        image = ii.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);

        resetState();
        //setRadius(radius);
        //width = image.getWidth(null);
        //height = image.getHeight(null);

    }

    public void move(){
        x += xdir;
        y += ydir;

        if(x==0){
            setXDir(1);
        }

        if(x == BALL_RIGHT){
            setXDir(-1);
        }
        if(y == 0){
            setYDir(1);
        }
    }

    public void resetState(){
        x = 230;
        y = 335;
    }

    public void setXDir(int x){
        xdir = x;
    }

    public void setYDir(int y){
        ydir = y;
    }

    public int getYdir(){
        return ydir;
    }

}
