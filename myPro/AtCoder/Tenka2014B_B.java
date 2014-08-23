import java.util.Scanner;

// テストケース5でタイムアウト
public class Tenka2014B_B {

    static int n;
    static String[] phrase;

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	n = stdIn.nextInt();
	stdIn.nextLine();
	String target = stdIn.nextLine();
	phrase = new String[n];
	for(int i=0;i<n;i++){
	    phrase[i] = stdIn.nextLine();
	}
	System.out.println(solve(target));
    }

    private static int solve(String target) {
	if(target.equals("")) return 1;
	// System.out.println(target);
	int count = 0;
	for(int i=0;i<n;i++){
	    int ph = phrase[i].length();
	    int ta = target.length();
	    if(ph<=ta&&phrase[i].equals(target.substring(0, ph))){
		count += solve(target.substring(ph, ta));
	    }
	}
	return count%1000000007;
    }
}
