import java.util.Scanner;

public class POJ2845 {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    char[] a = stdIn.next().toCharArray();
	    char[] b = stdIn.next().toCharArray();
	    char[] c = new char[81];
	    int ax = a.length-1;
	    int bx = b.length-1;
	    int d = 0;
	    for(int j=80;j>=0;j--){
		int tmp = 0;
		if(ax>=0){
		    tmp += a[ax--]-'0';
		}
		if(bx>=0){
		    tmp += b[bx--]-'0';
		}
		tmp += d;
		if(tmp>=2){
		    d = 1;
		    tmp -= 2;
		}
		else{
		    d = 0;
		}
		c[j] = (char) (tmp+'0');
	    }
	    boolean output = false;
	    for(int j=0;j<81;j++){
		if(output){
		    System.out.print(c[j]-'0');
		}
		else{
		    if(c[j]-'0'==1){
			System.out.print((i+1)+" 1");
			output = true;
		    }
		}
	    }
	    if(!output){
		System.out.print((i+1)+" 0");
	    }
	    System.out.println();
	}
    }
}
