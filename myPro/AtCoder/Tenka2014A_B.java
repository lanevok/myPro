import java.io.IOException;
import java.io.InputStreamReader;


public class CopyOfTenka2014A_B {

    public static void main(String[] args) {
	InputStreamReader stdIn = new InputStreamReader(System.in);

	int kabu = 5;
	long damageScore = 0;
	int combo = 0;
	int[] t = new int[2000000];
	int[] tCombo = new int[2000000];
	boolean[] ok = new boolean[2000000];
	int i = -1;
	while(true){
	    int input = -1;
	    i++;
	    try {
		input = stdIn.read();
	    } catch (IOException e) {
		System.out.println(damageScore);
		System.exit(0);
	    }
	    // if(input=='\n'){
	    //	i--;
	    //	continue;
	    // }
	    if(input=='\n'){
		System.out.println(damageScore);
		System.exit(0);
	    }

	    kabu += t[i];
	    combo += tCombo[i];

	    if(ok[i]){
		continue;
	    }

	    // System.out.println("cnt = ("+(i+1)+") ... kabu = "+kabu);
	    if(input=='N'){
		if(kabu>=1){
		    // System.out.println("N = ("+(i+1)+")");
		    kabu--;
		    damageScore += 10*(1+Math.floor(combo/10)*0.1);
		    // System.out.println("nowD= "+10*(1+Math.floor(combo/10)*0.1)+" , damageTotal = "+damageScore);
		    tCombo[i+2]++;
		    t[i+7]++;
		}
	    }
	    else if(input=='C'){
		if(kabu>=3){
		    // System.out.println("C = ("+(i+1)+")");
		    kabu -= 3;
		    damageScore += 50*(1+Math.floor(combo/10)*0.1);
		    // System.out.println("nowD= "+Math.floor(50*(1+Math.floor(combo/10)*0.1))+" , damageTotal = "+damageScore);
		    tCombo[i+4]++;
		    t[i+9]+=3;
		    ok[i+1] = true;
		    ok[i+2] = true;
		}
	    }
	}
    }
}
