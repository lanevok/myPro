//Similar 0085
#include<stdio.h>

int a[10001];  // Data List

void sort(){
  int i=0,j=0;
  
  while(1){
    if(a[i]==0)
      j++;
    a[i++]=a[j++];
    if(a[i]!=-1)
      break;
  }
  //  puts("Sort OK");
}
    
int main(){
  int n,k,m;    // n=element,k=kankaku,m=start
  int i,c,d;    // c=cnt,d=delete

  for(;;){
    scanf("%d%d%d",&n,&k,&m);
    if(n==0&&k==0&&m==0)
      break;

    for(i=1;i<=n;i++)
      a[i-1]=i;
    for(i=n+1;i<10001;i++)
      a[i]=-1;

    a[m-1]=0;  //First Delete
    d=1;
    i=m-1;     //index Start Set
    while(1){
      if(d==n-1)  //Fin Han
	break;
      else{
	c=0;
	while(1){
	  i++;
	  if(a[i]==-1) i=0; //Element OverFlow
	  if(a[i]!=0)
	    c++;
	  if(c==k){  //Delete Execute
	    //  printf("Delete:%d\n",a[i]);
	    a[i]=0;
	    d++;
	    if(d%10==0)
	      sort();
	    break;
	  }
	}
      }
    }
    for(i=0;i<=n;i++){
      if(a[i]!=0){
	printf("%d\n",a[i]);
	break;
      }
    }
  }
  return 0;
}
