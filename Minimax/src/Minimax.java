import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by usman on 03/05/15.
 */
public class Minimax extends Applet implements MouseListener {

    // Named-constants for the game board
    public static final int ROWS = 3;  // ROWS by COLS cells
    public static final int COLS = 3;

    // Named-constants of the various dimensions used for graphics drawing
    public static final int CELL_SIZE = 100; // cell width and height (square)
    public static final int CANVAS_WIDTH = CELL_SIZE * COLS;  // the drawing canvas
    public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
    public static final int GRID_WIDTH = 8;                   // Grid-line's width
    public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width
    // Symbols (cross/nought) are displayed inside a cell, with padding from border
    public static final int CELL_PADDING = CELL_SIZE / 6;
    public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; // width/height
    public static final int SYMBOL_STROKE_WIDTH = 8; // pen's stroke width

    //private DrawCanvas canvas; // Drawing canvas (JPanel) for the game board

    //public Minimax(){

    //    canvas = new DrawCanvas();  // Construct a drawing canvas (a JPanel)
    //    canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

    //}

    //class DrawCanvas extends JPanel {
    char[][] first = {{'-', 'X', '-'},
            {'-', '-', '-'},
            {'-', 'O', '-'}};
    char Mark = 'O';
    int mouseX;
    int mouseY;


    public void paint(Graphics g) {

        g.drawLine(CELL_SIZE, 0, CELL_SIZE, CANVAS_WIDTH);
        g.drawLine(CELL_SIZE * 2, 0, CELL_SIZE * 2, CANVAS_WIDTH);
        g.drawLine(0, CELL_SIZE, CANVAS_WIDTH, CELL_SIZE);
        g.drawLine(0, CELL_SIZE * 2, CANVAS_WIDTH, CELL_SIZE * 2);

        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                int x1 = col * CELL_SIZE + CELL_PADDING;
                int y1 = row * CELL_SIZE + CELL_PADDING;
                if (first[row][col] == 'X') {
                    g.setColor(Color.RED);
                    int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
                    int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
                    g.drawLine(x1, y1, x2, y2);
                    g.drawLine(x2, y1, x1, y2);
                } else if (first[row][col] == 'O') {
                    g.setColor(Color.BLUE);
                    g.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                }
            }
        }

        Mark = switchPlayer(Mark);

        //if(Mark == 'X'){
            ChangeBoard(first, Mark);
        //}

        /*if(Mark == 'O'){
            ComputerAI(first,Mark);
        }*/

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX=e.getX();
        mouseY=e.getY();
        //System.out.println(x+","+y);//these co-ords are relative to the component

        /*int rowSelected = mouseY / CELL_SIZE; //CELL_SIZE is 100, so 0, 1, 2
        int colSelected = mouseX / CELL_SIZE; //CELL_SIZE is 100, so 0, 1, 2

        if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0
                && colSelected < COLS && first[rowSelected][colSelected] == '-') {
            first[rowSelected][colSelected] = 'X';
        }*/
        //repaint();

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    private void ChangeBoard(char[][] first, char Turn) {

        int rowSelected = mouseY / CELL_SIZE; //CELL_SIZE is 100, so 0, 1, 2
        int colSelected = mouseX / CELL_SIZE; //CELL_SIZE is 100, so 0, 1, 2

        if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0
                && colSelected < COLS && first[rowSelected][colSelected] == '-') {
            first[rowSelected][colSelected] = Turn;
        }
        repaint();
    }

    private void ComputerAI(char[][] first, char Mark) {

        int highScore = -2;
        int hold_i = 0, hold_j = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (first[i][j] == '-') {
                    first[i][j] = Mark;
                    // PrintBoard(first);
                    // System.out.println("Press enter to continue...");
                    // Scanner keyboard = new Scanner(System.in);
                    // keyboard.nextLine();
                    int tempScore = -Minimax(first, Mark);
                    first[i][j] = '-';  //reset the space
                    if( tempScore > highScore ) {
                        highScore = tempScore;
                        hold_i = i;
                        hold_j = j;
                    }
                }
            }
        }
        first[hold_i][hold_j] = Mark;
        repaint();
    }

    private static int Minimax(char[][] first, char Mark) {

        if(hasXWon(first)) //If 'X' wins score is minimum. This is undesirable.
            return -1;
        if(hasOWon(first)) //If 'O' wins score is 0. This is desirable.
            return 0;

        int score = -2;

        for(int i = 0; i < 3; i++){ //For all moves,
            for(int j = 0; j < 3; j++){
                if(first[i][j] == '-'){  //If legal
                    first[i][j] = switchPlayer(Mark); //Try the move
                    int thisScore = -Minimax(first, switchPlayer(Mark)); //recurse back
                    if(thisScore > score)   score = thisScore;
                    first[i][j] = '-';  //reset the position, this is rucursion.
                    //Each step is node and then once a tree is finished it recurses back
                    //and follows a different branch of that tree.
                }
            }
        }

        if(score == -2) return 0;
        return score;
    }

    private static boolean hasXWon(char[][] board){

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == 'X')
                if ( (board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) )
                    return true;
            if (board[0][i] == 'X')
                if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]))
                    return true;
        }
        if (board[0][0] == 'X')
            if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]))
                return true;

        if (board[0][2] == 'X')
            if ((board[0][2] == board[1][1] && board[1][1] == board[2][0]))
                return true;
        return false;
    }

    private static boolean hasOWon(char[][] board){

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == 'O')
                if ( (board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) )
                    return true;
            if (board[0][i] == 'O')
                if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]))
                    return true;
        }
        if (board[0][0] == 'O')
            if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]))
                return true;

        if (board[0][2] == 'O')
            if ((board[0][2] == board[1][1] && board[1][1] == board[2][0]))
                return true;
        return false;
    }

    private static char switchPlayer(char Turn) {
        if(Turn == 'X')
            return 'O';
        else return 'X';
    }
}