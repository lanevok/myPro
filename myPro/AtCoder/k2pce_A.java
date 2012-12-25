import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		int a = stdIn.nextInt();
		int b = stdIn.nextInt();
		int c = stdIn.nextInt();
		int n = stdIn.nextInt();
		int ta = n;
		int tb = n*2;
		int tc = n*3;
		int pa = Math.max(0, ta-a);
		int pb = Math.max(0, tb-b);
		int pc = Math.max(0, tc-c);
		System.out.println(pa+" "+pb+" "+pc);
	}
}