import java.util.Arrays;
import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		int n = stdIn.nextInt();
		int[] a = new int[10];
		for(int i=0;i<10;i++){
			a[i] = stdIn.nextInt();
		}
		Arrays.sort(a);
		int[][]c = new int[100][7];
		stdIn.nextLine();
		for(int i=0;i<n;i++){
			char[] t = stdIn.nextLine().toCharArray();
			for(int j=0;j<7;j++){
				if(t[j]=='-'){
					c[i][j] = 1;
				}
				else{
					c[i][j] = 2;
				}
			}
		}
		int[] d = new int[7];
		for(int i=0;i<7;i++){
			int cnt = 0;
			for(int j=0;j<n;j++){
				if(c[j][i]==2){
					cnt++;
					d[i] = Math.max(cnt, d[i]);
				}
				else{
					cnt = 0;
				}
			}
		}
		Arrays.sort(d);
		for(int i=9;i>=3;i--){
			if(a[i]<d[i-3]){
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
}