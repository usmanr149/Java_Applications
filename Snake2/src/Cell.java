/**
 * Created by usman on 26/07/15.
 */
public class Cell {

    final static int CELL_TYPE_EMPTY = 0, CELL_TYPE_FOOD = 10, CELL_TYPE_SNAKE_NODE = 20;
    final int row, col;
    int type;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
    }


}
