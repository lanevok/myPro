import java.util.Scanner;
  
public class Main {

//AOJ1137
     
    static char ch1[];
    static char ch2[];
     
    static int ch_int(char ch[]){
        int i=0, sum=0;
        while(i<ch.length){
            if('2'<=ch[i] && ch[i]<='9'){
                int tmp = ch[i]-'0';
                i++;
                if(ch[i]=='m'){
                    sum += tmp*1000;
                }
                else if(ch[i]=='c'){
                    sum += tmp*100;
                }
                else if(ch[i]=='x'){
                    sum += tmp*10;
                }
                else if(ch[i]=='i'){
                    sum += tmp;
                }
            }
            else if(ch[i]=='m'){
                sum += 1000;
            }
            else if(ch[i]=='c'){
                sum += 100;
            }
            else if(ch[i]=='x'){
                sum += 10;
            }
            else if(ch[i]=='i'){
                sum += 1;
            }
            i++;
        }
        return sum; 
    }
     
    static char[] int_ch(int n){
        int i=0;
        char ch[] = new char[8];
        while(true){
            if(n==0){
                break;
            }
            if(1000<=n){
                if(n/1000!=1){
                    ch[i] = (char) (n/1000);
                    ch[i] += '0';
                    i++;
                    ch[i] = 'm';
                }
                else{
                    ch[i] = 'm';
                }
                n %= 1000;
            }
            else if(100<=n){
                if(n/100!=1){
                    ch[i] = (char) (n/100);
                    ch[i] += '0';
                    i++;
                    ch[i] = 'c';
                }
                else{
                    ch[i]='c';
                }
                n %= 100;
            }
            else if(10<=n){
                if(n/10!=1){
                    ch[i] = (char) (n/10);
                    ch[i] += '0';
                    i++;
                    ch[i] = 'x';
                }
                else{
                    ch[i] = 'x';
                }
                n %= 10;
            }
            else if(1<=n){
                if(n!=1){
                    ch[i] = (char)n;
                    ch[i] += '0';
                    i++;
                    ch[i] = 'i';
                }
                else{
                    ch[i] = 'i';
                }
                n %= 1;
            }
            i++;
        }
        char ans[] = new char[i];
        for(int j=0;j<i;j++){
            ans[j] = ch[j];
        }
        return ans;
    }
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0;i<n;i++){
            ch1 = sc.next().toCharArray();
            ch2 = sc.next().toCharArray();
            int tmp1 = ch_int(ch1);
            int tmp2 = ch_int(ch2);
            char ans[] = int_ch(tmp1+tmp2);
            System.out.println(ans);
        }
    }
}