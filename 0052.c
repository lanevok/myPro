#include<stdio.h>

void fact(int n){
  unsigned long long int r=1;
  
  //  scanf("%d",&n);
  while(n){
    r=r*n;
    n--;
  }
    printf("%lld\n",r);

  {
    int cnt=0;
    for(;;){
      if(r&1)
	cnt++;
      else
	break;
    }
    printf("%d\n",cnt);
  }

  // return 0;
}

int main(){
  int n;

  while(1){
    scanf("%d",&n);
    if(n==0)
      break;
    fact(n);
  }
  return 0;
}
