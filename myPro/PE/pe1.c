/*
Project Euler.net
 Problem 1 Sample Source
*/

#include<stdio.h>

int main(){
  int i,s=0;

  for(i=1;i<1000;i++){
    if(i%3==0||i%5==0)
      s+=i;
  }
  printf("%d\n",s);
  
  return 0;
}
