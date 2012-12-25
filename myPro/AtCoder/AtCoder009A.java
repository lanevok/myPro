import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	int n = stdIn.nextInt();
	double sum = 0;
	for(int i=0;i<n;i++){
	    sum += stdIn.nextInt()*stdIn.nextInt();
	}
	sum = sum*1.05;
	sum = Math.floor(sum);
	System.out.printf("%d\n",(int)sum);
    }
}