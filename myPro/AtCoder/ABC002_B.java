import java.util.Scanner;


public class ABC002_B {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	char[] w = stdIn.next().toCharArray();
	for(int i=0;i<w.length;i++){
	    if(w[i]=='a'||w[i]=='i'||w[i]=='u'||w[i]=='e'||w[i]=='o'){
		continue;
	    }
	    else{
		System.out.print(w[i]);
	    }
	}
	System.out.println();
    }
}
