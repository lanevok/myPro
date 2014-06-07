import java.util.Scanner;

public class ABC010_B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	int cnt = 0;
	for(int i=0;i<n;i++){
	    int a = stdIn.nextInt()%6;
	    if(a==2){
		cnt++;
	    }
	    else if(a==4){
		cnt++;
	    }
	    else if(a==5){
		cnt+=2;
	    }
	    else if(a==0){
		cnt+=3;
	    }
	}
	System.out.println(cnt);
    }
}
