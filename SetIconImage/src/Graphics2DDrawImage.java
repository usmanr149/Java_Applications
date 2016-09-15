import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 29/07/15.
 */
public class Graphics2DDrawImage {
    public static void main(String[] a) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 300, 300);
        window.getContentPane().add(new MyCanvas());
        window.setVisible(true);
    }
}

class MyCanvas extends JComponent {

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Image img1 = Toolkit.getDefaultToolkit().getImage("/home/usman/Java_Application/SetIconImage/src/dice.png");
        g2.drawImage(img1, 0, 0, getWidth(), getHeight(), this);
        g2.finalize();
    }
}