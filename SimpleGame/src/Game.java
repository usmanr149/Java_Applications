import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 07/06/15.
 */
@SuppressWarnings("serial")
public class Game extends JPanel {

    private static int OVAL_SIZE = 30;
    private static int WIDTH = 300, HEIGHT = 300;

    int x = 0;
    int y = 0;

    private void moveBall(){
        x = x + 1;
        y = y + 1;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if( x == WIDTH) x = 0;
        g2d.fillOval(x, y, OVAL_SIZE, OVAL_SIZE);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Sample game");
        Game game = new Game();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true){
            game.moveBall();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
