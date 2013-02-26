/*#################################
Analysis Program for Number Place
(Solution -> Backtrack Algorhythm)

Author  : (TAT)chaN
Version : 05
Date    : 2012/3/3
#################################*/

/*- Sample Input 1 ---
0 0 5 3 0 0 0 0 0
8 0 0 0 0 0 0 2 0
0 7 0 0 1 0 5 0 0
4 0 0 0 0 5 3 0 0
0 1 0 0 7 0 0 0 6
0 0 3 2 0 0 0 8 0
0 6 0 5 0 0 0 0 9
0 0 4 0 0 0 0 3 0
0 0 0 0 0 9 7 0 0
------------------*/

/*- Sample Input 2 ---
8 0 0 0 0 0 0 0 0
0 0 3 6 0 0 0 0 0
0 7 0 0 9 0 2 0 0
0 5 0 0 0 7 0 0 0
0 0 0 0 4 5 7 0 0
0 0 0 1 0 0 0 3 0
0 0 1 0 0 0 0 6 8
0 0 8 5 0 0 0 1 0
0 9 0 0 0 0 4 0 0
------------------*/

#include<stdio.h>

#define M 9     // Line or Group
#define X 81    // M*M
#define S 0     // S=1 log Output Setting

int pos[M][M];  // Cell
int tmp[X];     // Log

// Prototype
void scan();
void print();
void backtrack();
int check();

int emp=0;     // Empty Cell Number
int flg=0;  // Solve Flag
int z=0;     // tmp[X] Index
int i,j,k,l,m,n;

int main(){
  scan();
  print();
  backtrack(-1,0,emp);  // Solve
  print();
  return 0;
}

void scan(){
  for(i=0;i<M;i++){
    for(j=0;j<M;j++){
      scanf("%d",&pos[i][j]);
      if(pos[i][j])
	emp++;      // Empty Cell CountUp
    }
  }
}

void print(){
  puts("");
  for(i=0;i<M;i++){
    for(j=0;j<M;j++){
      if(pos[i][j])
	printf("%d ",pos[i][j]);
      else
	printf("  ");  // zero -> space
    }
    puts("");
  }
  puts("");
}

int check(){
  for(i=0;i<M;i++){
    for(j=0;j<M;j++){
      for(k=0;k<M;k++){
	if( j!=k && pos[i][j]==pos[i][k] && pos[i][j]!=0 )
	  return 1;  // Line Error
	if( i!=k && pos[i][j]==pos[k][j] && pos[i][j]!=0 )
	  return 1;  // Line Error
      }
    }
  }
  for(i=0;i<M;i++){
    for(j=0;j<M;j++){
      k = i<3? 0 : i<6? 3 : 6;
      l = j<3? 0 : j<6? 3 : 6;
      for(m=k;m<k+3;m++){
	for(n=l;n<l+3;n++){
	  if( (i!=m||j!=n) && pos[i][j]==pos[m][n] && pos[i][j]!=0)
	    return 1;  // Group Error
	}
      }
    }
  }
  return 0;
}

//backtrack Method (Main Algorhythm)
void backtrack(int a, int b, int c){
  int d,e;

  if(c==X){     // No Empty = Full(81)
    if(check()) // Mis
      return;
    else{
      flg=1;   // Solution
      return;
    }
  }
  else{
    if(check()) // Mis
      return;
    c++;        // Next Empty Cell Search
    while(1){
      a++;
      if(a>8){
	a=0;
	b++;
      }
      if(!pos[a][b])
	break;
    }           // Empty Cell Set
  }
  for(d=1;d<=M;d++){ // All Pattern at Cell
    pos[a][b]=d;     // Assignment Challenge

    if(S){
      tmp[z++]=d;
      for(e=0;e<z;e++)
	printf("%d ",tmp[e]);
      puts("");
    }  
    
    backtrack(a,b,c);  // Call

    if(flg)           // Solved Return Check
      return;
    if(S)
      z--;
  }
  c--;          // Delete Cell
  pos[a][b]=0;  //  & Back Cell
}
