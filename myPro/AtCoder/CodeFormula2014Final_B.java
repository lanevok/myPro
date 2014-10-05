import java.math.BigInteger;
import java.util.Scanner;

public class CodeFormula2014Final_B {

    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);

	BigInteger n = new BigInteger(s.next());
	BigInteger cnt = n.divide(new BigInteger("2"));
	if(n.remainder(new BigInteger("2")).equals(new BigInteger("1"))){
	    cnt = cnt.add(new BigInteger("3"));
	}
	System.out.println(cnt);
    }
}
/*
  B - 3歩進んで2歩下がる
  時間制限 : 2sec / スタック制限 : 10MB / メモリ制限 : 64MB

  問題文
  高橋君は、奇数日目に 3 歩前に進み、偶数日目に 2 歩後ろに下がります。

  1 歩の距離は、前に進むときも、後ろに下がるときも、同じ距離です。

  1 日目から n 日目の間に、高橋君が何歩分前に進んだかを出力してください。

  入力
  入力は以下の形式で標準入力から与えられる。

  n
  1 行目には、日数を表す整数 n(1≦n≦1015) が与えられる。
  出力
  高橋君が何歩分前に進んだかを 1 行で出力せよ。出力の末尾には改行をいれること。

  入力例1
  6
  出力例1
  3
  高橋君は、1, 3, 5 日目に 3 歩進み、2, 4, 6 日目に 2 歩戻ります。 よって、3−2+3−2+3−2=3 歩分、前に進みます。

  入力例2
  999999999999999
  出力例2
  500000000000002
  高橋君は、どれだけ長い期間でも、 3 歩進んで 2 歩戻り続けます。
*/