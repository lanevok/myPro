import java.util.Arrays;
import java.util.Scanner;


public class ABC012_D {

    int n;
    int m;
    int[][] time;

    public static void main(String[] args) {
	new ABC012_D().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	n = stdIn.nextInt();
	m = stdIn.nextInt();
	time = new int[n+1][n+1];
	for(int i=0;i<n+1;i++){
	    Arrays.fill(time[i], Integer.MAX_VALUE);
	}
	for(int i=0;i<m;i++){
	    int a = stdIn.nextInt();
	    int b = stdIn.nextInt();
	    int t = stdIn.nextInt();
	    time[a][b] = t;
	    time[b][a] = t;
	}

	int costMin = Integer.MAX_VALUE;
	for(int i=1;i<=n;i++){
	    int start = i;
	    costMin = Math.min(costMin, dijkstra(start));
	}
	System.out.println(costMin);
    }

    private int dijkstra(int s) {
	boolean[] enter = new boolean[n+1];
	int[] busStop = new int[n+1];
	Arrays.fill(busStop, Integer.MAX_VALUE);
	busStop[s] = 0;
	while(true){
	    int idx = -1;
	    int min = Integer.MAX_VALUE;
	    for(int i=1;i<=n;i++){
		if(!enter[i]&&busStop[i]<min){
		    min = busStop[i];
		    idx = i;
		}
	    }
	    if(min==Integer.MAX_VALUE){
		int max = -1;
		for(int i=1;i<=n;i++){
		    max = Math.max(max, busStop[i]);
		}
		return max;
	    }
	    enter[idx] = true;
	    for(int i=1;i<=n;i++){
		if(enter[i]||time[idx][i]==Integer.MAX_VALUE){
		    continue;
		}
		busStop[i] = Math.min(busStop[i], busStop[idx]+time[idx][i]);
	    }
	}
    }
}