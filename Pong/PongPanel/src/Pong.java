import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by usman on 01/11/14.
 */
public class Pong extends JApplet implements KeyListener, ActionListener {

    private boolean upPressed = false;
    private boolean downPressed = false;


    int paddle_speed = 5;
    int paddle_width = 10;
    int paddle_height = 60;
    int R_paddle_pos = 240;
    int L_paddle_pos = 240;
    int ball_x_speed = 5;
    int ball_y_speed = 5;
    int ball_x_pos = 240;
    int ball_y_pos = 400;
    int ball_x_temp;
    int ball_y_temp;
    int diameter = 20;
    int R_paddle_temp = 0;
    int x_axis = 800;
    int y_axis = 480;

    public Pong() {
        setSize(x_axis, y_axis);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        //call step() 60 fps
        Timer timer = new Timer(1000/60, this);
        timer.start();
    }

    public void paint(Graphics g) {
        //for (int y = 0; y < 420; y++) {

        System.out.println("ball_x_pos : " + ball_x_pos);
        System.out.println("ball_y_pos : " + ball_y_pos);

        if(ball_x_pos == x_axis - 50)
            ball_x_speed = ball_x_speed*-1;

        if(ball_x_pos == 50)
            ball_x_speed = ball_x_speed*-1;

        if(ball_y_pos == y_axis - 50)
            ball_y_speed = ball_y_speed*-1;

        if(ball_y_pos == 50)
            ball_y_speed = ball_y_speed*-1;


        ball_x_pos += ball_x_speed;
            ball_y_pos += ball_y_speed;

        ball_x_temp = ball_x_pos - diameter;
        ball_y_temp = ball_y_pos - diameter;


        g.setColor(Color.RED);
        g.fillOval(ball_x_pos,ball_y_pos,20,20);


        g.setColor(Color.RED);
            g.fillOval(ball_x_pos,ball_y_pos,20,20);

        g.setColor(Color.BLACK);
        g.fillOval(ball_x_temp,ball_y_temp,20,20);


        g.setColor(Color.magenta);
            g.fillRect(0, L_paddle_pos, paddle_width, paddle_height);

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, paddle_width, 0);

            g.setColor(Color.red);
            g.fillRect(789, R_paddle_pos, paddle_width, paddle_height);

            g.setColor(Color.BLACK);
            g.fillRect(789, R_paddle_temp, paddle_width, paddle_speed);

          //marcsPause(15);
        //}
    }

    public void moveup() {
        if (R_paddle_pos > 0) {
                R_paddle_temp = R_paddle_pos;
                R_paddle_pos -= paddle_speed;
                System.out.println("Right " + R_paddle_pos);
            }
        repaint();
    }

        public void movedown()
    {
            if (R_paddle_pos < 480) {
                R_paddle_temp = R_paddle_pos;
                R_paddle_pos += paddle_speed;
                //System.out.println(R_paddle_pos);
            }
        repaint();
    }

    public void keyPressed(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            System.out.println("Left");
        }
        else if (keyCode == KeyEvent.VK_RIGHT)
            System.out.println("Right");

        else if (keyCode == KeyEvent.VK_UP) {
            System.out.println("Up");
            upPressed = true;
            moveup();
        }
        else if (keyCode == KeyEvent.VK_DOWN)
            System.out.println("Down");
            downPressed = true;
            movedown();
    }

    public void keyReleased(KeyEvent evt) {
    }

    public void keyTyped(KeyEvent evt) {
    }

    void marcsPause(int ms)
    {
        try
        {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {

    }
}