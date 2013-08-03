import java.util.Scanner;

public class tenka1_2013q_A {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = 42;
	while(true){
	    if(n>130000000){
		break;
	    }
	    n*=2;
	}
	System.out.println(n);
    }
}
