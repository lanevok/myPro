import java.util.Scanner;

/**
 * 三山崩し(Nim)　というゲーム
 * 小石幾つかが三つの山に分けて積んである。
 * 二人が交互に石を取って、最後に石を取る(相手に取れない状態で渡す)と
 * 勝ちというゲーム。
 * 問題では、山の数は三つに限定しない。
 * 必勝する手の取り方の数を計算する。
 * 
 * XORを利用する必勝法が存在する。
 * 
 * 解法
 * 山の数それぞれでXORをとり、
 * その結果と山とのXORが山の数より小さい場合
 * 必勝する手の取り方がある。
 *
 */
public class POJ2975 {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	while(true){
	    int n =stdIn.nextInt();
	    if(n==0) return;
	    int[] m = new int[n];
	    for(int i=0;i<n;i++){
		m[i] = stdIn.nextInt();
	    }
	    int x = 0;
	    for(int i=0;i<n;i++){
		x = x^m[i];
	    }
	    int cnt = 0;
	    for(int i=0;i<n;i++){
		if((x^m[i])<m[i]){
		    cnt++;
		}
	    }
	    System.out.println(cnt);
	}
    }
}
