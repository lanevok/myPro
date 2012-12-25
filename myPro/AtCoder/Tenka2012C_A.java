import java.util.Scanner;
 
public class Main {
 
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
		Scanner stdIn = new Scanner(System.in);
		
		int n = stdIn.nextInt();
		primeSet(n+1);
		int cnt = 0;
		for(int i=2;i<n;i++){
			if(primes[i]){
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}