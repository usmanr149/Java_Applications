import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by usman on 23/11/14.
 */
public class CircleApplet extends JApplet {

    Circle ball = new Circle();

    public void start()
    {

        String sRadius;
        sRadius = JOptionPane.showInputDialog("Enter the circle's radius. ");

        ball.setRadius(Double.parseDouble(sRadius)); //Parse the string just added.
        JOptionPane.showMessageDialog( null, ball.toString(),
                "We just set the circle's radius.", JOptionPane.INFORMATION_MESSAGE);

    }

    public void paint (Graphics g){
        showStatus( ball.toString() );


        //drawOval is (xUpperLeft, yUpperLeft, width, height)
        g.setColor(Color.BLUE);
        int r = (int) ball.getRadius();
        g.drawOval(250,50,r,r);
        g.setColor(Color.GREEN);
        g.drawLine(250+r/2, 50, 250+r/2, 50+r/2);

        DecimalFormat df = new DecimalFormat("0.000");

        String sRad = "Radius = " + df.format( ball.getRadius() );
        String sArea = "Area = " + df.format(ball.getArea());
        String sCircum = "Circumference = " + df.format( ball.getCircumference() );

        g.drawString(sRad, 50, 50);
        g.drawString(sArea, 50, 70);
        g.drawString(sCircum, 50, 90);
    }
}
