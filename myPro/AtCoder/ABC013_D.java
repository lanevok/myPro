import java.util.Scanner;

// 30点
public class ABC013_D {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	int m = stdIn.nextInt();
	int d = stdIn.nextInt();
	int[] c = new int[m];
	for(int i=0;i<m;i++){
	    c[i] = stdIn.nextInt();
	}

	int[] idx = new int[n+1];
	for(int i=1;i<=n;i++){
	    idx[i] = i;
	}
	for(int dd=0;dd<d;dd++){
	    // System.out.println("dd="+dd);
	    for(int i=0;i<m;i++){
		int left_idx = c[i];
		int right_idx = c[i]+1;
		int left = idx[left_idx];
		int right = idx[right_idx];
		idx[left_idx] = right;
		idx[right_idx] = left;
		// System.out.println("idx["+left_idx+"] = "+right);
		// System.out.println("idx["+right_idx+"] = "+left);
		// System.out.println();
		// for(int j=1;j<=n;j++){
		//   System.out.println(idx[j]);
		// }
		// System.out.println("---");
	    }
	}
	int[] idx2 = new int[n+1];
	for(int i=1;i<=n;i++){
	    idx2[idx[i]] = i;
	    // System.out.println(i+"="+idx[i]);
	}
	for(int i=1;i<=n;i++){
	    System.out.println(idx2[i]);
	}
    }
}
