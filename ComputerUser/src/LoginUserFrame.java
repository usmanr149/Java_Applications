import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 19/07/15.
 */
public class LoginUserFrame extends JFrame {

    //We instantiate a Login object named todd.
    private Login todd = new Login();

    //We create an array reference called users.
    //The array is sized to hold 5 users.

    private ComputerJava users[] = new ComputerJava[5];

    private String findings;
    boolean bOK;

    public static void main(String[] args){
        LoginUserFrame hal = new LoginUserFrame();
        hal.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public LoginUserFrame(){
        setSize( 450, 200 );
        setTitle("Login Users Program");

        for(int i = 0; i < users.length; i++){
            users[i] = new ComputerJava();
        }

        users[0].setUser("BlueDog", "arfarf", 0);
        users[1].setUser("KiowaTheWonderDog", "awoooo", 0);
        users[2].setUser("Doc", "Irun4you", 0);
        users[3].setUser("Maddie", "WhenDoWeGo", 1);
        users[4].setUser("HannahBanana", "BarkBark", 2);

        loginAUser();

    }

    public void loginAUser(){
        todd.askForLoginInfo();

        bOK = todd.validateUser(users);

        show();
    }

    public void paint(Graphics g){
        String output = "Results";
        g.drawString( output, 25, 60 );
        output =  "Login Request for " + todd.toString();
        g.drawString( output, 25, 90 );

        findings = todd.toString();
        g.drawString( findings, 25, 120 );
    }

}
