#include<stdio.h>

int my_div(int x, int div){
  double y = x*1.0/div/100;
  return kiriage(y)*100;
}

int kiriage(double x){
  return (int)(x<0.0?x:x+0.9);
}

int richi=0;
int point[5];

void calc(int win){
  int i,parent,honba,tumo,score,pay,pay_point;
  
  printf("tumo?(=1) ron?(=0)\n");
  scanf("%d",&tumo);
  printf("honba?\n");
  scanf("%d",&honba);
  printf("score?\n");
  scanf("%d",&score);

  if(tumo==1){
    printf("parent?(1-4)\n");
    scanf("%d",&parent);
    if(parent==win){
      /* oya tumo */
      pay_point=my_div(score,3);
      for(i=1;i<5;i++){
	if(i!=win){
	  point[i]-=pay_point;
	  point[i]-=(honba*100);
	}
      }
      point[win]+=my_div(score,3)*3;
      point[win]+=(honba*300);
      while(1){
	int tmp;
	/* richi user fin=0 */
	printf("richi user? (1-4,0=fin)\n");
	scanf("%d",&tmp);
	if(tmp==0){
	  break;
	}
	point[tmp]-=1000;
	richi+=1000;
      }
      point[win]+=richi;
      richi=0;
    }
    else if(parent!=win){
      int pay_point_ko,pay_point_oya;
      /* ko tumo */
      pay_point_ko=my_div(score,4);
      pay_point_oya=my_div(score,2);
      for(i=1;i<5;i++){
	if(i==parent){
	  point[i]-=pay_point_oya;
	  point[i]-=(honba*100);
	}
	else if(i!=win){
	  point[i]-=pay_point_ko;
	  point[i]-=(honba*100);
	}
      }
      point[win]+=my_div(score,2);
      point[win]+=my_div(score,4)*2;
      point[win]+=(honba*300);
      while(1){
	int tmp;
	/* richi user fin=0 */
	printf("richi user? (1-4,0=fin)\n");
	scanf("%d",&tmp);
	if(tmp==0){
	  break;
	}
	point[tmp]-=1000;
	richi+=1000;
      }
      point[win]+=richi;
      richi=0;
    }
  }
  else if(tumo==0){
    /* ron */
    printf("pay user? (1-4)\n");
    scanf("%d",&pay);
    point[pay]-=score;
    point[win]+=score;
    point[pay]-=(honba*300);
    point[win]+=(honba*300);
    while(1){
      int tmp;
      /* richi user fin=0 */
      printf("richi user? (1-4,0=fin)\n");
      scanf("%d",&tmp);
      if(tmp==0){
	break;
      }
      point[tmp]-=1000;
      richi+=1000;
    }
    point[win]+=richi;
    richi=0;
  }
}

void water(void){
  int i,cnt=0;
  int tenpai[4];
  int tenpai_plus[5];
  int richi_tmp=0;

  for(i=1;i<5;i++){
    tenpai_plus[i]=1;
  }
  
  while(1){
    printf("tenpai user? (1-4,0=fin)\n");
    scanf("%d",&tenpai[cnt]);
    if(tenpai[cnt]!=0){
      cnt++;
    }
    else{
      break; 
    }
  }
  if(cnt!=0){
    for(i=0;i<cnt;i++){
      point[tenpai[i]]+=(3000/cnt);
      tenpai_plus[tenpai[i]]=0;
    }
    for(i=1;i<5;i++){
      if(tenpai_plus[i]==1){
	point[i]-=(3000/(4-cnt));
      }
    }
  }
  while(1){
    int tmp;
    /* richi user 0=fin */
    printf("richi user? (1-4,0=fin)\n");
    scanf("%d",&tmp);
    if(tmp==0){
      break;
    }
    point[tmp]-=1000;
    richi_tmp+=1000;
  }
  richi+=richi_tmp;
}

void debug(void){
  int i;
  for(i=1;i<5;i++){
    scanf("%d",&point[i]);
  }
}

void chon(){
  int chonUser,oya,oyaUser,i,richi_tmp=0;
  
  printf("chon user ? (1-4)\n");
  scanf("%d",&chonUser);
  printf("chon user is oya ? (yes=1,no=0)\n");
  scanf("%d",&oya);
  
  if(oya==1){
    /* oya chon */
    for(i=1;i<5;i++){
      if(chonUser==i){
	point[i]-=12000;
      }
      else{
	point[i]+=4000;
      }
    }
  }
  else{
    /* ko chon */
    printf("oya user ? (1-4)\n");
    scanf("%d",&oyaUser);
    for(i=1;i<5;i++){
      if(chonUser==i){
	point[i]-=8000;
      }
      else if(oyaUser==i){
	point[i]+=4000;
      }
      else{
	point[i]+=2000;
      }
    }
  }
  while(1){
    int tmp;
    /* richi user 0=fin */
    printf("richi user? (1-4,0=fin)\n");
    scanf("%d",&tmp);
    if(tmp==0){
      break;
    }
    point[tmp]-=1000;
    richi_tmp+=1000;
  }
  richi+=richi_tmp;
}

int main(void){
  int i,win,max,c;

  for(i=1;i<5;i++){
    point[i] = 25000;
  }

  while(1){
    /* water=5, fin=0 */
    printf("win user (1-4,0=fin,5=water,6=debug,7=chon)\n");
    scanf("%d",&win);
    if(win==5){
      water();
    }
    else if(win==6){
      debug();
    }
    else if(win==0){
      return 0;
    }
    else if(win==7){
      chon();
    }
    else{
      calc(win);
    }

    c=point[1];
    max=point[1];
    for(i=2;i<5;i++){
      c+=point[i];
      if(max<point[i]){
	max=point[i];
      }
    }

    printf("\n------------------------------\n");
    printf("            start    top\n");
    for(i=1;i<5;i++){
      printf("P%d: %6d [%6d] [%6d]\n",i,point[i],point[i]-25000,point[i]-max);
    }
    printf("\n");
    printf("ribo: %d\n",richi);
    if(c+richi!=100000){
      printf("error -> 100000\n");
    }
    printf("------------------------------\n\n");
  } 
}
