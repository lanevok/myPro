import java.util.Scanner;

public class ABC012_B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	System.out.printf("%02d", n/3600);
	System.out.print(":");
	n = n%3600;
	System.out.printf("%02d", n/60);
	System.out.print(":");
	n = n%60;
	System.out.printf("%02d", n);
    }
}
