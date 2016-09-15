package com.kilobolt.robotgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.animation.Animation;
import com.kilobolt.Implementation.Game;
import com.kilobolt.Implementation.Graphic;
import com.kilobolt.Implementation.Image;
import com.kilobolt.Implementation.Screen;

/**
 * Created by usman on 26/11/14.
 */
public class GameScreen extends Screen {

    enum GameState  {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    //Variable Setup

    private static Background bg1, bg2;
    private static Robot robot;
    public static Heliboy hb, hb2;

    private Image currentSprite, character, character2, character3, heliboy,
                    heliboy2, heliboy3, heliboy4, heliboy5;
    private Animation anim, hanim;

    private ArrayList<Title> tilearray = new ArrayList<Title>();

    int livesLeft = 1;
    Paint paint, paint2;

    public GameScreen(Game game){
        super(game);

        //Initialize game objects here

        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        robot = new Robot();
        hb = new Heliboy(340, 360);
        hb2 = new Heliboy(700, 360);


        // Assets is where the images are.
        character = Assets.character;
        character2 = Assets.character2;
        character3 = Assets.character3;

        heliboy = Assets.heliboy;
        heliboy2 = Assets.heliboy2;
        heliboy3 = Assets.heliboy3;
        heliboy4 = Assets.heliboy4;
        heliboy5 = Assets.heliboy5;

        anim = new Animation();
        anim.addFrame(character, 1250);
        anim.addFrame(character2, 50);
        anim.addFrame(character3, 50);
        anim.addFrame(character2, 50);
        //hanim is Animation
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

        loadMap();

        //Defining a paint object
        paint = new paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);
    }

    private void loadMap(){
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        Scanner scanner = new Scanner(SampleGame.map);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();

            //no more lines to read
            if(line == null){
                break;
            }

            if(!line.startsWith("!"))   {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }
        height = lines.size();

        for(int j = 0; j < 12; j++){
            String line = (String) lines.get(j);
            for(int i = 0; i < width; i++){
                char ch = line.charAt(i);
                Tile t = new Tile(i, j, Character.getNumericValue(ch)) //This is how the map is read and interpreted.
                tilearray.add(t);
            }
        }
    }

    @Override
    public void update(float deltatime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if ( state == GameState.Ready)
            updateReady(TouchEvents);
        if ( state == GameState.Running)
            updateRunning(TouchEvents, deltaTime);
        if ( state == GameState.Paused)
            updatePause(TouchEvents)
        if ( state == GameState.GameOver)
            updateGameover(touchEvents);
    }
    //touchEvent is in Input
    private void updateReady(List<TouchEvents> touchEvents, float deltatime){

        //All touch input is handled here.
        int len = touchEvents.size();
        for (int i =0; i < len; i++){
            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 0, 0, 800, 240)) {

                    if(!inBound(0, 0, 35, 35)){
                        resume();
                    }
                }

                if(inBounds (event, 0, 240, 800, 240)){
                    nullify();
                    goTomenu();
                }
            }
        }
    }

    private void updateGameover(List<TouchEvent> touchEvents){
        int len = touchEvents.size();
        for(int i = 0; i < len; i++){
           TouchEvent event = touchEvents.get(i);
            if(event.type == touchEvents.TOUCH_DOWN){
                if(inBound(event, 0, 0, 800, 400)){
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    private void updateTiles() {

        for(int i = 0; i < tilearray.size(); i++){
            Tile t = (Tile) tilearray.get(i);
            t.update();
        }

    }

    public void paint(float deltaTime){
        Graphics g = game.getGraphics();

        g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
        g.drawImage(Assets.background, bg2.getBgY(), bg2.getBgY());
        paintTiles(g);

        //This paints the projectiles
        ArrayList projectiles = robot.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);
            g.drawRect(p.getX(), p.getY(), 10, 5, Color.YELLOW);
        }
        //First draw the game elements.

        g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63 );
        g.drawImage(hanim.getImage(), hb.getCenterX() - 48, hb.getCenterY() - 48);

        if(state == GameState.Ready)
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI;
        if(state == GameState.Paused)
            drawPauseUI;
        if(state == GameState.GameOver)
            drawGameOverUI;

    }

    private void paintTiles(Graphics g) {
        for(int i = 0; i < tilearray.size() ; i++){
            Tile t = (Tile) tilearray.get(i);
            if(t.type != 0){
                g.drawImage(t.getImage(), t.getTileX(), t.getTileY())
            }
        }
    }

    public void animate() {
        anim.update(10);
        hanim.update(50);
    }

    private void nullify(){
        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;
        bg1 = null;
        bg2 = null;
        robot = null;
        hb = null;
        hb2 = null;
        currentSprite = null;
        character = null;
        character2 = null;
        character3 = null;
        heliboy = null;
        heliboy2 = null;
        heliboy3 = null;
        heliboy4 = null;
        heliboy5 = null;
        anim = null;
        hanim = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI(){
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to start", 400, 200, paint);

    }

    private void drawRunning(){
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);


    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 400, 240, paint2);
        g.drawString("Tap to return.", 400, 290, paint);

    }


    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }

    private void goToMenu() {
        // TODO Auto-generated method stub
        game.setScreen(new MainMenuScreen(game));

    }

    public static Background getBg1() {
        // TODO Auto-generated method stub
        return bg1;
    }

    public static Background getBg2() {
        // TODO Auto-generated method stub
        return bg2;
    }

    public static Robot getRobot() {
        // TODO Auto-generated method stub
        return robot;
    }

}