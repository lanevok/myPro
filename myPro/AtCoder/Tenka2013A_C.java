import java.util.Scanner;

public class Main {
    static int[][] map;
    static int m,n;
    static int cnt;
    
    public static void main(String[] args) {
	input();
	process();
	//	System.out.println("cnt: "+cnt);
	System.out.println(cnt);
    }
    
    private static void process() {
	//	System.out.println("call");
	// セル出力
	//	output();
	// 空き場所見つける
	int i=-1, j=-1;
	boolean flag = true;
	A:
	for(i=0;i<m;i++){
	    for(j=0;j<n;j++){
		if(map[i][j]==0){
		    flag = false;
		    break A;
		}
	    }
	}
	// 空き場所なし
	if(flag){
	    //	System.out.println("cnt++&return");
	    cnt++;
	    return;
	}
	// 埋めて
	for(int x=1;x<=3;x++){
	    //	System.out.println("map["+i+"]["+j+"] = "+x);
	    map[i][j] = x;
	    // エラーチェックし
	    if(errorJudge(i,j)){
		continue;
	    }
	    // 再帰
	    process();
	}
	// 再帰抜ける際に元に戻す　消す
	map[i][j] = 0;
    }
    
    private static void output() {
	System.out.println("---start--------------------------");
	for(int i=0;i<m;i++){
	    for(int j=0;j<n;j++){
		System.out.print(map[i][j]+" ");
	    }
	    System.out.println();
	}
	System.out.println("---end--------------------------");
    }
    
    private static boolean errorJudge(int i, int j) {
	//	for(int i=0;i<m;i++){
	//		for(int j=0;j<n;j++){
	int target = map[i][j];
	for(int x=i-target;x<=i+target;x++){
	    int y = j;
	    // targetセル除外
	    if(x==i&&y==j){
		continue;
	    }
	    // 配列外参照
	    if(x<0||x>=m){
		continue;
	    }
	    // 範囲内同一数字設定判定
	    if(target==map[x][y]&&map[x][y]!=0){
		//	System.out.println("error");
		return true;	//エラー
	    }
	}
	for(int y=j-target;y<=j+target;y++){
	    int x = i;
	    // targetセル除外
	    if(x==i&&y==j){
		continue;
	    }
	    // 配列外参照
	    if(y<0||y>=n){
		continue;
	    }
	    // 範囲内同一数字設定判定
	    if(target==map[x][y]&&map[x][y]!=0){
		//	System.out.println("error");
		return true;	//エラー
	    }
	}
	//		}
	//	}
	//	System.out.println("ok");
	return false;
    }
    
    private static void input() {
	Scanner stdIn = new Scanner(System.in);
	m = stdIn.nextInt();
	n = stdIn.nextInt();
	map = new int[m][n];
	for(int i=0;i<map.length;i++){
	    map[i] = new int[n];
	}
    }
}