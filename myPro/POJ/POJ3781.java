import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    int c = stdIn.nextInt();
	    int[] a = new int[10];
	    for(int j=0;j<10;j++){
		a[j] = stdIn.nextInt();
	    }
	    Arrays.sort(a);
	    System.out.println(c+" "+a[7]);
	}
    }
}
