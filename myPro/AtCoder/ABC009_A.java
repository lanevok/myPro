import java.util.Scanner;

public class ABC009_A {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	System.out.println((int)Math.ceil(n*1.0/2));
    }
}
