import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	char[] a = stdIn.next().toCharArray();
	char[] b = stdIn.next().toCharArray();
	int sum = 0;
	for(int i=0;i<a.length;i++){
	    for(int j=0;j<b.length;j++){
		sum+=(a[i]-'0')*(b[j]-'0');
	    }
	}
	System.out.println(sum);
    }
}
