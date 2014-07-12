import java.util.Arrays;
import java.util.Scanner;

public class ABC012_D2 {

    int n;
    int[][] time;

    public static void main(String[] args) {
	new ABC012_D2().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);
	n = stdIn.nextInt();
	int m = stdIn.nextInt();


	time = new int[n+1][n+1];
	for(int i=1;i<=n;i++){
	    Arrays.fill(time[i], 100000000);
	}

	for(int i=0;i<m;i++){
	    int a = stdIn.nextInt();
	    int b = stdIn.nextInt();
	    int t = stdIn.nextInt();

	    time[a][b] = t;
	    time[b][a] = t;

	}
	wf();

	int min = 100000000;
	for(int i=1;i<=n;i++){
	    int max = -1;
	    for(int j=1;j<=n;j++){
		if(i!=j) max = Math.max(max, time[i][j]);
	    }
	    min = Math.min(min, max);
	}
	System.out.println(min);
    }

    /**
     * ワーシャルフロイドアルゴリズム
     * 計算量 O(N^3)
     */
    private void wf() {
	for(int k=1;k<=n;k++){
	    for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
		    time[i][j] = Math.min(time[i][j], time[i][k]+time[k][j]);
		}
	    }
	}
    }
}
