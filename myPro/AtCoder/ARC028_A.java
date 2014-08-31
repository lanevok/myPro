import java.util.Scanner;


public class ARC028_A {

    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);

	int n = s.nextInt();
	int a = s.nextInt();
	int b = s.nextInt();
	while(true){
	    n-=a;
	    if(n<=0){
		System.out.println("Ant");
		break;
	    }
	    n-=b;
	    if(n<=0){
		System.out.println("Bug");
		break;
	    }
	}
    }
}
