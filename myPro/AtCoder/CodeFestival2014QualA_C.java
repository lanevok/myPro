import java.util.Scanner;


public class CodeFestival2014QualA_C {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	int a = stdIn.nextInt();
	int b = stdIn.nextInt();

	boolean uru;
	int cnt = 0;
	boolean flag = false;
	if(a<50000000&&b>1800000000) flag = true;
	for(int year=a;year<=b;year++){
	    if(flag&&year==50000000){
		year += 1800000000-50000000;
		cnt += 424375001;
		continue;
	    }
	    if (year % 4 == 0) {
		if (year % 100 == 0) {
		    if (year % 400 == 0) {
			uru = true;
		    } else {
			uru = false;
		    }
		} else {
		    uru = true;
		}
	    } else {
		uru = false;
	    }
	    if(uru) cnt++;
	}
	System.out.println(cnt);
    }
}