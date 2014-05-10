import java.util.Scanner;

// 部分点 80点．　TLE or MLE
public class ABC008_D {
    
    static int[] input;
    static int[][] field;
    static int[][] processField;
    static int w, h;
    static int cnt;
    static int max;
    static int n;
    static long start;
    static int[][] c;
    
    public static void main(String[] args) {
	new ABC008_D().run();
	//		System.out.println(System.currentTimeMillis()-start);
    }
    
    private void run() {
	Scanner stdIn = new Scanner(System.in);
	
	
	w = stdIn.nextInt();
	h = stdIn.nextInt();
	
	field = new int[w][h];
	processField = new int[w][h];
	
	for(int i=0;i<w;i++){
	    for(int j=0;j<h;j++){
		field[i][j] = -1;		// 金塊
	    }
	}
	
	n = stdIn.nextInt();
	
	int[] perm = new int[n];
	input = new int[n];
	boolean[] flag = new boolean[n];
	
	c = new int[n][2];
	
	for(int i=1;i<=n;i++){
	    c[i-1] = new int[2];
	    int x = stdIn.nextInt()-1;
	    int y = stdIn.nextInt()-1;
	    field[x][y] = i;		// 装置
	    input[i-1] = i;
	    c[i-1][0] = x;
	    c[i-1][1] = y;
	}
	start = System.currentTimeMillis();
	
	max = 0;
	
	permutation(0, perm, flag);
	
	System.out.println(max);
    }
    
    private void permutation(int idx, int[] perm, boolean[] flag) {
	if(idx==n){
	    copyField();
	    process(perm);
	    if(cnt>max){
		max = cnt;
	    }
	    cnt = 0;
	    return;
	}
	for(int i=0;i<n;i++){
	    if(flag[i]) continue;
	    perm[idx] = input[i];
	    flag[i] = true;
	    permutation(idx+1, perm, flag);
	    flag[i] = false;
	}
    }
    
    private void copyField() {
	for(int i=0;i<w;i++){
	    for(int j=0;j<h;j++){
		processField[i][j] = field[i][j];
	    }
	}
    }
    
    private void process(int[] perm) {
	for(int i=0;i<n;i++){
	    //			if(perm[i]==processField[c[i][0]][c[i][1]]){
	    collect(c[perm[i]-1][0], c[perm[i]-1][1]);
	    //			}
	}
	/*
	  int targetCrane = perm[i];
	  for(int j=0;j<w;j++){
	  for(int k=0;k<h;k++){
	  if(targetCrane==processField[j][k]){
	  collect(j, k);
	  }
	  }
	  }
	  }
	*/
    }
    
    private void collect(int j, int k) {
	cnt++;
	processField[j][k] = 0;
	
	int idx = 0;
	while(true){
	    idx++;
	    int t = j+idx;
	    if(t<w&&processField[t][k]==-1){
		cnt++;
		processField[t][k] = 0;
	    }
	    else break;
	}
	idx = 0;
	while(true){
	    idx--;
	    int t = j+idx;
	    if(t>=0&&processField[t][k]==-1){
		cnt++;
		processField[t][k] = 0;
	    }
	    else break;
	}
	idx = 0;
	while(true){
	    idx++;
	    int t = k+idx;
	    if(t<h&&processField[j][t]==-1){
		cnt++;
		processField[j][t] = 0;
	    }
	    else break;
	}
	idx = 0;
	while(true){
	    idx--;
	    int t = k+idx;
	    if(t>=0&&processField[j][t]==-1){
		cnt++;
		processField[j][t] = 0;
	    }
	    else break;
	}
    }
}