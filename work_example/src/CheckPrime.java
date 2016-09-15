import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 23/11/14.
 */
public class CheckPrime extends JApplet{

    int prime;

    public void start()
    {

        String sPrime;
        sPrime = JOptionPane.showInputDialog("Enter the number you want to check.");

        prime = Integer.parseInt(sPrime); //Parse the string just added.
        //JOptionPane.showMessageDialog( null, toString(),
                //"You just set the number", JOptionPane.INFORMATION_MESSAGE);

    }

    public void paint (Graphics g){

        String isPrime = prime + " is a prime.";
        String notPrime = prime + " is not a prime.";

        if(checkPrime() == true) {
            g.drawString(notPrime, 50, 50);
        }
        else g.drawString(isPrime, 50, 50);
    }

    public boolean checkPrime(){

        int N = (int) Math.sqrt(prime);
        System.out.println("N = " + N);

        for(int i = 2; i < N; i++){
            if( prime%N == 0){
                return true;
            }
        }
        return false;
    }
}