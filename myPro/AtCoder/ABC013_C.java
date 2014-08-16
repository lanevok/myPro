import java.util.Scanner;

/**
 * 100点
 */
public class ABC013_C {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	int h = stdIn.nextInt();
	int a = stdIn.nextInt();
	int b = stdIn.nextInt();
	int c = stdIn.nextInt();
	int d = stdIn.nextInt();
	int e = stdIn.nextInt();

	int minMoney = Integer.MAX_VALUE;

	for(int fu=0;fu<=n;fu++){
	    for(int sh=0;fu+sh<=n;sh++){
		int nu = n-fu-sh;
		if(fu+sh+nu==n){
		    if(h+(fu*b)+(sh*d)-(nu*e)>0){
			// System.out.println(fu+","+sh+","+nu+"="+(fu*a+sh*c));
			minMoney = Math.min(minMoney, fu*a+sh*c);
		    }
		}
	    }
	}
	System.out.println(minMoney);
    }
}
