#include<stdio.h>
#include<ctype.h>

int a[9][9];

void bomb(int x, int y){
  int i;

  a[x][y]=0;
  for(i=1;i<9;i++){
    if(a[x][i])
      bomb(x,i);
    if(a[i][y])
      bomb(i,y);
  }
}  

int main(void){
  int i,j,c,d=1;
  int x,y,n;
  
  scanf("%d",&n);
  while(n--){
    c=getchar();
    c=getchar();
    printf("Data %d:\n",d++);
    for(i=1;i<9;i++){
      for(j=1;j<=9;j++){
	c=getchar();
	if(c=='\n')
	  continue;
	else
	  a[i][j]=c-'0';
      }
    }
    scanf("%d%d",&y,&x);
    

    for(i=1;i<9;i++){
      for(j=1;j<9;j++){
	printf("%d",a[i][j]);
      }
      puts("");
    }
    puts("");


    if(a[x][y])
      bomb(x,y);  
    for(i=1;i<9;i++){
      for(j=1;j<9;j++){
	printf("%d",a[i][j]);
      }
      puts("");
    }
  }
  return 0;
}
