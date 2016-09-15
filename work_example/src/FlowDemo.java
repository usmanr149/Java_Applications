import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by usman on 21/11/14.
 */
public class FlowDemo extends JFrame {

    TitledBorder t1, t2, t3;

    JButton b1 = new JButton("Chicago");
    JButton b2 = new JButton("Portland");
    JButton b3 = new JButton("New Orleans");
    JButton b4 = new JButton("Chicago");
    JButton b5 = new JButton("Portland");
    JButton b6 = new JButton("New Orleans");
    JButton b7 = new JButton("Chicago");
    JButton b8 = new JButton("Portland");
    JButton b9 = new JButton("New Orleans");

    JPanel sp1 = new JPanel();
    JPanel sp2 = new JPanel();
    JPanel sp3 = new JPanel();

    public FlowDemo()
    {
        Container canvas = getContentPane();

        //Use GridLayout to position three rows in one column.
        canvas.setLayout ( new GridLayout(3,1) );
        canvas.add(sp1);
        canvas.add(sp2);
        canvas.add(sp3);

        //Size the first three buttons.
        b1.setPreferredSize(new Dimension(50,20));
        b2.setPreferredSize(new Dimension(60,25));
        b3.setPreferredSize(new Dimension(70,30));

        //First Panel
        sp1.setBackground(Color.cyan);
        sp1.setLayout(new FlowLayout());

        t1 = BorderFactory.createTitledBorder("Default FlowLayout Constructor");
        sp1.setBorder(t1);
        sp1.add(b1);
        sp1.add(b2);
        sp1.add(b3);

        sp2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        sp2.setBackground(Color.WHITE);
        t2 = BorderFactory.createTitledBorder("FlowLayout with Right justification");
        sp2.setBorder(t2);
        sp2.add(b4);
        sp2.add(b5);
        sp2.add(b6);

        sp3.setBackground(Color.GREEN);
        sp3.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        t3 = BorderFactory.createTitledBorder("FlowLayout with gap difference changed");
        sp3.setBorder(t3);
        sp3.add(b7);
        sp3.add(b8);
        sp3.add(b9);

        this.setSize(500,200);
        this.setTitle("Flow Layout Demonstration Program");
        this.show();

    }

    public static void main( String args[] )
    {
        FlowDemo app = new FlowDemo();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
