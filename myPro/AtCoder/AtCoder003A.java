import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		int n = stdIn.nextInt();
		double gp = 0;
		char[] score = stdIn.next().toCharArray();
		for(int i=0;i<score.length;i++){
			if(score[i]=='A'){
				gp += 4;
			}
			else if(score[i]=='B'){
				gp += 3;
			}
			else if(score[i]=='C'){
				gp += 2;
			}
			else if(score[i]=='D'){
				gp += 1;
			}
		}
		System.out.println(gp/n);
	}
}