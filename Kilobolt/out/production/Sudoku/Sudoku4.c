//If the Sudoku puzzle has no solution then the program just does an indefinite loop.

#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h> 

#define ROWS 9
#define COLS 9


int n =0;
int SetRows = 0;
int SetCols = 0;
int SetGrid = 0;
int SetUnique = 0;
int Validity = 0;

int printpuzzle(int puzzle[ROWS][COLS]){

  int i,j;

     printf("\n");
   for(i = 0 ; i < ROWS ; i++){
     for(j = 0 ; j < COLS ; j++){
       printf("%d   ", puzzle[i][j]);
     }
     printf("\n");
   }
   
}

int GridFill(int puzzle[ROWS][COLS], int i, int j){

  int m,n, zig=0, num=0;


  /* printf("In Gridfill\n"); */
  /* printpuzzle(puzzle); */

  //getchar();
    for(m=3*(i/3) ; m < 3*(i/3)+3 ; m++){
      for(n=3*(j/3) ; n < 3*(j/3)+3 ; n++){
	if(puzzle[m][n] != 0) num += puzzle[m][n];
	if(puzzle[m][n] == 0)
	  zig++; 
      }
    }
    
    if(zig == 1){
      num = 45 - num;
      puzzle[i][j] = num;
      SetRows = 1;
      SetCols = 1;
      SetGrid = 1;
      CheckRows(puzzle, i, j);
      CheckCols(puzzle, i, j);
      CheckGrid(puzzle, i, j);
      if(SetRows == 0 || SetCols == 0 || SetGrid == 0){
	printf("No Solution!\n");
	  exit(0);
	}
    }
}


int CheckRows(int puzzle [ROWS][COLS], int k, int l){

  int i;
    for(i = 0 ; i < ROWS ; i++){
      if(i != k){
	if( puzzle[i][l] == puzzle[k][l] ) return 0;
      }
    }
    return 1;
}

int CheckCols(int puzzle[ROWS][COLS], int k, int l){

 int j;
    for(j = 0 ; j < COLS ; j++){
      if(j != l){
	if( puzzle[k][j] == puzzle[k][l] ) return 0;
      }
    }
    return 1;
}

int CheckGrid(int puzzle[ROWS][COLS], int k, int l){

  int i,j;
  int m = k/3;
  int n = l/3;

 for(i = 3*m ; i < 3*m+3 ; i++){
   for(j = 3*n ; j < 3*n+3 ; j++){
     if( i != k || j != l ){
       if(puzzle[i][j] == puzzle[k][l]) return 0;
     }
   }
 }
 return 1;
}

int Solve(int puzzle[ROWS][COLS], int i, int j){
  
  int k;
  
  printpuzzle(puzzle);

  if( i == 8){
    return 1; //Success
  }
  else{
    //consider this row and column
    //See if there is a 0 there
    if( j > 8 )
      {
	j=0;
	i++;
      }
    printf("j = %d\n", j);
    if( puzzle[i][j] !=0 )
      Solve(puzzle, i, j+1);
    else if( puzzle[i][j] == 0 ){
      //Try putting a number there
      for(k=1; k < 10 ; k++){
	printf("k = %d\n", k);
	puzzle[i][j]=k;
	printpuzzle(puzzle);
	getchar();
	//If the number can be put there, call the Solve() function recursively
	if ( (CheckRows(puzzle, i, j))  && (CheckCols(puzzle, i, j)) && (CheckGrid(puzzle, i, j)) ){
	  printf("j = %d\n", j);
	  if( Solve(puzzle, i, j+1) )  return 1; /* true, success. */
	}
	else {
	  printf("puzzle[%d][%d] = %d\n", i, j, puzzle[i][j]);
	  puzzle[i][j]=0;
	  printf("puzzle[%d][%d] = %d\n", i, j, puzzle[i][j]);
	}
      } //falling out of the for loop here.
    }
    else return 0;
  } return 0; /* true, success */
}

int CheckContradiction(int puzzle[ROWS][COLS]){

  int i,j,k,l,m,n;

for(i = 0 ; i < ROWS ; i++){
  for(j=0 ; j < COLS ; j++){
    for(k=j+1 ; k < COLS ; k++){             //Check all the columns.
      if(puzzle[i][j] != 0)               
	if(puzzle[i][j] == puzzle[i][k]){
	  printf("puzzle[%d][%d] = %d\n", i, j, puzzle[i][j] );
	  printf("puzzle[%d][%d] = %d\n", i, k, puzzle[i][k] );
	  printf("Multiple exact entries in Row %d.\n", i );
	    printf("Contradiction\n");
	    exit(0);
	}
    }

    for(l=i+1 ; l < ROWS ; l++){   //Check all the rows.
      if(puzzle[i][j] != 0)
	if(puzzle[i][j] == puzzle[l][j]){
	   printf("puzzle[%d][%d] = %d\n", i, j, puzzle[i][j] );
	   printf("puzzle[%d][%d] = %d\n", l, j, puzzle[l][j] );
	   printf("Multiple exact entries in Col %d.\n", j);
	    printf("Contradiction\n");
	    exit(0);
	}
     }
    for(m=3*(i/3) ; m < 3*(i/3)+3 ; m++){
      for(n=3*(j/3) ; n < 3*(j/3)+3 ; n++){
	if(puzzle[i][j] != 0){
	  if(i != m || j != n){
	    if(puzzle[i][j] == puzzle[m][n]){
	      printf("m = %d\n", m);
	      printf("n = %d\n", n);
	      printf("puzzle[%d][%d] = %d\n", i, j, puzzle[i][j] );
	      printf("puzzle[%d][%d] = %d\n", m, n, puzzle[m][n] );
	      printf("Check : %d x %d and %d x %d\n", i, j, m, n);
	      printf("Contradiction\n");
	      exit(0);
	    }
	  }
	}
      }
    }
  }
  }
}

int main(){

  int i, j;
  char line[132];
  FILE * fp;

int puzzle[ROWS][COLS]={{3, 0, 6, 5, 0, 8, 4, 0, 0},
 			 {5, 2, 0, 0, 0, 0, 0, 0, 0},
 			 {0, 8, 7, 0, 0, 0, 0, 3, 1},
 			 {0, 0, 3, 0, 1, 0, 0, 8, 0},
 			 {9, 0, 0, 8, 6, 3, 0, 0, 5},
 			 {0, 5, 0, 0, 9, 0, 6, 0, 0},
 			 {1, 3, 0, 0, 0, 0, 2, 5, 0},
 			 {0, 0, 0, 0, 0, 0, 0, 7, 4},
 			 {0, 0, 5, 2, 0, 6, 3, 0, 0}};

  /* if((fp=fopen("Sudoku.txt","r"))==NULL) */
  /*   { */
  /*     printf("Cannot open Co60_abs_eff.dat.\n"); */
  /*     exit(0); */
  /*     return 0; */
  /*   } */

  /* printf("Parameters read from Sudoku.txt\n"); */

  /* 	for(i = 0 ; i < ROWS ; i++){ */
  /* 	  for(j=0 ; j < COLS ; j++){ */
  /* 	    if(!fscanf(fp,"%d", &puzzle[i][j])); */
  /* 	    //printf("puzzle[%d][%d] = %d\n", i, j, puzzle[i][j]); */
  /* 	  } */
  /* 	}    */
  /* 	      fclose(fp); */
  /* 	      //printf("Count for Co-60 = %d\n\n\n", count); */
	      
  /* 	      printpuzzle(puzzle); */
  /* 	      //getchar(); */
  /* 	      printf("\n\n"); */

	      CheckContradiction(puzzle);
	      Solve(puzzle, 0 , 0);
	      printf("\n\n");
	      printpuzzle(puzzle);
	}

 /* int puzzle[ROWS][COLS]={{0, 0, 0, 0, 0, 0, 0, 9, 2}, */
 /* 			 {1, 9, 0, 4, 7, 0, 6, 0, 8}, */
 /* 			 {0, 5, 2, 8, 1, 9, 4, 0, 7}, */
 /* 			 {2, 0, 0, 0, 4, 8, 0, 0, 0}, */
 /* 			 {0, 0, 9, 0, 0, 0, 5, 0, 0}, */
 /* 			 {0, 0, 0, 7, 5, 0, 0, 0, 9}, */
 /* 			 {9, 0, 7, 3, 6, 4, 1, 8, 0}, */
 /* 			 {5, 0, 6, 0, 8, 1, 0, 7, 4}, */
 /* 			 {0, 8, 0, 0, 0, 0, 0, 0, 0}}; */

 /* int puzzle[ROWS][COLS]={{0,0,0,0,0,0,0,0,0}, */
 /* 			 {0,0,0,0,0,3,0,8,5}, */
 /* 			 {0,0,1,0,2,0,0,0,0}, */
 /* 			 {0,0,0,5,0,7,0,0,0}, */
 /* 			 {0,0,4,0,0,0,1,0,0}, */
 /* 			 {0,9,0,0,0,0,0,0,0}, */
 /* 			 {5,0,0,0,0,0,0,7,3}, */
 /* 			 {0,0,2,0,1,0,0,0,0}, */
 /* 			 {0,0,0,0,4,0,0,0,9}}; */

 /* int puzzle[ROWS][COLS]={{0,2,0,0,5,0,7,0,0}, */
 /* 			 {4,0,0,1,0,0,0,0,6}, */
 /* 			 {8,0,0,0,0,3,0,0,0}, */
 /* 			 {2,0,0,0,0,8,0,0,3}, */
 /* 			 {0,4,0,0,2,0,5,0,0}, */
 /* 			 {0,0,0,6,0,0,0,1,0}, */
 /* 			 {0,0,2,0,9,0,0,0,0}, */
 /* 			 {0,9,0,0,0,0,0,0,5}, */
 /* 			 {7,0,4,0,0,0,9,0,0}}; */

 


/* 000000000 */
/* 000003085 */
/* 001020000 */
/* 000507000 */
/* 004000100 */
/* 090000000 */
/* 500000073 */
/* 002010000 */
/* 000040009 */


 /* int puzzle[ROWS][COLS]={{3, 0, 6, 5, 0, 8 }, */
/* 			{5, 2, 0, 0, 0, 0}, */
/* 			{0, 8, 7, 0, 0, 0}, */
/* 			{0, 0, 3, 0, 1, 0}, */
/* 			 {9, 0, 0, 8, 6, 3}, */
/* 			 {0, 5, 0, 0, 9, 0}}; */
