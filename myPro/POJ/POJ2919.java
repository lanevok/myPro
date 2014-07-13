import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 *

 テスト用(ナイトは2以上である)
 正答4
 1
 ...P....
 ...P..Q.
 ..NP....
 ...P....
 ...P....
 ...P....
 .B.P....
 ........

 Sample Input 1
 1
 .......Q
 ...P.P..
 ...PNP..
 ..NP.P..
 ........
 ........
 ..B.....
 ........
 正答4

 Sample Input 2
 1
 B.P.....
 ..P.....
 PPP..N..
 ........
 ........
 .N...Q..
 ........
 ........
 正答Imp

 ノーマル
 1
 N++++P++
 +++++PQ+
 +++++P++
 +++P+P++
 +++P+P++
 +++P+P++
 +B+P+N++
 +++P++++
 正答5

 ナイト囲み
 1
 N++++P++
 +++++PQ+
 +++++P++
 +++P+P++
 +++P++++
 +++PPPPP
 +B+P+N++
 +++P++++
 正答Imp

 クイーン囲み
 1
 N++++P++
 +++++PQ+
 +++++P++
 +++P+P++
 +++P+P++
 +++P+PPP
 +B+P+N++
 +++P++++
 正答Imp

 ノーマル2
 1
 N++++P+Q
 +++++P++
 +++++P++
 +++P+P++
 +++P+P++
 +++P+P+P
 +++P++++
 B++P+++N
 正答6

 全部囲い
 1
 N++++P+Q
 +++++P++
 +++++P++
 +++P+P++
 +++P+P++
 PPPPPPPP
 +++P++++
 B++P+++N
 正答Imp

 ポーンなし
 1
 N++++++Q
 ++++++++
 ++++++++
 ++++++++
 ++++++++
 ++++++++
 ++++++++
 B++++++N
 正答3

 ナイト11個
 1
 N++++++Q
 ++++++++
 +++++++N
 +N+N+N++
 ++++++++
 +N+N++N+
 ++++++++
 B+N+N++N

 *
 */
public class POJ2919 {

    class Point{
	int x;
	int y;
	Point(int _x, int _y){
	    x = _x;
	    y = _y;
	}

	boolean contain(List<Point> neighboor){
	    for(Point p : neighboor){
		if(x==p.x&&y==p.y) return true;
	    }
	    return false;
	}

	@Override
	    public String toString() {
	    return "("+x+","+y+")";
	}

	public boolean isFree(){
	    if(!inMat(x, y)||field[x][y]<-15){
		return false;
	    }
	    return true;
	}
    }

    int knightNum;
    final int MAT = 8;
    int[][] field;
    int[][] opt;
    List<Point> neighboorKnight;
    List<Point> neighboorBishop;

    Point start;

    final int PAWN = -40;
    final int QUEEN = -10;
    final int KNIGHT = -20;
    final int BISHOP = -30;

    public static void main(String[] args) {
	new POJ2919().run();
    }

    private void run() {
	input();
    }

    private void output() {
	// TODO debug
	//		System.out.println("===== output =====");

	int min = Integer.MAX_VALUE;
	for(int i=0;i<neighboorKnight.size();i++){
	    Point nowS = neighboorKnight.get(i);
	    if(!inMat(nowS.x, nowS.y)) continue;
	    for(int j=0;j<neighboorBishop.size();j++){
		Point nowG = neighboorBishop.get(j);
		if(!inMat(nowG.x, nowG.y)) continue;
		//				System.out.println(opt[(1<<knightNum)-1][nowS.x*8+nowS.y]);
		min = Math.min(min, opt[(1<<knightNum)-1][nowS.x*8+nowS.y]+bfs(nowS, nowG));
	    }
	}
	if(min>=10000){
	    System.out.println("impossible");
	}
	else{
	    System.out.println(min);
	}
    }

    private boolean inMat(int i, int j){
	if(i>=0&&i<MAT&&j>=0&&j<MAT) return true;
	else return false;
    }

    private void standby() {
	Queue<Point> q = new LinkedList<Point>();
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++){
		if(field[i][j]==KNIGHT){
		    for(int x=-1;x<2;x++){
			for(int y=-1;y<2;y++){
			    if(inMat(i+x, j+y)&&!(x==0&&y==0)){
				q.add(new Point(i+x, j+y));
			    }
			    else{
				if(x==0&&y==0) continue;
				q.add(new Point(-1,-1));
			    }
			}
		    }
		}
	    }
	}
	neighboorKnight = new ArrayList<Point>();
	for(Point p : q){
	    neighboorKnight.add(p);
	}

	Queue<Point> q2 = new LinkedList<Point>();
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++){
		if(field[i][j]==BISHOP){
		    for(int x=-1;x<2;x++){
			for(int y=-1;y<2;y++){
			    if(inMat(i+x, j+y)&&!(x==0&&y==0)){
				q2.add(new Point(i+x, j+y));
			    }
			    else{
				if(x==0&&y==0) continue;
				q.add(new Point(-1,-1));
			    }
			}
		    }
		}
	    }
	}
	neighboorBishop = new ArrayList<Point>();
	for(Point p : q2){
	    neighboorBishop.add(p);
	}
    }

    private void dp() {
	opt = new int[1<<knightNum][MAT*MAT];
	for(int i=0;i<(1<<knightNum);i++){
	    Arrays.fill(opt[i], 10000);
	}

	// TODO debug
	//		System.out.println("=== calc 0 ===");

	for(int i=0;i<MAT*MAT;i++){
	    Point now = new Point(i/MAT, i%MAT);
	    if(!now.contain(neighboorKnight)&&field[now.x][now.y]>-15){
		opt[0][i] = bfs(start, new Point(i/MAT, i%MAT));
	    }
	}

	// TODO debug
	//		System.out.println("=== calc 1 ===");

	for(int m=0;m<(1<<knightNum);m++){
	    //			System.out.println("m="+m);
	    if(Integer.bitCount(m)==1){
		for(int i=0;i<neighboorKnight.size();i++){
		    //					System.err.println(neighboorKnight.size());
		    //					System.out.println("nei="+i);

		    if(Integer.bitCount((i/8+1)&m)==1){
			Point temp = neighboorKnight.get(i);
			if(!inMat(temp.x, temp.y)||field[temp.x][temp.y]<-15) continue;
			int nowPattern = temp.x*8+temp.y;
			opt[m][nowPattern] = bfs(start, temp);

			// TODO debug
			//						System.out.println("opt["+Integer.toBinaryString(m)+"]["+convert(nowPattern)+"]="+opt[m][nowPattern]);
		    }
		}
	    }
	}

	for(int acKnightNum=2;acKnightNum<=knightNum;acKnightNum++){
	    // TODO debug
	    System.out.println("=== calc "+acKnightNum+" ===");

	    for(int nowKnightPattern=1;nowKnightPattern<(1<<knightNum);nowKnightPattern++){
		if(Integer.bitCount(nowKnightPattern)==acKnightNum){
		    for(int nowAcKnight=0;nowAcKnight<knightNum;nowAcKnight++){
			int neighboor_k_Knight_start = nowAcKnight*8;
			int neighboor_k_Knight_goal = nowAcKnight*8+8;
			for(int m=neighboor_k_Knight_start;m<neighboor_k_Knight_goal;m++){
			    Point optPoint = neighboorKnight.get(m);
			    if(optPoint.isFree()){
				int optPointPattern = optPoint.x*8+optPoint.y;
				for(int q=0;q<neighboorKnight.size();q++){
				    Point basePoint = neighboorKnight.get(q);
				    if(basePoint.isFree()){
					int basePointPattern = basePoint.x*8+basePoint.y;
					int baseKnightPattern = nowKnightPattern&(~(1<<(nowAcKnight)));
					if(q/8!=nowAcKnight){
					    // TODO debug
					    //											System.out.print("baseKnightPattern="+Integer.toBinaryString(baseKnightPattern)+" basePointPattern="+convert(basePointPattern));

					    //											System.out.print(nowKnightPattern);
					    //											System.out.print(optPointPattern);

					    // TODO debug
					    //											System.out.print(" opt["+Integer.toBinaryString(nowKnightPattern)+"]["+convert(optPointPattern)+"] = "+opt[nowKnightPattern][optPointPattern]+" => ");

					    opt[nowKnightPattern][optPointPattern] = Math.min(opt[nowKnightPattern][optPointPattern],
											      opt[baseKnightPattern][basePointPattern]+bfs(basePoint, optPoint));

					    // TODO debug
					    //											System.out.println(opt[nowKnightPattern][optPointPattern]);
					}
				    }
				}
			    }
			}
		    }
		}
	    }
	}
    }

    private String convert(int nowPattern) {
	return "("+(nowPattern/8)+","+(nowPattern%8)+")";
    }

    /**
     * locSから1ステップで移動できる候補の座標リストを返す
     * @param locS 現在の地点
     * @return 移動できる座標のリスト
     */
    private List<Point> possibleMove(Point locS){
	List<Point> result = new ArrayList<Point>();

	for(int i=locS.x-1;i>=0;i--){
	    if(field[i][locS.y]>-15) result.add(new Point(i, locS.y));
	    else break;
	}
	for(int i=locS.x+1;i<MAT;i++){
	    if(field[i][locS.y]>-15) result.add(new Point(i, locS.y));
	    else break;
	}
	for(int i=locS.y-1;i>=0;i--){
	    if(field[locS.x][i]>-15) result.add(new Point(locS.x, i));
	    else break;
	}
	for(int i=locS.y+1;i<MAT;i++){
	    if(field[locS.x][i]>-15) result.add(new Point(locS.x, i));
	    else break;
	}
	for(int i=1;i<MAT;i++){
	    if(inMat(locS.x+i, locS.y+i)&&field[locS.x+i][locS.y+i]>-15) result.add(new Point(locS.x+i, locS.y+i));
	    else break;
	}
	for(int i=1;i<MAT;i++){
	    if(inMat(locS.x+i, locS.y-i)&&field[locS.x+i][locS.y-i]>-15) result.add(new Point(locS.x+i, locS.y-i));
	    else break;
	}
	for(int i=1;i<MAT;i++){
	    if(inMat(locS.x-i, locS.y+i)&&field[locS.x-i][locS.y+i]>-15) result.add(new Point(locS.x-i, locS.y+i));
	    else break;
	}
	for(int i=1;i<MAT;i++){
	    if(inMat(locS.x-i, locS.y-i)&&field[locS.x-i][locS.y-i]>-15) result.add(new Point(locS.x-i, locS.y-i));
	    else break;
	}

	//		System.out.print("("+locS.x+","+locS.y+") == 1step ==> ");
	//		System.out.println(result);
	return result;
    }

    /**
     * locSからlocGまで移動する際の最短ステップ数を返す
     * @param locS スタート地点
     * @param locG ゴール地点
     */
    private int bfs(Point locS, Point locG) {
	boolean[][] visited = new boolean[MAT][MAT];
	Queue<Point> q2 = new LinkedList<Point>(), q1;
	q2.add(locS);
	visited[locS.x][locS.y] = true;
	int cnt = 0;
	while(!q2.isEmpty()){
	    //			System.out.println("cnt="+cnt +" ... "+q2);
	    cnt++;
	    q1 = null;		q1 = new LinkedList<Point>();
	    for(Point p : q2) q1.add(p);
	    q2 = null;		q2 = new LinkedList<Point>();
	    while(!q1.isEmpty()){
		List<Point> nextPoint = possibleMove(q1.poll());
		for(Point p : nextPoint){
		    if(!visited[p.x][p.y]){
			if(p.x==locG.x&&p.y==locG.y){
			    //							System.out.println("fin cnt="+cnt +" ... "+q1);
			    //							System.out.println("("+locS.x+","+locS.y+") => ("+locG.x+","+locG.y+") = "+cnt);
			    return cnt;
			}
			q2.add(p);
			visited[p.x][p.y] = true;
		    }
		}
	    }
	}
	return 20000;
    }

    private void input() {
	Scanner stdIn = new Scanner(System.in);

	int d = Integer.valueOf(stdIn.nextLine());
	for(int z=0;z<d;z++){
	    if(z!=0) stdIn.nextLine();
	    field = new int[MAT][MAT];
	    knightNum = 0;
	    for(int i=0;i<MAT;i++){
		char[] line = stdIn.nextLine().toCharArray();
		for(int j=0;j<MAT;j++){
		    if(line[j]=='Q'){
			field[i][j] = QUEEN;
			start = new Point(i, j);
		    }
		    else if(line[j]=='P') field[i][j] = PAWN;
		    else if(line[j]=='N'){
			field[i][j] = KNIGHT;
			knightNum++;
		    }
		    else if(line[j]=='B') field[i][j] = BISHOP;
		}
	    }
	    debug();
	    standby();
	    dp();
	    output();
	}
    }

    private void debug() {
	System.out.println("=== debug ===");
	System.out.print("\t");
	for(int i=0;i<MAT;i++) System.out.print(i+"\t");
	System.out.println();
	System.out.print("\t");
	for(int i=0;i<MAT;i++) System.out.print("---\t");
	System.out.println();
	for(int i=0;i<MAT;i++){
	    System.out.print(i+" |\t");
	    for(int j=0;j<MAT;j++){
		System.out.print(field[i][j]+"\t");
	    }
	    System.out.println();
	}
	System.out.println("===== === =====");
    }
}
