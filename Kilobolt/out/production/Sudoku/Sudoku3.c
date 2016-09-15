#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h> 

#define ROWS 9
#define COLS 9


int n =0;
int SetRows = 1;
int SetCols = 1;
int SetGrid = 1;
int SetUnique = 0;
int Validity = 0;

int printpuzzle(int puzzle[ROWS][COLS]){

  int i,j;

   for(i = 0 ; i < ROWS ; i++){
     for(j = 0 ; j < COLS ; j++){
       printf("%d   ", puzzle[i][j]);
     }
     printf("\n");
   }
   
}

int CheckRows(int puzzle [ROWS][COLS], int k, int l){

  int i;
    for(i = 0 ; i < ROWS ; i++){
      if(i != k){
	if( puzzle[i][l] == puzzle[k][l] ) SetRows = 0;
      }
    }
}

int CheckCols(int puzzle[ROWS][COLS], int k, int l){

 int j;
    for(j = 0 ; j < COLS ; j++){
      if(j != l){
	if( puzzle[k][j] == puzzle[k][l] ) SetCols = 0;
      }
   }
}

int CheckGrid(int puzzle[ROWS][COLS], int k, int l){

  int i,j;
  int m = k/3;
  int n = l/3;

 for(i = 3*m ; i < 3*m+3 ; i++){
   for(j = 3*n ; j < 3*n+3 ; j++){
     if( i != k && j != l ){
       if(puzzle[i][j] == puzzle[k][l]) SetGrid = 0;
     }
   }
 }
}

int isValid(int puzzle[ROWS][COLS]){

 int i,j;

 for(i = 0 ; i < ROWS ; i++){
    for(j=0 ; j < COLS ; j++){
	 if(puzzle[i][j] == 0)
	   Validity = 0;
	 else Validity = 1;
    }
}
}

int Solve(int puzzle[ROWS][COLS]){

  int i, j, k, opj, opi, m, n;
  int Grid[ROWS][COLS];

  isValid(puzzle);
  //Consider number 1 to 9.
  for( k = 1 ; k <= 10 ; k++)
    {	    
	    	    puzzle [i][j] = k;
		    CheckRows(puzzle,i,j);
		    CheckCols(puzzle,i,j);
		    CheckGrid(puzzle,i,j);
	  
		    if(SetRows == 1 && SetCols == 1 && SetGrid == 1){

 
		      printpuzzle(puzzle);
		      printf("\n");
		      
		      if(Validity == 0) Solve(puzzle);

	  }
	}
}

	 

int main(){

int i, j;

 int puzzle[ROWS][COLS]={{3, 0, 6, 5, 0, 8, 4, 0, 0},
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};

 /* int puzzle[ROWS][COLS]={{0, 0, 0, 0, 0, 0, 0, 9, 0}, */
 /* 			 {1, 9, 0, 4, 7, 0, 6, 0, 8}, */
 /* 			 {0, 5, 2, 8, 1, 9, 4, 0, 7}, */
 /* 			 {2, 0, 0, 0, 4, 8, 0, 0, 0}, */
 /* 			 {0, 0, 9, 0, 0, 0, 5, 0, 0}, */
 /* 			 {0, 0, 0, 7, 5, 0, 0, 0, 9}, */
 /* 			 {9, 0, 7, 3, 6, 4, 1, 8, 0}, */
 /* 			 {5, 0, 6, 0, 8, 1, 0, 7, 4}, */
 /* 			 {0, 8, 0, 0, 0, 0, 0, 0, 0}}; */


/* int puzzle[ROWS][COLS]={{3, 0, 6, 5, 0, 8 }, */
/* 			{5, 2, 0, 0, 0, 0}, */
/* 			{0, 8, 7, 0, 0, 0}, */
/* 			{0, 0, 3, 0, 1, 0}, */
/* 			 {9, 0, 0, 8, 6, 3}, */
/* 			 {0, 5, 0, 0, 9, 0}}; */



 printpuzzle(puzzle);
 getchar();
 printf("\n\n");

 //CheckContradiction(puzzle);
 Solve(puzzle);
}
