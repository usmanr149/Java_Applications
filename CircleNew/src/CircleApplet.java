import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by usman on 16/07/15.
 */
public class CircleApplet extends JApplet {

    Circle ball = new Circle();

    public void start(){
        String sRadius;
        sRadius = JOptionPane.showInputDialog("Enter a circle's radius");

        ball.setRadius(Double.parseDouble(sRadius));

        JOptionPane.showMessageDialog(null, ball.toString(), "We just set the circle's radius.",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void paint(Graphics g){
        showStatus(ball.toString());

        g.setColor(Color.MAGENTA);
        //drawOval() is (xUL, yUL, widthm height)
        int r = (int) ball.getRadius();
        g.drawOval(200, 50, r, r);

        DecimalFormat df = new DecimalFormat("0.000");

        String sRad = "Radius = " + df.format(ball.getRadius());
        String sArea = "Area = " + df.format(ball.getArea());
        String sCircum = "Circumference = " + df.format(ball.getCircumference());

        g.drawString(sRad, 50, 50);
        g.drawString(sArea, 50, 70);
        g.drawString(sCircum, 50, 90);
    }

}