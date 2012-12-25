/*#################################
Transformation
  HTML Source <blog> to <local>

Author  : (TAT)chaN
Version : 02
Date    : 2012/4/11
#################################*/


/*********************************************************
プログラムの実装概略
・blog.htmlからlocal.htmlに以下の条件でコピー
・</p>タグがあれば直後に改行追加
・<!-- コメント -->のタグ削除
・<img src="http:// ... .jpg">の画像リンクを最下層パス以外削除
・ヘッダーとして<BODY ... bgcolor="black" ... の追加
・フッターとして空白改行の<p>&#160;</p>を二つ追加
*********************************************************/


#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char block[1000];  // block tag
int i;
int j=0;           // block index
FILE *f2;          // local.html file pointer

void check(){
  char s[10];

  printf("Please input something!\n");
  scanf("%s",s);
  exit(0);
}

void delimg(){
  int k,len=strlen(block);  // len = block length

  for(i=0;i<len-2;i++){     // "alt" position shift
    if(block[i]=='a'&&
       block[i+1]=='l'&&
       block[i+2]=='t'){
      k=i+9;                // img number index(block)
      break;
    }
  }
  fprintf(f2,"<img src=\"IMG_");  // ex. IMG_001.jpg
  while(1){                       //     IMG_002a.jpg
    if(block[k]=='.'){            //     IMG_3.jpg
      break;
    }
    fprintf(f2,"%c",block[k]);
    k++;
  }
  fprintf(f2,".jpg\">");
}

void process(){
  char img[]={"<img src=@http"};  // <img src="http...
  int flag=1,len=strlen(img);     // len = img length

  for(i=0;i<len;i++){
    if(img[i]!=block[i]&&img[i]!='@'){
      flag=0;  // tag not equal img
      break;
    }
  }
  if(block[1]=='!')   // html comment delete
    return;
  if(flag)            // img tag true
    delimg();         // delete img (html->local)
  else{
    fprintf(f2,"%s",block);
    if(block[1]=='/'&&block[2]=='p')  // </p> is \r\n
      fprintf(f2,"\r\n");
  }
}

int main(){
  FILE *f1;           // blog.html file pointer
  char c;             // scan charactor
  int sn=0;           // block scan now (=1/true) flag
  char rt[]={"<p>&#160;</p>"};

  f1=fopen("blog.html","r");
  if(f1==NULL){       // blog.html not found
    printf("Not found! (blog.html)\n");
    check();          // console display
    exit(1);
  }
  f2=fopen("local.html","w");
  
  // header print
  fprintf(f2,"<BODY text=\"white\" bgcolor=\"black\" ></BODY>\r\n");

  while(1){
    c=getc(f1);       // scan
    if(c==EOF)        // finish judge
      break;

    if(c=='<'){       // block start
      block[j++]=c;
      sn=1;           // block scan now true
    }
    else if(c=='>'){  // block end
      block[j++]=c; 
      block[j]='\0';  // final charactor
      process();      // main process method call
      j=0;            // block index init
      sn=0;           // block scan now init
    }
    else if(sn)
      block[j++]=c;
    else
      fprintf(f2,"%c",c);  // between tag & tag
  }

  // footer print
  fprintf(f2,"\r\n%s\r\n%s",rt,rt);

  fclose(f1);
  fclose(f2);
  
  return 0;
}
