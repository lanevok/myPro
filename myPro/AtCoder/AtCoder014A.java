import java.util.Scanner;

public class AtCoder014A {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	int a = n%2;
	if(a==1){
	    System.out.println("Red");
	}
	else{
	    System.out.println("Blue");
	}
    }
}
