import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    int k = stdIn.nextInt();
	    int s = 0;
	    for(int j=0;j<k;j++){
		s += stdIn.nextInt();
	    }
	    System.out.println(s-(k-1));
	}
    }
}
