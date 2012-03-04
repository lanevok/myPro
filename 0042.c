#include<stdio.h>

int b;        // bag
int n;        // elements
int v[1000];  // value
int w[1000];  // weight
int vs,ws;    // best : value_sum , weight_sum
int vt[1000]; // value temp
int wt[1000]; // weight temp

void sort(){
  int i,j;

  for(i=0;i<n;i++){
    for(j=i+1;j<n;j++){
      if(w[i]<w[j]){
	int tmp=w[i];
	w[i]=w[j];
	w[j]=tmp;
	tmp=v[i];
	v[i]=v[j];
	v[j]=tmp;
      }
    }
  }
}

int sum(int a[], int k){
  int i,s=0;

  for(i=0;i<k;i++)
    s+=a[i];
  return s;
}

void backtrack(int i, int k){
  int vr=sum(vt,k);  // Now value result
  int wr=sum(wt,k);  // Now weight result

  if(b>=wr){  // bag into OK?
    if(vs<vr){  // value max update
      vs=vr;
      ws=wr;
    }
    else if(vs==vr&&ws>wr)  // value equal & weight min update
      ws=wr;
  }
  else
    return;

  if(i==n)
    return;
  else{
    backtrack(i+1,k);
    vt[k]=v[i];
    wt[k]=w[i];
    backtrack(i+1,k+1);
  }
}

int main(){
  int i,d=1;

  for(;;){
    scanf("%d",&b);
    if(b==0)
      break;
    scanf("%d",&n);
    for(i=0;i<n;i++)
      scanf("%d,%d",&v[i],&w[i]);
    
    vs=0;
    ws=0;
    sort();
    
    backtrack(0,0);

    printf("Case %d:\n%d\n%d\n",d++,vs,ws);
  }
  return 0;
}
