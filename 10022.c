//AOJ 10022 Finding a Word (WA)

#include<stdio.h>
#include<string.h>
#include<ctype.h>


char w[10000];
char e[]="end_of_text";
int w_len;
int e_len=11;


int check(char *t){
  int i,j,k,c1,c2,c3;
  int t_len;
  
  t_len=strlen(t);
  for(i=0;i<t_len;i++){
    t[i]=tolower(t[i]);
  }
  c1=0;
  c2=0;
  c3=0;
  j=0;
  k=0;
  for(i=0;i<t_len;i++){
    if(t[i]==w[i]){
      c1++;
      c3++;
    }
    if(t[i]==e[i]){
      c2++;
      c3++;
    }
    if(c3==0){
      return 0;
    }
    if(c2==e_len && e_len==t_len){
      return 2;
    }
    if(c1==w_len && w_len==t_len){
      return 1;
    }
    c3=0;
  }
  return 0;
}


int main(){
  int i,b=0,r=0;
  char t[10000];
  
  for(i=0;i<10000;i++){
    w[i]=0;
  }
  scanf("%s",w);
  w_len=strlen(w);
  for(i=0;i<w_len;i++){
    w[i]=tolower(w[i]);
  }
  while(1){
    for(i=0;i<10000;i++){
      t[i]=0;
    }
    scanf("%s",t);
    b=check(t);
    if(b==1){
      r++;
    }
    if(b==2){
      break;
    }
    b=0;
  }
  printf("%d\n",r);
  return 0;
}

/* test file

abc
xxx abc xxx abc xxx
xxx xxx
ABC xxx abcabc xxx aabc xxx abcd xxx
xxx
END_OF_TEXTa
abc
ENd_of_Text

*/
