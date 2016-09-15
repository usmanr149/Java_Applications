import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 17/07/15.
 */
public class ShowASortApplet2 extends JApplet {

    int numbers[] = new int [100];
    Sort2 sorter = new Sort2();

    public void start(){
        int count = 0;
        while( count < 100 ){
            numbers[count] = (int)(Math.random()*201);
            count++;
        }
    }

    public void paint(Graphics g){
        g.setColor(Color.MAGENTA);

        for(int j = 0; j < 100; j++){
            g.drawLine(10, 10+2*j, 10 + numbers[j], 10+2*j);
        }

        g.drawString("The original array", 10, 225);

        sorter.bubblesort(numbers);

        for(int j = 0; j < 100; j++){
            g.drawLine(225, 10+2*j, 225 + numbers[j], 10+2*j);
        }
        g.drawString("The sorted array", 225, 225);

    }

}
