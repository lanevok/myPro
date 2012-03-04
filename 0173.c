#include<stdio.h>

int main(){
  char a[3][9];
  int i,x,y;
  int n[9],z[9];

  for(i=0;i<9;i++){
    scanf("%s",a[i]);
    scanf("%d%d",&x,&y);
    n[i]=x+y;
    z[i]=x*200+y*300;
  }
  for(i=0;i<9;i++)
    printf("%s %d %d\n",a[i],n[i],z[i]);
  return 0;
}
