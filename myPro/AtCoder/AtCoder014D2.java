import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// TLE
public class AtCoder014D2 {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int all = stdIn.nextInt();
	int n_hit = stdIn.nextInt();
	int m_query = stdIn.nextInt();
	
	int[] base = new int[n_hit];
	for(int i=0;i<n_hit;i++){
	    base[i] = stdIn.nextInt();
	}
	
	for(int i=0;i<m_query;i++){
	    
	    Set<Integer> output = new HashSet<Integer>();
	    
	    int x_before = stdIn.nextInt();
	    int y_after = stdIn.nextInt();
	    
	    for(int j=0;j<n_hit;j++){
		output.add(base[j]);
		
		int z = 0;
		for(int k=base[j]-1;z<x_before;k--){
		    if(k==0){
			break;
		    }
		    output.add(k);
		    z++;
		}
		
		z = 0;
		for(int k=base[j]+1;z<y_after;k++){
		    if(k==all+1){
			break;
		    }
		    output.add(k);
		    z++;
		}
	    }
	    System.out.println(output.size());
	}
    }
}
