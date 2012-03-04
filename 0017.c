#include<stdio.h>
#include<string.h>

char a[100];
int flag=1;

void shift(){
  int i=0;
  
  while(a[i]!='\n'){
    if(a[i]>='a' && a[i]<='y')
      a[i]++;
    else if(a[i]=='z')
      a[i]='a';
    i++;
  }
}

void match(int x){
  char b1[5];
  char b2[4];
  char m1[2][5]={"that","this"};
  char m2[4]={"the"};
  int i;

  for(i=0;i<4;i++){
    b1[i]=a[x+i];
    if(i!=3)
      b2[i]=a[x+i];
  }
  b1[4]='\0';
  b2[3]='\0';
  if(strcmp(b1,m1[0])==0||
     strcmp(b1,m1[1])==0||
     strcmp(b2,m2)==0)
    flag=0;
}

int main(){
  int i,len;
  
  fgets(a,100,stdin);
  len=strlen(a);
  while(flag){
    shift();
    for(i=0;i+4<len;i++){
      match(i);
      if(!flag)
	break;
    }
    match(len-4);
  }
  fputs(a,stdout);
  return 0;
}
