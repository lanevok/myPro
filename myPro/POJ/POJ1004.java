import java.util.Scanner;

public class POJ1004 {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	double s = 0;
	for(int i=0;i<12;i++){
	    s += stdIn.nextDouble();
	}
	System.out.printf("$%.2f",s/12);
    }
}
