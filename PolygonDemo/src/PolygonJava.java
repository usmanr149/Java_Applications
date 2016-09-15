import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 31/10/14.
 */
public class PolygonJava extends JApplet
{

    //initialize four arrays that are polygons' x and y coordinates
    int xPositionLines[]= {25, 50, 75, 40, 10};
            int yPositionLines[] = { 50, 50 , 100, 120, 100};

    int xPointsFill[] = { 175, 200, 225, 190, 160 };
    int yPointsFill[] = { 50, 50, 100, 120, 100 };

        public void paint(Graphics g)
        {

            //set background color of the window
            setBackground(Color.white);

            g.setColor(Color.black);
            g.drawString("Black Lined Polygon", 10, 40);

            //Draws a swquence of connected lines defined by arrays of x and y coords
            g.drawPolygon(xPositionLines,yPositionLines, 5);

            g.setColor(Color.magenta);
            g.drawString("Magenta Filled Polygon", 150, 140);

            //Fills a closed polygon defined by arrays of x and y coordinates
            g.fillPolygon(xPointsFill,yPointsFill,5);

        }

}
