//WA
#include<stdio.h>

char a[3][1000]={};

void judge(int k){
  int flg=1;
  int b;
  
  for(b=0;b<3;b++){
    if(a[b][k]==a[b][k+1]&&
       a[b][k]==a[b][k+2]&&
       a[b][k]!='s'){
      printf("%c\n",a[b][k]);
      flg=0;
      break;
    }
  }
  if(flg){
    for(b=0;b<3;b++){
      if(a[0][k+b]==a[1][k+b]&&
	 a[0][k+b]==a[2][k+b]&&
	 a[0][k+b]!='s'){
	printf("%c\n",a[0][k+b]);
	flg=0;
	break;
      }
    }
  }
  if(flg){
    if(a[0][k]==a[1][k+1]&&
       a[0][k]==a[2][k+2]&&
       a[0][k]!='s'){
      printf("%c\n",a[0][k]);
      flg=0;
    }
  }
  if(flg){
    if(a[0][k+2]==a[1][k+1]&&
       a[0][k+2]==a[2][k]&&
       a[0][k+2]!='s'){
      printf("%c\n",a[0][k+2]);
      flg=0;
    }
  }
  if(flg)
    printf("%c\n",'d');
}

int main(){
  int i,j,f,k;
  
  for(i=0;i<3;i++)
    scanf("%s",a[i]);
  for(j=0;j<1000;j++)
    if(a[0][j]!='o'&&
       a[0][j]!='x'&&
       a[0][j]!='s')
      break;
  f=j;
  for(k=0;k<f;k=k+3)
    judge(k);
  return 0;
}
