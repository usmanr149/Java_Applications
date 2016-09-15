import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SudokuGame
        implements Cloneable {
    private Set<Integer>[][] PlayArea = new HashSet[9][9];
    private Set<Integer> AllPossible = new HashSet();

    // Arbitrary constants for passing out of search functions.
    private static Point PROGRESS = new Point(-1, -1);
    private static Point NOPROGRESS = new Point(-2, -2);

    public SudokuGame() {
        int cctr, rctr;
        AllPossible.add(new Integer(1));
        AllPossible.add(new Integer(2));
        AllPossible.add(new Integer(3));
        AllPossible.add(new Integer(4));
        AllPossible.add(new Integer(5));
        AllPossible.add(new Integer(6));
        AllPossible.add(new Integer(7));
        AllPossible.add(new Integer(8));
        AllPossible.add(new Integer(9));
        for(cctr = 0; cctr < 9; cctr++)
            for(rctr = 0; rctr < 9; rctr++)
                PlayArea[rctr][cctr] = new HashSet<Integer>(AllPossible);
    }

    public SudokuGame(SudokuGame SG) {
        int rctr, cctr;
        AllPossible.add(new Integer(1));
        AllPossible.add(new Integer(2));
        AllPossible.add(new Integer(3));
        AllPossible.add(new Integer(4));
        AllPossible.add(new Integer(5));
        AllPossible.add(new Integer(6));
        AllPossible.add(new Integer(7));
        AllPossible.add(new Integer(8));
        AllPossible.add(new Integer(9));
        for(cctr=0; cctr<9; cctr++)
            for(rctr=0; rctr<9; rctr++)
                PlayArea[rctr][cctr] = new HashSet<Integer>(SG.PlayArea[rctr][cctr]);
    }

    public SudokuGame clone() {
        return new SudokuGame(this);
    }

    // Returns a 9x9 array of the numbers that are known in the current puzzle.
    // Nulls indicate space is unknown
    public Integer[][] getKnown() {
        int rctr, cctr;
        Integer[][] ReturnVal = null;
        for(cctr=0; cctr < 9; cctr++)
            for(rctr=0; rctr < 9; rctr++)
                ReturnVal[rctr][cctr] = getValue(rctr, cctr);
        return ReturnVal;
    }

    // Returns the value in space (r, c) if known, otherwise returns null.
    public Integer getValue(int r, int c) {
        if(PlayArea[r][c].size()>1)
            return null;
        else
            return new Integer((Integer)PlayArea[r][c].toArray()[0]);
    }

    public Integer getValue(Point p) {
        return getValue(p.x, p.y);
    }

    // Takes a 9x9 array of the numbers that are known in the current puzzle.
    // Nulls indicate that the space is unknown. Does not verify input.
    public void setValues(Integer[][] knowns) {
        int rctr, cctr;
        for(cctr=0; cctr < 9; cctr++)
            for(rctr=0; rctr < 9; rctr++)
                setValue(rctr, cctr, knowns[rctr][cctr]);
    }

    // Sets space (r, c) to value n
    public void setValue(int r, int c, Integer n) {
        if(n==null)
            PlayArea[r][c].addAll(AllPossible);
        else {
            PlayArea[r][c].clear();
            PlayArea[r][c].add(n);
        }
    }

    // There are 9 squares that can be checked. We index them by row and column, 0 - 2;
    // The function returns true if the integer is found as a known value in that square,
    // otherwise, it returns false.
    protected boolean checkSquare(int r, int c, Integer n) {
        int rctr, cctr;
        for(rctr = (r/3)*3; rctr < (r/3+1)*3; rctr++)
            for(cctr = (c/3)*3; cctr < (c/3+1)*3; cctr++)
                if(PlayArea[rctr][cctr].contains(n) && PlayArea[rctr][cctr].size()==1)
                    return true;
        return false;
    }

    // Check column c for number n, known for sure. Returns true if found, false if not.
    protected boolean checkColumn(int c, Integer n) {
        int rctr;
        for(rctr=0; rctr < 9; rctr++)
            if(PlayArea[rctr][c].contains(n) && PlayArea[rctr][c].size()==1)
                return true;
        return false;
    }

    protected boolean checkRow(int r, Integer n) {
        int cctr;
        for(cctr=0; cctr < 9; cctr++)
            if(PlayArea[r][cctr].contains(n) && PlayArea[r][cctr].size()==1)
                return true;
        return false;
    }

    // Checks to see if n exists in the possibility sets of the OTHER cells in the square
    protected boolean checkSquareOtherPossible(int r, int c, Integer n) {
        int rctr, cctr;
        for(rctr = (r/3)*3; rctr < (r/3+1)*3; rctr++)
            for(cctr = (c/3)*3; cctr < (c/3+1)*3; cctr++)
                if(PlayArea[rctr][cctr].contains(n) && !((rctr==r) && (cctr==c)))
                    return true;
        return false;
    }

    // Check column c to see if n is possible in any OTHER cell.
    protected boolean checkColumnOtherPossible(int r, int c, Integer n) {
        int rctr;
        for(rctr=0; rctr < 9; rctr++)
            if(PlayArea[rctr][c].contains(n) && (rctr!=r))
                return true;
        return false;
    }

    protected boolean checkRowOtherPossible(int r, int c, Integer n) {
        int cctr;
        for(cctr=0; cctr < 9; cctr++)
            if(PlayArea[r][cctr].contains(n) && (cctr!=c))
                return true;
        return false;
    }

    // Updates the set of possibilities for cell (r, c). Returns true if the set is
    // reduced to 1 (i.e. value known for sure). This is by simply checking other
    // known values in the same row/column/square.
    protected Point updatePossible(int r, int c) {
        boolean ProgressMade = false;
        Integer n;
        // see if any of the remaining possibilities can be eliminated
        for (Iterator<Integer> i = PlayArea[r][c].iterator(); i.hasNext(); ) {
            n = i.next();
            if (checkSquare(r, c, n)) {
                ProgressMade = true;
                i.remove();
            }
            else if(checkColumn(c, n)) {
                ProgressMade = true;
                i.remove();
            }
            else if(checkRow(r, n)){
                ProgressMade = true;
                i.remove();
            }
            if(PlayArea[r][c].size()==1)
                return new Point(r, c);
        }
        if(ProgressMade)
            return PROGRESS;
        else
            return NOPROGRESS;
    }

    // Finds another place to play. Returns the (r, c) pair to the play. The board will
    // already be updated. Returns null if no play can be found.
    public Point findNextPlay() {
        // ProgressMade is a flag that lets us run through until either a play is found
        // and returned or no progress is made for an entire loop iteration.
        boolean ProgressMade;
        Point Result;
        int rctr, cctr, j, k; // loop counters

        // Initial check for validity. May avoid nonsense?
        if(!isValid()) return null;

        do {
            ProgressMade = false;
            // ############################################################################
            // First look for spaces that can be reduced to one possiblity
            for(rctr=0; rctr < 9; rctr++)
                for(cctr=0; cctr < 9; cctr++) {
                    if(PlayArea[rctr][cctr].size()>1) {
                        Result = updatePossible(rctr, cctr);
                        if(Result.equals(PROGRESS))
                            ProgressMade = true;
                        else if(!Result.equals(NOPROGRESS))
                            return new Point(rctr, cctr);
                    }
                }
            // ############################################################################
            // Then look for square/rows/columns that require the cell to take a value
            // All possibilities have already been updated by previous step.
            for(cctr=0; cctr < 9; cctr++)
                for(rctr=0; rctr < 9; rctr++) {
                    if(PlayArea[rctr][cctr].size()>1) {
                        for(Integer i: PlayArea[rctr][cctr]) {
                            if(!checkSquareOtherPossible(rctr, cctr, i) ||
                                    !checkRowOtherPossible(rctr, cctr, i) ||
                                    !checkColumnOtherPossible(rctr, cctr, i)) {
                                // make this a known play on the puzzle
                                setValue(rctr, cctr, i);
                                return new Point(rctr, cctr);
                            }
                        }
                    }
                }
            // ############################################################################
            // Look for values known in columns and rows. In a square, if the only spaces
            // that can take a value fall in the same row or column, that value can be
            // eliminated in the other squares in that row or column.
            // The counters iterate over the squares.

            // This could all be done with one set, but I think for clarity it's worthwhile to
            // use several....
            Set<Integer> In = new HashSet<Integer>(); // for the IN-row or IN-column sets
            Set<Integer> Out = new HashSet<Integer>(); // for the OUT-of-row or OUT-of-column sets
            Set<Integer> SemiFound = new HashSet<Integer>(); // determined to be in the IN-row or IN-column
            for(cctr=0; cctr<3; cctr++) {
                for(rctr=0; rctr<3; rctr++) {
                    // Check Rows
                    for(j=0; j<3; j++) {
                        // Row j
                        In.clear() ;
                        Out.clear();
                        In.addAll(PlayArea[rctr*3+j][cctr*3]);
                        In.addAll(PlayArea[rctr*3+j][cctr*3+1]);
                        In.addAll(PlayArea[rctr*3+j][cctr*3+2]);
                        Out.addAll(PlayArea[rctr*3+(1+j)%3][cctr*3]);
                        Out.addAll(PlayArea[rctr*3+(1+j)%3][cctr*3+1]);
                        Out.addAll(PlayArea[rctr*3+(1+j)%3][cctr*3+2]);
                        Out.addAll(PlayArea[rctr*3+(2+j)%3][cctr*3]);
                        Out.addAll(PlayArea[rctr*3+(2+j)%3][cctr*3+1]);
                        Out.addAll(PlayArea[rctr*3+(2+j)%3][cctr*3+2]);
                        // If a number exists in the set In but does not exist in the set Out,
                        // then that number must be placed in the row In represents. Therefore
                        // it can be eliminated from the rest of that row (the other 2 squares)
                        SemiFound.clear();
                        SemiFound.addAll(In);
                        SemiFound.removeAll(Out);
                        for(k=0; k<6; k++)
                            if(PlayArea[rctr*3+j][(cctr*3+3+k)%9].size()>1) {
                                ProgressMade = ProgressMade ||
                                        PlayArea[rctr*3+j][(cctr*3+3+k)%9].removeAll(SemiFound);
                                if(PlayArea[rctr*3+j][(cctr*3+3+k)%9].size()==1)
                                    return new Point(rctr*3+j, (cctr*3+3+k)%9);
                            }
                    }
                    // Check Columns
                    for(j=0; j<3; j++){
                        // Column j
                        In.clear() ;
                        Out.clear();
                        In.addAll(PlayArea[rctr*3][cctr*3+j]);
                        In.addAll(PlayArea[rctr*3+1][cctr*3+j]);
                        In.addAll(PlayArea[rctr*3+2][cctr*3+j]);
                        Out.addAll(PlayArea[rctr*3][cctr*3+(1+j)%3]);
                        Out.addAll(PlayArea[rctr*3+1][cctr*3+(1+j)%3]);
                        Out.addAll(PlayArea[rctr*3+2][cctr*3+(1+j)%3]);
                        Out.addAll(PlayArea[rctr*3][cctr*3+(2+j)%3]);
                        Out.addAll(PlayArea[rctr*3+1][cctr*3+(2+j)%3]);
                        Out.addAll(PlayArea[rctr*3+2][cctr*3+(2+j)%3]);
                        // If a number exists in the set In but does not exist in the set Out,
                        // then that number must be placed in the row In represents. Therefore
                        // it can be eliminated from the rest of that row (the other 2 squares)
                        SemiFound.clear();
                        SemiFound.addAll(In);
                        SemiFound.removeAll(Out);
                        for(k=0; k<6; k++)
                            if(PlayArea[(rctr*3+3+k)%9][cctr*3+j].size()>1) {
                                ProgressMade = ProgressMade ||
                                        PlayArea[(rctr*3+3+k)%9][cctr*3+j].removeAll(SemiFound);
                                if(PlayArea[(rctr*3+3+k)%9][cctr*3+j].size()==1)
                                    return new Point((rctr*3+3+k)%9, cctr*3+j);
                            }
                    }
                }
            }
            // ############################################################################
            // Look for twins, i.e. pairs in the same row/column/square with the same set of 2
            // possibilities. These 2 can be eliminated from the rest of the row/column/square.

            // Search rows
            // In each row (rctr) compare cell cctr and j.
            for(rctr=0; rctr<9; rctr++)
                for(cctr=0; cctr<8; cctr++)
                    if(PlayArea[rctr][cctr].size()==2)
                        for(j=cctr+1; j<9; j++)
                            if(PlayArea[rctr][cctr].equals(PlayArea[rctr][j]))
                                for(k=0; k<9; k++)
                                    if(k!=cctr && k!=j && PlayArea[rctr][k].size()>1) {
                                        ProgressMade = ProgressMade ||
                                                PlayArea[rctr][k].removeAll(PlayArea[rctr][cctr]);
                                        if(PlayArea[rctr][k].size()==1)
                                            return new Point(rctr, k);
                                    }
            // Search columns
            for(cctr=0; cctr<9; cctr++)
                for(rctr=0; rctr<8; rctr++)
                    if(PlayArea[rctr][cctr].size()==2)
                        for(j=rctr+1; j<9; j++)
                            if(PlayArea[rctr][cctr].equals(PlayArea[j][cctr]))
                                for(k=0; k<9; k++)
                                    if(k!=rctr && k!=j && PlayArea[k][cctr].size()>1) {
                                        ProgressMade = ProgressMade ||
                                                PlayArea[k][cctr].removeAll(PlayArea[rctr][cctr]);
                                        if(PlayArea[k][cctr].size()==1)
                                            return new Point(k, cctr);
                                    }
            // Search squares?? maybe later....


        } while(ProgressMade);

        // ############################################################################
        // OK, logic failed us. Now for "bifurcation" or "TRIAL-AND-ERROR"
        // We'll duplicate the game, choose a cell with 2 possibilities, arbitrarily
        // choose 1, then play out the game to see if it solves. If not, the other must.
        // Recursion makes this super simple!

        // First look for a cell with the smallest number of >1 possibilities.
        // Probably is 2 but who knows what hellish problem they might give.
        for(j=2; j<=9; j++)
            for(rctr=0; rctr<9; rctr++)
                for(cctr=0; cctr<9; cctr++)
                    if(PlayArea[rctr][cctr].size()==j) {
                        // Duplicate game
                        SudokuGame Dup = new SudokuGame(this);
                        // Pick one (first one)
                        Integer n = (Integer)PlayArea[rctr][cctr].toArray()[0];
                        Dup.setValue(rctr, cctr, n);
                        // See if it solves
                        if(Dup.SolveAll()) {
                            setValue(rctr, cctr, n);
                            return new Point(rctr, cctr);
                        }
                        else {
                            PlayArea[rctr][cctr].remove(n);
                            if(PlayArea[rctr][cctr].size()==1)
                                return new Point(rctr, cctr);
                            else
                                return findNextPlay();
                        }

                    }
        // Should never get here. Recursive part will go on trying and trying.
        // Every iteration will either set a value or remove a possibility so it
        // shouldn't go forever. I hope....
        return null;
    }

    // Solves to the end, if possible. Returns true if the problem is able to be solved.
    public boolean SolveAll() {
        while(!AllSolved())
            if(findNextPlay()==null)
                return false;
        return true;
    }

    // Returns true if the puzzle has been totally solved.
    public boolean AllSolved() {
        int rctr, cctr;
        for(cctr=0; cctr < 9; cctr++)
            for(rctr=0; rctr < 9; rctr++)
                if(PlayArea[rctr][cctr].size()>1)
                    return false;
        return true;
    }

    // Verifies if the puzzle is valid. Every space must have at least one possibility
    // and no two known in any row/column/square ought to be the same. Will not check
    // the possibility sets with more than one possibility and does not check if the
    // puzzle is solvable.
    public boolean isValid() {
        int rctr, cctr, n;

        // rows, cols, and squares will record if each number has already been found
        // in each row, col, or square.
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][][] squares = new boolean[3][3][10];

        //Initialize to zeros.
        for(rctr=0; rctr<9; rctr++)
            for(n=0; n<=9; n++) {
                rows[rctr][n]=false;
                cols[rctr][n]=false;
                squares[rctr/3][rctr%3][n]=false;
            }
        for(rctr=0; rctr<9; rctr++)
            for(cctr=0; cctr<9; cctr++) {
                if(PlayArea[rctr][cctr].size()==0)
                    return false;
                else if(PlayArea[rctr][cctr].size()==1) {
                    n = ((Integer)PlayArea[rctr][cctr].toArray()[0]).intValue();
                    if(rows[rctr][n]) return false;
                    else rows[rctr][n]= true;
                    if(cols[cctr][n]) return false;
                    else cols[cctr][n]= true;
                    if(squares[rctr/3][cctr/3][n]) return false;
                    else squares[rctr/3][cctr/3][n]= true;
                }
            }
        return true;
    }
}