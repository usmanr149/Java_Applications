package kiliboltgame;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import kiliboltgame.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {

    private Robot robot;
    private Heliboy hb, hb2;
    private Image image, currentSprite, character, character2, character3,
            characterDown, characterJumped, background, heliboy, heliboy2,
            heliboy3, heliboy4, heliboy5;
    private Graphics second;
    private URL base;
    private static Background bg1, bg2;
    private Animation anim, hanim;

    @Override
    public void init() {

        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Q-Bot Alpha");
        try{
            base = StartingClass.class.getResource("character.png");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

        //System.out.println(base);
        // Image Setups
        character = getImage(base, "character.png");
        character2 = getImage(base, "character2.png");
        character3 = getImage(base, "character3.png");

        characterDown = getImage(base, "down.png");
        characterJumped = getImage(base, "jumped.png");

        heliboy = getImage(base, "heliboy.png");
        heliboy2 = getImage(base, "heliboy2.png");
        heliboy3 = getImage(base, "heliboy3.png");
        heliboy4 = getImage(base, "heliboy4.png");
        heliboy5 = getImage(base, "heliboy5.png");

        background = getImage(base, "background.png");

        anim = new Animation();
        anim.addFrame(character, 1250);  //Image and duration
        anim.addFrame(character2, 50);
        anim.addFrame(character3, 50);
        anim.addFrame(character2, 50);

        hanim = new Animation();
        hanim.addFrame(heliboy, 100);
        hanim.addFrame(heliboy2, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy5, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy2, 100);

        currentSprite = anim.getImage();
    }

    @Override
    public void start() {
        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        hb = new Heliboy(340, 360);
        hb2 = new Heliboy(700, 360);
        robot = new Robot();

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void run() {
        while (true) {
            robot.update();
            if (robot.isJumped()) {
                currentSprite = characterJumped;
            } else if (robot.isJumped() == false && robot.isDucked() == false) {
                currentSprite = anim.getImage();
            }

            ArrayList projectiles = robot.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);
                if (p.isVisible() == true) {
                    p.update();
                } else {
                    projectiles.remove(i);
                }
            }

            hb.update();
            hb2.update();
            bg1.update();
            bg2.update();
            animate();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void animate() {
        anim.update(10);
        hanim.update(50);
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }

        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
        g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

        ArrayList projectiles = robot.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            g.setColor(Color.YELLOW);
            g.fillRect(p.getX(), p.getY(), 10, 5);
        }

        g.drawImage(currentSprite, robot.getCenterX() - 61,
                robot.getCenterY() - 63, this);
        g.drawImage(hanim.getImage(), hb.getCenterX() - 48, hb.getCenterY() - 48, this);
        g.drawImage(hanim.getImage(), hb2.getCenterX() - 48, hb2.getCenterY() - 48, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Move up");
                break;

            case KeyEvent.VK_DOWN:
                currentSprite = characterDown;
                if (robot.isJumped() == false) {
                    robot.setDucked(true);
                    robot.setSpeedX(0);
                }
                break;

            case KeyEvent.VK_LEFT:
                robot.moveLeft();
                robot.setMovingLeft(true);
                break;

            case KeyEvent.VK_RIGHT:
                robot.moveRight();
                robot.setMovingRight(true);
                break;

            case KeyEvent.VK_SPACE:
                robot.jump();
                break;

            case KeyEvent.VK_CONTROL:
                if (robot.isDucked() == false && robot.isJumped() == false) {
                    robot.shoot();
                }
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Stop moving up");
                break;

            case KeyEvent.VK_DOWN:
                currentSprite = anim.getImage();
                robot.setDucked(false);
                break;

            case KeyEvent.VK_LEFT:
                robot.stopLeft();
                break;

            case KeyEvent.VK_RIGHT:
                robot.stopRight();
                break;

            case KeyEvent.VK_SPACE:
                break;

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }

}
