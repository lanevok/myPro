import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Runtime Error?
 * N = 14 で 5秒かかってしまう．

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


 ナイト10個
 1
 N+P++N+Q
 ++NP++++
 +++++++N
 +++N++++
 ++++++++
 +N++++N+
 ++++++++
 B+N+N++N
 正答 7?
 h8g7d4a7b6f2g2b2 ?

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
public class POJ2919p3 {

    /**
     * ナイトの隣接管理クラス
     *
     */
    class Nei{
	// 隣接しているナイト番号
	ArrayList<Integer> knightNo;

	public Nei() {
	    knightNo = new ArrayList<Integer>();
	}

	// 隣接しているナイトの追加
	public void add(int n){
	    knightNo.add(n);
	}

	public String toString(){
	    return knightNo.toString();
	}
    }

    /**
     * 座標クラス
     *
     */
    class Point implements Comparable<Point>{
	int id;		// 自身の管理番号
	int x, y;	// 座標
	int parent;	// 親(生成元)の管理番号
	boolean[] acKnight;	// 訪ねたナイト配列
	int goBishop;	// 訪ねたナイト数

	Point(int _x, int _y){
	    x = _x;
	    y = _y;
	}

	/**
	 * 状態の生成
	 * @param _x
	 * @param _y
	 * @param _parent
	 * @param _acKnight
	 * @param _goBishop
	 */
	Point(int _x, int _y, int _parent, boolean[] _acKnight, int _goBishop){
	    id = -100;	// publish命令がくるまで正規なIDを新規に与えない
	    x = _x;
	    y = _y;
	    parent = _parent;
	    goBishop = _goBishop;
	    acKnight = new boolean[knightNum];
	    if(_acKnight!=null){
		for(int i=0;i<knightNum;i++){
		    acKnight[i] = _acKnight[i];
		}
	    }
	}

	/**
	 * 引数のナイトに訪問したことにする．(すでに訪問してても問題ない)
	 * それと同時に訪問したナイト数を更新する
	 * @param checkKnight
	 */
	public void checkKnight(int checkKnight){
	    if(!acKnight[checkKnight]){
		acKnight[checkKnight] = true;
		goBishop++;
	    }
	}

	/**
	 * 状態にIDを振り，状態一覧に必要な情報を保存する
	 */
	public void doPublish(){
	    id = createID++;
	    published.put(id, this);	// ルート逆引き用の保存

	    // 一致状態検索の速引き用の保存
	    HashMap<Integer, List<Point>> temp = publishedSuper.get(x);
	    if(temp==null) temp = new HashMap<Integer, List<Point>>();
	    List<Point> temp2 = new ArrayList<Point>();
	    try {
		temp2 = temp.get(y);
	    } catch (Exception e) {}
	    if(temp2==null) temp2 = new ArrayList<Point>();
	    temp2.add(this);
	    temp.put(y, temp2);
	    publishedSuper.put(x, temp);
	}

	@Override
	    public String toString() {
	    String[] c = {"a","b","c","d","e","f","g","h"};
	    return "("+c[y]+(8-x)+")";
	    // return "(id="+id+"/"+c[y]+(8-x)+":"+accessKnightString()+" at "+parent+")";
	}

	/**
	 * デバッグ用．訪問したナイトをo，訪問していないナイトをxとして文字列生成
	 */
	public String accessKnightString(){
	    StringBuilder sb = new StringBuilder();
	    for(int i=0;i<acKnight.length;i++){
		if(acKnight[i])
		    sb.append("o");
		else
		    sb.append("x");
	    }
	    return sb.toString();
	}

	/**
	 * ソートの優先順位を定義する
	 */
	@Override
	    public int compareTo(Point o) {
	    return (((8-x)+y*8)-((8-o.x)+o.y*8));
	}

	/**
	 * 座標の座標名を文字列で取得する
	 * @return
	 */
	public String getStr() {
	    String[] c = {"a","b","c","d","e","f","g","h"};
	    return c[y]+(8-x);
	}

	/**
	 * メンバが引数と同じ状態であるか判定する
	 * 同じ状態とはx,y座標が等しく，
	 * 訪問しているナイトの数とそのナイトの番号も等しいことを意味する．
	 */
	public boolean isEqual(Point target){
	    if(goBishop!=target.goBishop) return false;
	    if(x!=target.x) return false;
	    if(y!=target.y) return false;
	    // 上記で早期リターンにひっかからなければ，一つずつ走査
	    for(int i=0;i<knightNum;i++){
		if(acKnight[i]!=target.acKnight[i]){
		    return false;
		}
	    }
	    return true;
	}
    }

    int knightNum;		// ナイト数
    final int MAT = 8;
    int[][] field;			// 盤面状態
    // Map<x, Map<y, 隣接ナイト番号リスト>>
    HashMap<Integer, HashMap<Integer, Nei>> neighboorKnightSerial;
    // ビショップの隣接座標(最大でも8点)
    List<Point> neighboorBishop;
    Point start;	// スタート位置
    final int PAWN = -40;
    final int QUEEN = -10;
    final int KNIGHT = -20;
    final int BISHOP = -30;
    int createID = 0;
    // Map<id, Point情報> で状態の保存．ルートの逆引きに使う
    Map<Integer, Point> published = new HashMap<Integer, Point>();
    // 状態が既出であるか速引きするためのMap<x, Map<y, (x,y)で過去に出現したPointリスト>>
    HashMap<Integer, HashMap<Integer, List<Point>>> publishedSuper =
	new HashMap<Integer, HashMap<Integer,List<Point>>>();
    // 1手でどこに移動できるか参照するようの速引き変数．中身はソートしておく．
    // 最大でも64マスしかないので64情報．Map<x, Map<y, (x,y)から1手でいけるPointリスト>>
    HashMap<Integer, HashMap<Integer, List<Point>>> moveFasterMap =
	new HashMap<Integer, HashMap<Integer,List<Point>>>();;

    public static void main(String[] args) {
	new POJ2919p3().run();
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
		    }
		    else if(line[j]=='P') field[i][j] = PAWN;
		    else if(line[j]=='N'){
			knightNum++;
			field[i][j] = KNIGHT;
		    }
		    else if(line[j]=='B') field[i][j] = BISHOP;
		}
	    }
	    start = new Point(start.x, start.y, -1, null, 0);
	    standby();
	    // debug();
	    // debug2();
	    // debug3();
	    System.out.println("Scenario #"+(z+1)+":");
	    // long start = System.currentTimeMillis();
	    solve();
	    // System.out.println((System.currentTimeMillis()-start)*1.0/1000+" sec");
	    // for(Integer id : moveFasterMap.keySet()){
	    //   System.out.println(id+"="+moveFasterMap.get(id).keySet());
	    // }
	    // System.out.println(publishedSuper);
	    System.out.println();
	}
    }

    //	private void debug3() {
    //	  for(int i=0;i<MAT;i++){
    //	    for(int j=0;j<MAT;j++){
    //	      HashMap<Integer, Nei> temp = neighboorKnightSerial.get(i);
    //	      Nei nei = temp.get(j);
    //	      System.out.print("("+i+","+j+") = ");
    //	      for(Integer p : nei.knightNo){
    //	        System.out.print(p+",");
    //	      }
    //	      System.out.println();
    //	    }
    //	  }
    //	}

    private void solve(){
	List<Point> nextList = new ArrayList<Point>();
	nextList.add(start);
	start.doPublish();
	int cnt = 0;

	while(true){
	    // 走査リストを初期化，次走査リストをコピーし
	    List<Point> list = null;
	    list = new ArrayList<Point>();
	    // Collections.sort(nextList);

	    // 次走査リストが空であれば，全ての状態をためして
	    // ナイトがすべて訪問できない　もしくは，
	    // ビショップに到達できない．
	    if(nextList.size()==0){
		System.out.println("impossible");
		return;
	    }

	    // 次リストの移し替え
	    for(Point nl : nextList){
		list.add(nl);
	    }
	    // 次走査リストの初期化
	    nextList = null;
	    nextList = new ArrayList<Point>();
	    // System.out.println("===== "+cnt+" step ( "+list.size()+" ) =====");
	    // System.out.println(list);
	    cnt++;
	    // 走査リストを走査
	    for(Point nowPoint : list){
		// 可能性座標を取得
		List<Point> possiblePoint = possibleMove(nowPoint);	// 高速化済
		// 候補順序のソート
		// Collections.sort(possiblePoint);
		// System.out.print(nowPoint+"→");
		// System.out.println(possiblePoint);
		// 可能性座標一つずつ走査
		for(Point nextPoint : possiblePoint){
		    // System.out.println("step="+cnt+" , next="+nextPoint);
		    // アクセスしちゃうナイト番号の取得
		    Nei nowAccessKnight = neighboorKnightSerial.get(nextPoint.x).get(nextPoint.y);
		    // 新しい状態の初期生成
		    Point check = new Point(nextPoint.x, nextPoint.y, nowPoint.id, nowPoint.acKnight, nowPoint.goBishop);
		    // アクセスしちゃうナイト番号を新しい状態に適用
		    for(Integer nei: nowAccessKnight.knightNo){
			check.checkKnight(nei);
		    }
		    // ナイトすべてアクセス済みである判定
		    if(check.goBishop==knightNum){  // if(check.getAccessKnightNum()==knightNum){
			// System.out.println("step="+cnt+" , fin?");
			// System.out.println(nowPoint+","+nextPoint+","+check);
			for(int k=0;k<neighboorBishop.size();k++){
			    Point nei = neighboorBishop.get(k);
			    if(nei.x==check.x&&nei.y==check.y){
				// 答えが見つかった．
				// System.out.println(cnt);
				check.doPublish();
				route(check.id);
				return;
			    }
			}
		    }
		    // 既出な状態でないか調査
		    if(!alreadyPublished(check)){
			// 次リストに追加とIDの割り当て
			nextList.add(check);
			check.doPublish();
		    }
		}
	    }
	}
    }

    /**
     * 現在のIDから親のIDに向けて遡り経路文字列を生成する
     */
    private void route(int id) {
	String s = "";
	while(id!=0){
	    s = published.get(id).getStr()+s;
	    id = published.get(id).parent;
	}
	s = published.get(id).getStr()+s;
	System.out.println(s);
    }

    /**
     * 引数が既出であるか調べる
     */
    private boolean alreadyPublished(Point target){
	try {
	    List<Point> list = publishedSuper.get(target.x).get(target.y);
	    for(Point p : list){
		if(p.isEqual(target)) return true;
	    }
	    return false;
	} catch (Exception e) {
	    return false;
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

    /**
     * locSから1手でどこの座標に行けるか調べる
     * @param locS
     * @return
     */

    private List<Point> possibleMove(Point locS) {
	List<Point> result = null;
	try {
	    HashMap<Integer, List<Point>> temp = moveFasterMap.get(locS.x);
	    result = temp.get(locS.y);
	} catch (Exception e) {}
	if(result!=null){
	    // 速引きに成功
	    return result;
	}
	result = new ArrayList<Point>();
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
	HashMap<Integer, List<Point>> temp = moveFasterMap.get(locS.x);
	if(temp==null) temp = new HashMap<Integer, List<Point>>();

	// 候補のソート
	Collections.sort(result);

	// 速引き用の保存
	temp.put(locS.y, result);
	moveFasterMap.put(locS.x, temp);
	return result;
    }

    /**
     * KNIGHTとBISHOPの隣接リストの作成
     */
    private void standby() {
	neighboorBishop = new ArrayList<Point>();
	// 初期化
	neighboorKnightSerial = new HashMap<Integer, HashMap<Integer, Nei>>();
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++){
		HashMap<Integer, Nei> temp = neighboorKnightSerial.get(i);
		if(temp==null){
		    temp = new HashMap<Integer, Nei>();
		}
		temp.put(j, new Nei());
		neighboorKnightSerial.put(i, temp);
	    }
	}
	int cnt = -1;
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++){
		if(field[i][j]==KNIGHT){
		    // cnt番目のナイト
		    cnt++;
		    for(int x=-1;x<2;x++)
			for(int y=-1;y<2;y++)
			    if(inMat(i+x, j+y)&&!(x==0&&y==0)&&field[i+x][j+y]!=PAWN){
				HashMap<Integer, Nei> temp = neighboorKnightSerial.get(i+x);
				Nei nei = temp.get(j+y);
				nei.add(cnt);
				temp.put(j+y, nei);
				neighboorKnightSerial.put(i+x, temp);
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
