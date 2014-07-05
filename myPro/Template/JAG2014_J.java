import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


public class JAG2014_J {

    public static void main(String[] args) throws FileNotFoundException {
	System.setOut(new PrintStream(new File(System.currentTimeMillis()/1000+".txt")));
	Scanner sc = new Scanner(System.in);

	while(true){
	    int a = sc.nextInt();
	    int b = sc.nextInt();
	    if(a==0&&b==0){
		return;
	    }
	    System.out.println(a*b);
	}
    }
}
