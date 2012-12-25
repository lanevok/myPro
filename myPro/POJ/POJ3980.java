import java.util.Scanner;

public class POJ3980 {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	while(stdIn.hasNext()){
	    System.out.println(stdIn.nextInt()%stdIn.nextInt());
	}
    }
}
