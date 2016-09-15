import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 13/07/15.
 */
public class DaysInTheYearApplet extends JApplet {

    int daysIntheYear[][];   //dysnamic array

    public void start(){
        daysIntheYear = new int[5][];   //allocate 5 rows
        daysIntheYear[0] = new int[31];  //January
        daysIntheYear[1] = new int[28];  //February
        daysIntheYear[2] = new int[31];  //March
        daysIntheYear[3] = new int[30];  //April
        daysIntheYear[4] = new int[31];  //May

        //fill the array with days in each month
        //daysIntheYear.length returns 5
        for(int row = 0; row < daysIntheYear.length; row++){
            for(int col = 0; col < daysIntheYear[row].length; col++){
               daysIntheYear[row][col] = col + 1;
            }
        }
    }

    public void paint(Graphics g){
        int x1, y1;
        for(int row = 0; row < daysIntheYear.length; row++){
            for(int col = 0; col < daysIntheYear[row].length; col++){
                switch (daysIntheYear[row][col] %3){
                    case 0: g.setColor(Color.cyan);
                        break;
                    case 1: g.setColor(Color.WHITE);
                        break;
                    case 2: g.setColor(Color.magenta);
                        break;
                }
                y1 = row*20;
                x1 = col*20;
                g.fillRect(x1, y1, 20, 20);
                g.setColor(Color.black);
                g.drawString(Integer.toString(daysIntheYear[row][col]), x1+5, y1+12);
            }
        }
        showStatus("The days of the first 5 months of the year.");
    }
}

