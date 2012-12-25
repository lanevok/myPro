import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		char[] n = stdIn.nextLine().toCharArray();
		int y = (n[0]-'0')*1000+(n[1]-'0')*100+(n[2]-'0')*10+(n[3]-'0');
		int m = (n[5]-'0')*10+(n[6]-'0');
		int d = (n[8]-'0')*10+(n[9]-'0');
		while(true){
			if(y%(m*d)==0){	
				System.out.printf("%d/%02d/%02d\n",y,m,d);
				break;
			}
			d++;
			if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
				if(d>=32){
					d = 1;
					m++;
				}
				if(m>=13){
					m = 1;
					y++;
				}
			}
			else{
				if(m==2){		
					boolean f = false;
					int cnt = 0;
					if(y%4==0){
						cnt++;
						f = true;
					}
					if(y%100==0){
						cnt++;
						f = false;
					}
					if(y%400==0){
						cnt++;
						f = true;
					}
					if(cnt==0){
						f = false;
					}
					if(f){
						if(d>=30){
							d = 1;
							m = 3;
						}
					}
					else{
						if(d>=29){
							d = 1;
							m = 3;
						}
					}
				}
				else{
					if(d>=31){
						d = 1;
						m++;
					}
				}
			}
		}
	}
}