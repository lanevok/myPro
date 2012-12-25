import java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		String a = stdIn.nextLine().replaceAll(",", "0");
		String b = a.replaceAll(" ", ",");
		String c1 = b.replaceAll(",,,,,,,,,", ",");
		String c2 = c1.replaceAll(",,,,,,,,", ",");
		String c3 = c2.replaceAll(",,,,,,,", ",");
		String c4 = c3.replaceAll(",,,,,,", ",");
		String c5 = c4.replaceAll(",,,,,", ",");
		String c6 = c5.replaceAll(",,,,", ",");
		String c7 = c6.replaceAll(",,,", ",");
		String c8 = c7.replaceAll(",,", ",");
		String c9 = c8.replaceAll(",", ",");
		String d = c9.replaceAll("0", ",");
		System.out.println(d);
	}
}