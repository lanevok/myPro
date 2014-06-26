import java.util.Arrays;
import java.util.Scanner;

/**
 * Colored stones (PKU POJ 2978)
 *
 * 色のついた石の列が与えられる．
 * (同じ色の石)の間に(別の色の石)が含まれないようにしたい．
 * つまり，同じ色のグループを色の数だけできるようにする．
 * 上記条件を満たすように，石の列から，任意の位置の石をいくつか
 * 取り除き，条件を満たすような，石を最低取る必要がある数を答える．
 *
 * 問題文に，
 * 「no two stones of one color are separated by a stone of a different color」
 * とある．
 * 同じ色の間に別の色が2個以上あるなら良いという訳ではない．
 * あくまで，ある色が同じ色の間にあってはならないということである．
 *
 * 解法は一般的に動的計画法(Dynamic Programming, DP)
 * opt[i番目までの石を採用][i番目までに使用した色の順列パターン]=最適値
 * ※順列パターンとは，色の出現順序順列のことであり，重複する色は含まれない
 *
 * 採用した解法の概要は以下．
 * 0番目から入力の石の数m番目まで順にDP表optに全て埋めていく，下から処理．
 * opt表は，大きい値で初期化．
 * 漸化式について．呼び出し opt(i番目まで採用, 採用した順列パターンP)
 * 1. opt(i-1, P)+1　→　i番目の色取り除く
 * 2. opt(i-1, P)   ただし， if(Pの最終色==i番目の色)　→　最後の色に等しいのでそのまま採用
 * 3. opt(i-1, 最終色除いたP)　ただし，if(Pの最終色==i番目の色)　→　初めてi番目の色を採用
 * 　　ただし，最終色除いたPが空である(いままで1色も採用していない)なら，
 *　　 いままでみてきた 「i-1」を取り除いていることになる処理を実行
 * 解の所在は，opt[m][*]の最小値．
 *
 *
 * @author T.Koike / lanevok / (TAT)chaN
 * @since 2014/6/27
 *
 */
public class POJ2978 {
    int m, k;
    int[] input;
    int[][] opt;		// DP表　※ opt[0][*]は使用しない
    int[] lookupForward;		// 正引き
    int[] lookupReverse;		// 逆引き

    // 最大5種類の色しか入力されないので順列パターン表現のint型最大は54321である
    final int END = 54321;

    int PATTERN;	// 順列パターン数

    public static void main(String[] args) {
	new POJ2978().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	standbyMatrix();		// 1度だけ起動時に走らせれば使いまわせる

	while(true){
	    m = stdIn.nextInt();
	    k = stdIn.nextInt();
	    if(m==0&&k==0) return;

	    input = new int[m+1];
	    for(int i=1;i<=m;i++){
		input[i] = stdIn.nextInt();
	    }

	    bridge();
	}
    }

    private void bridge() {
	opt = new int[101][PATTERN];
	// opt[m=100][*] がありうるので101とする

	// 初期化
	for(int i=0;i<101;i++){
	    // 石が100個まで入らないので200回 石を取り除くという大きい値を最適解として初期化
	    Arrays.fill(opt[i], 200);
	}

	dp();

	// opt(DP)表から最小値を見つける
	int answer = 200;
	for(int j=0;j<PATTERN;j++){
	    // m個入力するので，m番目まで見たときの各順列パターンの最小値が解になる
	    answer = Math.min(answer, opt[m][j]);
	}

	System.out.println(answer);
    }

    /**
     * DP実行
     */
    private void dp() {
	for(int i=1;i<=m;i++){	// 1番目から順に採用した場合で実行
	    for(int j=0;j<PATTERN;j++){		// i番目の順列パターンの最適値を全て求める

		int nowPattern = lookupForward[j];	// 現在の順列パターン表現
		// 順列を先頭からi個採用，現在の対象となる順列パターンの色種類数を比べて
		// 色種類数の方が多ければ，どう考えても生成できないので next step
		if(i<String.valueOf(nowPattern).length()) continue;

		// 漸化式その1
		opt[i][j] = Math.min(opt[i][j], opt[i-1][j]+1);
		// i番目の値を採用する順列パターンjにする，
		// 同じ順列パターンjについて，i-1番目までの最適値に1足す　ことが
		// 最適解になるのであれば更新する．1足す，つまりi番目の石を取り除くということ

		int temp = nowPattern;
		int place = 10000;
		while(true){
		    if(temp%place<10){
			break;
		    }
		    temp%=place;
		    place/=10;
		}
		// 1の位，つまり順列パターンの最終色を抽出する
		int lastType = temp%place;
		int nowStone = input[i];

		// 現在の色と順列パターンの最終色が同じであれば，以下についても確かめる必要あり
		if(lastType==nowStone){
		    // 漸化式その2
		    opt[i][j] = Math.min(opt[i][j], opt[i-1][j]);
		    // i番目の値を採用する順列パターンがjで
		    // その順列パターンjの最終色が現在採用するi番目の色と同じなので，
		    // そのままの順列パターンのまま，横にくっつけて採用(取り除かない)

		    // 現在までに採用されている順列パターンの色種類数が2以上であれば，
		    if(String.valueOf(nowPattern).length()>=2){
			// 順列パターンの最終色だけ除いたパターン表現を取る
			int beforePattern = nowPattern/10;
			// 漸化式その3
			opt[i][j] = Math.min(opt[i][j], opt[i-1][lookupReverse[beforePattern]]);
			// i番目を色を採用することで，j番目の順列パターンとなるように...
			// 今，最終色と現在の値が同じわけで，現在の値が最終色にないような
			// 順列パターン(前述のbeforePattern)について，一つ前 i-1 までの最適値を
			// 使い最適解であれば更新できる．最終色を除いた順列パターン表現が
			// 何番目に出現するかを逆引きで得ることができる
		    }
		    // ～色種類数が1であれば
		    else{
			// 現在のi番目石が初めて採用される，採用までにいままで取り除いていた
			// 石の数はi-1である
			opt[i][j] = Math.min(opt[i][j], i-1);
		    }
		}
	    }
	}
    }


    /**
     * 参照配列を用意する
     */
    private void standbyMatrix() {
	lookupForward = new int[400];	// 325であるが，最初は大きめな配列を用意しておいた
	int idx = 0;
	// 0～54321で色の順列パターンとなりうる整数値を判定する
	for(int i=0;i<=END;i++){
	    int num = i;
	    char[] c = String.valueOf(num).toCharArray();
	    boolean flag = true;
	    for(int j=0;j<c.length;j++){
		if(c[j]<'1'||c[j]>'5'){	// 1～5以外の文字列が出現したら不採用
		    flag = false;
		    break;
		}
		for(int k=j+1;k<c.length;k++){
		    if(c[j]==c[k]){	// 同じ色があれば，その順列パターンの整数値は不採用
			flag = false;
			break;
		    }
		}
	    }
	    if(flag){	// 順列パターン表現として採用
		lookupForward[idx++] = i;
	    }
	}
	PATTERN = idx;		// 順列パターンは最大325種類
	lookupReverse = new int [END+1];
	Arrays.fill(lookupReverse, -1);	// 参照できない場所は -1
	// 逆引きの生成
	for(int i=0;i<PATTERN;i++){
	    lookupReverse[lookupForward[i]] = i;
	}
    }

    /*
     * 上記standbyMatrixで作成される，参照配列．
     * 正引きと逆引きを用意．
     * 正引き: lookupForward[パターンi番目]=順列パターン表現
     * 逆引き: lookupReverse[順列パターン表現]=パターンi番目
     * 以下は先頭の一部．

     lookupForward[0]=1
     lookupForward[1]=2
     lookupForward[2]=3
     lookupForward[3]=4
     lookupForward[4]=5
     lookupForward[5]=12
     lookupForward[6]=13
     lookupForward[7]=14
     lookupForward[8]=15
     lookupForward[9]=21
     lookupForward[10]=23
     lookupForward[11]=24
     lookupForward[12]=25
     lookupForward[13]=31
     lookupForward[14]=32
     lookupReverse[0]=-1
     lookupReverse[1]=0
     lookupReverse[2]=1
     lookupReverse[3]=2
     lookupReverse[4]=3
     lookupReverse[5]=4
     lookupReverse[6]=-1
     lookupReverse[7]=-1
     lookupReverse[8]=-1
     lookupReverse[9]=-1
     lookupReverse[10]=-1
     lookupReverse[11]=-1
     lookupReverse[12]=5
     lookupReverse[13]=6
     lookupReverse[14]=7

    */
}
