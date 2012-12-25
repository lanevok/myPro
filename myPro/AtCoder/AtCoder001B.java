import java.util.Scanner;

public class AtCoder001B {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int a = stdIn.nextInt();
	int b = stdIn.nextInt();
	int c = b-a;
	int cnt = 0;
	if(c<0){
	    c*=-1;
	}
	while(true){
	    if(c>=10){
		cnt++;
		c-=10;
	    }
	    else if(c==9){
		cnt+=2;
		c-=9;
	    }
	    else if(c==8){
		cnt+=3;
		c-=8;
	    }
	    else{
		break;
	    }
	}
	while(true){
	    if(c>=5){
		cnt++;
		c-=5;
	    }
	    else if(c==4){
		cnt+=2;
		c-=4;
	    }
	    else{
		break;
	    }
	}
	cnt+=c;
	System.out.println(cnt);
    }
}
