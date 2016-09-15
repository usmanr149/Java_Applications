import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 16/07/15.
 */
public class RandomLinesApplet2 extends JApplet {

    LineGenerator line = new LineGenerator();

    public void init(){
        line.setWidthRange(400);
        line.setHeightRange(200);
    }

    public void paint(Graphics g){
        int coords[] = new int[4];  //x1 y1 x2 y2
        for(int i = 0; i < 15; i++){
            g.setColor(line.getColor());

            coords = line.getCoords();

            //draw our line
            g.drawLine( coords[0], coords[1], coords[2], coords[3] );
            marcsPause(333);
        }
    }

    void marcsPause(int ms){
        try{
            Thread.sleep(ms);
        }
        catch (InterruptedException e){

        }
    }

}