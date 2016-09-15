import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 29/07/15.
 */
public class Main {

    public static void main(String[] args){
        JFrame frame = new JFrame("Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        PongPanel pongPanel = new PongPanel();
        frame.add(pongPanel, BorderLayout.CENTER);

        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
