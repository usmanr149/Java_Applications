import java.applet.Applet;
import java.awt.*;

/**
 * Created by usman on 30/11/14.
 */
public class daApplet extends Applet
{
    private int radius = 30;   // the radius of the ball
    private int ball2_pos_x=100, ball2_pos_y=250,ball_pos_x =300, ball_pos_y=200;
    private int speedX=4, speedY=2;
    private int speedX2=-4, speedY2=-10;
    private int width, height;
    private boolean ballballCollssion = true;

    Image offScreenBuffer;
    public void update(Graphics g)
    {
        Graphics gr;
        setBackground(Color.WHITE);
        if (offScreenBuffer==null ||
                (! (offScreenBuffer.getWidth(this) == this.size().width
                        && offScreenBuffer.getHeight(this) == this.size().height) ) )
        {
            offScreenBuffer = this.createImage(size().width, size().height);
        }
        gr = offScreenBuffer.getGraphics();
        paint(gr);
        g.drawImage(offScreenBuffer, 0, 0, this);
    }

    public void paint(Graphics g) {
        // paint whatever normally gets painted.
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 400, 400);
        //g.fillRect(ball2_pos_x - 5*radius, ball2_pos_y - 5*radius, 10 * radius, 10 * radius);
        g.setColor(Color.BLUE);  // set the color to paint with
        g.fillOval(ball_pos_x, ball_pos_y, 2 * radius, 2 * radius); // paint a circle
        g.setColor(Color.RED);
        g.drawLine(ball_pos_x+radius, ball_pos_y+radius, ball_pos_x+radius+8*speedX,ball_pos_y+radius+8*speedY);
        // onto the graphics object centered on location and with defined radius.

        g.setColor(Color.BLACK);  // set the color to paint with
        g.fillOval(ball2_pos_x, ball2_pos_y, 2 * radius, 2 * radius); // paint a circle
        g.setColor(Color.RED);
        g.drawLine(ball2_pos_x+radius, ball2_pos_y+radius, ball2_pos_x+radius+8*speedX2,ball2_pos_y+radius+8*speedY2);
        // onto the graphics object centered on location and with defined radius.
        try{
            Thread.sleep(15);
        } catch (Exception exc){}
        move();
        CheckCollission();

    }

    private void CheckCollission(){

        double speedholdx1, speedholdy1, speedholdx2, speedholdy2;
        double angle1, angle2, phi=0, deltaX, deltaY, delta1,delta2;
        double finalspeedX1, finalspeedY1, finalspeedX2, finalspeedY2;

        //This code shouldn't be executed if balls are already colliding
        if( ballballCollssion == true && ( (Math.pow(ball_pos_x-ball2_pos_x,2)
                + Math.pow(ball_pos_y-ball2_pos_y,2) ) <= Math.pow(2*radius, 2) ) )
        {
            System.out.println("Collission");

            System.out.println("speedX = " + speedX);
            System.out.println("speedY = " + speedY);
            System.out.println("speedX2 = " + speedX2);
            System.out.println("speedY2 = " + speedY2);
            System.out.println("*******************************");

            speedholdx1 = speedX;
            speedholdy1 = speedY;
            speedholdx2 = speedX2;
            speedholdy2 = speedY2;

            delta1 = ball_pos_x + speedholdx1;
            delta2 = ball_pos_y + speedholdy1;

            angle1 = Math.atan(delta2/delta1);

            delta1 = ball2_pos_x + speedholdx2;
            delta2 = ball2_pos_y + speedholdy2;

            angle2 = Math.atan(delta2/delta1);

            //speedX = speedholdx2;
            //speedY = speedholdy2;
            //speedX2 = speedholdx1;
            //speedY2 = speedholdy1;
            //
                deltaX = ball2_pos_x - ball_pos_x;
                deltaY = ball_pos_y - ball2_pos_y;
                phi = Math.atan(deltaY / deltaX);

                if (deltaX == 0) phi = 90;


            //speedX = (int) (Math.sqrt(speedholdx2*speedholdx2 + speedholdy2*speedholdy2) * Math.cos(angle2-phi));
            //speedY = (int) (Math.sqrt(speedholdx1*speedholdx1 + speedholdy1*speedholdy1) * Math.sin(angle1 - phi));

            //speedX2 = (int) (Math.sqrt(speedholdx1*speedholdx1 + speedholdy1*speedholdy1) * Math.cos(angle1-phi));
            //speedY2 = (int) (Math.sqrt(speedholdx2*speedholdx2 + speedholdy2*speedholdy2) * Math.sin(angle2-phi));


            finalspeedX1 = ((speedholdx2 * Math.cos(phi) - speedholdy2 * Math.sin(phi)) * Math.cos(phi))
                    + ((speedholdx1 * Math.sin(phi) + speedholdy1 * Math.cos(phi)) * Math.sin(phi));

            finalspeedY1 = -((speedholdx2 * Math.cos(phi) - speedholdy2 * Math.sin(phi)) * Math.sin(phi))
                    + ((speedholdx1 * Math.sin(phi) + speedholdy1 * Math.cos(phi)) * Math.cos(phi));

            finalspeedX2 = ((speedholdx1 * Math.cos(phi) - speedholdy1 * Math.sin(phi)) * Math.cos(phi))
                    + ((speedholdx2 * Math.sin(phi) + speedholdy2 * Math.cos(phi)) * Math.sin(phi));

            finalspeedY2 = -((speedholdx1 * Math.cos(phi) - speedholdy1 * Math.sin(phi)) * Math.sin(phi))
                    + ((speedholdx2 * Math.sin(phi) + speedholdy2 * Math.cos(phi)) * Math.cos(phi));



            speedX = round(finalspeedX1);
            speedY = round(finalspeedY1);


            speedX2 = round(finalspeedX2);
            speedY2 = round(finalspeedY2);


                ball_pos_x+=speedX;
                ball_pos_y+=speedY;
                ball2_pos_x+=speedX2;
                ball2_pos_y+=speedY2;

            ballballCollssion = false;

        }

        //Once the ball collision calculations are done ballballcollision is set to false and it can only be set to
        //true again by the following statement.
        if( (Math.pow(ball_pos_x-ball2_pos_x,2) + Math.pow(ball_pos_y-ball2_pos_y,2)) >= Math.pow(2*radius, 2) )
            ballballCollssion = true;


    }

    private int round (double d)
    {
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;
        }else{
            return d<0 ? -(i+1) : i+1;
        }
    }

    private void move() {

        // May cross both x and y bounds

            //Ball 1: Hit the bottom edge?
                if ( ( ball_pos_y + 2 * radius > 400 ) ){
                    speedY = -speedY;
                    ball_pos_y = 400 - 2 * radius ;
                }

        //Ball 1: Hit the top edge?
                if( ball_pos_y < 0 ) {
                   speedY = -speedY;
                    ball_pos_y = 0;
                }

        //Ball 1: Hit the right side?
                if( ball_pos_x < 0 ) {
                    speedX = -speedX;
                    ball_pos_x = 0;
                }

        //Ball 1: Hit the left side?
                if( ball_pos_x + 2*radius > 400 ) {
                    speedX = -speedX;
                    ball_pos_x = 400 - 2*radius;
                }


        //Ball 2: Hit the bottom edge?
        if ( ( ball2_pos_y + 2 * radius > 400 ) ){
            speedY2 = -speedY2;
            ball2_pos_y = 400 - 2 * radius ;
        }

        //Ball 2: Hit the top edge?
        if( ball2_pos_y < 0 ) {
            speedY2 = -speedY2;
            ball2_pos_y = 0;
        }

        //Ball 2: Hit the right side?
        if( ball2_pos_x < 0 ) {
            speedX2 = -speedX2;
            ball2_pos_x = 0;
        }

        //Ball 2: Hit the left side?
        if( ball2_pos_x + 2*radius > 400 ) {
            speedX2 = -speedX2;
            ball2_pos_x = 400 - 2*radius;
        }

            ball_pos_x+=speedX;
            ball_pos_y+=speedY;
            ball2_pos_x+=speedX2;
            ball2_pos_y+=speedY2;

            repaint();

    }

}