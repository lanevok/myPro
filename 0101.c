#include<stdio.h>
#include<string.h>
#include<stdlib.h>

int main(){
  char a[1001];
  char b[]={"Hoshino"};
  char t[8]={};
  int i,j,k,n,c;

  scanf("%d\n",&n);
  for(k=0;k<n;k++){
    for(;;){
      c=getchar();
      if(c==\n)
	break;
      else
	a[k]=c;
    }
    for(i=0;i<strlen(a)-6;i++){
      for(j=0;j<7;j++){
	t[j]=a[j+i];
	printf("in:%s\n",t);
      }
      t[7]='\0';
      printf("cmp:%s\n",t);
      if(!strcmp(t,b))
	a[i+6]='a';
    }
    printf("%s\n",a);
  }
  return 0;
}
