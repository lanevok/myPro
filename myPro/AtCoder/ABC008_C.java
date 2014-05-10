import java.util.Scanner;

// 部分点　99点．　TLE
public class ABC008_C {
    static int[] input;
    static int numerator;
    static int denominator;
    
    public static void main(String[] args) {
	new ABC008_C().run();
    }
    
    private void run() {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	input = new int[n];
	
	for(int i=0;i<n;i++){
	    input[i] = stdIn.nextInt();
	}
	
	boolean[] flag = new boolean[n];
	int[] coin = new int[n];
	denominator = 0;
	permutation(0, coin, flag);
	
	//		System.out.println(numerator+"\t"+denominator);
	System.out.printf("%.12f\n", numerator*1.0/denominator);
    }
    
    private void permutation(int idx, int[] perm, boolean[] flag) {
	if(idx==perm.length){
	    //			output(perm);
	    denominator++;
	    process(perm);
	    return;
	}
	for(int i=0;i<perm.length;i++){
	    if(flag[i]) continue;
	    perm[idx] = input[i];
	    flag[i] = true;
	    permutation(idx+1, perm, flag);
	    flag[i] = false;
	}
    }
    
    private void output(int[] perm) {
	System.out.print("[\t");
	for(int value : perm){
	    System.out.print(value+"\t");
	}
	System.out.println("]");
    }
    
    private void process(int[] perm) {
	boolean[] viewBack = new boolean[perm.length];
	for(int i=0;i<perm.length;i++){
	    int value = perm[i];
	    for(int j=i+1;j<perm.length;j++){
		if(perm[j]%value==0){
		    if(viewBack[j]){
			viewBack[j] = false;
		    }
		    else{
			viewBack[j] = true;
		    }
		}
	    }
	}
	int cnt = 0;
	for(int i=0;i<perm.length;i++){
	    if(!viewBack[i]) cnt++;
	}
	//		System.out.print("data \t");
	//		output(perm);
	//		System.out.println(cnt);
	numerator += cnt;
    }
}
