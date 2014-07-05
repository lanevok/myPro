import java.util.Scanner;

public class KUPC2014_B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	boolean[] impossible = new boolean[1001];
	impossible[0] = true;
	//		impossible[1] = true;
	int request = 1;
	while(true){
	    int cnt = 0;
	    for(int i=0;i<impossible.length;i++){
		if(!impossible[i]) cnt++;
	    }
	    //			System.out.println("残り"+cnt);
	    if(cnt==1) break;
	    for(int i=request+1;i<impossible.length;i++){
		if(!impossible[i]){
		    System.out.println("? "+i);
		    request = i;
		    break;
		}
	    }
	    String response = stdIn.nextLine();
	    if(response.contains("N")){
		for(int i=0;i<impossible.length;i++){
		    if(!impossible[i]&&i%request==0){
			//						System.out.println(i+"が割り切れてしまい答えじゃないことが判明した");
			impossible[i] = true;
		    }
		}
	    }
	    else if(response.contains("Y")){
		for(int i=0;i<impossible.length;i++){
		    if(!impossible[i]&&i%request!=0){
			//						System.out.println(i+"が割り切れないから答えじゃないことが判明した");
			impossible[i] = true;
		    }
		}
	    }
	}
	for(int i=0;i<impossible.length;i++){
	    if(!impossible[i]){
		System.out.println("! "+i);
	    }
	}
    }
}
