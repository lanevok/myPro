import java.util.Arrays;
import java.util.Scanner;

/*
 * TLE
 */
public class Donuts2014_C {

    static int[] a;
    static int[][] speed;

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	a = new int[n];
	for(int i=0;i<n;i++){
	    a[i] = stdIn.nextInt();
	}

	speed = new int[n][n];
	for(int i=0;i<n;i++){
	    Arrays.fill(speed[i], -20000);
	}

	int max = -10000;
	for(int i=0;i<n;i++){
	    for(int j=i;j<n;j++){
		max = Math.max(sum(i,j), max);
	    }
	}
	System.out.println(max);
    }

    private static int sum(int i, int j) {
	if(speed[i][j]!=-20000) return speed[i][j];
	int sum = 0;
	for(int x=i;x<=j;x++){
	    sum += a[x];
	}
	speed[i][j] = sum;
	return sum;
    }
}
