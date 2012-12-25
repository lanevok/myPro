import java.util.Arrays;
import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		int n = stdIn.nextInt();
		String[] dic = new String[n];
		for(int i=0;i<n;i++){
			dic[i] = stdIn.next();
			char[] tmp = dic[i].toCharArray();
			for(int j=0;j<tmp.length/2;j++){
				char ex = tmp[j];
				tmp[j] = tmp[tmp.length-1-j];
				tmp[tmp.length-1-j] = ex;
			}
			dic[i] = String.valueOf(tmp);
		}
		Arrays.sort(dic);
		for(int i=0;i<n;i++){
			char[] tmp = dic[i].toCharArray();
			for(int j=0;j<tmp.length/2;j++){
				char ex = tmp[j];
				tmp[j] = tmp[tmp.length-1-j];
				tmp[tmp.length-1-j] = ex;
			}
			dic[i] = String.valueOf(tmp);
		}
		for(int i=0;i<n;i++){
			System.out.println(dic[i]);
		}
	}
}