import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by usman on 05/08/15.
 */
public class Board extends JPanel implements Commons, ActionListener, KeyListener {

    Timer timer;
    Ball ball = new Ball();
    Brick brick[] = new Brick[30];

    public Board(){

        //listen to key presses
        setFocusable(true);
        addKeyListener(this);

        brick[0] = new Brick();
        //timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10);
        setBackground(Color.BLACK);
        run();
    }

    public void gameInit(){

    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.green);
        g.drawRect(50, 50, 50, 50);
        g.drawImage(ball.getImage(), ball.getX(), ball.getY(), 50, 50, this);
        g.drawImage(brick[0].getImage(), 150, 150, 50, 20, this);
        //g.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight(), this);
    }


        public void run(){

            ball.move();
            repaint();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
