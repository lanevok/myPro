import java.util.Scanner;


public class CodeFestival2014QualA_B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	String a = stdIn.next();
	stdIn.nextLine();
	int b = stdIn.nextInt();
	int len = a.length();
	int ans = b%len-1;
	if(ans==-1) ans += len;
	System.out.println(a.charAt(ans));
    }
}
