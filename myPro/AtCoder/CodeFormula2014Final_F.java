public class CodeFormula2014Final_F {

    boolean[][] field;
    int[][] ans;

    public static void main(String[] args) {
	new CodeFormula2014Final_F().run();
    }

    private void run() {
	ans = new int[100][2];
	field = new boolean[1500][1500];
	for(int i=100;i>=1;i--){		// circle
	    // System.out.println(i);
	    Label:
	    for(int x=1;x<1499;x++){		// center x
		for(int y=1;y<1499;y++){		// center y
		    boolean flag = true;		// put ok?
		    Check:
		    {
			for(int x1=x-i-1;x1<x+i+1;x1++)
			    for(int y1=y-i-1;y1<y+i+1;y1++){
				if(x1<0||y1<0||x1>=1500||y1>=1500){
				    flag = false;
				    break Check;
				}
				if(field[x1][y1]){
				    flag = false;
				    break Check;
				}
			    }
		    }
		    if(flag){
			// System.out.println("d: "+x+","+y);
			ans[i-1][0] = x;
			ans[i-1][1] = y;
			for(int x1=x-i-1;x1<x+i+1;x1++){
			    for(int y1=y-i-1;y1<y+i+1;y1++){
				if(x1>=0&&y1>=0&&x1<1500&&y1<1500) field[x1][y1] = true;
			    }
			}
			break Label;
		    }
		}
	    }
	}
	for(int i=0;i<100;i++){
	    System.out.println(ans[i][0]+" "+ans[i][1]);
	}
    }
}
/*
  F - 100個の円
  時間制限 : 2sec / スタック制限 : 10MB / メモリ制限 : 64MB

  問題文
  100 個の円があります。 このうち k 番目の円の半径は k であることがわかっています。

  これを、1500 × 1500 の正方形の中に敷き詰めることにしました。

  敷き詰め方の一例を出力してください。

  なお、円の中心の座標は、x 座標、 y 座標ともに、整数でないといけないことに注意してください。

  入力
  入力は与えられない。

  出力
  出力形式は、以下のようなものである。

  X1 Y1
  X2 Y2
  :
  X100 Y100
  円の情報を 100 行で出力せよ。

  k 行目には、k 番目の円の中心の x 座標と y 座標を表す Xk,Yk を、スペース区切りで 1 行で出力せよ。

  なお、出力は整数しか認められず、全ての円が、座標 (0,0) と (1500,1500) を結ぶ線分を対角線とする正方形の内部に収まっている必要がある。

  円や正方形が接していても良いが、重なることは許されない。

  出力例1
  1 1
  5 10
  500 30
  :
  以上の例では、半径 1 の円を座標 (1,1) に、半径 2 の円を座標 (5,10) に、半径 3 の円を座標 (500,30) に置いています。

  上記のような形で、 100 行出力するのが、今回の課題です。
*/
