import java.util.Scanner;

public class ABC009_B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	int[] x = new int[n];
	for(int i=0;i<n;i++){
	    x[i] = stdIn.nextInt();
	}

	for(int i=0;i<n;i++){
	    int max = x[i];
	    for(int j=i+1;j<n;j++){
		if(max<x[j]){
		    max = x[j];
		    int tmp = x[i];
		    x[i] = x[j];
		    x[j] = tmp;
		}
	    }
	}
	for(int i=1;i<n;i++){
	    if(x[i]!=x[i-1]){
		System.out.println(x[i]);
		break;
	    }
	}
    }
}
