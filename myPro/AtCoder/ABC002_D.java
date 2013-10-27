import java.util.Scanner;

// WA
public class ABC002_D {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	int m = stdIn.nextInt();
	boolean[][] friend = new boolean[n+1][n+1];
	for(int i=0;i<m;i++){
	    int tmpX = stdIn.nextInt();
	    int tmpY = stdIn.nextInt();
	    friend[tmpX][tmpY] = true;
	}
	if(m==0){
	    System.out.println(1);
	    return;
	}
	int max = 0;
	for(int i=1;i<n+1;i++){
	    int cnt = 0;
	    for(int j=1;j<n+1;j++){
		if(friend[i][j]==true){
		    cnt++;
		}
	    }
	    if(cnt>max){
		max = cnt;
	    }
	}
	for(int z=max;z>=1;z--){
	    for(int i=1;i<n+1;i++){
		int cnt = 0;
		for(int j=1;j<n+1;j++){
		    if(friend[i][j]==true){
			cnt++;
		    }
		}
		if(cnt==z){
		    boolean flag = true;
		    Check:
		    for(int k=i+1;k<n+1;k++){
			for(int p=k+1;p<n+1;p++){
			    if(friend[i][k]==true&&friend[i][p]==true){
				if(friend[k][p]==true){
				    continue;
				}
				else{
				    flag = false;
				    break Check;
				}
			    }
			}
		    }
		    if(flag){
			System.out.println(cnt+1);
			return;
		    }
		}
	    }
	}
    }
}