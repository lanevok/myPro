import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 部分点
 *
 */
public class ARC028_B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	int k = stdIn.nextInt();
	int[] x = new int[n];
	for(int i=0;i<n;i++){
	    x[i] = stdIn.nextInt();
	}
	Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
	for(int i=0;i<k-1;i++){
	    map.put(x[i], i+1);
	}
	for(int i=k-1;i<n;i++){
	    map.put(x[i], i+1);
	    List<Integer> list = new ArrayList<Integer>(map.values());
	    System.out.println(list.get(k-1));
	}
    }
}