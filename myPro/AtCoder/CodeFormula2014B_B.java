import java.util.Scanner;

public class B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	String n = stdIn.nextLine();
	int kiCount = 0;
	int guCount = 0;
	int index = 1;
	for(int i=0;i<n.length();i++){
	    int c = n.charAt(n.length()-i-1)-'0';
	    // System.out.println(c);
	    if(index++%2==0){
		guCount += c;
	    }
	    else{
		kiCount += c;
	    }
	}
	System.out.println(guCount+" "+kiCount);
    }
}
