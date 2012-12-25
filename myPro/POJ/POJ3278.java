import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    
    /*
      POJ3278
      整数nとkを受け取って、nからkまで遷移する最小ステップ数を出力する。
      1ステップで遷移できるのは、a->a+1 , a->a*2 , a->a-1 の3つ。
      よってn>=kならばn-kが答。よってn<kのときの解を探索する。
    */
    
    
    static int k;
    static boolean[] d = new boolean[100001];
    
    static int Search(int n){
	Queue<Integer> q = new LinkedList<Integer>();
	Queue<Integer> r = new LinkedList<Integer>();
	q.offer(n);
	r.offer(0);
	
	while(!q.isEmpty()){
	    int a = q.poll();
	    int s = r.poll();
	    if(a>100000||a<0||d[a]){
		continue;
	    }
	    d[a]=true;
	    if(d[k]){
		return s;
	    }
	    else{
		q.offer(a+1);
		r.offer(++s);
		q.offer(a*2);
		r.offer(s);
		q.offer(a-1);
		r.offer(s);
	    }
	}
	return -123;
    }
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	int n = stdIn.nextInt();
	k = stdIn.nextInt();
	if(n>=k){
	    System.out.println(n-k);
	}
	else{
	    System.out.println(Search(n));
	}
    }
}
