import java.util.Scanner;

public class ABC010_C {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int txa = stdIn.nextInt();
	int tya = stdIn.nextInt();
	int txb = stdIn.nextInt();
	int tyb = stdIn.nextInt();
	int t = stdIn.nextInt();
	int v = stdIn.nextInt();
	int n = stdIn.nextInt();
	boolean yes = false;
	for(int i=0;i<n;i++){
	    int x = stdIn.nextInt();
	    int y = stdIn.nextInt();
	    double d1 = dist(txa, tya, x, y);
	    double d2 = dist(x, y, txb, tyb);
	    double d = d1+d2;
	    if(d<=t*v){
		yes = true;
	    }
	}
	System.out.println(yes?"YES":"NO");
    }

    private static double dist(int txa, int tya, int x, int y) {
	return Math.sqrt((x-txa)*(x-txa)+(y-tya)*(y-tya));
    }
}
