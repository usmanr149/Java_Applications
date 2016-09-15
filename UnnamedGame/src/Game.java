import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 04/05/15.
 */
@SuppressWarnings("serial")
public class Game extends JPanel {

    int x = 0;
    int y = 0;

    private void moveBall() {
        x = x + 1;
        y = y + 1;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //The call to "super.paint(g)",
        // cleans the screen and if we comment this line we can see the following effect:
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON); //makes the borders of the figures smoother
        g2d.fillOval(x, y, 30, 30);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Mini Tennis");
        Game game = new Game();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.moveBall();
            game.repaint();
            Thread.sleep(10); //thread which is being run must sleep for 10 milliseconds
        }
    }
}
