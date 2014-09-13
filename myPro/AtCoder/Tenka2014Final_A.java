import java.util.Scanner;

/**
 * 部分点
 *
 */
public class Tenka2014Final_A {

    static int h, n, w;

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	h = stdIn.nextInt();
	n = stdIn.nextInt();
	w = stdIn.nextInt();
	int[] x = new int[4];
	int count = 0;
	for(int i=1;i<36;i++){
	    x[0] = i;
	    for(int j=0;j<36;j++){
		x[1] = j;
		for(int k=0;k<36;k++){
		    x[2] = k;
		    for(int m=0;m<36;m++){
			x[3] = m;
			if(judge(x)){
			    count++;
			}
		    }
		}
	    }
	}
	System.out.println(count);
    }

    private static boolean judge(int[] x) {
	int[] c = new int[36];
	int max = 0;
	for(int i=w;i<4;i++){
	    if(x[i]!=0) return false;
	}
	for(int i=0;i<w;i++){
	    if(x[i]>=h) return false;
	    c[x[i]]++;
	    max = Math.max(max, c[x[i]]);
	}
	if(max<=n) return true; else return false;
    }
}
