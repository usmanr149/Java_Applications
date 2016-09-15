import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Created by usman on 16/07/15.
 */
public class DieTossFrame2 extends JFrame {

    //instatiate 2 die objects for our program.
    private Die die1 = new Die();
    private Die die2 = new Die();

    private int bins[] = new int[13];
    private int total;
    private String sTotal;

    public static void main( String[] args ){
        DieTossFrame2 testDice = new DieTossFrame2();
        testDice.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //class constructor initializes values and calls throw the dice
    public DieTossFrame2(){
        setSize(250, 300);
        setTitle("Dice Tossing Program");

        sTotal = JOptionPane.showInputDialog("How many tosses?");
        total = Integer.parseInt(sTotal);

        //first zero all the values in the bin array
        for(int i = 2; i < bins.length; i++){
            bins[i] = 0;
        }

        throwTheDice();

    }

    public void throwTheDice(){
        int sum;
        //fill the array with the sum of the dice
        for(int i = 0; i < total; i++){
            die1.tossDie();
            die2.tossDie();
            sum = die1.getFaceValue() + die2.getFaceValue();

            bins[sum]++;
        }

        show();

    }

    public void paint(Graphics g){
        String output = "Result for " + sTotal + " tosses.";
        g.drawString( output, 25, 60 );
        output = "Sum of 2 Dice     Frequency";
        g.drawString(output, 25, 90);
        DecimalFormat df = new DecimalFormat("000");

        //data contained in bins 2 through 12
        for(int i = 2; i < bins.length; i++){
            output = "   " + df.format(i) + "                    " + df.format(bins[i]);
            g.drawString(output, 25, 90+15*i);
        }

    }

}