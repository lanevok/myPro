import java.util.Scanner;

public class tenka1_2013q_B {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	int cnt = 0;
	for(int i=0;i<n;i++){
	    int input = 0;
	    for(int j=0;j<5;j++){
		input+=stdIn.nextInt();
	    }
	    if(input<20){
		cnt++;
	    }
	}
	System.out.println(cnt);
    }
}
