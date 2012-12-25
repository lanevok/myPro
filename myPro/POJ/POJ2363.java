import java.util.Scanner;

public class Main {
    
    /* POJ2363
       一辺が1の立方体の個数を入力として受け取り、立方体を並べ積み上げる。
       縦*横*高さがその個数となるとき(大きい直方体として欠けない)の
       最小となる表面積を出力するプログラム(ラッピング問題)
       最初に与えられる整数はデータセット数。
    */
    
    
    static int minArea(int a, int b, int c){
	return (a*b*2)+(a*c*2)+(b*c*2);
    }
    
    static int search(int n){
	int result = -1;
	for(int a=1;a<=n;a++){
	    for(int b=1;b<=n;b++){
		int c;
		if(n%a==0){
		    c=n/a;
		    if(c%b==0){
			c=c/b;
			if(c==0){
			    continue;
			}
			else{
			    int tmp=minArea(a,b,c);
			    if(result==-1||result>tmp){
				result=tmp;
			    }
			}
		    }
		}
	    }
	}
	return result;
    }
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    int a = stdIn.nextInt();
	    System.out.println(search(a));
	}
    }
}
