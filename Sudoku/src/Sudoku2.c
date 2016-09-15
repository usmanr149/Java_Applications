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
     if( i != k || j != l ){
       /* printf("puzzle[%d][%d] = %d  ", i,j,puzzle[i][j]); */
       if(puzzle[i][j] == puzzle[k][l])
	 {
	   SetGrid = 0;
	 }
     }
   }/* printf("\n"); */
 }
 /* printf("puzzle[%d][%d] = %d\n", k,l,puzzle[k][l]); */
 /* printf("SetGrid = %d", SetGrid); */
 /* getchar(); */
}


int CheckUnique(int puzzle[ROWS][COLS], int i, int j, int k){

  int m = i/3, n = j/3, num;
  int opj, opi;

SetRows = 0;
SetCols = 0;
SetGrid = 0;
SetUnique = 1;  //Assume it is unique and verify


 for(opi = 3*m ; opi < 3*m+3 ; opi++){
    for(opj = 3*n ; opj < 3*n+3 ; opj++){
      if(puzzle[opi][opj] == 0 ){
	if(SetUnique==1)
	  if(i != opi || j != opj){
	    SetRows = 1;
	    SetCols = 1;
	    SetGrid = 1;
	    puzzle[opi][opj] = k;
	    CheckRows(puzzle, opi, opj);
	    CheckCols(puzzle, opi, opj);
	    if( (SetRows == 0 || SetCols == 0) ){
	      SetUnique=1;
	      puzzle[opi][opj]=0;
	    } //The solution is unique.
	    else{
	      SetUnique=0;
	      puzzle[opi][opj] = 0;//The solution is not unique. Move On.
	      //Is it the the only solution possible in the given block.
	      //If only one number can belong there that it is unique.
	    }
	      
	  }
      }
    }
 }
 
 if(SetUnique == 0)
   for( num = 1 ; num < 10 ; num ++){
     puzzle[i][j]=num;
     SetRows = 1;
     SetCols = 1;
     SetGrid = 1;
     CheckRows(puzzle, i, j);
     CheckCols(puzzle, i, j);
     CheckGrid(puzzle, i, j);
     if(SetRows == 1 && SetCols == 1 && SetGrid == 1) {SetUnique++;}
     //If the solution is unique because it is the only fillable answer. Check that it doesn't lead to a caontradiction. If it does then there is no solution.
   }

 if(SetUnique == 1){
    SetRows = 1;
     SetCols = 1;
     SetGrid = 1;
     CheckRows(puzzle, i, j);
     CheckCols(puzzle, i, j);
     CheckGrid(puzzle, i, j);
     
     /* if(SetRows == 0 && SetCols == 0 && SetGrid == 0 && SetUnique == 1){ */
     /*   printf("Unique\n"); */
     /*   printf("SetUnique = %d\n",SetUnique); */
     /*   printf("***************"); */
     /*   printpuzzle(puzzle); */
     /*   getchar(); */
     /* } */
 }
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

int isValid(int puzzle[ROWS][COLS]){

 int i,j;

 for(i = 0 ; i < ROWS ; i++){
    for(j=0 ; j < COLS ; j++){
	 if(puzzle[i][j] == 0)
	   Solve(puzzle);
    }
}
}

int Solve(int puzzle[ROWS][COLS]){

  int i, j, k, opj, opi, m, n, num,zig;
  int Grid[ROWS][COLS], Grid2[ROWS][COLS];
	   
  k=1;	    
  for(i = 0 ; i < ROWS ; i++){
     for(j=0 ; j < COLS ; j++){
       if( puzzle[i][j] == 0 ){
	 while(SetRows == 0 && SetCols == 0 && SetGrid == 0 && k < 10){
	   //SetRows gets reset if the value of k and some number in the array match.
	   GridFill(puzzle, i, j);
	   
	   SetRows = 1;
	   SetCols = 1;
	   SetGrid = 1;
	   //As an initial guess set the first empty cell to 1.
	   //1 means no contradiction.
	   puzzle[i][j] = k;
	   //Check that the value of k is not already occuring in either the column or the row.
	   CheckRows(puzzle,i,j);
	   CheckCols(puzzle,i,j);
	   CheckGrid(puzzle,i,j);
	 
	   //If there is no contradiction set empty cell to the current value of k.
	   
	 if(SetRows == 1 && SetCols == 1 && SetGrid == 1) {
	   //Check Uniqueness
	   for(opi = 0 ; opi < ROWS ; opi++){
	     for(opj=0 ; opj < COLS ; opj++){
	       Grid[opi][opj] = puzzle[opi][opj];
	     }
	   }
	   Grid[i][j] = 0;
	   
	   CheckUnique(Grid, i, j, k);
	   if(SetUnique == 1)  {
	     puzzle[i][j] = k;
	     //CheckContradiction(puzzle);
	     SetRows = 1; SetCols = 1; SetGrid = 1;
	     //This is to exit the loop.
	   }
	   else {
	     puzzle[i][j]=0; SetRows = 0; SetCols = 0; SetGrid = 0;
	     //Don't exit the loop if k<10.
	   }
	 }
	  
	 else{
	     puzzle[i][j]=0; SetRows = 0; SetCols = 0; SetGrid =0;
	     //Don't exit the loop if k<10.
	 }	   
	 k++;
        //If it doesn't work increment the value of k.
	 }
       SetRows = 0;     SetCols = 0;  SetGrid = 0;
       } k=1;  
     }
 }
  //printpuzzle(puzzle);
 isValid(puzzle);
}


int main(){


  int puzzle[ROWS][COLS];
  int i, j;
  char line[132];
  FILE * fp;

  if((fp=fopen("Sudoku.txt","r"))==NULL)
    {
      printf("Cannot open Co60_abs_eff.dat.\n");
      exit(0);
      return 0;
    }

  printf("Parameters read from Sudoku.txt\n");

	for(i = 0 ; i < ROWS ; i++){
	  for(j=0 ; j < COLS ; j++){
	    if(!fscanf(fp,"%d", &puzzle[i][j]));
	    //printf("puzzle[%d][%d] = %d\n", i, j, puzzle[i][j]);
	  }
	}   
	      fclose(fp);
	      //printf("Count for Co-60 = %d\n\n\n", count);
	      
	      printpuzzle(puzzle);
	      //getchar();
	      printf("\n\n");

	      CheckContradiction(puzzle);
	      Solve(puzzle);
	      printf("\n\n");
	      printpuzzle(puzzle);
	}


 /* int puzzle[ROWS][COLS]={{3, 0, 6, 5, 0, 8, 4, 0, 0}, */
 /* 			 {5, 2, 0, 0, 0, 0, 0, 0, 0}, */
 /* 			 {0, 8, 7, 0, 0, 0, 0, 3, 1}, */
 /* 			 {0, 0, 3, 0, 1, 0, 0, 8, 0}, */
 /* 			 {9, 0, 0, 8, 6, 3, 0, 0, 5}, */
 /* 			 {0, 5, 0, 0, 9, 0, 6, 0, 0}, */
 /* 			 {1, 3, 0, 0, 0, 0, 2, 5, 0}, */
 /* 			 {0, 0, 0, 0, 0, 0, 0, 7, 4}, */
 /* 			 {0, 0, 5, 2, 0, 6, 3, 0, 0}}; */

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
