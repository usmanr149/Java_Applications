import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 01/11/14.
 */
public class SortArrays extends JApplet {

    int numbers[] = new int [100];

    public void start()
    {

        int count = 0;
        while(count < numbers.length)
        {
            numbers[count] = (int)(Math.random()*201);
            count++;
        }
    }

    public void paint(Graphics g)
    {
        setSize(800, 480);
        int i,j;

        g.setColor(Color.magenta);

        for(j=0; j < numbers.length; j++)
        {
            g.drawLine(10, 10+2*j, 10+numbers[j], 10+2*j);
        }

        g.drawString("The original array is", 10, 225);

        bubblesort();

        for(j=0; j < numbers.length; j++)
        {
            g.drawLine(225, 10+2*j, 225 + numbers[j], 10+2*j);
        }
        g.drawString("The sorted array", 225,225);

    }

    public void bubblesort()
    {
        int size = numbers.length;
        int x, y, temp;
        //System.out.print("Hello");

        for(x = 1; x < size; x++)
        {
            for(y = 0; y <size - 1; y++)
            {
                if(numbers[y] > numbers[y+1])
                {
                    temp = numbers[y];
                    numbers[y] = numbers[y+1];
                    numbers[y+1]=temp;
                }
            }
        }
    }
}