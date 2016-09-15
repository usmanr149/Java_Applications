import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 13/07/15.
 */
public class ThreeColor2dApplet extends JApplet {

    int table[][] = new int[10][20];    //10 rows by 20 columns

    int ROWSIZE = table.length;
    int COLSIZE = table[0].length;

    public void start(){

        //String output = "There are " + ROWSIZE + " rows and " + COLSIZE + " columns";
        //JOptionPane.showMessageDialog(null, output);

        //fill the table with numbers 0,1 or 2
        for(int row = 0; row < ROWSIZE; row++){
            for(int col =0; col < COLSIZE; col++){
                table[row][col] = (int)(Math.random()*3);
            }
        }
    }

    public void paint(Graphics g) {
        int x1, y1;
        for (int row = 0; row < ROWSIZE; row++) {
            for (int col = 0; col < COLSIZE; col++) {
                //set the color
                switch (table[row][col]){
                    case 0: g.setColor(Color.CYAN); break;

                    case 1: g.setColor(Color.white); break;

                    case 2: g.setColor(Color.MAGENTA); break;
                }
                //draw the rect
                y1 = row*20;
                x1 = col*20;
                g.fillRect(x1, y1, 20, 20);

                //write the number assigned to rectangle
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(table[row][col]), x1+12, y1+12);
            }
        }
        showStatus("Good pattern");
    }

}
