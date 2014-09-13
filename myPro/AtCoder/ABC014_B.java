import java.util.Scanner;


public class ABC014_B {

    public static void main(String[] args) {
	Scanner s = new Scanner(System.in);

	int n = s.nextInt();
	int x = s.nextInt();
	int[] a = new int[n];
	for(int i=0;i<n;i++){
	    a[n-1-i] = s.nextInt();
	}
	int sum = 0;
	String b = Integer.toBinaryString(x);
	while(true){
	    if(b.length()!=n){
		b = "0"+b;
	    }
	    else{
		break;
	    }
	}
	for(int i=0;i<n;i++){
	    if(b.substring(i, i+1).equals("1")){
		sum += a[i];
	    }
	}
	System.out.println(sum);
    }
}
