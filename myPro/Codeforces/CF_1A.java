import java.util.Scanner;

/**
 * [問題]
 * n*mの長方形をすべてa*aの正方形でカバーするのに何個正方形が必要か．
 * ただし，正方形は平行に配置しなければならない．
 * また，正方形を切って配置してはいけない．(はみ出しOK)
 */
public class CF_1A {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	int n = stdIn.nextInt();
	int m = stdIn.nextInt();
	int a = stdIn.nextInt();
	System.out.println((long)(Math.ceil((n*1.0/a))*Math.ceil(m*1.0/a)));
    }
}
