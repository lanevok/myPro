import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int n = stdIn.nextInt();
		int[] a = new int[46];
		a[0] = 1;
		a[1] = 1;
		for(int i=2;i<=n;i++){
			a[i] = a[i-1]+a[i-2];
		}
		System.out.println(a[n]);
	}
}