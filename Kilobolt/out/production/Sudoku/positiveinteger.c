#include <stdio.h>
int sum(int n);
int main(){
    int num,add;
    printf("Enter a positive integer:\n");
    scanf("%d",&num);
    add=sum(num);
    printf("sum=%d\n",add);
}
int sum(int n){
  if(n==0){
    printf("n = %d\n", n);
    return 0;
  }
    else{
      printf("n = %d\n", n);
       return n+sum(n-1);  
    }/*self call  to function sum() */
}
