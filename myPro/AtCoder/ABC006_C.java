import java.util.Scanner;

// 30points, TLE
public class ABC006_C {

    private static int calcTotalFoot(int man, int old, int baby){
	return man*2+old*3+baby*4;
    }
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n_human = stdIn.nextInt();
	int m_foot = stdIn.nextInt();
	
	//		if(n_human*4<m_foot){
	//			System.out.println("-1 -1 -1");
	//			System.exit(0);
	//		}
	//		if(m_foot%2==0&&n_human*2>=m_foot){
	//			int man = m_foot/2;
	//			System.out.println(man+" 0 0");
	//			System.exit(0);
	//		}
	//		if(m_foot%5==0&&n_human/2*5>=m_foot&&m_foot/5>=n_human/2){
	//			int man = m_foot/5;
	//			System.out.println(man+" "+man+" 0");
	//			System.exit(0);
	//		}
	
	
	for(int i=0;i<=n_human;i++){
	    int now_man = i;
	    for(int j=0;j<=n_human-now_man;j++){
		int now_old = j;
		int now_baby = n_human-now_man-now_old;
		//				System.out.println("check: "+now_man+" "+now_old+" "+now_baby);
		if(calcTotalFoot(now_man, now_old, now_baby)==m_foot){
		    System.out.println(now_man+" "+now_old+" "+now_baby);
		    System.exit(0);
		}
	    }
	}
	System.out.println("-1 -1 -1");
    }
}