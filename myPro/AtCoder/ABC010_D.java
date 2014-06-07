import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ABC010_D {

    int n;
    int g;
    int e;
    int[] p;
    int answer;
    boolean[] cut;
    int cutCnt;

    class Point{
	int x;
	int y;
	public Point(int _x, int _y) {
	    x = _x;
	    y = _y;
	}
    }

    Point[] a;

    public static void main(String[] args) {
	new ABC010_D().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);
	n = stdIn.nextInt();
	g = stdIn.nextInt();
	e = stdIn.nextInt();
	p = new int[g];
	for(int i=0;i<g;i++){
	    p[i] = stdIn.nextInt();
	}
	a = new Point[e];
	for(int i=0;i<e;i++){
	    a[i] = new Point(stdIn.nextInt(), stdIn.nextInt());
	}

	cut = new boolean[e];
	answer = g;

	solve(0);

	System.out.println(answer);
    }

    private void solve(int index) {
	if(index==e){
	    int safe = search();

	    cutCnt = 0;
	    for(int i=0;i<e;i++){
		if(cut[i]) cutCnt++;
	    }

	    //			System.out.print("\t");
	    //			for(int i=0;i<e;i++){
	    //				System.out.print(cut[i]?"o":"x");
	    //			}
	    //			System.out.println();
	    //
	    //			System.out.println("\tcutCnt: "+cutCnt+"\t safe: "+safe);

	    if(cutCnt+safe<answer) answer = cutCnt+safe;

	    return;
	}
	cut[index] = false;
	solve(index+1);
	cut[index] = true;
	solve(index+1);
    }

    private int search() {
	boolean[] access = new boolean[n];
	Queue<Integer> link = new LinkedList<Integer>();
	link.add(0);
	int nowID = -1;
	//		System.out.println("-------  start -----------");
	while(true){
	    if(link.size()==0) break;
	    nowID = link.poll();
	    //			System.out.println("access: "+nowID);
	    access[nowID] = true;
	    for(int i=0;i<e;i++){
		if(cut[i]) continue;
		if(a[i].x==nowID&&!access[a[i].y]&&!link.contains(a[i].y)){
		    link.offer(a[i].y);
		}
		else if(a[i].y==nowID&&!access[a[i].x]&&!link.contains(a[i].y)){
		    link.offer(a[i].x);
		}
	    }
	}
	//		System.out.println("--------- goal -------------");
	int safeCnt = 0;
	for(int i=0;i<g;i++){
	    if(access[p[i]]) safeCnt++;
	}
	return safeCnt;
    }
}
