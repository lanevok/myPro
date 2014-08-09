import java.util.Arrays;


public class Tenka2014A_A {

    public static void main(String[] args) {
	String[] a = new String[1000];
	for(int i=0;i<1000;i++){
	    a[i] = String.valueOf(i+1);
	}
	Arrays.sort(a);
	for(int i=0;i<1000;i++){
	    System.out.println(a[i]);
	}
    }
}
