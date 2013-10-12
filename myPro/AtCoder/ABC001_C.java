import java.util.Scanner;


public class ABC001_C {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int deg = stdIn.nextInt();
	int dis = stdIn.nextInt();
	
	deg*=10;
	
	int init = 1125;
	int inc = 2250;
	int degSet = 15;
	
	String[] force = {"NNE","NE","ENE","E","ESE","SE","SSE","S","SSW","SW","WSW","W","WNW","NW","NNW","N"};
	for(int i=0;i<force.length-1;i++){
	    if(init<=deg&&deg<init+inc){
		degSet = i;
		break;
	    }
	    init+=inc;
	}
	
	double disCalc = dis*1.0/60;
	//		System.err.println(disCalc);
	disCalc*=10;
	disCalc = Math.round(disCalc);
	//		disCalc/=10;
	//		System.err.println(disCalc);
	int[] list = {3,16,34,55,80,108,139,172,208,245,285,327};
	int disSet = 12;
	for(int i=0;i<list.length;i++){
	    if(disCalc<list[i]){
		disSet = i;
		break;
	    }
	}
	if(disSet==0){
	    System.out.println("C 0");
	}
	else{
	    System.out.println(force[degSet]+" "+disSet);
	}
    }
}
