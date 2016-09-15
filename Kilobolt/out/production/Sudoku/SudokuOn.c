#include <stdio.h>

int n=0;

int isAvailable(int puzzle[][9], int row, int col, int num)
{
    int rowStart = (row/3) * 3;
    int colStart = (col/3) * 3;
    int i, j;

    for(i=0; i<9; ++i)
    {
        if (puzzle[row][i] == num) return 0;  /* false */
        if (puzzle[i][col] == num) return 0;  /* false */
        if (puzzle[rowStart + (i%3)][colStart + (i/3)] == num) return 0; /* false */
    }
    return 1; /* true */
}

int fillSudoku(int puzzle[][9], int row, int col)
{
  int i,k,l;

 
  if(row<9 && col<9)
    {
      if(puzzle[row][col] != 0) //If there is a number in the column mover over to the next column.
        {
	  if((col+1)<9) return fillSudoku(puzzle, row, col+1);
	  else if((row+1)<9) return fillSudoku(puzzle, row+1, 0);
	  else return 1;  /* true */
        }
      else
        {
	  printf("begining1\n");
	  for(i=0; i<9; ++i)
            {
	      printf("i = %d\n", i);
	      printf("puzzle[%d][%d] = %d\n", row, col,puzzle[row][col]);
	      printf("begining2\n");
	      if(isAvailable(puzzle, row, col, i+1))
                {
		  puzzle[row][col] = i+1;
		  printf("i = %d\n", i);
		  printf("puzzle[%d][%d] = %d\n", row, col,puzzle[row][col]);
		  for(k=1; k<10; ++k)
		    {
		      for(l=1; l<10; ++l) printf("|%d", puzzle[k-1][l-1]);
		      printf("|\n");
		      if (k%3 == 0) printf("+-----+-----+-----+\n");
		    }
		  printf("n = %d\n", n);
		  getchar();
		  if((col+1)<9)
                    {
		      printf("i = %d\n", i);
		      printf("row = %d\n", row);
		      printf("col = %d\n", col);
		      printf("################");
		      getchar();
		      if(fillSudoku(puzzle, row, col +1)) return 1; /* true */
		      else puzzle[row][col] = 0;
                    }
		  else if((row+1)<9)
                    {
		      printf("i = %d\n", i);
		      printf("row = %d\n", row);
		      printf("col = %d\n", col);
		      printf("@@@@@@@@@@@@@@@@");
		      getchar();
		      if(fillSudoku(puzzle, row+1, 0)) return 1;  /* true, success */
		      else puzzle[row][col] = 0;
                    }
		  else return 1;  /* true */
		}
            } //falling out of the for loop here.
        }
      printf("f\n");
      printf("i = %d\n", i);
      printf("row = %d\n", row);
      printf("col = %d\n", col);
      printf("***************\n");
      return 0;  /* fasle, the puzzle is not solve, backtrack. Goes back to the for loop. */
    }
  else return 1; /* true */
  printf("g\n");
}

int main()
{
    int i, j;
    /* int puzzle[9][9]={{0, 0, 0, 0, 0, 0, 0, 9, 0}, */
    /*                   {1, 9, 0, 4, 7, 0, 6, 0, 8}, */
    /*                   {0, 5, 2, 8, 1, 9, 4, 0, 7}, */
    /*                   {2, 0, 0, 0, 4, 8, 0, 0, 0}, */
    /*                   {0, 0, 9, 0, 0, 0, 5, 0, 0}, */
    /*                   {0, 0, 0, 7, 5, 0, 0, 0, 9}, */
    /*                   {9, 0, 7, 3, 6, 4, 1, 8, 0}, */
    /*                   {5, 0, 6, 0, 8, 1, 0, 7, 4}, */
    /*                   {0, 8, 0, 0, 0, 0, 0, 0, 0}}; */

    /* int puzzle[9][9]={{0,0,0,0,0,0,0,0,0}, */
    /* 			 {0,0,0,0,0,3,0,8,5}, */
    /* 			 {0,0,1,0,2,0,0,0,0}, */
    /* 			 {0,0,0,5,0,7,0,0,0}, */
    /* 			 {0,0,4,0,0,0,1,0,0}, */
    /* 			 {0,9,0,0,0,0,0,0,0}, */
    /* 			 {5,0,0,0,0,0,0,7,3}, */
    /* 			 {0,0,2,0,1,0,0,0,0}, */
    /* 			 {0,0,0,0,4,0,0,0,9}}; */

    /* int puzzle[9][9]={{0,2,0,0,3,0,0,4,0}, */
    /* 		      {6,0,0,0,0,0,0,0,3}, */
    /* 		      {0,0,4,0,0,0,5,0,0}, */
    /* 		      {0,0,0,8,0,6,0,0,0}, */
    /* 		      {8,0,0,0,1,0,0,0,6}, */
    /* 		      {0,0,0,7,0,5,0,0,0}, */
    /* 		      {0,0,7,0,0,0,6,0,0}, */
    /* 		      {4,0,0,0,0,0,0,0,8}, */
    /* 		      {0,3,0,0,4,0,0,2,0}}; */

    /* int puzzle[9][9]={{0,2,0,0,5,0,7,0,0}, */
    /* 			 {4,0,0,1,0,0,0,0,6}, */
    /* 			 {8,0,0,0,0,3,0,0,0}, */
    /* 			 {2,0,0,0,0,8,0,0,3}, */
    /* 			 {0,4,0,0,2,0,5,0,0}, */
    /* 			 {0,0,0,6,0,0,0,1,0}, */
    /* 			 {0,0,2,0,9,0,0,0,0}, */
    /* 			 {0,9,0,0,0,0,0,0,5}, */
    /* 			 {7,0,4,0,0,0,9,0,0}}; */


    int puzzle[9][9]={{3, 0, 6, 5, 0, 8, 4, 0, 0},
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};


    if(fillSudoku(puzzle, 0, 0))
    {
        printf("\n+-----+-----+-----+\n");
        for(i=1; i<10; ++i)
        {
            for(j=1; j<10; ++j) printf("|%d", puzzle[i-1][j-1]);
            printf("|\n");
            if (i%3 == 0) printf("+-----+-----+-----+\n");
	}
	printf("n = %d\n", n);
    }
    else printf("\n\nNO SOLUTION\n\n");

    return 0;
}


/* n++; */
	      /* printf("\n+-----+-----+-----+\n"); */
	      /* for(k=1; k<10; ++k) */
	      /* 	{ */
	      /* 	  for(l=1; l<10; ++l) printf("|%d", puzzle[k-1][l-1]); */
	      /* 	  printf("|\n"); */
	      /* 	  if (k%3 == 0) printf("+-----+-----+-----+\n"); */
	      /* 	} */
	      /* printf("n = %d\n", n); */
	      /* printf("i = %d\n", i); */
	      /* getchar(); */
