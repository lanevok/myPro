import java.util.Scanner;

public class ABC012_C {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	for(int i=1;i<10;i++){
	    for(int j=1;j<10;j++){
		int sum = 0;
		for(int x=1;x<10;x++){
		    for(int y=1;y<10;y++){
			if(i==x&&j==y){
			    continue;
			}
			sum += x*y;
		    }
		}
		if(sum==n){
		    System.out.println(i+" x "+j);
		}
	    }
	}
    }
}
