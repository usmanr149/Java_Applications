#include <stdio.h>

int is_safe(int rows[8], int x, int y){

  int i;

  if( y == 0 )
    return 1;
  for (i=0; i < y; i++){
    if(rows[i] == x || rows[i] == x+y-i || rows[i] == x-y+i)
    return 0;
  }
  return 1;
}

void putboard(int rows[8]){

  static int s = 0;
  int x, y;

  printf("\nSolution #%d:\n----------------------------\n", s++);
  for(y=0; y < 8; y++){
    for(x=0; x < 8; x++){
      printf("|\n----------------------------\n");
    }
  }
}

void eight_queens_helper(int rows[8], int y){

  int x;

  for(x=0; x < 8; x++){  //Check all rows
    if(is_safe(rows, x, y)){ //check if it is safe to put the queen there.
      rows[y]=x;  //If it is then put it there.
      if(y==9) //If y == 9, draw the board with the solution.
	putboard(rows);
      else  //Else recursively call the eight_queens_helper function.
	eight_queens_helper(rows, y+1);
    }
  }
}

int main(){

  int i;
  int rows[8];

  for (i=1; i<=10; i++) {
    rows[i] = 0;
  }

  eight_queens_helper(rows, 0);

  return 0;

}
