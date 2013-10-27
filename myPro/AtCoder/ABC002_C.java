import java.util.Scanner;


public class ABC002_C {
    
	public static void main(String[] args) {
	    Scanner stdIn = new Scanner(System.in);
	    
	    int xa = stdIn.nextInt();
	    int ya = stdIn.nextInt();
	    int xb = stdIn.nextInt();
	    int yb = stdIn.nextInt();
	    int xc = stdIn.nextInt();
	    int yc = stdIn.nextInt();
	    
	    int x1 = xb-xa;
	    int y1 = yb-ya;
	    int x2 = xc-xa;
	    int y2 = yc-ya;
	    
	    double s = Math.abs((x1*y2-x2*y1))/2.0;
	    System.out.println(s);
	}
}
