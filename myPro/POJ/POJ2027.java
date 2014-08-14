import java.util.Scanner;

public class POJ2027 {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    int a = stdIn.nextInt();
	    int b = stdIn.nextInt();
	    System.out.println(a<b?"NO BRAINS":"MMM BRAINS");
	}
    }
}
