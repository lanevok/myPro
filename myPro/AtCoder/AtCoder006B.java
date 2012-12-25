import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		int N = stdIn.nextInt();
		int L = stdIn.nextInt();
		stdIn.nextLine();
		int[][] f = new int[L+1][2*N-1];
		for(int i=0;i<L+1;i++){
			char[] t = stdIn.nextLine().toCharArray();
			for(int j=0;j<2*N-1;j++){
				if(t[j]=='o'){
					f[i][j] = 4;
				}
				else if(t[j]=='|'){
					f[i][j] = 1;
				}
				else if(t[j]=='-'){
					f[i][j] = 2;
				}
				else if(t[j]==' '){
					f[i][j] = 3;
				}
			}
		}
		int s = 0;
		int d = L;
		for(int i=0;i<2*N-1;i++){
			if(f[L][i]==4){
				s = i;
				break;
			}
		}
		while(true){
			d--;
			if(d==-1){
				break;
			}
			if(s-1>=0&&f[d][s-1]==2){
				s-=2;
			}
			else if(s+1<2*N-1&&f[d][s+1]==2){
				s+=2;
			}
		}
		System.out.println(s/2+1);
	}
}