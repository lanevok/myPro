import java.util.Scanner;
 
public class Main {
 
	static int[][] dp;
	static int[][] num;
	static int ans;
	static int n;
	
	static void DP(int x, int y){
		if(x-1>=0){
			if(y-1>=0&&x!=y){
				dp[x][y] = num[x][y]+Math.max(dp[x-1][y-1], dp[x-1][y]);
			}
			else if(y-1>=0){
				dp[x][y] = num[x][y]+dp[x-1][y-1];
			}
			else{
				dp[x][y] = num[x][y]+dp[x-1][y];
			}
		}
		else{
			dp[x][y] = num[x][y];
		}
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		n = stdIn.nextInt();
		num = new int[n][n];
		dp = new int[n][n];
		ans = 0;
		for(int i=0;i<n;i++){
			for(int j=0;j<=i;j++){
				num[i][j] = stdIn.nextInt();
				DP(i,j);
				ans = Math.max(ans, dp[i][j]);
			}
		}
		System.out.println(ans);
	}
}