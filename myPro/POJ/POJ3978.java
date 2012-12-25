import java.util.Scanner;

public class POJ3978 {
    
    static boolean[] primes;
    
    static void primeSet(final int MAX){
	primes = new boolean[MAX+1];
	
	primes[2] = true;
	for(int i=3;i<=MAX;i+=2){
	    primes[i] = true;
	}
	int rt = (int) Math.sqrt(MAX);
	for(int i=3;i<=rt;i+=2){
	    if(primes[i]){
		for(int j=i*i;j<=MAX;j+=i){
		    primes[j] = false;
		}
	    }
	}
    }
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	int max = 1000;
	primeSet(max);
	
	while(true){
	    int a = stdIn.nextInt();
	    int b = stdIn.nextInt();
	    if(a==-1&&b==-1){
		return;
	    }
	    int cnt=0;
	    if(max<b){
		primeSet(b);
		max = b;
	    }
	    if(a<2){
		a = 2;
	    }
	    for(int i=a;i<=b;i++){
		if(primes[i]){
		    cnt++;
		}
	    }
	    System.out.println(cnt);
	}
    }
}
