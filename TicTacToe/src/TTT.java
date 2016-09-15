import java.util.Scanner;

/**
 * Created by usman on 19/04/15.
 */
class TTT {

    public static char[][] first = {{'-', '-', 'O'},
                                    {'-', '-', 'O'},
                                    {'-', '-', 'O'}};
    public static char Turn = 'X';
    private static boolean CheckState;

    //Player is X, Computer is O.

    public static void main(String args[]) {

        int p;

        for (int c = 0; c < 3; c++) {
            for (int d = 0; d < 3; d++) {
                System.out.print(first[c][d] + "  ");
            }
            System.out.println("");
        }

        System.out.println("");

        while (CheckState() == false || CheckWin() == false) {
            if (Turn == 'X') {
                Turn = 'O';
                p = ComputerAI();
            }

            else {
                Turn = 'X';
                System.out.println("Enter the elements of puzzle");
                Scanner in = new Scanner(System.in);
                p = in.nextInt();
            }

            ChangeBoard(p);

            for (int c = 0; c < 3; c++) {
                for (int d = 0; d < 3; d++) {
                    System.out.print(first[c][d] + "  ");
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }

    private static boolean CheckWin() {

        char[][] board = new char[4][4];


        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = first[i][j];


        if (CheckRow(board) == true || CheckCol(board) == true || CheckDia(board) == true)
            return true;
        else
            return false;

    }

    private static void ChangeBoard(int p) {
        int m = p / 3;
        int n = p % 3;
        first[m][n] = Turn;
    }

    private static boolean CheckState() {

        for (int c = 0; c < 3; c++)
            for (int d = 0; d < 3; d++) {
                if (first[c][d] == '-')
                    return true;
            }
        return false;

    }

    private static boolean CheckRow(char[][] board) {
        //PrintBoard(board);
        //System.out.println("");
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-')
                if ((board[i][0] == board[i][1]) && (board[i][1] == board[i][2]))
                    return true;
        }
        return false;
    }

    private static boolean CheckCol(char[][] board) {

        //PrintBoard(board);
        //System.out.println("");
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '-')
                if ((board[0][i] == board[1][i]) && (board[1][i] == board[2][i]))
                    return true;
        }
        return false;
    }

    private static boolean CheckDia(char[][] board) {

        //PrintBoard(board);
        //System.out.println("");
        if (board[0][0] != '-')
            if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]))
                return true;

        if (board[0][2] != '-')
            if ((board[0][2] == board[1][1] && board[1][1] == board[2][0]))
                return true;

        return false;
    }

    private static int ComputerAI() {

        char[][] DummyBoard = new char[4][4];
        int highScore = -2;
        int hold_i = -1;
        int i,j;

        for (i = 0; i < 3; i++)
            for (j = 0; j < 3; j++)
                DummyBoard[i][j] = first[i][j];

        for (i = 0; i < 3; i++){
            for (j = 0; j < 3; j++){
                //System.out.println("i = " + i);
                //System.out.println("j = " + j);
                if ( DummyBoard[i][j] == '-' ) {
                    DummyBoard[i][j] = 'O';
                    //PrintBoard(DummyBoard);
                    if (CheckCol(DummyBoard) == true) {
                        return i*3+j%3;
                    }
                    if (CheckRow(DummyBoard) == true) {
                        return i*3+j%3;
                    }
                    if (CheckDia(DummyBoard) == true) {
                        return i*3+j%3;
                    }
                    int tempScore = MiniMax(DummyBoard, 'O');
                    System.out.println("Here");
                    System.out.println("tempScore = " + tempScore);
                    if( tempScore > highScore ) {
                        highScore = tempScore;
                        hold_i = i * 3 + j % 3;
                    }

                    //System.out.println("tempScore = " + tempScore);
                    System.out.println("Press enter to continue...");
                    Scanner keyboard = new Scanner(System.in);
                    keyboard.nextLine();

                    //reset the board
                    //System.out.println("Gee");
                    for (int a = 0; a < 3; a++)
                        for (int b = 0; b < 3; b++)
                            DummyBoard[a][b] = first[a][b];
                    PrintBoard(DummyBoard);
                }
            }
        }
        //System.out.println("Press enter to continue...");
        //Scanner keyboard = new Scanner(System.in);
        //keyboard.nextLine();
        return hold_i;
    }

    private static int MiniMax(char[][] DummyBoard, char Mark) {

        if(Mark == 'O')
            Mark = 'X';
        else Mark = 'O';
        int bear = 1;
        int i, j;


        for (i = 0; i < 3; i++) {
            for ( j = 0; j < 3; j++) {
                if (DummyBoard[i][j] == '-') {
                    DummyBoard[i][j] = Mark;
                    if (CheckCol(DummyBoard) == true){
                        if(Mark == 'O') bear = -10;
                        if(Mark == 'X') bear = 10;
                        return bear;
                    }
                    if (CheckRow(DummyBoard) == true){
                        if(Mark == 'O') bear = -10;
                        if(Mark == 'X') bear = 10;
                        return bear;
                    }
                    if (CheckDia(DummyBoard) == true){
                        if(Mark == 'O') bear = 10;
                        if(Mark == 'X') bear = -10;
                        System.out.println("jjjjjjj = " + j);
                        return bear;
                    }
                    PrintBoard(DummyBoard);
                    System.out.println("jug 2");
                    MiniMax(DummyBoard, Mark);
                }
                System.out.println("i = " + i);
                System.out.println("j = " + j);
            }
            //System.out.println("i = " + i);
        }
        System.out.println("i = " + i);
        //System.out.println("j = " + j);
        System.out.println("bear = " + bear);
        return bear;
    }

    private static void PrintBoard(char[][] DummyBoard){

        for (int c = 0; c < 3; c++) {
            for (int d = 0; d < 3; d++) {
                System.out.print(DummyBoard[c][d] + "  ");
            }
            System.out.println("");
        }

        System.out.println("");

    }

}