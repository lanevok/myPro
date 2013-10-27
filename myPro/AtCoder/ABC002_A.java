import java.util.Scanner;


public class ABC002_A {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int x = stdIn.nextInt();
	int y = stdIn.nextInt();
	if(x>y){
	    System.out.println(x);
	}
	else{
	    System.out.println(y);
	}
    }
}
