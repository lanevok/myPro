#include<stdio.h>
#include<math.h>

int main(){
  double x=0,y=0;
  int w,a;

  for(;;){
    scanf("%d,%d",&w,&a);
    if(w==0&&a==0)
      break;
    x+=w*sin(M_PI*a/180.0);
    y+=w*cos(M_PI*a/180.0);
  }
  printf("%.0f\n%.0f\n",x,y);
  return 0;
}
