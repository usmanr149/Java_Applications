import java.applet.Applet;
import java.awt.*;

/**
 * Created by usman on 31/12/14.
 */
public class MosueListenerBallBounceextends extends Applet
{
    private int radius = 5;   // the radius of the ball
    private static int NUMBER_OF_BALLS = 1000;
    int[] balls_pos_x = new int[NUMBER_OF_BALLS];
    int[] balls_pos_y = new int[NUMBER_OF_BALLS];
    int[] ball_speedx = new int[NUMBER_OF_BALLS];
    int[] ball_speedy = new int[NUMBER_OF_BALLS];
    int[] ball_speed = new int[NUMBER_OF_BALLS];
    int[] count = new int[NUMBER_OF_BALLS];
    private int width = 400, height =400;
    private int largestspeed = -1;
    private boolean ballballCollssion = true;

    public void init() {

        for(int i = 0; i< NUMBER_OF_BALLS; i++ ){
            balls_pos_x[i] = (int) ( Math.random() * (width - radius) + radius );
            balls_pos_y[i] = (int) ( Math.random() * (height - radius) + radius );
            ball_speedx[i] = (int) (Math.random() * 8 );
            ball_speedy[i] = (int) (Math.random() * 8 );
        }
    }

    Image offScreenBuffer;
    public void update(Graphics g)
    {
        Graphics gr;
        setBackground(Color.GRAY);
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
            g.fillRect(0, 0, 800, 400);

            for (int i = 0; i < NUMBER_OF_BALLS; i++) {
                ball_speed[i] = (int) Math.sqrt( ball_speedx[i] * ball_speedx[i]
                        + ball_speedy[i] * ball_speedy[i] );
            }

            sort();

//            for (int i = 0; i < NUMBER_OF_BALLS; i ++){
//            g.setColor(Color.BLUE);  // set the color to paint with
//            g.fillOval(balls_pos_x[i], balls_pos_y[i], 2 * radius, 2 * radius); // paint a circle
//            //g.setColor(Color.RED);
//            //g.drawLine(balls_pos_x[i]+radius, balls_pos_y[i]+radius, balls_pos_x[i]+radius+8*ball_speedx[i],
//             //       balls_pos_y[i]+radius+8*ball_speedy[i]);
//            }

        for(int i = 0; i <= largestspeed; i++) {
            g.setColor(Color.magenta);
            //g.setStroke(new BasicStroke(10));
            g.drawLine(425 + 2 * i, 375, 425 + 2 * i, 375 - count[i]);
        }
        g.drawString(String.format("Largest speed %d", largestspeed), 600, 100);

        try{
            Thread.sleep(15);
        } catch (Exception exc){}
        move();
        for( int i = 0; i < NUMBER_OF_BALLS ; i++ ){
            CheckCollission(i);
        }
    }

    private void CheckCollission(int i){

        double speedholdx[] = new double[NUMBER_OF_BALLS];
        double speedholdy[] = new double[NUMBER_OF_BALLS];
        double finalspeedx[] = new double[NUMBER_OF_BALLS];
        double finalspeedy[] = new double[NUMBER_OF_BALLS];
        double phi=0, deltaX, deltaY;

        //This code shouldn't be executed if balls are already colliding
        for( int j = i+1; j < NUMBER_OF_BALLS; j++ ){

            if (ballballCollssion == true && ( (Math.pow(balls_pos_x[i]-balls_pos_x[j],2)
                    + Math.pow(balls_pos_y[i]-balls_pos_y[j],2) ) <= Math.pow(2*radius, 2) ) ){

            speedholdx[i] = (double)ball_speedx[i];
            speedholdy[i] = (double)ball_speedy[i];
            speedholdx[j] = (double)ball_speedx[j];
            speedholdy[j] = (double)ball_speedy[j];


               deltaX = balls_pos_x[j] - balls_pos_x[i];
               deltaY = balls_pos_y[i] - balls_pos_y[j];
               phi = Math.atan(deltaY / deltaX);

               if (deltaX == 0) phi = 90;

                finalspeedx[i] = ((speedholdx[j] * Math.cos(phi) - speedholdy[j] * Math.sin(phi)) * Math.cos(phi))
                        + ((speedholdx[i] * Math.sin(phi) + speedholdy[i] * Math.cos(phi)) * Math.sin(phi));

                finalspeedy[i] = -((speedholdx[j] * Math.cos(phi) - speedholdy[j] * Math.sin(phi)) * Math.sin(phi))
                        + ((speedholdx[i] * Math.sin(phi) + speedholdy[i] * Math.cos(phi)) * Math.cos(phi));

                finalspeedx[j] = ((speedholdx[i] * Math.cos(phi) - speedholdy[i] * Math.sin(phi)) * Math.cos(phi))
                        + ((speedholdx[j] * Math.sin(phi) + speedholdy[j] * Math.cos(phi)) * Math.sin(phi));

                finalspeedy[j] = -((speedholdx[i] * Math.cos(phi) - speedholdy[i] * Math.sin(phi)) * Math.sin(phi))
                        + ((speedholdx[j] * Math.sin(phi) + speedholdy[j] * Math.cos(phi)) * Math.cos(phi));

                ball_speedx[i] = round(finalspeedx[i]);
                ball_speedy[i] = round(finalspeedy[i]);

                ball_speedx[j] = round(finalspeedx[j]);
                ball_speedy[j] = round(finalspeedy[j]);


                balls_pos_x[i]+=ball_speedx[i];
                balls_pos_y[i]+=ball_speedy[i];
                balls_pos_x[j]+=ball_speedx[j];
                balls_pos_y[j]+=ball_speedy[j];

                ballballCollssion = false;

            }

            //Once the ball collision calculations are done ballballcollision is set to false and it can only be set to
            //true again by the following statement.
            if( (Math.pow(balls_pos_x[i]-balls_pos_x[j],2) + Math.pow(balls_pos_y[i]-balls_pos_y[j],2))
                    >= Math.pow(2*radius, 2) )
                ballballCollssion = true;


        }
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
        for( int i = 0 ; i < NUMBER_OF_BALLS; i++){

            if ( ( balls_pos_y[i] + 2 * radius > 400 ) ){
                ball_speedy[i] = -ball_speedy[i];
                balls_pos_y[i] = 400 - 2 * radius ;
            }

            //Ball 1: Hit the top edge?
            if( balls_pos_y[i] < 0 ) {
                ball_speedy[i] = -ball_speedy[i];
                balls_pos_y[i] = 0;
            }

            //Ball 1: Hit the right side?
            if( balls_pos_x[i] < 0 ) {
                ball_speedx[i] = -ball_speedx[i];
                balls_pos_x[i] = 0;
            }

            //Ball 1: Hit the left side?
            if( balls_pos_x[i] + 2*radius > 400 ) {
                ball_speedx[i] = -ball_speedx[i];
                balls_pos_x[i] = 400 - 2*radius;
            }

            balls_pos_x[i]+=ball_speedx[i];
            balls_pos_y[i]+=ball_speedy[i];
        }
        repaint();
    }

    public void sort(){

        largestspeed = -1;
        for(int x=0; x < NUMBER_OF_BALLS; x++){
            count[x] = 0;
            if(ball_speed[x] > largestspeed)
                largestspeed = ball_speed[x];
        }

        for (int j = 0; j < NUMBER_OF_BALLS; j++){
        for(int i = 0 ; i < largestspeed; i++) {
            if (ball_speed[j] == i) {
                count[i] = count[i] + 1;
            }
        }
            }
    }
}