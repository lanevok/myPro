import java.util.Scanner;

public class AtCoder011A {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int m = stdIn.nextInt();
	int sn = stdIn.nextInt();
	int ln = stdIn.nextInt();
	
	int sum = 0;
	int get = 0;
	while(true){
	    sum += ln;
	    get += ln;
	    if(get/m==0){
		break;
	    }
	    ln = get/m*sn;
	    get -= get/m*m;
	}
	System.out.println(sum);
    }
}
