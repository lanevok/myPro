import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    class Nei{
	ArrayList<Integer> knightNo;

	public Nei() {
	    this.knightNo = new ArrayList<Integer>();
	}

	public void add(int n){
	    this.knightNo.add(n);
	}
    }

    class Point implements Comparable<Point>{
	int id;
	int x, y;
	int parent;
	boolean[] acKnight;
	int goBishop;
	int visitKnightNumber;

	Point(int _x, int _y){
	    this.x = _x;
	    this.y = _y;
	}

	Point(int _x, int _y, int _parent, boolean[] _acKnight, int _goBishop, int _visitKnightNumber){
	    this.id = -100;
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

	public void checkKnight(int checkKnight){
	    if(!this.acKnight[checkKnight]){
		this.acKnight[checkKnight] = true;
		this.goBishop++;
		this.visitKnightNumber += Math.pow(2, checkKnight);
	    }
	}

	public void doPublish(){
	    this.id = createID++;
	    published.put(this.id, this);
	    publishedSuper[this.x*MAT+this.y][this.visitKnightNumber] = true;
	}

	//	public String toString() {
	//	    String[] c = {"a","b","c","d","e","f","g","h"};
	//	    return "("+c[y]+(8-x)+")";
	//	}

	public String accessKnightString(){
	    StringBuilder sb = new StringBuilder();
	    for(int i=0;i<knightNum;i++){
		if(this.acKnight[i])
		    sb.append("o");
		else
		    sb.append("x");
	    }
	    return sb.toString();
	}

	@Override
	    public int compareTo(Point o) {
	    return (((8-this.x)+this.y*8)-((8-o.x)+o.y*8));
	}

	public String getStr() {
	    String[] c = {"a","b","c","d","e","f","g","h"};
	    return c[this.y]+(8-this.x);
	}
    }

    static int knightNum;
    static final int MAT = 8;
    static int[][] field;
    static HashMap<Integer, Nei> neighboorKnightSerial;
    static List<Point> neighboorBishop;
    static Point start;
    static final int PAWN = -40;
    static final int QUEEN = -10;
    static final int KNIGHT = -20;
    static final int BISHOP = -30;
    static int createID;
    static Map<Integer, Point> published;
    static boolean[][] publishedSuper;
    static HashMap<Integer, List<Point>> moveFasterMap;

    public static void main(String[] args) {
	new Main().run();
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
	    start = new Point(start.x, start.y, -1, null, 0, 0);
	    standby();
	    System.out.println("Scenario #"+(z+1)+":");
	    //			long start = System.currentTimeMillis();
	    solve();
	    //			System.out.println((System.currentTimeMillis()-start)*1.0/1000+" sec");
	    System.out.println();
	}
    }

    private void solve(){
	createID = 0;
	published = new HashMap<Integer, Point>();
	publishedSuper = new boolean[MAT*MAT][1<<14];
	moveFasterMap =	new HashMap<Integer,List<Point>>();
	for(int i=0;i<MAT*MAT;i++){
	    publishedSuper[i] = new boolean[1<<14];
	}
	List<Point> nextList = new ArrayList<Point>();
	nextList.add(start);
	start.doPublish();
	//		int cnt = 0;
	while(true){
	    //			cnt++;
	    //			System.out.println("===== "+cnt+" ("+nextList.size()+") =====");
	    if(nextList.size()==0){
		System.out.println("impossible");
		return;
	    }
	    List<Point> list = null;
	    list = new ArrayList<Point>(nextList);
	    nextList = null;
	    nextList = new ArrayList<Point>();
	    for(Point nowPoint : list){
		List<Point> possiblePoint = possibleMove(nowPoint);
		for(Point nextPoint : possiblePoint){
		    Nei nowAccessKnight = neighboorKnightSerial.get(nextPoint.x*MAT+nextPoint.y);
		    Point check = new Point(nextPoint.x, nextPoint.y, nowPoint.id,
					    nowPoint.acKnight, nowPoint.goBishop, nowPoint.visitKnightNumber);
		    for(Integer nei: nowAccessKnight.knightNo){
			check.checkKnight(nei);
		    }
		    if(check.goBishop==knightNum){
			for(int k=0;k<neighboorBishop.size();k++){
			    Point nei = neighboorBishop.get(k);
			    if(nei.x==check.x&&nei.y==check.y){
				check.doPublish();
				route(check.id);
				return;
			    }
			}
		    }
		    if(!alreadyPublished(check)){
			nextList.add(check);
			check.doPublish();
		    }
		}
	    }
	}
    }

    private static void route(int id) {
	String s = "";
	while(id!=0){
	    s = published.get(id).getStr()+s;
	    id = published.get(id).parent;
	}
	s = published.get(id).getStr()+s;
	System.out.println(s);
    }

    private static boolean alreadyPublished(Point target){
	if(publishedSuper[target.x*MAT+target.y][target.visitKnightNumber]){
	    return true;
	}
	return false;
    }

    private List<Point> possibleMove(Point locS) {
	List<Point> temp = moveFasterMap.get(locS.x*MAT+locS.y);
	if(temp!=null&&temp.size()!=0){
	    return temp;
	}
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

	Collections.sort(result);
	moveFasterMap.put(locS.x*MAT+locS.y, result);
	return result;
    }

    private void standby() {
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
		    cnt++;
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

    private static boolean inMat(int i, int j) {
	if(i>=0&&i<MAT&&j>=0&&j<MAT) return true;
	return false;
    }
}
