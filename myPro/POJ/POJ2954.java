import java.util.Scanner;

/**
 * 3点のx座標とy座標が与えられる．
 * 3点から生成できる三角形の内部にできる格子点(x座標,y座標とも整数)の個数を求める．
 *
 *
 * 解法
 * ピックの定理を用いる．
 * (面積) = (まわりの点の数)/2 + (内部の点の数) －1　を変形して，
 * (格子点の数)=(面積)－(返上の点の数)/2 + 1　となる．
 *
 * (返上の点の数)は辺の端点をa,bとすると，
 * gcd(abs(a.x－b.x), abs(a.y－b.y))－1
 * で求まる．gcdは最大公約数，absは絶対値である．
 * 面積に関してはヘロンの公式を用いればよい．
 *
 */
public class POJ2954 {

    public static void main(String[] args) {
	new POJ2954().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	while(true){
	    int x1 = stdIn.nextInt();
	    int y1 = stdIn.nextInt();
	    int x2 = stdIn.nextInt();
	    int y2 = stdIn.nextInt();
	    int x3 = stdIn.nextInt();
	    int y3 = stdIn.nextInt();
	    if((x1|y1|x2|y2|x3|y3)==0) return;

	    double a = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	    double b = Math.sqrt((x3-x1)*(x3-x1)+(y3-y1)*(y3-y1));
	    double c = Math.sqrt((x3-x2)*(x3-x2)+(y3-y2)*(y3-y2));
	    double t = (a+b+c)/2;
	    double s = Math.sqrt(t*(t-a)*(t-b)*(t-c));

	    int ka = gcd(Math.abs(x2-x1), Math.abs(y2-y1))-1;
	    int kb = gcd(Math.abs(x3-x1), Math.abs(y3-y1))-1;
	    int kc = gcd(Math.abs(x3-x2), Math.abs(y3-y2))-1;

	    int kt = ka+kb+kc;
	    System.out.println((int)(s-(kt*1.0/2)));
	}
    }

    private int gcd(int m, int n) {
	return n==0 ? m : gcd(n, m % n);
    }
}
