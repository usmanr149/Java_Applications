import javax.swing.*;

/**
 * Created by usman on 15/07/15.
 */
public class SqrtDemo {
    public SqrtDemo() {
        String sNumber;

        //Make a JOptionPane object named jop
        JOptionPane jop = new JOptionPane();

        //use the jop object to show an input box
        sNumber = jop.showInputDialog("Enter a number");

        double number, sqrootNumber;

        //make a Double object
        Double doubleObject = new Double(0.00);

        //use the Double object to convert the string to a double
        number = doubleObject.parseDouble(sNumber);

        sqrootNumber = Math.sqrt(number);

        String sAnswer = doubleObject.toString(sqrootNumber);

        jop.showMessageDialog(null, sAnswer);

    }

  /*
        //use the static method to show an input dialog
        sNumber = JOptionPane.showInputDialog("Enter a number");

        double number, sqrootNumber;

        //static parseDouble converts string to double
        number = Double.parseDouble(sNumber);

        //static sqrt calculates and returns a square root
        sqrootNumber = Math.sqrt(number);

        //static toString converts the number method to show the answer
        String sAnswer = Double.toString(sqrootNumber);

        //now we use another static method to show the answer
        JOptionPane.showMessageDialog(null, sAnswer);
    }
*/
    public static void main(String args[]){
        new SqrtDemo();
        System.exit(0);
    }
}
