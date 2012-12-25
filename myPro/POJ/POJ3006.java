import java.util.Scanner;
 
public class Main {
     
//AOJ1141

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
                for(int j=i*2;j<=MAX;j+=i){
                    primes[j] = false;
                }
            }
        }
    }
 
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
 
        primeSet(1000000);
 
        while(true){
            int a = stdIn.nextInt();
            int d = stdIn.nextInt();
            int n = stdIn.nextInt();
            int cnt = 0;
 
            if(a==0&&d==0&&n==0){
                break;
            }
 
            for(int i=a;;i+=d){
                if(primes[i]){
                    cnt++;
                }
                if(cnt==n){
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}