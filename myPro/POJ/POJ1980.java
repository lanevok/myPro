import java.util.Scanner;
 
public class Main {
 
//AOJ1131

    static int p, q, a, n, cnt;
     
    static void search(int num, int den, int pro, int emt){
        // num = 分子(numerator) , den = 分母(denominator)
        // pro = 積(product) , emt = 要素個数(element)
        if(p*den==q*num){
            cnt++;
            return;
        }
        else if(emt==n||p*den<q*num){
            return;
        }
        else{
            emt++;
            // multi = 掛ける(multiply)
            for(int multi=pro;den*multi<=a;multi++){
                int den_new = den*multi;
                int num_new = num*multi+den;
                search(num_new,den_new,multi,emt);
            }
        }
    }
     
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
         
        while(true){
            p = stdIn.nextInt();
            q = stdIn.nextInt();
            a = stdIn.nextInt();
            n = stdIn.nextInt();
            if((p|q|a|n)==0){
                return;
            }
            cnt = 0;
            search(0,1,1,0);
            System.out.println(cnt);
        }
    }
}