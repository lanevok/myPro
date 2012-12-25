public class PE7 {
    
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
	
	primeSet(1000000);
	int cnt = 0;
	for(int i=2;;i++){
	    if(primes[i]){
		cnt++;
	    }
	    if(cnt==10001){
		System.out.println(i);
		return;
	    }
	}
    }
}