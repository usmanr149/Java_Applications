import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 30/11/14.
 */
public class Poolball extends JPanel {

    private int radius = 10;   // the radius of the ball
    private int ball_pos_x=100, ball_pos_y=150,ball2_pos_x =300, ball2_pos_y=350;
    private int speedX=1, speedY=1;
    private int speedX2=-1, speedY2=1;
    private int width, height;
    private Graphics offScreenGraphics;
    Image offscreen;

    public void init()
    {
        // We'll redraw the applet eacht time the mouse has moved.
        setBackground(Color.WHITE);
        width = getSize().width;
        height = getSize().height;
        // Create an offscreen image to draw on
        // Make it the size of the applet, this is just perfect larger
        // size could slow it down unnecessary.
        offscreen = createImage(width,height);
        offScreenGraphics = offscreen.getGraphics();
        offScreenGraphics.setColor(Color.WHITE);
        // by doing this everything that is drawn by bufferGraphics
        // will be written on the offscreen image.
        //bufferGraphics = offscreen.getGraphics();
    }

    public void update(Graphics g)
    {
        paint(g);
    }

        public void paint(Graphics g) {
          // paint whatever normally gets painted.
        g.setColor(Color.WHITE);
        g.fillRect(ball_pos_x - radius, ball_pos_y - radius, 4 * radius, 4 * radius);
        g.fillRect(ball2_pos_x - radius, ball2_pos_y - radius, 4 * radius, 4 * radius);
        g.setColor(Color.BLUE);  // set the color to paint with
        g.fillOval(ball_pos_x, ball_pos_y, 2 * radius, 2 * radius); // paint a circle
                                // onto the graphics object centered on location and with defined radius.

        g.setColor(Color.BLACK);  // set the color to paint with
        g.fillOval(ball2_pos_x, ball2_pos_y, 2 * radius, 2 * radius); // paint a circle
        // onto the graphics object centered on location and with defined radius.
            try{
                Thread.sleep(10);
            } catch (Exception exc){}
            move();
            g.drawImage(offscreen,0,0,this);
    }

    private void move() {


        if ((ball_pos_y + 2 * radius > 400) || ball_pos_y < 0)
            speedY = -speedY;

        if ( (ball2_pos_y + 2 * radius > 400) || ball2_pos_y < 0 )
            speedY2 = -speedY2;


        if( (ball_pos_x + 2*radius > 400) || ball_pos_x < 0 )
            speedX = -speedX;

        if( (ball2_pos_x+2*radius > 400) || ball2_pos_x < 0 )
            speedX2 = -speedX2;

        // May cross both x and y bounds

        ball_pos_x+=speedX;
        ball_pos_y+=speedY;
        ball2_pos_x+=speedX2;
        ball2_pos_y+=speedY2;

        repaint();
    }

}
