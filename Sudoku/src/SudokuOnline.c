#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h> 

#define ROWS 9
#define COLS 9

int count = 0;

int printgrid(int puzzle[ROWS][COLS]){

  int i,j;

     printf("\n");
   for(i = 0 ; i < ROWS ; i++){
     for(j = 0 ; j < COLS ; j++){
       printf("%d   ", puzzle[i][j]);
     }
     printf("\n");
   }
   
}

int CheckRows(int puzzle [ROWS][COLS], int k, int l, int num){

  int i;
    for(i = 0 ; i < ROWS ; i++){
	if( puzzle[i][l] == num ) return 1;
    }
    return 0;
}

int CheckCols(int puzzle[ROWS][COLS], int k, int l, int num){

 int j;
    for(j = 0 ; j < COLS ; j++){
	if( puzzle[k][j] == num ) return 1;
    }
    return 0;
}

int CheckGrid(int puzzle[ROWS][COLS], int k, int l, int num){

  int i,j;
  int m = k/3;
  int n = l/3;

 for(i = 3*m ; i < 3*m+3 ; i++){
   for(j = 3*n ; j < 3*n+3 ; j++){
       if(puzzle[i][j] == num) return 1;
   }
}
 return 0;
}

int isSafe(int grid[ROWS][COLS], int row, int col, int num)
{

if( !CheckRows(grid, row, col, num) && !CheckCols(grid, row, col, num) && !CheckGrid(grid, row, col, num) ) return 1;
 else return 0;
}

int SolveSudoku(int grid[ROWS][COLS])
{
  int row, col;
  int num;

for (row = 0; row < ROWS; row++)
        for (col = 0; col < COLS; col++)
	  if (grid[row][col] == 0){
count++;
            for( num = 1; num <= 9; num++ )
             {
         if(isSafe(grid, row, col, num) == 1 ) //Check if the number is safe to be assigned.
	{
           grid[row][col] = num; //If it is then make a tentative assigned.
           //printf("grid[%d][%d] = %d\n", row, col, grid[row][col]);
           //printgrid(grid);
           if (SolveSudoku(grid))  return 1;
	   grid[row][col] = 0;
         }
} //This is the end of for loop.
 return 0;  //This triggers backtracking, goes back to the for loop for the last function on the stack.
}
}

	int main()
	{

	int row, col;
	char line[132];
	  FILE * fp;
	    // 0 means unassigned cells
	int grid[ROWS][COLS];

	if((fp=fopen("Sudoku.txt","r"))==NULL)
	    {
	      printf("Cannot open Sudoku.txt.\n");
	exit(0);
	      return 0;
	    }	
	
		  printf("Parameters read from Sudoku.txt\n");

	while( !feof(fp) ){
	  	for(row = 0 ; row < ROWS ; row++){
	  	  for(col=0 ; col < COLS ; col++){
	  	    if(!fscanf(fp,"%d", &grid[row][col]));
	  	    //printf("puzzle[%d][%d] = %d\n", i, j, puzzle[i][j]);
	  	  }
	}
	 printgrid(grid);
	if(feof(fp))
	  break;
	if (SolveSudoku(grid) == 1)
		  printgrid(grid);
	else
		 printf("No solution exists\n");

	printf("Number of steps : %d\n", count);
	row=0;
	col=0;
	count = 0;
	getchar();
	}
	  	      fclose(fp);
	  	      //printf("Count for Co-60 = %d\n\n\n", count);
		      
	  	      //printgrid(grid);
	  	      //getchar();
	  	      printf("Done\n\n");


	    /* printgrid(grid); */
	/* if (SolveSudoku(grid) == 1){ */
	/*           printgrid(grid); */
	/* printf("Number of steps : %d\n", count); */
	/* } */
	/*     else */
	/*          printf("No solution exists\n"); */
	 
	    return 0;
	}


