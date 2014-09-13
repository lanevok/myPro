import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ABC014_C {

    class Point implements Comparable<Point>{
	int value;
	boolean start;

	public Point(int _value, boolean _start) {
	    value = _value;
	    start = _start;
	}

	@Override
	    public int compareTo(Point o) {
	    if(value!=o.value){
		return value - o.value;
	    }
	    if(start!=o.start&&start){
		return -1;
	    }
	    else if(start!=o.start&&!start){
		return 1;
	    }
	    return 0;
	}
    }

    List<Point> list;

    public static void main(String[] args) {
	new ABC014_C().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	list = new ArrayList<Point>();
	for(int i=0;i<n;i++){
	    int a = stdIn.nextInt();
	    int b = stdIn.nextInt();
	    list.add(new Point(a, true));
	    list.add(new Point(b, false));
	}
	Collections.sort(list);
	int x = 0;
	int max = 0;
	for(Point p : list){
	    //			System.out.println(p.value+","+p.start);
	    if(p.start){
		x++;
	    }
	    else{
		x--;
	    }
	    max = Math.max(max, x);
	}
	System.out.println(max);
    }
}
