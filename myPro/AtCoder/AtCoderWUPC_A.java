import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int[] max = {31,29,31,30,31,30,31,31,30,31,30,31};
		
		int ma = stdIn.nextInt();
		int da = stdIn.nextInt();
		int mb = stdIn.nextInt();
		int db = stdIn.nextInt();
		int day = 0;
		while(true){
			if(ma==mb&&da==db){
				break;
			}
			da++;
			if(max[ma-1]<da){
				da = 0;
				ma++;
				if(ma==13){
					ma = 1;
				}
			}
			else{
				day++;
			}
		}
		System.out.println(day);
	}
}