import java.util.Arrays;
import java.util.Scanner;

public class AtCoder001A {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	String s = stdIn.nextLine();
	s = stdIn.nextLine();
	char[] a = s.toCharArray();
	int[] b = new int[4];
	for(int i=0;i<n;i++){
	    b[a[i]-'0'-1]++;
	}
	Arrays.sort(b);
	System.out.println(b[3]+" "+b[0]);
    }
}
