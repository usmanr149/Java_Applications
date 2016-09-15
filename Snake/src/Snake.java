import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Created by usman on 06/06/15.
 */
@SuppressWarnings("serial")
public class Snake extends JPanel implements KeyListener {

    private int apple_x;
    private int apple_y;
    private static int DOT_SIZE = 20;
    private static int WIDTH = 480, HEIGHT = 480;
    int dots  = 9;
    int ALL_DOTS = 2500;
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private boolean appleapple = false;
    private boolean moveRight = true;
    private boolean moveLeft = false;
    private boolean moveUp = false;
    private boolean moveDown = false;

    public Snake() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    private void begin() {
        for (int z = 0; z < dots; z++) {
            x[z] = WIDTH/2+DOT_SIZE*z;
            y[z] = HEIGHT/2;
        }
    }

    private void CheckCollision(){

        if( (x[dots-1]==apple_x) ){
            if( (y[dots-1]==apple_y) ) {
                appleapple = false;
                for(int z = dots; z >= 0; z--){
                    x[z+1] = x[z];
                    y[z+1] = y[z];
                }
                dots++;
                System.out.println("appleapple = " + appleapple);
            }
        }
    }

    private void moveSnake(){
        int z;
            for (z = 0; z < (dots - 1); z++) {
                x[z] = x[(z + 1)];
                y[z] = y[(z + 1)];
            }

        if (moveLeft) {
            x[z] -= DOT_SIZE;
        }

        if (moveRight) {
            x[z] += DOT_SIZE;
        }

        if (moveUp) {
            y[z] -= DOT_SIZE;
        }

        if (moveDown) {
            y[z] += DOT_SIZE;
        }

    }

    public void paintComponent(Graphics g) {

        g.clearRect(0, 0, getWidth(), getHeight());
        //g.drawString("the key that pressed is " + c, 250, 250);

        for (int z = 0; z < dots; z++) {
            g.drawRect(x[z], y[z], DOT_SIZE, DOT_SIZE);
        }

        CheckCollision();

        if(!appleapple) {
            locateApple();
        }

        g.setColor(Color.RED);
        g.fillRect(apple_x, apple_y, DOT_SIZE, DOT_SIZE);

    }

    private void locateApple() {

        Random rand = new Random();

        int r = rand.nextInt(WIDTH + 1);
        apple_x = r/DOT_SIZE;
        apple_x = apple_x * DOT_SIZE;
        r = rand.nextInt(HEIGHT + 1);
        apple_y = r/DOT_SIZE;
        apple_y = apple_y*DOT_SIZE;

        appleapple = true;

    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_DOWN && moveUp == false) {
            moveDown = true;
            moveUp = false;
            moveLeft = false;
            moveRight = false;
            //System.out.println("Down");
        }
        if(key == KeyEvent.VK_UP && moveDown == false) {
            moveUp = true;
            moveDown = false;
            moveRight = false;
            moveLeft = false;
            //System.out.println("Up");
        }
        if(key == KeyEvent.VK_RIGHT && moveLeft == false) {
            moveRight = true;
            moveDown = false;
            moveLeft = false;
            moveUp = false;
            //System.out.println("Right");
        }
        if(key == KeyEvent.VK_LEFT && moveRight == false) {
            moveLeft = true;
            moveDown = false;
            moveUp = false;
            moveRight = false;
            //System.out.println("Left");
        }

        repaint();
    }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) {

        //c = e.getKeyChar();
        //repaint();
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Snake");
        Snake game = new Snake();
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game.begin();
        game.repaint();

        while (true){
            game.moveSnake();
            game.repaint();
            Thread.sleep(100);
        }
    }
}