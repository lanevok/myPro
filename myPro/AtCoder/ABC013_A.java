import java.util.Scanner;

public class ABC013_A {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	char[] x = stdIn.next().toCharArray();
	System.out.println((x[0]-'A')+1);
    }
}
