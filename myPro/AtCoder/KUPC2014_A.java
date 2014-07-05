import java.util.Scanner;

public class KUPC2014_A {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int[] a = new int[3];
	int[] b = new int[3];
	for(int i=0;i<3;i++){
	    a[i] = stdIn.nextInt();
	}
	for(int i=0;i<3;i++){
	    b[i] = stdIn.nextInt();
	}
	int min = Integer.MAX_VALUE;
	int[][] index = {{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
	for(int i=0;i<index.length;i++){
	    int now = 0;
	    for(int j=0;j<3;j++){
		now += dist(a[j],b[index[i][j]]);
	    }
	    min = Math.min(min, now);
	}
	System.out.println(min);
    }

    private static int dist(int i, int j) {
	return Math.abs(i-j);
    }
}
