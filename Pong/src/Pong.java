import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by usman on 01/11/14.
 */
public class Pong extends JApplet implements KeyListener {

    //private boolean upPressed = false;
    //private boolean downPressed = false;

    int paddle_speed = 5;
    int paddle_width = 30;
    int paddle_height = 60;
    int ball_x_speed = 2;
    int ball_y_speed = 2;
    int ball_x_pos = 100;
    int ball_y_pos = 100;
    int ball_x_temp;
    int ball_y_temp;
    int diameter = 10;
    int R_paddle_temp = 0;
    int L_paddle_temp = 0;
    int x_axis = 800;
    int y_axis = 480;
    int L_Score = 0;
    int R_Score = 0;

    int R_paddle_pos = y_axis/2 + 5;
    int L_paddle_pos = y_axis/2 + 5;

    public Pong() {
        setBackground(Color.WHITE);
        setSize(x_axis, y_axis);

        setFocusable(true);
        addKeyListener(this);

        //call step() 60 fps
        Timer timer = new Timer(17, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                ball();
                repaint();
            }
        });
        timer.start();
    }

    public void paint(Graphics g) {
        //for (int y = 0; y < 420; y++) {

        //System.out.println("ball_x_pos : " + ball_x_pos);
        //System.out.println("ball_y_pos : " + ball_y_pos);

        BufferedImage buffer = new BufferedImage(x_axis, y_axis, BufferedImage.TYPE_INT_ARGB);
        Graphics gr = buffer.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0,0, x_axis,y_axis);

        //g.setColor(Color.WHITE);
        //g.fillOval(ball_x_temp, ball_y_temp, diameter, diameter);

        g.setColor(Color.RED);
        g.fillOval(ball_x_pos, ball_y_pos, diameter, diameter);

        //g.setColor(Color.WHITE);
        //g.fillRect(0, L_paddle_temp, paddle_width, paddle_speed);

        g.setColor(Color.magenta);
        g.fillRect(0, L_paddle_pos, paddle_width, paddle_height);

        //g.setColor(Color.WHITE);
        //g.fillRect(x_axis - paddle_width, R_paddle_temp, paddle_width, paddle_speed);

        g.setColor(Color.GREEN);
        g.fillRect(x_axis - paddle_width, R_paddle_pos, paddle_width, paddle_height);

        //draw the scores
        g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
        g.drawString(String.valueOf(R_Score), 100, 100);
        g.drawString(String.valueOf(L_Score), 400, 100);

        g.drawImage(buffer, 0, 0, this );

          //marcsPause(15);
        //}
    }

    public void ball() {

        if(ball_x_pos == (x_axis - paddle_width))  ball_x_speed = -ball_x_speed;

        if(ball_x_pos == paddle_width)  ball_x_speed = -ball_x_speed;

        if(ball_y_pos == y_axis - diameter)  ball_y_speed = -ball_y_speed;

        if(ball_y_pos == 0)  ball_y_speed = -ball_y_speed;

        if(ball_x_pos < 0 || ball_x_pos > x_axis || ball_y_pos < 0 || ball_y_pos > y_axis) {
            ball_y_speed = ball_y_speed*-1;
            ball_x_pos = x_axis/2;
            ball_y_pos = y_axis/2;
        }

        ball_x_temp = ball_x_pos;
        ball_y_temp = ball_y_pos;

        ball_x_pos += ball_x_speed;
        ball_y_pos += ball_y_speed;
    }


    public void moveup() {
        if (L_paddle_pos > 0) {
                L_paddle_temp = (L_paddle_pos + paddle_height - paddle_speed);
                L_paddle_pos -= paddle_speed;
                //System.out.println("Left " + L_paddle_pos);
            }
        repaint();
    }

        public void movedown(){
            if (L_paddle_pos < y_axis - paddle_height) {
                L_paddle_temp = L_paddle_pos;
                L_paddle_pos += paddle_speed;
                //System.out.println(R_paddle_pos);
            }
        repaint();
    }

    public void keyPressed(KeyEvent evt) {
        int keyCode = evt.getKeyCode();


        if (keyCode == KeyEvent.VK_UP) {
            //System.out.println("Up");
            moveup();
        }

        else if (keyCode == KeyEvent.VK_DOWN)
            //System.out.println("Down");
            movedown();
    }

    public void keyReleased(KeyEvent evt) {
    }

    public void keyTyped(KeyEvent evt) {
    }

    //void marcsPause(int ms)
    //{
      //  try
        //{
          //  Thread.sleep(ms);
        //} catch (InterruptedException e) {
          //  e.printStackTrace();
      //  }
    //}
}