import java.util.Scanner;

public class POJ3302 {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    char[] a = stdIn.next().toCharArray();
	    char[] b = stdIn.next().toCharArray();
	    boolean flag = false;
	    
	    Right:{
		int j = 0;
		for(int k=0;k<a.length;k++){
		    if(b[j]==a[k]){
			j++;
			if(j==b.length){
			    flag = true;
			    break Right;
			}
		    }
		}
	    }
	    
	    Left:{
		int j = 0;
		for(int k=a.length-1;k>=0;k--){
		    if(b[j]==a[k]){
			j++;
			if(j==b.length){
			    flag = true;
			    break Left;
			}
		    }
		}
	    }
	    System.out.println(flag?"YES":"NO");
	}
    }
}
