import java.util.Scanner;

public class POJ3173 {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	int s = stdIn.nextInt();
	int[][] d = new int[n][n];
	for(int j=0;j<n;j++){
	    for(int i=0;i<n;i++){
		if(i<=j){
		    d[i][j] = s++;
		    if(s==10){
			s = 1;
		    }
		}
	    }
	}
	for(int i=0;i<n;i++){
	    for(int j=0;j<n-1;j++){
		if(d[i][j]!=0){
		    System.out.print(d[i][j]+" ");
		}
		else{
		    System.out.print("  ");
		}
	    }
	    System.out.println(d[i][n-1]);
	}
    }
}
