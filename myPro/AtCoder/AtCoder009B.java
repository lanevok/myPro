import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    static int[] b;
    static int[] sort;
    static int[] output;
    
    static void conv(int s,int number){
	int lng = 1;
	int copy = number;
	while(true){
	    if(number/10>=1){
		lng++;
		number = number/10;
	    }
	    else{
		break;
	    }
	}
	int[] letu = new int[lng];
	for(int i=lng-1;i>=0;i--){
	    letu[i] = copy%10;
	    copy = copy/10;
	}
	int rt = 0;
	for(int i=0;i<lng;i++){
	    for(int j=0;j<10;j++){
		if(letu[i]==b[j]){
		    rt = rt*10;
		    rt += j; 
		    break;
		}
	    }
	}
	sort[s] = rt;
    }
    
    static void rev(int s,int number){
	int lng = 1;
	int copy = number;
	while(true){
	    if(number/10>=1){
		lng++;
		number = number/10;
	    }
	    else{
		break;
	    }
	}
	int[] letu = new int[lng];
	for(int i=lng-1;i>=0;i--){
	    letu[i] = copy%10;
	    copy = copy/10;
	}
	int rt = 0;
	for(int i=0;i<lng;i++){
	    rt*=10;
	    rt+=b[letu[i]];
	}
	output[s] = rt;
    }
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	b = new int[10];
	for(int i=0;i<10;i++){
	    b[i] = stdIn.nextInt(); 
	}
	int n = stdIn.nextInt();
	int[] input = new int[n];
	sort = new int[n];
	output = new int[n];
	for(int i=0;i<n;i++){
	    input[i] = stdIn.nextInt();
	    conv(i,input[i]);
	}
	Arrays.sort(sort);
	for(int i=0;i<n;i++){
	    rev(i,sort[i]);
	}
	for(int i=0;i<n;i++){
	    System.out.println(output[i]);
	}
    }
}