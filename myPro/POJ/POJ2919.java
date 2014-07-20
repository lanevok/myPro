import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *

 テスト用(ナイトは2以上である)
 1
 ...P....
 ...P..Q.
 ..NP....
 ...P....
 ...P....
 ...P....
 .B.P....
 ........
 正答4

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

 ノーマル
 1
 N++++P++
 +++++PQ+
 +++++P++
 +++N+N++
 +++P+P++
 +++P+P++
 +B+P+N++
 +++P++++
 正答5

 ノーマル
 1
 N+++++++
 ++++++Q+
 ++++++++
 +++N+N++
 ++++++++
 ++++++++
 +B+++N++
 ++++++++
 正答4

 ナイトとなり
 1
 +++++P++
 +++++PQ+
 +++++P++
 +++P+N+N
 +++P+N+N
 +++P+P++
 +++P+P++
 +++P+B++
 正答2

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

 近接part1
 1
 N++++++Q
 ++++++P+
 ++++++P+
 ++++++P+
 ++++++P+
 +++++PP+
 +++++PP+
 +B+++PN+
 正答4

 近接part2
 1
 N++++++Q
 ++++++P+
 ++++++P+
 ++++++P+
 ++++++P+
 +++++PP+
 +++++PN+
 +B+++PN+
 正答4

 ナイト14個
 1
 N+PN+N+Q
 ++NP++++
 +++++++N
 +N+N+N++
 ++++++++
 +N+N++N+
 ++++++++
 B+N+N++N
 正答8
 (2秒でも間に合わない可能性)
 h8d4a7b6g6e8e2g2b2 ?


 *
 */
public class CopyOfPOJ2919p2 {

    class Point implements Comparable<Point>{
	int x, y;
	String beforeStr;

	Point(int _x, int _y){
	    x = _x;
	    y = _y;
	    beforeStr = "";
	}

	int getIndex(){
	    return x*8+y;
	}

	@Override
	    public String toString() {
	    String[] c = {"a","b","c","d","e","f","g","h"};
	    return "("+c[y]+(8-x)+")";
	}

	@Override
	    public int compareTo(Point o) {
	    return (((8-x)+y*8)-((8-o.x)+o.y*8));
	}

	public void append(String str){
	    beforeStr += str;
	}

	public String getStr() {
	    String[] c = {"a","b","c","d","e","f","g","h"};
	    return c[y]+(8-x);
	}
    }

    int knightNum;
    final int MAT = 8;
    final int MAX_VALUE = 20000;
    int[][] field;
    String[][] memo;
    String[][] memoBFS;
    List<Point> neighboorKnight;
    List<Point> neighboorBishop;
    Point start;
    final int PAWN = -40;
    final int QUEEN = -10;
    final int KNIGHT = -20;
    final int BISHOP = -30;

    public static void main(String[] args) {
	new CopyOfPOJ2919p2().run();
    }

    private void run() {
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
			start.beforeStr = start.getStr();
		    }
		    else if(line[j]=='P') field[i][j] = PAWN;
		    else if(line[j]=='N'){
			field[i][j] = KNIGHT;
			knightNum++;
		    }
		    else if(line[j]=='B') field[i][j] = BISHOP;
		}
	    }
	    standby();
	    // debug();
	    debug2();
	    System.out.println("Scenario #"+(z+1)+":");
	    solve();
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
	    for(int j=0;j<MAT;j++)
		System.out.print(field[i][j]+"\t");
	    System.out.println();
	}
	System.out.println("=== ===== ===");
    }

    private void debug2() {
	System.out.println("=== debug2 ===");
	System.out.print("\t");
	System.out.println("a\tb\tc\td\te\tf\tg\th");
	System.out.print("\t");
	for(int i=0;i<MAT;i++) System.out.print("---\t");
	System.out.println();
	for(int i=0;i<MAT;i++){
	    System.out.print((8-i)+" |\t");
	    for(int j=0;j<MAT;j++)
		System.out.print(field[i][j]+"\t");
	    System.out.println();
	}
	System.out.println("=== ===== ===");
    }

    private void solve() {
	memo = new String[1<<knightNum][MAT*MAT];
	memoBFS = new String[MAT*MAT][MAT*MAT];
	for(int i=0;i<(1<<knightNum);i++)
	    Arrays.fill(memo[i], "z");
	for(int i=0;i<MAT*MAT;i++)
	    Arrays.fill(memoBFS[i], "z");

	// TODO debug
	// System.out.println(bfs(new Point(0, 7), new Point(7, 7)));
	// System.exit(0);

	long timeStart = System.currentTimeMillis();
	f(0, start);
	System.err.println((System.currentTimeMillis()-timeStart)+"msec");


	int ans = memo[0][start.getIndex()].length()/2-1;
	// System.out.println("ans="+ans+" , str="+memo[0][start.getIndex()]);

	if(ans==-1)
	    System.out.println("impossible"); else System.out.println(start.getStr()+memo[0][start.getIndex()]);
	System.out.println();
    }

    private String f(int s, Point loc) {
	// TODO debug
	// padding(s);
	// System.out.println("call : {"+Integer.toBinaryString(s)+"}\t"+loc);
	if(memo[s][loc.getIndex()].compareTo("z")!=0){
	    // TODO debug
	    // padding(s);
	    // System.out.println("\t return : {"+Integer.toBinaryString(s)+"}\t"+loc+"="+memo[s][loc.getIndex()]+" は一度計算 済");
	    return memo[s][loc.getIndex()];
	}
	int ans = MAX_VALUE;
	String ansStr = "z";
	if(Integer.bitCount(s)==knightNum){
	    for(Point p : neighboorBishop){
		String temp = bfs(loc, p);
		int temp_length = temp.length()/2;
		if(temp.compareTo("z")==0) temp_length = MAX_VALUE;
		if(ans>temp_length){
		    // System.out.println("更新1 "+ansStr);
		    ans = temp_length;
		    ansStr = temp;
		}
		else if(ans==temp_length&&ansStr.compareTo(temp)>0){
		    // System.out.println("更新2 "+ansStr);
		    ansStr = temp;
		}
	    }
	}
	else{
	    for(int k=0;k<knightNum;k++)
		// iがsに含まれていないなら
		if(((1<<k)&s)==0){
		    int end = k*8+8;
		    for(int nk=k*8;nk<end;nk++){
			Point nei = neighboorKnight.get(nk);
			// TODO 異変対応
			if(!inMat(nei.x, nei.y)||field[nei.x][nei.y]<-15) continue;


			String f_Str = f(s|(1<<k), nei);
			if(f_Str.compareTo("z")==0) continue;

			// TODO speed (これによりトラブル発生→わる2してなかった解決)
			if(f_Str.length()/2>ans) continue;

			String b_Str = bfs(loc, nei);
			if(b_Str.compareTo("z")==0) continue;
						
			// TODO debug
			// padding(s);
			// System.out.println("bfs : "+loc+" => "+nei);


			String now_Str = b_Str+f_Str;
			// System.out.println("nowStr = "+now_Str);
			int now_length = now_Str.length()/2;
			// if(b_Str.compareTo("z")==0) continue;//now_length = MAX_VALUE;
			if(ans>now_length){
			    // System.out.println("更新3 "+ansStr);
			    ans = now_length;
			    ansStr = now_Str;
			}
			else if(ans==now_length&&ansStr.compareTo(now_Str)>0){
			    // System.out.println("更新4 "+ansStr);
			    ansStr = now_Str;
			}
		    }
		}
	}
	memo[s][loc.getIndex()] = ansStr;
	// TODO debug
	// if(Integer.bitCount(s)==knightNum){
	// TODO debug
	// padding(ans);
	// System.out.println("\t return : {"+Integer.toBinaryString(s)+"}\t"+loc+"="+memo[s][loc.getIndex()]+" の計算 完了");
	// }
	return ansStr;
    }

    private void padding(int s) {
	for(int i=0;i<Integer.bitCount(s);i++)
	    System.out.print("\t");
    }

    private String bfs(Point locS, Point locG) {
	if(memoBFS[locS.getIndex()][locG.getIndex()].compareTo("z")!=0){

	    // TODO debug
	    // System.out.println("bfs memo ok : "+locS+","+locG+" => "+memoBFS[locS.getIndex()][locG.getIndex()]);

	    return memoBFS[locS.getIndex()][locG.getIndex()];
	}
	else if(locS.x==locG.x&&locS.y==locG.y){
	    memoBFS[locS.getIndex()][locG.getIndex()] = "";
	    return memoBFS[locS.getIndex()][locG.getIndex()];
	}

	// TODO debug
	// System.out.print("bfs memo ng : "+locS+" ==> "+locG+" ... ");


	boolean[][] visited = new boolean[MAT][MAT];
	Queue<Point> q2 = new PriorityQueue<Point>(), q1;
	locS.beforeStr = "";
	q2.add(locS);
	int cnt = 0;
	while(!q2.isEmpty()){
	    cnt++;
	    // System.out.println("cnt = "+cnt);
	    q1 = null;	q1 = new PriorityQueue<Point>();
	    for(Point p : q2){
		visited[p.x][p.y] = true;
		q1.add(p);
	    }
	    q2 = null;	q2 = new PriorityQueue<Point>();
	    boolean finish_flag = false;
	    while(!q1.isEmpty()){
		Point now = q1.poll();
		// TODO debug
		// System.out.println("now "+now);
		List<Point> nextPoint = possibleMove(now);
		for(Point p : nextPoint){
		    if(visited[p.x][p.y]) continue;
		    // TODO debug
		    // System.out.print(p+" ");
		    String before_Str = memoBFS[locS.getIndex()][locG.getIndex()];
		    String after_Str = now.beforeStr+p.getStr();
		    if(p.x==locG.x&&p.y==locG.y){
			finish_flag = true;
			// System.out.println(before_Str+" ... "+after_Str);
			// TODO 異変対応
			if(before_Str.compareTo(after_Str)>0){
			    // if(finish_flag){
			    // System.out.println("更新");
			    // }
			    memoBFS[locS.getIndex()][locG.getIndex()] = after_Str;
			}
		    }
		    p.append(after_Str);
		    q2.add(p);
		}
	    }
	    if(finish_flag){
		// System.out.println(memoBFS[locS.getIndex()][locG.getIndex()]);
		return memoBFS[locS.getIndex()][locG.getIndex()];
	    }
	}
	memoBFS[locS.getIndex()][locG.getIndex()] = "z";
	// System.out.println(memoBFS[locS.getIndex()][locG.getIndex()]);
	return "z";
    }

    private List<Point> possibleMove(Point locS) {
	// System.out.print(" "+locS+" ");
	List<Point> result = new ArrayList<Point>();

	// TODO debug 自分も加える
	// if(field[locS.x][locS.y]>-15) result.add(locS);

	for(int i=locS.x-1;i>=0;i--)
	    if(field[i][locS.y]>-15) result.add(new Point(i, locS.y));
	    else break;
	for(int i=locS.x+1;i<MAT;i++)
	    if(field[i][locS.y]>-15) result.add(new Point(i, locS.y));
	    else break;
	for(int i=locS.y-1;i>=0;i--)
	    if(field[locS.x][i]>-15) result.add(new Point(locS.x, i));
	    else break;
	for(int i=locS.y+1;i<MAT;i++)
	    if(field[locS.x][i]>-15) result.add(new Point(locS.x, i));
	    else break;
	for(int i=1;i<MAT;i++)
	    if(inMat(locS.x+i, locS.y+i)&&field[locS.x+i][locS.y+i]>-15) result.add(new Point(locS.x+i, locS.y+i));
	    else break;
	for(int i=1;i<MAT;i++)
	    if(inMat(locS.x+i, locS.y-i)&&field[locS.x+i][locS.y-i]>-15) result.add(new Point(locS.x+i, locS.y-i));
	    else break;
	for(int i=1;i<MAT;i++)
	    if(inMat(locS.x-i, locS.y+i)&&field[locS.x-i][locS.y+i]>-15) result.add(new Point(locS.x-i, locS.y+i));
	    else break;
	for(int i=1;i<MAT;i++)
	    if(inMat(locS.x-i, locS.y-i)&&field[locS.x-i][locS.y-i]>-15) result.add(new Point(locS.x-i, locS.y-i));
	    else break;
	return result;
    }

    /**
     * KNIGHTとBISHOPの隣接リストの作成
     */
    private void standby() {
	neighboorKnight = new ArrayList<Point>();
	neighboorBishop = new ArrayList<Point>();
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++)
		if(field[i][j]==KNIGHT){
		    for(int x=-1;x<2;x++)
			for(int y=-1;y<2;y++)
			    if(inMat(i+x, j+y)&&!(x==0&&y==0)&&field[i+x][j+y]!=PAWN)
				neighboorKnight.add(new Point(i+x, j+y));
			    else{
				if(x==0&&y==0) continue;
				neighboorKnight.add(new Point(-1,-1));
			    }
		}
		else if(field[i][j]==BISHOP){
		    for(int x=-1;x<2;x++)
			for(int y=-1;y<2;y++)
			    if(inMat(i+x, j+y)&&!(x==0&&y==0))
				neighboorBishop.add(new Point(i+x, j+y));
		}
	}
    }

    /**
     * マップ内であるか判定する
     * @param i
     * @param j
     * @return マップ内である
     */
    private boolean inMat(int i, int j) {
	if(i>=0&&i<MAT&&j>=0&&j<MAT) return true;
	return false;
    }
}
