import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;


public class CodeFormula2014Final_C {

    public static void main(String[] args) {
	new CodeFormula2014Final_C().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	char[] line = stdIn.nextLine().toCharArray();
	StringBuilder sb = new StringBuilder();
	for(int i=0;i<line.length;i++){
	    if(line[i]=='@'){
		sb.append(" ");
	    }
	    sb.append(line[i]);
	}
	String line2 = sb.toString();
	String[] word = line2.split(" ");

	HashSet<String> set = new HashSet<String>();
	for(int i=0;i<word.length;i++){
	    if(word[i].length()>=1&&word[i].charAt(0)=='@'){
		String temp = word[i].substring(1);
		if(temp.length()>=1&&temp.charAt(0)>='a'&&temp.charAt(0)<='z'){
		    set.add(temp);
		}
	    }
	}
	ArrayList<String> list = new ArrayList<String>();
	for(String ok : set){
	    list.add(ok);
	}
	Collections.sort(list);
	for(String ok : list){
	    System.out.println(ok);
	}
    }
}
/*
  C - 次世代SNS
  時間制限 : 2sec / スタック制限 : 10MB / メモリ制限 : 64MB

  問題文
  あなたは、とあるSNSを作ろうとしています。

  このSNSでは、@usernameという形式で、特定のユーザーにメッセージを送ることが可能であり、1 つの発言に複数のユーザーを指定することで、複数のユーザーに同時にメッセージを送ることが可能になります。

  このSNSは、以下のようなルールに従っています。

  書き込まれるメッセージは、半角小文字アルファベット、半角スペース、@のみを含む。
  書き込まれたメッセージに@が含まれていた場合、@直後の、アルファベットのみで構成される文字列のうち、最も長い文字列をユーザー名として扱い、そのユーザーにメッセージを届ける。
  @の直後がアルファベットでなかった場合は無視する。
  複数回同じユーザーが指定されても、メッセージは 1 回届ければ良い。
  あなたは、このシステムを実装するために、書き込まれたメッセージに対し、メッセージを届けるべきユーザーを列挙するプログラムを作りたいです。

  メッセージを送るべきユーザーを全て出力しなさい。なお、ユーザーが複数いる場合は、辞書順で出力しなさい。

  入力
  入力は以下の形式で標準入力から与えられる。

  S
  1 行目には、書き込まれるメッセージを表す文字列 S(1≦|S|≦140) が与えられる。
  文字列 S には、小文字アルファベット、 、@以外の文字は含まれない。
  出力
  メッセージを送るべきユーザーを、 1 行ずつ全て出力しなさい。なお、ユーザーが複数いる場合は、辞書順で出力しなさい。

  入力例1
  @codeformula why is this contest so easy
  出力例1
  codeformula
  codeformulaさんへのメッセージです。半角スペースが挟まれているので、why以降をユーザー名として認識することはありません。

  また、@を出力する必要はありません。

  入力例2
  myon @@c @a @aba@a @@bb bbb @@@@@ @a test  @ space  aaa test @a@a  test@takoyaki
  出力例2
  a
  aba
  bb
  c
  takoyaki
  aが何度も指定されていますが、一度だけ出力する必要があります。

  また、ユーザ名は辞書順で出力する必要があります。

  入力例3
  no atmark
  出力例3

  メッセージを送るべきユーザーがいない場合、何も出力しないで構いません。
*/