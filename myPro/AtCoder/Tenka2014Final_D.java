import java.util.Scanner;

/**
 * 部分点のサンプルが通らず
 *
 */
public class Tenka2014Final_D {

    int[] x;
    int n, k;
    int ans;
    int now;

    public static void main(String[] args) {
	new Tenka2014Final_D().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);
	int t = stdIn.nextInt();
	for(int i=0;i<t;i++){
	    n = stdIn.nextInt();
	    k = stdIn.nextInt();
	    x = new int[n];
	    ans = 0;
	    now = 0;
	    func(0);
	    System.out.println(ans);
	}
    }

    private void func(int index) {
	if(index==n){
	    judge();
	    return;
	}
	func(index+1);
	now++;
	if(now>k){
	    now--;
	    return;
	}
	func(index+1);
	now--;
    }

    private void judge() {
	if(now<=k){
	    ans++;
	}
    }
}