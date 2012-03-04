#include<stdio.h>
#include<string.h>

int main(){
  char c[10000];
  char t[10000];
  int m,i,h;

  while(1){
    if(scanf("%s",c)=='-'){
      break;
    }
    scanf("%d",&m);
    for(i=0;i<m;i++){
      scanf("%d",&h);
      t=c.substr(0,h);
      c=c.substr(h,strlen(c))+t;
    }
    printf("%s",c);
  }
  return 0;
}

      
