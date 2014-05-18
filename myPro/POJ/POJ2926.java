import java.util.Scanner;

public class POJ2926 {
    double[][] point;		// 座標
    double[][] max;
    int[] maxIndex;
    double[] tmpMax;
    int z;
    
    /**
     * 5次元空間の点がある。
     * 点のマンハッタン距離が最大になる点同士の距離を求める。
     * 
     * 実装は2重のfor分で行うとTLEとなる。
     */
    public static void main(String[] args) {
	new POJ2926().run();
    }
    
    private void run() {
	Scanner stdIn = new Scanner(System.in);
	
	// 入力
	final int N = stdIn.nextInt();
	point = new double[N][];
	for(int i=0;i<N;i++){
	    point[i] = new double[5];
	    for(int j=0;j<5;j++){
		point[i][j] = stdIn.nextDouble();
	    }
	}
	
	// 計算の保存
	max = new double[N][];		// 計算結果の表
	maxIndex = new int[32];		// パターンごとの最大値に当たる点の添え字を保存
	tmpMax = new double[32];		// パターンごとの最大値
	
	// すべての点に対して処理
	for(int i=0;i<N;i++){
	    max[i] = new double[32];	// 計算結果の表の初期化
	    z = 0;	// 結果を格納するためのパターン添え字(初期化)
	    scanningMaxMin(i, 0, 0.0);	// 点を再帰メソッドで計算
	}
	
	// 計算結果の表から最適な値を抽出する
	double ans = 0.0;
	//		for(int i=0;i<N;i++){		// 2重ループによりO(N^2)にならないようにする
	for(int j=0;j<32;j++){
	    int i = maxIndex[j];		// jからiを一意に算出する
	    for(int p=0;p<N;p++){
		if(i!=p){	// 同じ点を選んではいけない
		    double tmp = max[i][j]+max[p][32-j-1];	// パターンが対になってなければいけない
		    // 注) {+,+,+,-,-} : {-,-,-,+,+} ということ
		    
		    // 最大値の更新
		    if(ans<tmp){
			ans = tmp;
		    }
		}
	    }
	}
	//		}
	// 結果の出力
	System.out.printf("%.2f\n",ans);
    }
    
    /**
     * 再帰メソッド。
     * パターンを1つ決めて再帰する。
     * {+,+,+,+,+},{+,+,+,+,-}, ... , {-,-,-,-,-}　32パターン
     */
    private void scanningMaxMin(int i, int index, double value) {
	if(index==5){
	    update(value, i);
	    return;
	}
	value+=point[i][index];	// プラスの決定
	scanningMaxMin(i, index+1, value);
	value-=point[i][index];	// プラスの除去
	value-=point[i][index];	// マイナスの決定
	scanningMaxMin(i, index+1, value);
    }
    
    /**
     * 求めた値を計算結果の表に格納し、
     * パターンの最大値であれば、最大値とその添え字を保存する
     */
    private void update(double value, int i) {
	max[i][z] = value;
	if(tmpMax[z]<value){
	    maxIndex[z] = i;
	    tmpMax[z] = value;
	}
	z++;	// パターンの添え字についてインクリメント
    }
}
