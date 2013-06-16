import java.util.Scanner;

// RE (Memory Over)
public class AtCoder014D {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int all = stdIn.nextInt();
	int n_hit = stdIn.nextInt();
	int m_query = stdIn.nextInt();
	
	int[] base = new int[all+1];
	
	for(int i=0;i<n_hit;i++){
	    int line = stdIn.nextInt();
	    base[line] = 1;
	}
	
	for(int i=0;i<m_query;i++){
	    int[] output = new int[all+1];
	    for(int j=1;j<all+1;j++){
		output[j] = base[j];
	    }
	    
	    int x_before = stdIn.nextInt();
	    int y_after = stdIn.nextInt();
	    
	    for(int j=1;j<all+1;j++){
		if(output[j]==1){
		    int z = 0;
		    for(int k=j-1;z<x_before;k--){
			if(k==0){
			    break;
			}
			if(output[k]!=1){
			    output[k] = 2;
			}
			z++;
		    }
		    z = 0;
		    for(int k=j+1;z<y_after;k++){
			if(k==all+1){
			    break;
			}
			if(output[k]!=1){
			    output[k] = 2;
			}
			z++;
		    }
		}
	    }
	    int cnt = 0;
	    for(int j=1;j<all+1;j++){
		if(output[j]>=1){
		    cnt++;
		}
	    }
	    System.out.println(cnt);
	}
    }
}
