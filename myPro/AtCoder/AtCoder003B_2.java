import java.util.Arrays;
import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
 
		int n = stdIn.nextInt();
		String[] dic = new String[n];
		for(int i=0;i<n;i++){
			StringBuffer tmp = new StringBuffer(stdIn.next());
			tmp.reverse();
			dic[i] = tmp.toString();
		}
		Arrays.sort(dic);
		for(int i=0;i<n;i++){
			StringBuffer tmp = new StringBuffer(dic[i]);
			tmp.reverse();
			System.out.println(tmp);
		}
	}
}