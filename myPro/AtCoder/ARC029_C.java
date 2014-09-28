import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * WA
 */
public class ARC029_C {

    int n, m;
    int[] city_money;
    boolean[][] route;
    int[][] route_money;

    boolean[] post_city;
    boolean[][] post_route;

    int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
	new ARC029_C().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	n = stdIn.nextInt();
	m = stdIn.nextInt();
	city_money = new int[n];
	for(int i=0;i<n;i++){
	    city_money[i] = stdIn.nextInt();
	}
	route = new boolean[n][n];
	route_money = new int [n][n];
	for(int i=0;i<m;i++){
	    int a = stdIn.nextInt()-1;
	    int b = stdIn.nextInt()-1;
	    int r = stdIn.nextInt();
	    route[a][b] = true;
	    route_money[a][b] = r;
	}

	post_city = new boolean[n];
	post_route = new boolean[n][n];
	calc(0, 0);
	System.out.println(min);
    }

    private void calc(int x, int y) {
	//		System.out.println(x+","+y);
	if(x==n&&y==n*n){
	    if(judge()){
		//				System.out.println("OK");
		min = Math.min(min, getTotalMoney());
		System.out.println(min);
	    }
	    else{
		//				System.out.println("NG");
	    }
	    return;
	}
	if(x==n){
	    int y1 = y/n;
	    int y2 = y%n;
	    if(y1>y2) y2 = y1;
	    //			System.out.println("\t"+y1+","+y2);
	    post_route[y1][y2] = true;
	    calc(x, y+1);
	    post_route[y1][y2] = false;
	    calc(x, y+1);
	}
	else{
	    //			System.err.println(x);
	    post_city[x] = true;
	    calc(x+1, y);
	    post_city[x] = false;
	    calc(x+1, y);
	}
    }

    private int getTotalMoney() {
	int sum = 0;
	for(int i=0;i<n;i++){
	    if(post_city[i]) sum += city_money[i];
	}
	for(int i=0;i<n;i++){
	    for(int j=i+1;j<n;j++){
		if(post_route[i][j]) sum += route_money[i][j];
	    }
	}
	return sum;
    }

    private boolean judge() {
	for(int i=0;i<n;i++){
	    if(post_city[i]) continue;
	    if(!access_challenge(i)) return false;
	}
	return true;
    }

    private boolean access_challenge(int start) {
	Queue<Integer> queue = new LinkedList<Integer>();
	queue.add(start);
	while(!queue.isEmpty()){
	    int now = queue.poll();
	    for(int i=0;i<n;i++){
		if(post_route[now][i]){
		    if(post_city[i]) return true;
		    queue.add(i);
		}
	    }
	}
	return false;
    }
}
