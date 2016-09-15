package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by usman on 04/05/15.
 */
@SuppressWarnings("serial")
public class Game extends JPanel {

    Ball ball = new Ball(this); //you can use the this keyword to call another constructor in the same class
    Racquet racquet = new Racquet(this);
    int speed = 1;

    private int getScore(){
        return speed-1;
    }

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
            }

            @Override
            public void keyPressed(KeyEvent ev) {
                racquet.keyPressed(ev);
            }

            @Override
            public void keyReleased(KeyEvent ev) {
                racquet.keyRelease(ev);
            }
        });
        setFocusable(true);
        //Sound.BACK.loop();
    }

    private void move(){
        ball.move();
        racquet.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racquet.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(getScore()), 10, 30);
        g2d.drawString(String.valueOf(speed), 10, 60);
    }

    public void gameOver(){
        Sound.GAMEOVER.play();
        JOptionPane.showMessageDialog(this, "Your score is: " + getScore(),"Game Over", JOptionPane.YES_NO_OPTION);
        System.exit(ABORT);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Mini Tennis");
        Game game = new Game();
        frame.add(game);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}