/*############################################
Analysis Program for frequent English Word

Author :(TAT)chaN
Version:03
Date   :2011/12/1
############################################*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<ctype.h>
//needless #include<malloc.h>

//Max Spell long Size
#define L 20

//code (case)
#define TEST 0
#define EXIT 1
#define LOAD 2
#define SAVE 3
#define ADD 4
#define DEL 5
#define PRINT 6
#define COUNT 7
#define SORTA 8
#define SORTN 9
#define FIND 10
#define HELP 11
#define NUM 12
#define ALDEL 13
#define PRINTA 14
#define OTHER -1

/***********************************
Class Summary:
 <spell> is English Word Spell
 <cnt> is Appearance Number
 <flag> is Attribute Setting (1bit)
 <lng> is Spell Long
***********************************/

typedef struct lst{
  struct lst *next;
  char spell[L];
  int cnt;
  int flag;
  int lng;
} WORD;


/*******************************
Program Algorhytem Method  *****/

// mac ... Memory Allocation
WORD *mac(){
  WORD *new;
  
  if((new=(WORD *)malloc(sizeof(WORD)))==NULL){
    fprintf(stderr,"Can't Allocate Memory!\n");
    exit(-1);
  }
  return new;
}

// newWORD ... Add WORD to List
WORD *newWORD(WORD *start, char *string, int c, int f){
  WORD *p=start;
  WORD *new;
  
  while(p->next!=NULL){
    p=p->next;
  }
  new=mac();
  p->next=new;
  strcpy(new->spell,string);
  new->next=NULL;
  new->cnt=c;
  new->flag=f;
  new->lng=strlen(new->spell);
  return new;
}

// load ... Import from Text or Log File to List (Call:newWORD)
void load(WORD *start, char *file, int opt){
  WORD *p=start;
  FILE *fp;
  int c,d,e;
  char spell[L];
  char cnt[L];
  int i=0,j=0,k;
  int flg=0;
  
  if((fp=fopen(file,"r"))==NULL){
    printf("File Name <%s> is not Found!\n",file);
  }
  else{
    while(c!=EOF){
      c=getc(fp);
      if((c>='a'&&c<='z')||(c>='A'&&c<='Z')){
	flg=1;
	c=tolower(c);
	spell[i++]=c;
      }
      else{
	spell[i]='\0';
	if(opt){  //opt=1 Text
	  if(i>1){
	    newWORD(p,spell,1,0);
	    p=p->next;
	  }
	}
	else{  //opt=0 Log
	  if(flg){
	    c=getc(fp)-'0';
	    while(c>=0&&c<=9){
	      cnt[j++]=c;
	      c=getc(fp)-'0';
	    }
	    if(cnt[0]==0){
	      d=0;
	    }
	    else{
	      d=cnt[0];
	      for(k=1;k<j;k++){
		d=(d*10)+cnt[k];
	      }
	    }
	    e=getc(fp)-'0';
	    if(i>1){
	      newWORD(p,spell,d,e);
	      p=p->next;
	    }
	  }
	}
	flg=0;
	i=0;
	j=0;
      }
    }
  }
  fclose(fp);
}

// save ... Export from List to Text or Log File
void save(WORD *start, char *file, int opt){
  WORD *p=start;
  FILE *fp;
  
  fp=fopen(file,"w");
  p=p->next;
  while(p!=NULL){
    fputs(p->spell,fp);
    if(!opt){
      fprintf(fp,",%d %d",p->cnt,p->flag);
    }
    putc('\n',fp);
    p=p->next;
  }
  fclose(fp);
}

// del ... Delete WORD from List
WORD *del(WORD *start, char *target){
  WORD *p=start;
  WORD *tmp;
  
  if(!strcmp(p->spell,target)){
    tmp=p;
    p=p->next;
    free(tmp);
  }
  else{
    while(p->next!=NULL){
      if(!strcmp((p->next)->spell,target)){
	tmp=p->next;
	p->next=p->next->next;
	free(tmp);
	break;
      }
      else{
	p=p->next;
      }
    }
    p=start;
  }
  return p;
}

// search ... Search WORD at List
int search(WORD *start, char *target, int opt){
  WORD *p=start;
  WORD *q;
  //  int flag=0;

  while(p!=NULL){
    if(strcmp(p->spell,target)!=0){
      p=p->next;
    }
    else{
      q=p;
      //      flag=1;
      if(opt){
	printf("There is %s!\n",target);
      }
      return 1;
    }
  }
  if(opt){
    printf("There is not %s!\n",target);
  }
  return 0;
}

// searchPP ... cnt++ by Search
WORD *searchPP(WORD *start, char *target, int counter){
  WORD *p=start;
  WORD *q=p;

  while(strcmp(p->spell,target)!=0){
    p=p->next;
  }
  //  p->cnt++;
  p->cnt=p->cnt+counter;
  return q;
}

// counting ... Appear WORD Counting & needless WORD Delete (image)
WORD *counting(WORD *start){
  WORD *p=start;
  WORD *q;
  WORD *r;
  
  q=mac();
  q->next=NULL;
  strcpy(q->spell,"");
  q->cnt=1;
  q->flag=0;
  q->lng=0;
  r=q;
  while(p!=NULL){
    if(search(r,p->spell,0)){
      //r=searchPP(r,p->spell);
      r=searchPP(r,p->spell,p->cnt);
    }
    else{
      newWORD(q,p->spell,p->cnt,0);
    }
    p=p->next;
  }
  return r;
}

// sort ... Sort by Alphabetical or Count
WORD *sort(WORD *start, int opt){
  WORD *p=start;
  WORD *q, *r;
  int tmp1;
  char tmp2[L];
  int tmp3, tmp4;
  
  while(p->next!=NULL){
    p=p->next;
    q=p;
    r=p;
    while(q!=NULL){
      if(opt){  //opt=1 Alpha
	if(strcmp(r->spell,q->spell)>0){
	  r=q;
	}
      }
      else{  //opt=0 Count
        if( (r->cnt) < (q->cnt) ){
	  r=q;
	}
      }
      q=q->next;
    }
    tmp1=p->cnt;
    tmp3=p->flag;
    tmp4=p->lng;
    strcpy(tmp2,p->spell);

    p->cnt=r->cnt;
    strcpy(p->spell,r->spell);
    p->flag=r->flag;
    p->lng=r->lng;

    r->cnt=tmp1;
    r->flag=tmp3;
    r->lng=tmp4;
    strcpy(r->spell,tmp2);
  }
  p=start;
  return p;
}

// printNumber ...Number of WORD
void printNumber(WORD *init){
  WORD *p=init;
  unsigned long long int n=1;

  if(p!=NULL){
    n--;
  }
  while(p->next!=NULL){
    n++;
    p=p->next;
  }
  printf("Number of WORD is %llu!\n",n);
}

// print ... print All WORD (+opt)
void print(WORD *init, int opt){
  WORD *p=init;

  p=p->next;  //first label delele
  while(p!=NULL){
    if(opt){
      printf("%d %2d %4d %s\n",p->flag,p->lng,p->cnt,p->spell);
    }
    else{
      printf("%s\n",p->spell);
    }
    p=p->next;
  }
}

// AllDelete ... Delete All WORD in List
WORD *AllDelete(WORD *start){
  WORD *p=start;
  WORD *q=start;
  WORD *tmp;
  
  p=p->next;
  while(p!=NULL){
    tmp=p;
    p=p->next;
    free(tmp);
  }
  p=q;
  strcpy(p->spell,"");
  p->next=NULL;
  return p;
}

// MenuCode ... Type Charactor to Menu Index
int MenuCode(char *menu){
  int code=-1;
  char cmd[][15]={"test","exit","load","save",
		  "add","del","print","count",
		  "sorta","sortn","find",
		  "help","num","aldel","printa"}; //define code (case)
  int n=15,i;
  
  for(i=0;i<n;i++){
    if(strcmp(menu,cmd[i])==0){
      code=i;
    }
  }  
  return code;
}

void help(){
  printf("  exit   = Quit Program\n");
  printf("  load   = Text or Log File Load\n");
  printf("  save   = Text or Log File Save\n");
  printf("  add    = Add 1 Word to List\n");
  printf("  del    = Delete 1 Word from List\n");
  printf("  aldel  = Delete All WORD in List\n");
  printf("  print  = List Spell Print\n");
  printf("  printa = List All Infomation Print\n");
  printf("  count  = Combine Count at List\n");
  printf("  sorta  = Sort Spelling (Alpha)\n");
  printf("  sortn  = Sort Count\n");
  printf("  num    = Number of WORD\n");
  printf("  find   = Search WORD at List\n");
}


/**********************************
Application Enter Main Point  *****/

int main(){
  WORD *list;
  char text[L];
  int code;
  char opt[5];
  
  list=mac();
  strcpy(list->spell,"");
  list->next=NULL;
  
  while(1){
    printf("Please Input Command ... ");
    scanf("%s",text);
    code=MenuCode(text);
    switch(code)
      {
      case TEST:
	break;
      case EXIT:
	printf(" Exit Program!\n");
	return 0;
      case LOAD:
	printf(" Please Input Import File Neme ... ");
	scanf("%s",text);
	printf("  File <%s> is Type ( text or log ) ... ",text);
	scanf("%s",opt);
	if(strcmp("text",opt)==0){
	  load(list,text,1);
	}
	else if(strcmp("log",opt)==0){
	  load(list,text,0);
	}
	else{
	  printf("   Type is not text or log!\n");
	}
	break;
      case SAVE:
	printf(" Please Input Export File Name ... ");
	scanf("%s",text);
	printf("  File <%s> is Type ( text or log ) ... ",text);
	scanf("%s",opt);
	if(strcmp("text",opt)==0){
	  save(list,text,1);
	}
	else if(strcmp("log",opt)==0){
	  save(list,text,0);
	}
	else{
	  printf("   Type is not text or log!\n");
	}
	break;
      case ADD:
	printf(" Please Input Add Word Spelling ... ");
	scanf("%s",text);
	newWORD(list,text,1,0);
	break;
      case DEL:
	printf(" Please Input Delete Word Spelling ... ");
	scanf("%s",text);
	list=del(list,text);
	break;
      case PRINTA:
	print(list,1);
	break;
      case PRINT:
	print(list,0);
	break;
      case COUNT:
	list=counting(list);
	break;
      case SORTA:
	list=sort(list,1);
	break;
      case SORTN:
	list=sort(list,0);
	break;
      case FIND:
	printf(" Please Input Search Word Spelling ... ");
	scanf("%s",text);
	search(list,text,1);
	break;
      case HELP:
	help();
	break;
      case NUM:
	printNumber(list);
	break;
      case ALDEL:
	list=AllDelete(list);
	break;
      case OTHER:
	printf(" Command <%s> is not found!\n",text);
	printf(" Reference command is <help>\n");
	break;
      }
  }
}
// Program Source End
