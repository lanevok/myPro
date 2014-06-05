import java.util.Random;
import java.util.Scanner;

/**
 * 2次元マップが与えられる。
 * 各座標が山の高さとしている。
 * 左上からスタートし、右下のゴールまで移動する。
 * 問題はそのルートについて、通過する山の高低差が最少になるよう
 * ルートを選択したときに、最少の高低差を求める。
 *
 *現状テストケースと下記の入力4つ対して正しい答えが得られるのにWrong Answer
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

Ans. 98?


 *
 */
public class POJ2922p2 {

	class D{
		int max;
		int min;
		int cost;

		public D() {
			cost = 300;
			max = -1;
			min = 300;
		}

		public D(int _t1, int _t2) {
			max = _t1;
			min = _t2;
		}

		int get(){
			return cost;
		}

		void update(D _d, int value){
//			System.out.println("\t 現在のコスト: "+cost);
			if(cost==300){
//				System.out.println("\t 最初に訪問したのでmax,min更新");
				max = _d.max;
				min = _d.min;
				max = Math.max(_d.max, value);
				min = Math.min(_d.min, value);
				cost = max-min;
			}
			else{
				// 元々のcost が良いか　新しいルートからのコストが良いか
				int t_max = Math.max(_d.max, value);
				int t_min = Math.min(_d.min, value);
//				cost = _d.cost;
//				System.out.println("\t 訪問値(value): "+value+", t_max: "+t_max+", t_min: "+t_min);


				if(t_max-t_min<cost){
//					System.out.println("\t 現在探索したルートが最小のコストだったので更新");
					max = Math.max(_d.max, value);
					min = Math.min(_d.min, value);
					//			}
					cost = max-min;
				}
				else if(_d.min<=value&&value<=_d.max){
//					System.out.println("\t 最小値と最大値の中に納まるのでコスト保持");
					max = Math.max(t_max, max);
					min = Math.min(t_min, min);
//					if(_d.min<=value&&value<=_d.max){
						cost = _d.cost;
//					}
				}
				else if(t_max-t_min==cost){
//					System.out.println("\t 謎");
					max = Math.max(max, t_max);
					min = Math.min(min, t_min);
				}
			}
		}
	}

	boolean[][] flag;
	int[][] field;
	D[][] d;
	int h;
	//	int cost;
//	int cnt;

	public static void main(String[] args) {
		new POJ2922p2().run();
	}

	private void run() {
		Scanner stdIn = new Scanner(System.in);
		Random random = new Random();

		int n = stdIn.nextInt();
//				int n = 2;

		for(int i=0;i<n;i++){

			h = stdIn.nextInt();

			//			乱数用
//						h = random.nextInt(6);
//						h++;

			//			決め打ち用
//						h = 3;

			flag = new boolean[h][h];
			field = new int[h][h];
			for(int j=0;j<h;j++){
				for(int k=0;k<h;k++){

					field[j][k] = stdIn.nextInt();

//										乱数用
//										field[j][k] = random.nextInt(200);
//										field[j][k] = random.nextInt(10);

				}
			}
			//			cost = 201;
			//			long start = System.currentTimeMillis();
			//			System.out.println("h="+h);

			//			init
			d = new D[h][h];
			for(int j=0;j<h;j++){
				for(int k=0;k<h;k++){
					d[j][k] = new D();
				}
			}

//			cnt = 0;

			solve();



			//			System.out.println(System.currentTimeMillis()-start);
			System.out.println("Scenario #"+(i+1)+":");
			//			System.err.println(d[h-1][h-1].max+"\t"+d[h-1][h-1].min);
			System.out.println(d[h-1][h-1].get());


			// debug
//			for(int j=0;j<h;j++){
//				for(int k=0;k<h;k++){
//					System.out.print(field[j][k]+"\t");
//				}
//				System.out.println();
//			}
//			System.out.println("------------------------------------");
//
//			for(int j=0;j<h;j++){
//				for(int k=0;k<h;k++){
//					System.out.print("("+d[j][k].max+","+d[j][k].min+" ["+d[j][k].get()+"] )\t");
//				}
//				System.out.println();
//			}
//
//			if(d[h-1][h-1].get()!=Math.abs(field[0][0]-field[h-1][h-1])){
//				System.out.println("wow!");
//			}

		}
	}



	private void solve() {
		//		スタート地点の設定
		D st = new D(field[0][0], field[0][0]);
		d[0][0].update(st, field[0][0]);
		while(true){
			//			最小コスト探索
			int min = -1;
			int x = -1;
			int y = -1;
			for(int i=0;i<h;i++){
				for(int j=0;j<h;j++){
					if(!flag[i][j]){
						if(min==-1||(min>d[i][j].get()&&d[i][j].get()!=-1)){
							min = d[i][j].get();
							x = i;
							y = j;
						}
					}
				}
			}
			//			頂点の確定
			flag[x][y] = true;
//			cnt++;
//			System.out.println("=====     enter : "+x+", "+y+"   ========");

			//			ゴールの到達
			if(x==h-1&&y==h-1) break;
//			if(h*h==cnt){
//				break;
//			}

			//			隣接先への探索
			if(x+1<h&&x+1>=0&&!flag[x+1][y]){
//				System.out.println("update A : "+(x+1)+", "+y);
				d[x+1][y].update(d[x][y], field[x+1][y]);
			}
			if(y+1<h&&y+1>=0&&!flag[x][y+1]){
//				System.out.println("update B : "+x+", "+(y+1));
				d[x][y+1].update(d[x][y], field[x][y+1]);
			}
			if(x-1<h&&x-1>=0&&!flag[x-1][y]){
//				System.out.println("update C : "+(x-1)+", "+y);
				d[x-1][y].update(d[x][y], field[x-1][y]);
			}
			if(y-1<h&&y-1>=0&&!flag[x][y-1]){
//				System.out.println("update D : "+x+", "+(y-1));
				d[x][y-1].update(d[x][y], field[x][y-1]);
			}



			//			debug
//			for(int j=0;j<h;j++){
//				for(int k=0;k<h;k++){
//					System.out.print("("+d[j][k].max+","+d[j][k].min+" ["+d[j][k].get()+"]");
//					if(flag[j][k])
//						System.out.print("*");
//					System.out.print(")\t");
//				}
//				System.out.println();
//			}
		}
	}
}
