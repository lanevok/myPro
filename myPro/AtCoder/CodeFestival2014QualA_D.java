import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class CodeFestival2014QualA_D {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int a = stdIn.nextInt();
	int k = stdIn.nextInt();
	int s=a, b=a;
	while(true){
	    if(judge(s, k)||judge(b, k)){
		System.out.println(a-s);
		break;
	    }
	    s--;
	    b++;
	}
    }

    private static boolean judge(int s, int k) {
	String str = String.valueOf(s);
	Set<Integer> set = new HashSet<Integer>();
	for(int i=0;i<str.length();i++){
	    set.add(str.charAt(i)-'0');
	    if(set.size()>k) return false;
	}
	return true;
    }
}