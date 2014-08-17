import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * POJ2919 Traveling Queen Problem
 *
 * @author (TAT)chaN
 * @since 2014.6.30
 * @date 2014.8.18 (Accepted: 76)
 *
 * 【問題概要】
 * まず，チェスをイメージする．チェスの盤面とコマの扱いは同じ．
 * ここでは，1つのクイーン(Q)が盤面を移動することを考える．
 * クイーンが盤面に配置されているそれぞれのナイト(N, 個数は2～14)を訪問する必要がある，
 * 訪問とは，ナイトの隣接マス(基本的に周囲8マス)の何れかにクイーンが最低1度止まることを指す．
 * クイーンが盤面に配置されている全てのナイトを訪問したのち，ビショップ(B)を訪問する経路を考える．
 * 問題は，この経路について，クイーンの移動回数が最小，
 * またその中で経路(コマの移動順序)が辞書式最速(アルファベット順の先頭)なものを求める．
 * もし，経路が一つもない場合は「impossible」と出力する．
 *
 * 【解法】
 * 幅優先探索(BFS)．クイーン(スタート地点)から1手でいけるマスを洗い出していくと同時に，
 * 洗い出されたマスにおいて，ナイトを訪問することになったかを判定する．
 * マスの座標(64マス)とどのクイーンを訪問したか(最大でも2^14パターン)を状態として，保持させる．
 * 探索を辞書式にすることで，最初に見つかった経路を答えとすることができる．
 * なので，1手で移動できるマスについてソートをかけておく．
 * 同じマスに戻ってくる場合は，状態数が爆発し，無限ループ化するので，既出の状態が発生したら無視する．
 * (ここでの状態だが，同じマスでも，ナイトの訪問状況が異なれば，状態は別もの)
 * NG解法として「動的計画法」，「メモ化再帰(cf. ソースは p2)(深さ優先探索 DFS)」にも取り組んだ．
 * 特に，メモ化再帰の深さ優先探索では，最短ステップがいくつかわからない状態で，
 * 経路の辞書式を意識しないといけないので，全探索が必要となり，枝刈りをしても実行時間が追い付かない．
 *
 * 【ポイント】
 * 出力はシナリオごとに空行を挟む．
 * あるマスに訪問した際，複数のナイトを訪問することになる可能性も考慮する必要がある．
 * また，初期のマスにクイーンがいる時点で，ナイトを訪問することがあれば，訪問済みにしておく必要がある．
 *
 * 【高速化】
 * POJのTime Limitが5秒に設定されているが，N=14のデータ1つで1.3秒で答えが得られても，
 * TLEとなっている．最終的には0.3秒まで高速化してAcceptが取れた．
 * 1. あるマスから1手でどのマスに行けるかは，どんな状態でも一意に定まる．
 * 　マスは64しかないため，一度計算したら保存し，同じ計算が必要になれば，引用することで高速化する．
 * 　(moveFasterMap)
 * 2. どのマスにクイーンがくれば，どのナイトを訪問することになるか，事前に計算した．
 * 　(neighboorKnightSerial)
 * 3. 既出な状態チェックのため，真偽配列を用意．状態は8*8*(2^14)=約100万程度しかないので
 * 　二次元配列[x*8+y][1<<14]とすれば，O(1)で管理可能
 * 　(publishedSuper)
 * 4. ナイトをいくつ訪ねたかを毎回ナイト訪問状況配列(acKnight)を調べては時間がかかるので，
 * 　goBishopでナイト訪問数を管理，ナイト出現数とgoBishopを比較することで，あとはビショップだけか分かる
 * 5. 上記3において，O(1)で取得できるよう，ナイト訪問状況配列のパターン種類番号に当たるものを
 * 　保持しておくとよい(visitKnightNumber) パターンは2^14=2万弱しかない．
 *
 * ※テストは POJ2919test.txt, POJ2919ans.txt を参照．
 *
 */
public class POJ2919 {

    /**
     * neighboorKnightSerial生成のためのクラス
     * 訪問できるナイトの管理番号を保持させる．
     */
    class Nei{
	ArrayList<Integer> knightNo;

	public Nei() {
	    this.knightNo = new ArrayList<Integer>();
	}

	/**
	 * 訪問できるナイトを追加する
	 * @param n 追加するナイトの管理番号
	 */
	public void add(int n){
	    this.knightNo.add(n);
	}
    }

    /**
     * 座標また状態のクラス
     */
    class Point implements Comparable<Point>{
	int id;	         	        // 状態のID
	int x, y;
	int parent;	        	// この状態の親(生成元のID)
	boolean[] acKnight;		// ナイトの訪問状況配列
	int goBishop;			// ナイトの訪問数
	int visitKnightNumber;		// ナイトの訪問状況パターン番号

	/**
	 * 座標の生成コンストラクタ
	 * @param _x
	 * @param _y
	 */
	Point(int _x, int _y){
	    this.x = _x;
	    this.y = _y;
	}

	/**
	 * 座標と状態の生成コンストラクタ
	 * @param _x
	 * @param _y
	 * @param _parent
	 * @param _acKnight
	 * @param _goBishop
	 * @param _visitKnightNumber
	 */
	Point(int _x, int _y, int _parent, boolean[] _acKnight, int _goBishop, int _visitKnightNumber){
	    this.id = -100;	// 正規のIDを振らない
	    this.x = _x;
	    this.y = _y;
	    this.parent = _parent;
	    this.goBishop = _goBishop;
	    this.visitKnightNumber = _visitKnightNumber;
	    this.acKnight = new boolean[knightNum];
	    if(_acKnight!=null){
		for(int i=0;i<knightNum;i++){
		    this.acKnight[i] = _acKnight[i];
		}
	    }
	}

	/**
	 * 指定したナイトを訪問にする(訪問にしてても動作可)
	 * それと同時に，ナイトの訪問数とナイトの訪問状況パターンを更新する．
	 * @param checkKnight 訪問するナイトの管理番号
	 */
	public void checkKnight(int checkKnight){
	    if(!this.acKnight[checkKnight]){
		this.acKnight[checkKnight] = true;
		this.goBishop++;
		this.visitKnightNumber += Math.pow(2, checkKnight);
	    }
	}

	/**
	 * 新規の状態として正規にIDを発行する．
	 * また，経路の逆引きができるよう状態の保存，状態を既出として追加処理．
	 */
	public void doPublish(){
	    this.id = createID++;
	    published.put(this.id, this);
	    publishedSuper[this.x*MAT+this.y][this.visitKnightNumber] = true;
	}

	//	public String toString() {
	//	    String[] c = {"a","b","c","d","e","f","g","h"};
	//	    return "("+c[y]+(8-x)+")";
	//	}

	//	public String accessKnightString(){
	//		StringBuilder sb = new StringBuilder();
	//		for(int i=0;i<knightNum;i++){
	//			if(this.acKnight[i])
	//				sb.append("o");
	//			else
	//				sb.append("x");
	//		}
	//		return sb.toString();
	//	}

	/**
	 * compareTo実装．マスの辞書式ソート用
	 */
	@Override
	    public int compareTo(Point o) {
	    return (((8-this.x)+this.y*8)-((8-o.x)+o.y*8));
	}

	/**
	 * マスの名称を文字列で取得する
	 * @return マスの名称文字列
	 */
	public String getStr() {
	    String[] c = {"a","b","c","d","e","f","g","h"};
	    return c[this.y]+(8-this.x);
	}
    }

    static int knightNum;	        		// ナイトの数
    static final int MAT = 8;
    static int[][] field;				// 盤面の状況
    static HashMap<Integer, Nei> neighboorKnightSerial;	// 各マスについてのナイト訪問実現リスト
    static List<Point> neighboorBishop;                 // ビショップの隣接マスリスト
    static Point start;					// 初期クイーン(スタート地点)
    static final int PAWN = -40;
    static final int QUEEN = -10;
    static final int KNIGHT = -20;
    static final int BISHOP = -30;
    static int createID;				// 状態のシリアルID
    static Map<Integer, Point> published;	        // 経路逆引き用の状態保存
    static boolean[][] publishedSuper;			// 既出状態判定用
    static HashMap<Integer, List<Point>> moveFasterMap;	// 1手移動可能マスの保存用

    public static void main(String[] args)  {
	// System.setOut(new PrintStream(new File("koike.txt")));
	new POJ2919().run();
    }

    /**
     * 入力と実行呼び出しの親メソッド
     */
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
	    start = new Point(start.x, start.y, -1, null, 0, 0);
	    standby();
	    System.out.println("Scenario #"+(z+1)+":");
	    // long start = System.currentTimeMillis();
	    solve();
	    // System.out.println((System.currentTimeMillis()-start)*1.0/1000+" sec");
	    System.out.println();
	}
    }

    /**
     * 主計算
     */
    private void solve(){
	// 初期化
	createID = 0;
	published = new HashMap<Integer, Point>();
	publishedSuper = new boolean[MAT*MAT][1<<14];
	moveFasterMap =	new HashMap<Integer,List<Point>>();
	for(int i=0;i<MAT*MAT;i++){
	    publishedSuper[i] = new boolean[1<<14];
	}
	List<Point> nextList = new ArrayList<Point>();

	// クイーン初期状態でのナイト(ビショップ)訪問に関する処理
	Nei nowAccessKnight2 = neighboorKnightSerial.get(start.x*MAT+start.y);
	Point check2 = new Point(start.x, start.y, start.id,
				 start.acKnight, start.goBishop, start.visitKnightNumber);
	for(Integer nei: nowAccessKnight2.knightNo){
	    check2.checkKnight(nei);
	}
	if(check2.goBishop==knightNum){
	    for(int k=0;k<neighboorBishop.size();k++){
		Point nei = neighboorBishop.get(k);
		if(nei.x==check2.x&&nei.y==check2.y){
		    check2.doPublish();
		    route(check2.id);
		    return;
		}
	    }
	}
	nextList.add(check2);
	check2.doPublish();

	// 初期クイーン(スタート)地点から順に処理
	// int cnt = 0;
	while(true){
	    //	cnt++;
	    //	System.out.println("===== "+cnt+" ("+nextList.size()+") =====");
	    // 処理する状態がなくなれば，経路なしの目的達成不可
	    if(nextList.size()==0){
		System.out.println("impossible");
		return;
	    }
	    // 走査リストの初期化
	    List<Point> list = null;
	    // 次リストを走査リストへコピー
	    list = new ArrayList<Point>(nextList);
	    // 次リストの初期化
	    nextList = null;
	    nextList = new ArrayList<Point>();
	    // 走査リストから順にマス状態を取得
	    for(Point nowPoint : list){
		// 候補マスの取得
		List<Point> possiblePoint = possibleMove(nowPoint);
		// 候補マスを順に処理
		for(Point nextPoint : possiblePoint){
		    // 候補マスでナイト訪問を処理
		    Nei nowAccessKnight = neighboorKnightSerial.get(nextPoint.x*MAT+nextPoint.y);
		    Point check = new Point(nextPoint.x, nextPoint.y, nowPoint.id,
					    nowPoint.acKnight, nowPoint.goBishop, nowPoint.visitKnightNumber);
		    for(Integer nei: nowAccessKnight.knightNo){
			check.checkKnight(nei);
		    }
		    // 全てのナイト訪問が完了し，ビショップ到達判定が必要か確認
		    if(check.goBishop==knightNum){
			for(int k=0;k<neighboorBishop.size();k++){
			    Point nei = neighboorBishop.get(k);
			    if(nei.x==check.x&&nei.y==check.y){
				// 目的達成解の発見
				check.doPublish();
				route(check.id);
				return;
			    }
			}
		    }
		    // 既出状態か確認
		    if(!alreadyPublished(check)){
			nextList.add(check);
			check.doPublish();
		    }
		}
	    }
	}
    }

    /**
     * ビショップ(ゴールマス)の隣接マス(目的達成最終マス)からスタート(初期クイーン)に
     * 遡りながら，移動マス(経路)の文字列生成し出力する．
     * @param id ビショップの隣接マス
     */
    private static void route(int id) {
	String s = "";
	while(id!=0){
	    s = published.get(id).getStr()+s;
	    id = published.get(id).parent;
	}
	s = published.get(id).getStr()+s;
	System.out.println(s);
    }

    /**
     * この状態がすでに既出であるかどうか判定する
     * @param target 判定する状態Pointクラス
     * @return 既出である
     */
    private static boolean alreadyPublished(Point target){
	if(publishedSuper[target.x*MAT+target.y][target.visitKnightNumber]) return true;
	return false;
    }

    /**
     * あるマスから1手で移動できるマスをリストで返す
     * @param locS 基準のマス
     * @return 移動できるマスのリスト
     */
    private List<Point> possibleMove(Point locS) {
	// 既に計算したか確認する
	List<Point> temp = moveFasterMap.get(locS.x*MAT+locS.y);
	if(temp!=null&&temp.size()!=0){
	    // 計算済み結果を返す
	    return temp;
	}
	// 新規計算．上下左右，斜めの方向で計8種類
	List<Point> result = new ArrayList<Point>();
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

	// 移動可能マスのソート
	Collections.sort(result);
	// 計算結果の保存
	moveFasterMap.put(locS.x*MAT+locS.y, result);
	return result;
    }

    /**
     * ビショップの隣接マスリスト生成　と　
     * 　各マスについてのナイト訪問実現リスト生成
     */
    private void standby() {
	// 初期化
	neighboorBishop = new ArrayList<Point>();
	neighboorKnightSerial = new HashMap<Integer, Nei>();
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++){
		Nei nei = new Nei();
		neighboorKnightSerial.put(i*MAT+j, nei);
	    }
	}

	int cnt = -1;
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++){
		if(field[i][j]==KNIGHT){
		    cnt++;	// ナイトの管理番号
		    for(int x=-1;x<2;x++)
			for(int y=-1;y<2;y++)
			    if(inMat(i+x, j+y)&&!(x==0&&y==0)&&field[i+x][j+y]!=PAWN){
				Nei nei = neighboorKnightSerial.get((i+x)*MAT+(j+y));
				nei.add(cnt);
				neighboorKnightSerial.put((i+x)*MAT+(j+y), nei);
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
     * 座標が盤面内であるか
     * @param i x座標
     * @param j y座標
     * @return 盤面内である
     */
    private static boolean inMat(int i, int j) {
	if(i>=0&&i<MAT&&j>=0&&j<MAT) return true;
	return false;
    }
}
