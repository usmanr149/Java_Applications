package kilobolt;

import java.awt.*;

/**
 * Created by usman on 19/11/14.
 */
public class Projectile {

    private int x, y, speedX;
    private boolean visible;

    private Rectangle r;

    public Projectile(int StartX, int StartY){
        x=StartX;
        y=StartY;
        speedX=7;
        visible=true;

        r = new Rectangle(0, 0, 0, 0);

    }

    public void update()
    {
        x+=speedX;
        r.setBounds(x, y, 10, 5);
        if(x>800){
            visible=false;
            r=null;
        }
        if(x<800){
            checkCollision();
        }
    }

    private void checkCollision() {
        if(r.intersects(StartingClass.hb.r)){
            visible = false;
            if (StartingClass.hb.health > 0) {
                StartingClass.hb.health -= 1;
            }
            if (StartingClass.hb.health == 0) {
                StartingClass.hb.setCenterX(-100);
                StartingClass.score += 5;


            }
        }

        if (r.intersects(StartingClass.hb2.r)){
            visible = false;
            if (StartingClass.hb2.health > 0) {
                StartingClass.hb2.health -= 1;
            }
            if (StartingClass.hb2.health == 0) {
                StartingClass.hb2.setCenterX(-100);
                StartingClass.score += 5;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}
