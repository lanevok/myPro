import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * TLE
 *
 */
public class ARC028_C {

    static boolean[][] route;
    static int n;

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	n = stdIn.nextInt();
	route = new boolean[n][n];
	for(int i=1;i<n;i++){
	    int t = stdIn.nextInt();
	    route[i][t] = true;
	    route[t][i] = true;
	}
	long start = System.currentTimeMillis();
	for(int i=0;i<n;i++){
	    System.out.println(getBalance(i));
	}
	System.err.println(System.currentTimeMillis()-start);
    }

    private static int getBalance(int lostCountry) {
	HashSet<Integer> ac = new HashSet<Integer>();
	int max = 0;
	// int cnt = 0;
	for(int i=0;i<n;i++){
	    if(!ac.contains(i)&&i!=lostCountry){
		// cnt++;
		HashSet<Integer> acNew = getAccess(i, lostCountry);
		max = Math.max(max, acNew.size());
		if(max==n-1){
		    // System.out.println("\tcnt1: "+cnt);
		    return max;
		}
		ac.addAll(acNew);
		// for(Integer a : acNew){
		//	ac.add(a);
		// }
	    }
	}
	// System.out.println("\tcnt2: "+cnt);
	return max;
    }

    private static HashSet<Integer> getAccess(int start, int lostCountry) {
	HashSet<Integer> visited = new HashSet<Integer>();
	visited.add(start);
	visited.add(lostCountry);
	Queue<Integer> queue = new LinkedList<Integer>();
	for(int i=0;i<n;i++){
	    if(route[start][i]&&!visited.contains(i)){
		visited.add(i);
		queue.add(i);
	    }
	}
	while(!queue.isEmpty()){
	    int target = queue.poll();
	    for(int i=0;i<n;i++){
		if(route[target][i]&&!visited.contains(i)){
		    visited.add(i);
		    queue.add(i);
		}
	    }
	}
	visited.remove(lostCountry);
	return visited;
    }
}
