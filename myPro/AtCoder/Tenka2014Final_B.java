import java.util.Scanner;

/**
 * 部分点
 *
 */
public class Tenka2014Final_B {

    Point[] night;
    Point[][] star;

    class Point{
	int x,y;

	public Point(int _x, int _y) {
	    x = _x;
	    y = _y;
	}
    }

    public static void main(String[] args) {
	new Tenka2014Final_B().run();
    }

    private void run() {
	Scanner stdIn = new Scanner(System.in);

	int n = stdIn.nextInt();
	int m = stdIn.nextInt();
	night = new Point[n];
	for(int i=0;i<n;i++){
	    night[i] = new Point(stdIn.nextInt(), stdIn.nextInt());
	}
	star = new Point[m][];
	for(int i=0;i<m;i++){
	    int k = stdIn.nextInt();
	    star[i] = new Point[k];
	    for(int j=0;j<k;j++){
		star[i][j] = new Point(stdIn.nextInt(), stdIn.nextInt());
	    }
	}

	int[] count = new int[m];
	for(int i=0;i<n;i++){
	    for(int j=0;j<m;j++){
		if(judge(i,j)) count[j]++;
	    }
	}
	for(int i=0;i<m;i++){
	    System.out.println(count[i]);
	}
    }

    private boolean judge(int nightBase, int starIndex) {
	Point start = new Point(star[starIndex][0].x, star[starIndex][0].y);
	for(int i=0;i<star[starIndex].length;i++){
	    Point Base = new Point(night[nightBase].x, night[nightBase].y);
	    Point add = new Point(star[starIndex][i].x, star[starIndex][i].y);
	    Base.x += add.x;
	    Base.y += add.y;
	    Base.x -= start.x;
	    Base.y -= start.y;
	    if(!contains(Base)) return false;
	}
	return true;
    }

    private boolean contains(Point base) {
	for(int i=0;i<night.length;i++){
	    if(base.x==night[i].x&&base.y==night[i].y) return true;
	}
	return false;
    }
}
