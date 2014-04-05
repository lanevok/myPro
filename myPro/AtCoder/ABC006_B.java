import java.util.Scanner;

public class ABC006_B {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	int[] a = {0,0,0,1};
	if(n==1||n==2){
	    System.out.println(0);
	    System.exit(0);
	}
	else if(n==3){
	    System.out.println(1);
	    System.exit(0);
	}
	int next = 0;
	for(int i=4;i<=n;i++){
	    next = (a[1]+a[2]+a[3])%10007;
	    a[1] = a[2];
	    a[2] = a[3];
	    a[3] = next;
	    // System.out.println("a["+i+"] = "+next);
	}
	System.out.println(a[3]);
    }
}
