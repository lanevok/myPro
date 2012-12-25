import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		Set s = new TreeSet();
		for(int i=0;i<6;i++){
			s.add(stdIn.nextInt());
		}
		int b = stdIn.nextInt();
		boolean flag = false;
		int cnt = 0;
		for(int i=0;i<6;i++){
			int in = stdIn.nextInt();
			if(s.contains(in)){
				cnt++;
			}
			if(b==in){
				flag = true;
			}
		}
		int ans = 0;
		if(cnt==6){
			ans = 1;
		}
		else if(cnt==5&&flag){
			ans = 2;
		}
		else if(cnt==5){
			ans = 3;
		}
		else if(cnt==4){
			ans = 4;
		}
		else if(cnt==3){
			ans = 5;
		}
		System.out.println(ans);
	}
}