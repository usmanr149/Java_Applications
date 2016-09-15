import java.awt.*;

/**
 * Created by usman on 05/08/15.
 */
public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    protected double radius;

    public void setX(int x){
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

    public double getRadius(){
        return radius;
    }

    Image getImage(){
        return image;
    }
}
