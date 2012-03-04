#include<stdio.h>

char map[1000][1000];
int n;

int search(int i,int j){
  int max=0;
  int x,y,z,m;
  int flg;
  
  if(i>j)
    m=i;
  else
    m=j;
 
  for(z=1;z<n-m;z++){
    printf("%d,",z);
    flg=1;
    for(x=i;x<x+z;x++){
      if(flg){
	for(y=j;y<x+z;y++){
	  if(flg){
	    if(map[x][y]!='.'){
	      flg=0;
	      break;
	    }
	  }
	}
      }
    }
    if(flg)
      max=z;
  }
  puts("");
  return max;
}

int main(void){
  int i,j,k;
  int tmp,max;
  
  for(;;){
    scanf("%d",&n);
    if(n==0)
      break;
    max=0;
    
    for(k=0;k<n;k++)
      scanf("%s",map[k]);
    
    for(i=0;i<n;i++){
      for(j=0;j<n;j++){
	printf("Call[%d,%d]-",i,j);
	tmp=search(i,j);
	printf("%c",map[i][j]);
	if(tmp>max)
	  max=tmp;
      }
      puts("");
    }
    printf("%d\n",max);
  }
  return 0;
}
