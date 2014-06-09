import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2次元マップが与えられる。
 * 各座標が山の高さとしている。
 * 左上からスタートし、右下のゴールまで移動する。
 * 問題はそのルートについて、通過する山の高低差が最少になるよう
 * ルートを選択したときに、最少の高低差を求める。
 *
 * テストケース

 1
 3
 3 4 4
 1 4 4
 6 5 1

 Ans. 3

 --------------------


 1
 3
 3 1 9
 5 0 1
 4 7 7

 Ans. 4

 --------------------

 1
 3
 4	6	3
 9	2	6
 4	7	9

 Ans. 5

 --------------------

 1
 3
 7 9 7
 1 8 3
 3 0 4

 Ans. 6

 ----------------------

 1
 6
 144	44	14	128	164	23
 78	108	128	44	5	36
 32	84	37	89	4	35
 23	166	83	144	175	94
 43	192	174	93	23	77
 45	38	162	112	7	137

 Ans. 98

 -----------------------

 1
 5
 6 7 4 1 6
 2 3 9 8 3
 1 2 3 6 4
 8 6 3 4 4
 1 6 2 8 6

 Ans. 4

 ------------------------

 1
 5
 1 0 0 9 9
 1 9 5 9 9
 1 6 1 7 7
 9 9 7 9 7
 9 9 7 7 7

 Ans.  6

 ------------------------

 1
 5
 3 0 4 1 8
 4 7 2 0 6
 5 2 0 9 1
 1 8 3 8 1
 8 3 1 3 7

 Ans. 6

 *
 */
public class POJ2922p3 {

    class Point{
	int x;
	int y;
		
	Point(int _x, int _y){
	    x = _x;
	    y = _y;
	}
    }
	
    int[][] field;
    int h;

    public static void main(String[] args) {
	new POJ2922p3().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    h = stdIn.nextInt();
	    field = new int[h][h];
	    for(int j=0;j<h;j++){
		for(int k=0;k<h;k++){
		    field[j][k] = stdIn.nextInt();
		}
	    }

	    System.out.println("Scenario #"+(i+1)+":");
	    System.out.println(solve());
	    System.out.println();
	}
    }

    private int solve() {
	int cost = 201;
		
	// 探索の初期化
	int low = 0;
	int high = Math.max(field[0][0], field[h-1][h-1]);
		
	/**
	 * アルゴリズム
	 * low = 0,  high = スタートとゴールの最低値
	 * highを1ずつ増やして、到達できるまで探す。
	 * 到達できれば、コストの更新チェック。
	 * lowを1増やして、現在のhighからまた1ずつ増やす。
	 * これを繰り返す。
	 * 終了条件、lowはスタートとゴールの最低値になるまで、
	 * highは200までとすればよい
	 * 
	 */
		
	while(true){
	    boolean arrived = search(low, high);
	    if(!arrived){
		// 到達できず。最大値の範囲を広げる
		high++;
		// 終了条件(201以上の山は登場しない、探索不要)
		if(high>200){
		    break;
		}
	    }
	    else{
		// 到達。最低コストの更新
		cost = Math.min(cost, high-low);
		// 通ってよい最低値の範囲を1上げて狭める
		low++;
		// 終了条件(かならず、スタートかゴールの小さい方は使用する)
		if(low>Math.min(field[0][0], field[h-1][h-1])){
		    break;
		}
	    }
	}
		
	return cost;
    }

    /**
     * スタート地点からゴール地点まで辿り着けるか
     * @param low 通ってよい山の最低値
     * @param high 通ってよい山の最大値
     * @return 辿り着けた
     */
    private boolean search(int low, int high) {
	boolean[][] flag = new boolean[h][h];
	Queue<Point> queue = new LinkedList<Point>();
	// スタート地点
	queue.offer(new Point(0, 0));
		
	while(true){
	    if(queue.size()==0) return false;
			
	    Point p = queue.poll();
			
	    // 指定範囲内の山ではない
	    if(low>field[p.x][p.y]||field[p.x][p.y]>high) continue;
			
	    flag[p.x][p.y] = true;
			
	    // ゴール到達
	    if(p.x==h-1&&p.y==h-1) return true;
			
	    if(p.x+1<h&&!flag[p.x+1][p.y]){
		queue.offer(new Point(p.x+1, p.y));
		flag[p.x+1][p.y] = true;		// MLE
	    }
	    if(p.y+1<h&&!flag[p.x][p.y+1]){
		queue.offer(new Point(p.x, p.y+1));
		flag[p.x][p.y+1] = true;		// MLE
	    }
	    if(p.x-1>=0&&!flag[p.x-1][p.y]){
		queue.offer(new Point(p.x-1, p.y));
		flag[p.x-1][p.y] = true; 		// MLE
	    }
	    if(p.y-1>=0&&!flag[p.x][p.y-1]){
		queue.offer(new Point(p.x, p.y-1));
		flag[p.x][p.y-1] = true;		// MLE
	    }
	}
    }
}
