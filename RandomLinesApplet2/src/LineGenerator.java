import java.awt.*;

/**
 * Created by usman on 16/07/15.
 */
public class LineGenerator {

    private int lineCoords[] = new int [4];  //order x1 y1 x2 y2

    private int heightRange, widthRange;

    private int colorCounter;

    public LineGenerator(){
        heightRange = 300;
        widthRange = 300;

        colorCounter = -1;
    }

    public void setRanges(int w, int h){
        widthRange = w;
        heightRange = h;
    }

    public void setWidthRange(int w){
        widthRange = w;
    }

    public void setHeightRange(int h){
        heightRange = h;
    }

    public Color getColor(){
        colorCounter++;

        if(colorCounter % 3 == 0)
            return Color.BLACK;
        else if( colorCounter %3 == 1)
            return Color.green;
        else return Color.MAGENTA;
    }

    public int[] getCoords(){
        //obtain the four random numbers for the coords
        lineCoords[0] = (int)(Math.random()*widthRange + 1); //x1
        lineCoords[1] = (int)(Math.random()*heightRange + 1); //y1
        lineCoords[2] = (int)(Math.random()*widthRange + 1); //x2
        lineCoords[3] = (int)(Math.random()*heightRange + 1); //y2

        return lineCoords;

    }
}