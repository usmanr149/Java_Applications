import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 17/07/15.
 */
public class CoffeeLottoDriver extends JApplet {

    private CoffeeLotto game = new CoffeeLotto();

    public void start(){

        //ask the user to select ball values
        game.askUserForChoices();

        //generate the game balls
        game.generateGameBalls();

    }

    public void paint(Graphics g){

        int userCreamBalls[] = new int[4];
        int userCoffeeBall;
        int gameCreamBalls[] = new int[4];
        int gameCoffeeBall;

        g.drawString("Welcome to CoffeeLotto!", 25, 25);
        showStatus("Here are the winning numbers! ");

        g.drawString("Game's Cream Balls ", 25, 50);
        g.drawString("User's Cream Balls ", 250, 50);

        //Get the values from the object.
        userCreamBalls = game.getUserCreamBalls();
        userCoffeeBall = game.getUserCoffeeBall();
        gameCreamBalls = game.getGameCreamBalls();
        gameCoffeeBall = game.getGameCoffeeBall();

        String output;
        for(int i = 0; i < gameCreamBalls.length; i++){
            marcsPause(500);
            output = Integer.toString(gameCreamBalls[i]);
            g.drawString(output, 50+30*i, 75);

            output = Integer.toString(userCreamBalls[i]);
            g.drawString(output, 270+30*i, 75);

        }

        output = "Game's Coffee Ball is " + Integer.toString(gameCoffeeBall);
        g.drawString(output, 25, 100);

        output = "User's Coffee Ball is " + Integer.toString(userCoffeeBall);
        g.drawString(output, 250, 100);

        //Now lets se if there is a winner
        boolean bWinner = game.doWeHaveAWinner();

        if( bWinner )
            g.drawString("We have a CoffeeLotto Winner!!", 25, 125);
        else
            g.drawString("Not a winner. Please play again!", 25, 125);
    }

    void marcsPause(int ms){
        try{
            Thread.sleep(ms);
        }
        catch (InterruptedException e){}
    }


}
