import java.util.Scanner;
 
public class Main {
 
	static double Area(double x1, double y1, double x2, double y2){
		return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		int n = stdIn.nextInt();
		double max = 0;
		double[][] p = new double[n][2];
		for(int i=0;i<n;i++){
			p[i][0] = stdIn.nextDouble();
			p[i][1] = stdIn.nextDouble();
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i==j){
					continue;
				}
				max = Math.max(Area(p[i][0],p[i][1],p[j][0],p[j][1]),max);
			}
		}
		System.out.printf("%.6f\n",max);
	}
}