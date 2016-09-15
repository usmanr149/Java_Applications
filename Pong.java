import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Pong extends JFrame implements ActionListener{

        //implement constants

        PongPanel pongPanel = new PongPanel();  

        //JFrame pong x and y coordinates 
        static final int jfpX = 150;
        static final int jfpY = 20;

        // JFrame pong width and height
        static final int jfpW = 800;
        static final int jfpH = 600;

        Thread thrd;

        public static void main(String[] args) {
                Pong jfp = new Pong();
                jfp.setVisible(true);

        }

        public Pong(){
                setBounds(jfpX,jfpY,jfpW,jfpH); 
                setTitle("Pong");
                setResizable(false);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setBackground(Color.black);


                add(pongPanel);
                addKeyListener(pongPanel);
                thrd = new Thread (pongPanel);
        thrd.start();
        }

        public void actionPerformed(ActionEvent e) {

        }



}

class PongPanel extends JPanel implements Runnable, KeyListener{
        Random random = new Random();
        static final int jpW = 800;
        static final int jpH = 600;
        int paddleStart = (jpH/2)-35;
        int paddleStarttwo = (jpH/2)-35;
        int ballStartX = (jpW/2)-20;
        int ballStartY = (jpH/2)-20;
        int ytwo,x,y;
        int ballD = 30;
        int paddleW1 = 20;
        int paddleH1 = 100;
        int paddleW2 = 20;
        int paddleH2 = 100;
        int min = -2;
        int max = 2;
        int randomBallx, randomBally;
//        int randomBallx = random.nextInt(max-min+1)+min;
//        int randomBally = random.nextInt(max-min+1)+min;

        int rand1 = random.nextInt(2-1 + 1)+1; // random for function to determine ballx and bally
        int rand2 = random.nextInt(2-1+2)+1;
        int dx = 4;
        int dy = 4; //direction of y

        public void ballNotZero(){// makes sure the ball doesnt go straight up and down
        if (randomBallx ==0){
              randomBallx = random.nextInt(max-min+1)+min;
             }
             if(randomBally == 0){
              randomBally=random.nextInt(max-min+1)+min;
             }
//         if(rand1 ==1){
//         randomBallx=-1;
//         }
//         if(rand1 ==2){
//         randomBallx=1;
//         }
//         if(rand2 ==1){
//         randomBally =-1;
//         }
//         if(rand2==2){
//         randomBally = 1;
//         }

        }


        public PongPanel(){

        }

        protected void paintComponent(Graphics g) 
        {
        super.paintComponent(g);

        Color ball;
        Color paddleOne;
        Color paddleTwo;
        ball = new Color(255,0,255);
        paddleOne = new Color(255,0,0);
        paddleTwo = new Color(0,0,255);


        g.setColor(ball);
        g.fillOval(ballStartX+randomBallx,ballStartY+randomBally,ballD,ballD);

        g.setColor(paddleOne);
        g.fillRect(20,paddleStart+y,paddleW1,paddleH1);

        g.setColor(paddleTwo);
        g.fillRect(760,paddleStarttwo+ytwo,paddleW2,paddleH2);



        }
        public void run() {
                while(true){
                ballNotZero(); 
                detectPaddle();
                randomBall();
                ballMove();
                repaint();
        try {Thread.sleep(75); } catch(Exception e){

        };

                }
        }
        public static boolean intervallContains(int low, int high, int n) { //determines if something is in a certain range
            return n >= low && n <= high;
        }
        public void detectPaddle(){  //determines if ball is close enough to paddle for detection
        int withinY = (paddleStart+y) -(ballStartY+randomBally);
        int withinY1 = (paddleStarttwo+ytwo)-(ballStartY+randomBally);

        if (ballStartX+randomBallx <=20  &&  intervallContains(-50,50,withinY)){
        dx = -dx;
        }
        if(ballStartX+randomBallx >=760 && intervallContains(-50,50,withinY1)){
        dx = -dx;
        }
        }

        public void randomBall(){
        if(randomBallx >=0 ){
        randomBallx+=dx;
        }
        if(randomBallx<0){
        randomBallx-=dx;
        }
        if(randomBally>=0){
        randomBally+=dy;
        }
        if(randomBally<0){
        randomBally-=dy;
        }
//                randomBallx+=randomBallx;
//                randomBally+=randomBally;
        }
        public void ballMove(){
        if(ballStartY+randomBally > jpH-60){
        dy= -dy;

        }
        if(ballStartY+randomBally <0){
        dy = -dy;
        }
        }

        public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_QUOTE || e.getKeyCode() == KeyEvent.VK_SEMICOLON){
               if(e.getKeyCode() == KeyEvent.VK_A){
                   y-=10;
               }

         if(e.getKeyCode() == KeyEvent.VK_S){
                        y+=10;
                }

        if(e.getKeyCode() == KeyEvent.VK_QUOTE){
                ytwo-=10;
        }
         if(e.getKeyCode() == KeyEvent.VK_SEMICOLON){
                        ytwo+=10;
                }

        }
        }



        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub

        }

}
