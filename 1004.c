#include<stdio.h>

int p[10000];
int pp=1;

int prime(int n){
  int i;
  
  if(n<=1||n==4){
    return 0;
  }
  else if(n==2||n==3){
    return 1;
  }
  else if(n%2==0){
    return 0;
  }
  for(i=0;i<pp;i++){
    if(n>=p[i]){
      break;
    }
    if(n%p[i]==0){
      return 0;
    }
  }
  p[pp++]=n;
  return 1;
}

int main(){
  int n,i,r;
  int a[10000];
  int b[10000];
  int c[10000];

  p[0]=3;
  
  while(scanf("%d",&n)!=EOF){
/*
    for(i=1;i<=n;i++){
      a[i-1]=i;
      b[i-1]=n-i+1;
      c[i-1]=0;
    }
    for(i=0;i<n;i++){
      c[i]=c[i]+prime(a[i]);
      c[i]=c[i]+prime(b[i]);
    }
    r=0;
    for(i=0;i<n;i++){
      if(c[i]==2){
	r++;
      }
    }
    printf("%d\n",r);
*/
    printf("%d\n",prime(n));
  }
  return 0;
}
