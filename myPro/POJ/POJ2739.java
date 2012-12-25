import java.util.Scanner;
 
public class Main {
 
//AOJ1257

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
        int p = 0;
         
        while(true){
            int n = stdIn.nextInt();
            if(p<n){
                primeSet(n);
            }
            if(n==0){
                return;
            }
             
            int before = 0;
            int cnt = 0;
            for(int i=2;i<=n;i++){
                int sum=0;
                int start = 0;
//              System.out.print(i+":");
                for(int j=i;j<=n;j++){
                    if(primes[j]){
//                      System.out.print(j+",");
                        if(start==0){
                            start=j;
                        }
                        sum+=j;
                        if(sum==n){
                            if(start==before){
                                sum=0;
                                start=0;
                                break;
                            }
//                          System.out.print("*****");
                            before=start;
                            start=0;
                            cnt++;
                            sum=0;
                            break;
                        }
                        else if(sum>n){
                            sum=0;
                            break;
                        }
                    }
                }
//              System.out.println();
            }
            System.out.println(cnt);
        }
    }
}