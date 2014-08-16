import java.util.Scanner;


public class ABC013_B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int a = stdIn.nextInt();
	int b = stdIn.nextInt();
	int cnt1 = 0;
	int xa = a;
	while(true){
	    xa++;
	    xa %= 10;
	    cnt1++;
	    if(xa==b){
		break;
	    }
	}
	int ya = a;
	int cnt2 = 0;
	while(true){
	    ya--;
	    if(ya==-1){
		ya = 9;
	    }
	    cnt2++;
	    if(ya==b){
		break;
	    }
	}
	System.out.println(Math.min(cnt1, cnt2));
    }
}
