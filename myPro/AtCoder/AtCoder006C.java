import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		int[] t = new int[51];
		int[] d = new int[50];
		int n = stdIn.nextInt();
		for(int i=0;i<n;i++){
			d[i] = stdIn.nextInt();
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<50;j++){
				if(t[j]==0||t[j]>=d[i]){
					t[j] = d[i];
					break;
				}
			}
		}
		for(int i=0;i<51;i++){
			if(t[i]==0){
				System.out.println(i);
				break;
			}
		}
	}
}