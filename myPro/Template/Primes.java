public class Primes {
    
    // 素数判定配列(添え字がtrueなら添え字は素数)
    static boolean[] primes;
    
    // 素数判定を生成(2からMAX以下で素数をtrueに)
    static void primeSet(final int MAX){
	primes = new boolean[MAX+1];
	
	primes[2] = true;
	for(int i=3;i<=MAX;i+=2){
	    // 一時的に奇数を素数とする
	    primes[i] = true;
	}
	int rt = (int) Math.sqrt(MAX);
	for(int i=3;i<=rt;i+=2){
	    if(primes[i]){
		// iが素数ならiの倍数は素数でない
		for(int j=i*i;j<=MAX;j+=i){
		    primes[j] = false;
		}
	    }
	}
    }
    
    public static void main(String[] args) {
	int n=100;
	int cnt=0;
	
	primeSet(n);	// 素数判定を生成する
	
	// 2からn以下の素数を出力
	for(int i=2;i<=n;i++){
	    if(primes[i]){
		System.out.println(i);
		cnt++;
	    }
	}
	
	// 素数の要素数を出力
	System.out.println("cnt="+cnt);
	
	if(primes[97]){	// 97は素数か
	    System.out.println("Primes!");
	}
    }
}
