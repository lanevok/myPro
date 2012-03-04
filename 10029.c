#include<stdio.h>

void sort(int a[],int n){
  int i,j,min,t;
  
  for(i=0;i<n;i++){
    min=i;
    for(j=i+1;j<n;j++){
      if(a[j]<a[min]){
	min=j;
      }
      t=a[min];
      a[min]=a[i];
      a[i]=t;
    }
  }
}


int main(){
  int i,j,n,p;
  int a[3];
  int b[3];
/*
  int c[100000];
  int d[100000];
  int e[100000];
  int f[100000];
  int g[100000];
  int h[100000];
  int k[100000];
  int l[100000];
*/
  scanf("%d",&n);
  if(n==0){
    return 0;
  }
  p=(n/(3+1))+1;
  printf("p=%d\n",p);
  for(i=0;i<p;i++){
    scanf("%d",&a[i]);
  }
    if(p>=2){
      scanf("%d",&b[i]);
    }
  }
  sort(a,n);
  if(p>=2){
    sort(b,n);
  }
  for(i=0;i<n-1;i++){
    printf("%d ",a[i]);
  }
  printf("%d\n",a[n-1]);
  for(i=0;i<n-1;i++){
    printf("%d ",b[i]);
  }
  printf("%d\n",b[n-1]);
  return 0;
}
