import java.util.Arrays;
import java.util.Scanner;
 
public class Main {
 
//AOJ1147

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
         
        while(true){
            int n = stdIn.nextInt();
            if(n==0){
                return;
            }
            int[] point = new int[n];
            for(int i=0;i<n;i++){
                point[i] = stdIn.nextInt();
            }
            Arrays.sort(point);
            int ans = 0;
            for(int i=1;i<n-1;i++){
                ans += point[i];
            }
            ans /= n-2;
            System.out.println(ans);
        }
    }
}