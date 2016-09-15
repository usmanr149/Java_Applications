import java.util.Scanner;

/**
 * Created by usman on 26/04/15.
 */

class TicTacToe {

    public static void main(String args[]) {

        int p = 0;
        char[][] board = {{'-', '-', '-'},
                         {'-', '-', '-'},
                         {'-', '-', '-'}};
        char Turn = 'O';
        PrintBoard(board);

        for(int i=1;i<10 && CheckWin(board) == false;i++)
        {
            Turn = switchPlayer(Turn);
            System.out.println("Turn = " + Turn);
            if(Turn == 'O')  {
                    ComputerAI(board, Turn);
            }
            if(Turn == 'X'){
                ChangeBoard(board, Turn);
                //System.out.println("You entered integer "+p);
            }
            PrintBoard(board);
        }

        if(hasXWon(board))
            System.out.println("You win");

        else if(hasOWon(board))
            System.out.println("You lose");

        else System.out.println("Draw");

    }

    private static char switchPlayer(char Turn) {
        if(Turn == 'X')
            return 'O';
        else return 'X';
    }


    private static void ChangeBoard(char[][] first, char Turn) {
        int p = -1;
        do{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter an integer");
            p = in.nextInt();
            if(first[p/3][p%3] != '-' || p < 0 || p >8 ){
                p = -1;
		PrintBoard(first);
                System.out.println("Invalid move");
            }
            else first[p/3][p%3] = Turn;
        }while(p == -1);
    }

    private static boolean CheckWin(char[][] first) {


        if (CheckRow(first) == true || CheckCol(first) == true || CheckDia(first) == true )
            return true;
        else
            return false;

    }


    private static boolean CheckRow(char[][] board) {
        //PrintBoard(board);
        //System.out.println("Checking Row");
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-')
                if ( (board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) )
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

        if (board[0][0] != '-')
            if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]))
                return true;

        if (board[0][2] != '-')
            if ((board[0][2] == board[1][1] && board[1][1] == board[2][0]))
                return true;

        return false;
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


    private static void ComputerAI(char[][] first, char Mark) {

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
        //PrintBoard(first);
        //System.out.println("highScore = " + highScore);
        //System.out.println("hold_i = " + hold_i);
        //System.out.println("Press enter to continue...");
        //Scanner keyboard = new Scanner(System.in);
        //keyboard.nextLine();
        //return 0;
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
                    // PrintBoard(first);
                    // System.out.println("highScore = " + score);
                    // System.out.println("Press enter to continue...");
                    // Scanner keyboard = new Scanner(System.in);
                    // keyboard.nextLine();
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
