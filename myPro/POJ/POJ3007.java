import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
 
public class Main {
 
//AOJ1142

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
         
        int m = stdIn.nextInt();
        for(int i=0;i<m;i++){
            Set<String> s = new TreeSet<String>();
            String a = stdIn.next();
            s.add(a);
            for(int j=1;j<a.length();j++){
                String b = a.substring(0, j);
                String c = a.substring(j);
                StringBuffer d = new StringBuffer(b);
                StringBuffer e = new StringBuffer(c);
                String f = d.reverse().toString();
                String g = e.reverse().toString();
                String h1 = b.concat(c);
                String h2 = b.concat(g);
                String h3 = c.concat(b);
                String h4 = c.concat(f);
                String h5 = f.concat(g);
                String h6 = g.concat(b);
                String h7 = f.concat(c);
                s.add(h1);
                s.add(h2);
                s.add(h3);
                s.add(h4);
                s.add(h5);
                s.add(h6);
                s.add(h7);
            }
            System.out.println(s.size());
        }
    }
}