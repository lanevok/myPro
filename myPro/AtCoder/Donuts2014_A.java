import java.util.Scanner;


public class Donuts2014_A {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	int sum = 0;
	int a = stdIn.nextInt();
	for(int i=0;i<n-1;i++){
	    int b = stdIn.nextInt();
	    if(i%2==0) sum += b-a;
	    a = b;
	}
	System.out.println(n%2==0?sum:"error");
    }
}
