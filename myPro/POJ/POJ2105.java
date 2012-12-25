import java.util.Scanner;

public class POJ2105 {
    
    static char[] c;
    
    static void Sum(int d){
	int s = 0;
	s += (c[d]-'0')*128;
	s += (c[d+1]-'0')*64;
	s += (c[d+2]-'0')*32;
	s += (c[d+3]-'0')*16;
	s += (c[d+4]-'0')*8;
	s += (c[d+5]-'0')*4;
	s += (c[d+6]-'0')*2;
	s += c[d+7]-'0';
	System.out.print(s);
    }
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    c = stdIn.next().toCharArray();
	    for(int j=0;j<3;j++){
		Sum(j*8);
		System.out.print(".");
	    }
	    Sum(24);
	    System.out.println();
	}
    }
}
