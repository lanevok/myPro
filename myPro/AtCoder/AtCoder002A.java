import java.util.Scanner;

public class AtCoder002A {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int y = stdIn.nextInt();
	boolean f = false;
	int cnt = 0;
	if(y%4==0){
	    cnt++;
	    f = true;
	}
	if(y%100==0){
	    cnt++;
	    f = false;
	}
	if(y%400==0){
	    cnt++;
	    f = true;
	}
	if(cnt==0){
	    f = false;
	}
	System.out.println(f?"YES":"NO");
    }
}
