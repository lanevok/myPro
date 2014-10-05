import java.util.Scanner;

public class CodeFormula2014Final_A {
    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);
	int a = s.nextInt();
	int b = s.nextInt();
	System.out.println(a*b);
    }
}
/*
  A - Code Formula 2015
  時間制限 : 2sec / スタック制限 : 10MB / メモリ制限 : 64MB

  問題文
  Code Formulaは、非常に大きなコンテストです。 来年の予選では、 A 回の予選で、上位 B 人を本選に招待する予定です。 毎回の予選では参加者の重複がないものとして、合計何人を本選に招待することになるかを出力しなさい。

  入力
  入力は以下の形式で標準入力から与えられる。

  A B
  1 行目には、予選の回数を表す整数 A(1≦A≦1000) 、1 回の予選での予選通過人数を表す整数 B(1≦B≦1000) が、スペース区切りで与えられる。
  出力
  本選の招待人数を 1 行で出力せよ。出力の末尾には改行をいれること。

  入力例1
  1000 1000
  出力例1
  1000000
  来年のCode Formulaは大盛況です。 1,000,000 人で本選を行います。

  入力例2
  1 1
  出力例2
  1
  本選参加者が 1 人しかいない場合でも、本選を行います。
*/