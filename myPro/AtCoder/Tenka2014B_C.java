import java.util.Scanner;

// 部分25点
// テストケース3でタイムアウト
public class Tenka2014B_C3 {

    static int n;
    static boolean[][] before;
    static boolean[][] after;
    static long start;
    static int cnt = 0;

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	n = stdIn.nextInt();
	stdIn.nextLine();
	after = new boolean[n][n];
	before = new boolean[n][n];
	for(int i=0;i<n;i++){
	    char[] line = stdIn.nextLine().toCharArray();
	    for(int j=0;j<n;j++){
		if(line[j]=='#'){
		    after[i][j] = true;
		}
	    }
	}
	start = System.currentTimeMillis();
	backtrack(0);
    }

    private static void backtrack(int x) {
	if(!superCheck(x-1)) return;
	if(x==n*n){
	    check();
	    return;
	}
	// System.out.println((++cnt)+","+x/n+","+x%n);
	before[x/n][x%n] = true;
	backtrack(x+1);
	before[x/n][x%n] = false;
	backtrack(x+1);
    }

    private static boolean superCheck(int x) {
	int i = (x/n)-1;
	int j = (x%n)-1;
	if(i>=0&&j>=0){
	    if(!superIsEqual(i, j)){
		return false;
	    }
	}
	return true;
    }

    private static boolean superIsEqual(int x, int y) {
	int ex = Math.max(0, x-3);
	int ey = Math.max(0, y-3);
	for(int i=x-1;i>=ex;i--){
	    for(int j=y-1;j>=ey;j--){
		boolean flag = false;
		if(countBlack(i, j)%2!=0){
		    flag = true;
		}
		if(flag!=after[i][j]){
		    return false;
		}
	    }
	}
	return true;
    }

    private static void check() {
	if(isEqual()){
	    output();
	}
    }

    private static void output() {
	for(int i=0;i<n;i++){
	    for(int j=0;j<n;j++){
		if(before[i][j]){
		    System.out.print("#");
		}
		else{
		    System.out.print(".");
		}
	    }
	    System.out.println();
	}
	// System.out.println(System.currentTimeMillis()-start);
	System.exit(0);
    }

    private static boolean isEqual() {
	for(int i=n-1;i>=0;i--){
	    for(int j=n-1;j>=0;j--){
		boolean flag = false;
		if(countBlack(i, j)%2!=0){
		    flag = true;
		}
		if(flag!=after[i][j]){
		    return false;
		}
	    }
	}
	return true;
    }

    private static int countBlack(int i, int j) {
	int cnt = 0;
	if(inMat(i-1)&&before[i-1][j]){
	    cnt++;
	}
	if(inMat(j-1)&&before[i][j-1]){
	    cnt++;
	}
	if(inMat(i+1)&&before[i+1][j]){
	    cnt++;
	}
	if(inMat(j+1)&&before[i][j+1]){
	    cnt++;
	}
	return cnt;
    }

    private static boolean inMat(int i) {
	if(i>=0&&i<n) return true; else return false;
    }
}
