import java.util.Arrays;
import java.util.Scanner;

// Hit & Blow Game Analysis
public class Hit_Blow {
    
    static int input_number, input_hit, input_blow;     // 思考番号,応答ヒット(Eat),応答ブロー(Bite)
    static int[] number, target;                        // 思想番号配列,探索番号配列
    static boolean[] data;                              // 候補番号配列
    static boolean[] next_data;                         // 最善思考番号配列
    static int possible;                                // 候補数
    static int next_number;				// 最善思考番号
    static float next_expect;				// 最善思考番号に対しての期待値
    static int next_denom;				// 最善思考番号の応答種類数
    
    // 番号を配列に変換
    static int[] trans(int n){
	// int型の整数nを受け取り、int型配列aに数を設定し返す。
	// a[0]=千の位, a[1]=百の位, a[2]=十の位, a[3]=一の位
	int[] a = new int[4];
	int tmp = n;
	a[0]=tmp/1000;
	tmp-=a[0]*1000;
	a[1]=tmp/100;
	tmp-=a[1]*100;
	a[2]=tmp/10;
	a[3]=tmp%10;
	return a;
    }
    
    // もし番号nをコールしhのヒット,bのblowが返った場合
    // 絞れた候補数を返す。
    static int search(int n, int h, int b){
	
	//		System.out.println();
	//		System.out.print("search "+n+" "+h+" "+b);
	
	int cnt = 0;		// 候補が消せた数
	// dataのコピー(元を破壊してはならない)
	next_data = new boolean[10000];
	for(int i=0;i<10000;i++){
	    next_data[i]=data[i];
	}
	for(int k=0;k<10000;k++){
	    // 候補の数で同じ数をコールしない場合のループ
	    if(next_data[k]==false||k==n){
		continue;
	    }
	    // ループさせているkをtargetとして扱う
	    target=trans(k);
	    number=trans(n);
	    int hit=0, blow=0;
	    for(int i=0;i<4;i++){
		if(number[i]==target[i]){
		    hit++;
		}
	    }
	    for(int i=0;i<4;i++){
		for(int j=0;j<4;j++){
		    if(i!=j&&number[i]==target[j]){
			blow++;
		    }
		}
	    }
	    if(h!=hit||b!=blow){
		
		//				System.out.print(" x"+k);
		
		next_data[k]=false;
		cnt++;
	    }
	    else{
		
		//				System.out.print(" o"+k);
		
	    }
	}
	
	//		System.out.print("  p-c= "+(possible-cnt-1));
	
	// あり得るhit&blow値であったかの判定
	// 可能性がすべて潰れてないか
	if(possible-cnt-1>0){
	    next_denom++;
	}
	return possible-cnt-1;
    }
    
    // 次の思考する最善の番号探索
    static void predict(int n){
	float sum = 0;
	next_denom = 0;
	// 候補がsearchによる指定したhit&blow数で
	// いくつ候補まで候補が絞れたかをsumに足しこむ。
	// あり得ないhit&blow数がされた場合のsearch返り値は0
	for(int i=0;i<3;i++){
	    for(int j=0;i+j<5;j++){
		sum+=search(n,i,j);
	    }
	}
	sum+=search(n,3,0);
	sum+=search(n,4,0);
	// 期待値の計算(確率ではなく平均値で算出)
	sum/=next_denom;
	
	//		System.out.println(" sum="+sum);
	
	// 最善の更新
	if(next_expect>sum){
	    next_expect=sum;
	    next_number=n;
	}
    }
    
    // メインメソッド
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	// 初期化
	target = new int[4];
	possible=10000;
	data = new boolean[10000];
	Arrays.fill(data, true);
	// 0000～9999を候補としてtrueにし、
	// 重複して数字を使用してるものをfalseに設定
	for(int i=0;i<10000;i++){
	    target=trans(i);
	    for(int j=0;j<4;j++){
		for(int k=0;k<4;k++){
		    if(j!=k&&target[j]==target[k]){
			if(data[i]==true){
			    data[i]=false;
			    possible--;
			}
		    }
		}
	    }
	}
	// ここで、possibleは5040となる。
	while(true){
	    // ここで「数字」「ヒット数」「ブロー数」を入力として受け取る。
	    input_number = stdIn.nextInt();
	    number = new int[4];
	    number=trans(input_number);
	    input_hit = stdIn.nextInt();
	    input_blow = stdIn.nextInt();
	    // 4Hit で終了
	    if(input_hit==4){
		System.out.println("正解番号 = "+input_number);
		return;
	    }
	    for(int k=0;k<10000;k++){
		// k=0000～9999のtrueだけ実行
		if(data[k]==false){
		    continue;
		}
		// 入力数とターゲット(k)を比較し、hitとblow数の判定をする。
		target=trans(k);
		int hit=0, blow=0;
		for(int i=0;i<4;i++){
		    if(number[i]==target[i]){
			hit++;
		    }
		}
		for(int i=0;i<4;i++){
		    for(int j=0;j<4;j++){
			if(i!=j&&number[i]==target[j]){
			    blow++;
			}
		    }
		}
		// 判定したhitとblow数が入力と一致しないのであれば
		// 数字kは候補としては消去できる。
		if(input_hit!=hit||input_blow!=blow){
		    data[k]=false;
		    possible--;
		}
	    }
	    // for文の遷移後、候補が0となる異常が発生した場合
	    if(possible<=0){
		System.out.println("Error!");
		return;
	    }
	    // 候補数を出力
	    System.out.print("候補数 = "+possible);
	    // 候補数1のときの終了処理
	    if(possible==1){
		System.out.println();
		for(int i=0;i<10000;i++){
		    if(data[i]==true){
			System.out.println("正解番号 = "+i);
			return;
		    }
		}
	    }
	    // 候補数字の出力(10以下の時)ならびに次コールの最善番号探索
	    System.out.print(" ... ");
	    next_expect=possible;
	    for(int i=0;i<10000;i++){
		if(data[i]==true){
		    if(possible<=10){
			System.out.print("["+i+"] ");
		    }
		    predict(i);
		}
	    }
	    // 次コールの最善番号出力
	    System.out.println("(Call: "+next_number+" )");
	}	
    }
}
