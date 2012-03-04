#include<stdio.h>
#include<math.h>
int main(){
  double a,b,c;
  double s,l,h;
  scanf("%lf%lf%lf",&a,&b,&c);
  printf("%lf\n",sin(c));
  s=a*b*sin(c)/2;
  l=a*a+b*b-2*a*b*cos(c);
  l=sqrt(l)+a+b;
  h=2*s/a;
  printf("%lf\n%lf\n%lf\n",s,l,h);
  return 0;
}
