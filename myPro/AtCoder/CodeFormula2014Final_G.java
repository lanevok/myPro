import java.util.Scanner;

/**
 * WA．Sample Input が通る．
 */
public class CodeFormula2014Final_G{

    int n;
    int[] r1,r2,r3;
    int r1_len,r2_len,r3_len;
    int move[][];

    public static void main(String[] args) {
	new CodeFormula2014Final_G().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	n = stdIn.nextInt();
	if(n>500){
	    return;
	}
	r1 = new int[n];
	r2 = new int[n];
	r3 = new int[n];
	r1_len = n;
	r2_len = 0;
	r3_len = 0;
	for(int i=0;i<n;i++){
	    r1[i] = stdIn.nextInt();
	}
	solve();
    }

    private void solve() {
	move = new int[225000][2];
	int target = n;
	boolean r_1to2 = true;
	int cnt = 0;
	int ps = 0;
	while(true){
	    ps++;
	    if(ps>50) return;
	    // System.out.println(r1_len+","+r2_len+","+r3_len);
	    if(r3_len==n){
		break;
	    }
	    if(r2_len==0){
		r_1to2 = true;
	    }
	    if(r1_len==0){
		r_1to2 = false;
	    }
	    if(r_1to2){
		if(target==r1[r1_len-1]){
		    r3[r3_len++] = r1[--r1_len];
		    // System.out.println("go r3");
		    move[cnt][0] = 1;
		    move[cnt][1] = 3;
		    cnt++;
		    target--;
		}
		else{
		    r2[r2_len++] = r1[--r1_len];
		    // System.out.println("\tgo r1=>r2");
		    move[cnt][0] = 1;
		    move[cnt][1] = 2;
		    cnt++;
		}
	    }
	    else{
		if(target==r2[r2_len-1]){
		    r3[r3_len++] = r2[--r2_len];
		    // System.out.println("go r3");
		    move[cnt][0] = 2;
		    move[cnt][1] = 3;
		    cnt++;
		    target--;
		}
		else{
		    r1[r1_len++] = r2[--r2_len];
		    // System.out.println("\tgo r2>=r1");
		    move[cnt][0] = 2;
		    move[cnt][1] = 1;
		    cnt++;
		}
	    }
	}
	System.out.println(cnt);
	for(int i=0;i<cnt;i++){
	    System.out.println(move[i][0]+" "+move[i][1]);
	}
    }
}
/*
  G - ノイハの塔
  時間制限 : 2sec / スタック制限 : 10MB / メモリ制限 : 64MB

  問題文
  「ハノイの塔」という有名なパズルがあります。 ハノイの塔は 3 つの杭と、中央に穴の空いたサイズが相異なる N 個の円盤からなるパズルです。 3 つの杭には 1 から 3 の番号が付けられています。また、 i 番目に小さい円盤の半径は i センチメートルです。

  初め、全ての円盤は杭 1 に、サイズが大きい順に下から積み重ねられて、「塔」を形成しています。杭 2,3 には何も置いていません。 プレイヤーは 1 回の操作で、ある杭の塔の一番上にある円盤を、他の杭に移動させて、移動先の杭の塔の一番上に積み重ねるという事ができます。 このとき、小さい円盤の上に大きい円盤を置くことはできません。 ハノイの塔のゴールはできるだけ少ない回数の操作で、高さNの塔を杭 2 もしくは杭 3 に移すことです。

  高橋君は久しぶりにこのパズルを遊ぼうと思い、押し入れの奥からハノイの塔のおもちゃを探しだしました。 しかし、だれかがいたずらをしたようで、N 個全ての円盤がバラバラの順番で杭 1 に積まれていました。 そこで高橋君はそのバラバラの状態から開始して、「小さい円盤の上に大きい円盤を置いてはならない」というルールを無視して操作し、いずれかの杭に全ての円盤がサイズが大きい順に下から積み重なるように移動させる、という別のパズルを遊ぶことにしました。

  あらかじめ、杭 1 に積み重ねられている円盤のサイズの情報が与えられるので、いずれかの杭(杭 1 でもよい)に全ての円盤がサイズ順に積み重なるように移動させる操作をひとつ挙げてください。 ただし操作が多すぎるとパズルとして美しくないので 225,000 回以内の操作で移動させてください。

  入力
  入力は以下の形式で標準入力から与えられる

  N
  r1
  r2
  :
  rN
  1 行目には円盤の個数 N(1≦N≦10,000) が与えられる。
  2 行目からの N 行のうち i 行目には、初め杭 1 に下から i 番目に置かれている円盤の半径 ri(1≦ri≦N)が与えられる。
  x≠y ならば rx≠ry が成り立つ。
  部分点
  この問題には部分点が設定されている。

  1≦N≦400を満たすデータセットに正解した場合は 30 点が与えられる。
  1≦N≦10,000を満たすデータセットに正解した場合はさらに 70 点が与えられる。合計で100点となる。
  出力
  出力形式は以下のようなものである。

  M
  from1 to1
  from2 to2
  :
  fromM toM
  1 行目には操作の回数 M を出力せよ。
  2 行目からの M 行のうち i 行目にはi番目の操作で動かす円盤の元あった杭の番号 fromi と動かす先の杭の番号 toi を空白区切りで出力せよ。
  i 番目の操作の時に、杭 fromi に円盤が一つも存在しない場合、その操作は妥当でないとする。それ以外の操作は妥当であるとする。
  本来のハノイの塔と異なり小さい円盤の上に大きい円盤を置くことが許されていることに注意せよ。
  M≦225,000 かつすべての操作が妥当であり、最後にいずれかの杭に全ての円盤がサイズの大きい順に下から積み重ねられていたときのみ、解答は正解とされる。
  入力例1
  5
  1
  2
  3
  4
  5
  出力例1
  5
  1 2
  1 2
  1 2
  1 2
  1 2
  初め、全ての円盤が逆順に杭 1 に積み重ねられています。上から順番に杭 2 に移動させれば、下から大きい順に並び替えられます。

  入力例2
  5
  5
  3
  2
  4
  1
  出力例2
  8
  1 2
  1 2
  1 3
  1 3
  2 1
  3 1
  3 1
  2 1
  最後に全ての円盤が積み重ねられる杭は 1 でもかまいません。
*/