﻿import java.util.Scanner;

public class Main {

//POJ1163
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	int[][] s = new int[n][n];
	for(int i=0;i<n;i++){
	    for(int j=0;j<=i;j++){
		s[i][j] = stdIn.nextInt();
	    }
	}
	int[][] r = new int[n][n];
	r[0][0] = s[0][0];
	for(int i=0;i<n-1;i++){
	    for(int j=0;j<=i;j++){
		r[i+1][j] = Math.max(r[i+1][j] , s[i+1][j] + r[i][j]);
		r[i+1][j+1] = Math.max(r[i+1][j+1] , s[i+1][j+1] + r[i][j]);
		// System.out.println(r[i+1][j]+" "+r[i+1][j+1]);
	    }
	}
	int ans = 0;
	for(int i=0;i<n;i++){
	    ans = Math.max(ans , r[n-1][i]);
	}
	System.out.println(ans);
    }
}