#include<stdio.h>
#include<stdbool.h>

int main(){
  int c[4][13];
  char m1;
  int num,m2;
  int i,j,n;
  
  scanf("%d",&n);
  for(i=0;i<4;i++){
    for(j=0;j<13;j++){
      c[i][j]=false;
    }
  }
  for(i=0;i<n;i++){
    scanf("%s %d",&m1,&num);
    if(m1=='S')
      m2=0;
    else if(m1=='H')
      m2=1;
    else if(m1=='C')
      m2=2;
    else if(m1=='D')
      m2=3;
    else
      m2=4;
    c[m2][num-1]=true;
  }
  for(i=0;i<4;i++){
    for(j=0;j<13;j++){
      if(c[i][j]==false){
	if(i==0){
	  printf("S %d\n",j+1);
	}
	else if(i==1){
	  printf("H %d\n",j+1);
	}
	else if(i==2){
	  printf("C %d\n",j+1);
	}
	else if(i==3){
	  printf("D %d\n",j+1);
	}
	else{
	  break;
	}
      }
    }
  }
  return 0;
}
