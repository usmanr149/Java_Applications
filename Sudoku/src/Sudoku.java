import javax.swing.*;
import java.awt.*;

/**
 * Created by usman on 09/11/14.
 */
public class Sudoku extends JApplet{

    final int ROWS = 9;
    final int COLS = 9;
    int count = 0;

    public void paint(Graphics g) {
        int x=0, y= 10;

        int[][] puzzle={{0, 0, 0, 0, 0, 0, 0, 9, 0},
                    {1, 9, 0, 4, 7, 0, 6, 0, 8},
                    {0, 5, 2, 8, 1, 9, 4, 0, 7},
                    {2, 0, 0, 0, 4, 8, 0, 0, 0},
                    {0, 0, 9, 0, 0, 0, 5, 0, 0},
                    {0, 0, 0, 7, 5, 0, 0, 0, 9},
                    {9, 0, 7, 3, 6, 4, 1, 8, 0},
                    {5, 0, 6, 0, 8, 1, 0, 7, 4},
                    {0, 8, 0, 0, 0, 0, 0, 0, 0}};

        for(int i = 0; i < ROWS ; i++){
            for(int j = 0 ; j < COLS ; j++){
                g.setColor(Color.cyan);
                g.fillRect(30*j, 30*i, 30, 30);
                g.setColor(Color.black);
                g.drawString(String.valueOf(puzzle[i][j]), 30*j+15, 30*i+15);
                marcsPause(100);
            }
            System.out.print("\n");
        }
        fillSoduko(g, puzzle);
        for(int i = 0; i < ROWS ; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(puzzle[i][j] + "  ");
            }System.out.print("\n");
        }
        showStatus(count + " number of steps to complete");
    }

    public void update(Graphics g, int i, int j, int num) {
        g.setColor(Color.cyan);
        g.fillRect(30*j, 30*i, 30, 30);
        g.setColor(Color.black);
        g.drawString(String.valueOf(num), 30*j+15, 30*i+15);
        marcsPause(10);
    }

    boolean checkRow(int [][] puzzle, int i, int j, int num){

        for(i = 0; i < ROWS; i++){
                    if(puzzle[i][j] == num) return false;
                } return true;
            }


    boolean checkColumn(int [][] puzzle, int i, int j, int num){

            for(j = 0; j < COLS; j++) {
                if (puzzle[i][j] == num) return false;
            } return true;
        }

    boolean checkGrid(int [][] puzzle, int i, int j, int num){

        int k,l;

        for(k = 3*(i/3); k < 3*(i/3)+3; k++){
            for( l = 3*(j/3); l < 3*(j/3)+3; l++){
                    if( puzzle[k][l] == num) return false;
                }
            }return true;
        }

    void marcsPause(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException e){}

    }

    boolean isSafe(int [][] puzzle, int i, int j, int num){
        if(checkRow(puzzle, i, j, num) == true && checkColumn(puzzle, i, j, num) == true && checkGrid(puzzle, i, j, num) == true) {
            return true;
        }
        else return false;
    }

    boolean fillSoduko(Graphics g, int [][] puzzle){

        int i,j, num;

    for(i=0; i < ROWS ; i ++)
        for(j=0; j < COLS; j++)
            if(puzzle[i][j] == 0){
                    count++;
                for(num=1; num <10 ; num++)
                {
                    if(isSafe(puzzle, i, j, num) == true){
                        puzzle[i][j]=num;
                        update( g, i, j, num );
                            if( fillSoduko(g, puzzle) == true ) {
                                return true;
                            }
                            puzzle[i][j] = 0;
                            update( g, i, j, 0 );
                    }
                }
                return false;
        }
        return true;
    }

}