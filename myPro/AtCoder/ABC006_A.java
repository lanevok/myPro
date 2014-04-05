import java.util.Scanner;

public class ABC006_A {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	int n = stdIn.nextInt();
	if(n==3||n==6||n==9){
	    System.out.println("YES");
	}
	else{
	    System.out.println("NO");
	}
    }
}
