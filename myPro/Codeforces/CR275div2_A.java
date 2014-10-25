import java.util.Scanner;

/**
 * [問題]
 * 1<=l<=a<b<c<=r の条件のもとで，
 * aとb，bとcが互いに素(最大公約数が1である)であり，
 * aとcが互いに素ではない，aとbとcの組み合わせを1つ挙げる．
 * もしそのような組み合わせがなければ，-1を出力．
 *
 */
public class CR275div2_A {

    private static long gcd(long a, long b){
	if(b==0){
	    return a;
	}
	else{
	    return gcd(b, a%b);
	}
    }

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	long l = stdIn.nextLong();
	long r = stdIn.nextLong();
	for(long a=l;a<=r;a++){
	    for(long b=a+1;b<=r;b++){
		for(long c=b+1;c<=r;c++){
		    // System.out.println("process: "+a+","+b+","+c);
		    judge(a,b,c);
		}
	    }
	}
	System.out.println("-1");
    }

    private static void judge(long a, long b, long c) {
	long ab = gcd(a, b);
	// System.out.println("ab="+ab);
	if(ab!=1) return;
	long ac = gcd(a, c);
	// System.out.println("ac="+ac);
	if(ac==1) return;
	long bc = gcd(b, c);
	// System.out.println("bc="+bc);
	if(bc!=1) return;
	System.out.println(a+" "+b+" "+c);
	System.exit(0);
    }
}
