import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	while(stdIn.hasNext()){
	    System.out.println(stdIn.nextLine().replaceAll("you", "we"));
	}
    }
}
