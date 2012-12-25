import java.util.Arrays;
import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		boolean[] r = new boolean[128];
		Arrays.fill(r, true);
		r[0] = false;
		
		int a = stdIn.nextInt();
		int b = stdIn.nextInt();
		int c = stdIn.nextInt();
		
		for(int i=0;i<128;i++){
			if(i%3!=a){
				r[i] = false;
			}
			if(i%5!=b){
				r[i] = false;
			}
			if(i%7!=c){
				r[i] = false;
			}
			if(r[i]){
				System.out.println(i);
			}
		}		
	}
}