import java.util.Scanner;


public class ARC029_A {

    static int n;
    static int[] a;
    static boolean[] flag;
    static int best = Integer.MAX_VALUE;

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	n = stdIn.nextInt();
	a = new int[n];
	flag = new boolean[n];
	for(int i=0;i<n;i++){
	    a[i] = stdIn.nextInt();
	}
	func(0);
	System.out.println(best);
    }

    private static void func(int i) {
	if(i==n){
	    judge();
	    return;
	}
	flag[i] = true;
	func(i+1);
	flag[i] = false;
	func(i+1);
    }

    private static void judge() {
	int x=0, y=0;
	for(int i=0;i<n;i++){
	    if(flag[i]){
		x += a[i];
	    }
	    else{
		y += a[i];
	    }
	}
	int temp = x>y?x:y;
	best = Math.min(best, temp);
    }
}
