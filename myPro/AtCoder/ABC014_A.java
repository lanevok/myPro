import java.util.Scanner;


public class ABC014_A {

    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);

	int a = s.nextInt();
	int b = s.nextInt();
	int x = 0;
	while(true){
	    if((a+x)%b==0){
		System.out.println(x);
		return;
	    }
	    x++;
	}
    }
}
