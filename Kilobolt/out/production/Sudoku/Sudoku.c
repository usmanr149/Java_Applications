#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h> 

#define ROWS 9
#define COLS 9


/* int puzzle[ROWS][COLS]={{0, 0, 0, 0, 0, 0, 0, 9, 0},
{1, 9, 0, 4, 7, 0, 6, 0, 8},
{0, 5, 2, 8, 1, 9, 4, 0, 7},
{2, 0, 0, 0, 4, 8, 0, 0, 0},
{0, 0, 9, 0, 0, 0, 5, 0, 0},
{0, 0, 0, 7, 5, 0, 0, 0, 9},
{9, 0, 7, 3, 6, 4, 1, 8, 0},
{5, 0, 6, 0, 8, 1, 0, 7, 4},
{0, 8, 0, 0, 0, 0, 0, 0, 0}}; */

int puzzle[ROWS]={5, 0, 0, 0, 0, 3, 0, 9, 1};
int SetRows = 0;
int SetCols = 0;
int SetGrid = 0;

int CheckRows(int k){

int i;

for(i = 0 ; i < ROWS ; i++){
if(i != k){
    if( puzzle[i] == puzzle[k] ) SetRows = 0;

/* printf("SetRows = %d\n", SetRows); */
//getchar();
}
}
}

/* int CheckCols(int k, int l){ */

/*   int i,j; */
  
/*    i=k; */
/*     for(i = 0 ; i < COLS ; i++) */
/*       if(l != i) */
/* 	if(puzzle[l][j] == puzzle[i][l]) */
/* 	  SetRows = 1; */
/* } */

/* int CheckGrid(int k, int l){ */

/*   int i,j; */
/*   int m = k/3; */
/*   int n = l/3; */

/*   for(i = 3*m ; i < m+3 ; i++) */
/*     for(j = 3*n ; j < n+3 ; j++) */
/*       if(k != i && l != j) */
/* 	if(puzzle[i][j]==puzzle[k][l]) */
/* 	  SetGrid = 1; */
/* } */

int CheckContradiction(){

int i,j;

for(i = 0 ; i < ROWS ; i++){
for(j=i+1 ; j< ROWS ; j++){
if(puzzle[i] != 0)
    if(puzzle[i]==puzzle[j]){
       printf("Contradiction\n");
       exit(0);
}
}
}
}

int main(){

int i, j, k;

for(i = 0 ; i < ROWS ; i++){
      printf("%d   ", puzzle[i]);
}
printf("\n");

CheckContradiction();

k=1;
  for(i = 0 ; i < ROWS ; i++){


     if( puzzle[i] == 0 ){

        while(SetRows == 0 && k < 10){
           //SetRows gets reset if the value of k and some number in the array match.
           SetRows = 1;
           //As an initial guess set the first empty cell to 1.
           puzzle[i] = k;
           //Check that the value of k is not already occuring.
           CheckRows(i);
       
       /* printf("SetRows = %d\n", SetRows); */
       /* printf("######\n"); */
       /* getchar(); */

//If there is no contradiction set empty cell to the current value of k.
           if(SetRows == 1) puzzle[i]=k;
           else k++;          
//If it doesn't work increment the value of k.
}
SetRows = 0;
}
}


for(i = 0 ; i < ROWS ; i++){
      printf("%d   ", puzzle[i]);
      }
      printf("\n");

}
