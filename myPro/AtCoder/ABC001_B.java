import java.util.Scanner;


public class ABC001_B {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int m = stdIn.nextInt();
	String vv = "";
	if(m<100){
	    vv = "00";
	}
	else if(m<=5000){
	    int temp = m/100;
	    if(temp<10){
		vv = "0"+temp;
	    }
	    else{
		vv = String.valueOf(temp);
	    }
	}
	else if(m<=30000){
	    int temp = (m/1000)+50;
	    vv = String.valueOf(temp);
	}
	else if(m<=70000){
	    int temp = (((m/1000)-30)/5)+80;
	    vv = String.valueOf(temp);
	}
	else if(m>70000){
	    vv = "89";
	}
	System.out.println(vv);
    }
}
