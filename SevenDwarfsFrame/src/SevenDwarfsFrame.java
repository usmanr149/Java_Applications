import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 15/07/15.
 */
public class SevenDwarfsFrame extends JFrame {

    String dwarfs[] = { "Dogs", "Sleepy", "Dopey", "Grumpy", "Sneezy",
                                    "Bashful", "Happy"};

    public static void main ( String[] args){
        SevenDwarfsFrame hiho = new SevenDwarfsFrame();
        hiho.setDefaultCloseOperation( EXIT_ON_CLOSE );
    }

    public SevenDwarfsFrame()
    {
        bubblesort();
        setSize( 250, 175 );
        setTitle("Seven Dwarfs Program");
        show();
    }

    public void paint( Graphics g ){
        for(int i = 0; i < dwarfs.length; i++){
            g.drawString(dwarfs[i], 25, 60+25*i);
        }
    }

    //this bubble sort routine sorts Strings in alphabetical order
    public void bubblesort(){
        //we can obtain the size of a Java array like this:
        int size = dwarfs.length;
        int x, y;
        String temp;

        for(x = 1; x < size; x++){
            for(y = 1; y < size - 1; y++){
                if( (dwarfs[y].compareTo( dwarfs[y+1] ) ) > 0 ){
                    temp = dwarfs[y];
                    dwarfs[y] = dwarfs[y + 1];
                    dwarfs[y + 1] = temp;
                }
            }
        }
    }
}