import java.util.Scanner;
 
public class Main {
 
// AOJ1129

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
         
        while(true){
            int n = stdIn.nextInt();
            int r = stdIn.nextInt();
            if(n==0&&r==0){
                return;
            }
            int[] a = new int[n+1];
            for(int i=1;i<=n;i++){
                a[n-i+1] = i;
            }
            for(int i=0;i<r;i++){
                int p = stdIn.nextInt();
                int c = stdIn.nextInt();
                int[] b = new int[n+1];
                for(int j=1;j<=n;j++){
                    b[j] = a[j];
                }
                for(int j=1;j<=c;j++){
                    a[j] = b[p+j-1];
                }
                for(int j=c+1;j<p+c;j++){
                    a[j] = b[j-c];
                }
            }
            System.out.println(a[1]);
        }
    }
}