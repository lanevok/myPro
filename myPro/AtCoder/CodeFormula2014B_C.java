import java.util.HashMap;
import java.util.Scanner;

/**
 * 3つのテストケースでTLE
 *
 */
public class C {

    static String base;
    static String target;
    static HashMap<Integer, HashMap<String, String>> speedSwap;
    static HashMap<String, Integer> speedMap;
    static long start;

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);

	speedSwap = new HashMap<Integer, HashMap<String, String>>();
	speedMap = new HashMap<String, Integer>();

	base = stdIn.next();
	target = stdIn.next();

	start = System.currentTimeMillis();
	standby();
	run(base, 0);
	// System.out.println(System.currentTimeMillis()-start);
	System.out.println("NO");
    }

    private static void standby() {
	if(base.equals(target)&&base.length()>26){
	    System.out.println("YES");
	    System.exit(0);
	}
	int count = 0;
	for(int i=0;i<base.length();i++){
	    if(base.charAt(i)!=target.charAt(i)){
		count++;
	    }
	}
	if(count>6){
	    System.out.println("NO");
	    System.exit(0);
	}
    }

    private static void run(String text, int count) {
	// if(speedMap.containsKey(text)){
	//	int memo = speedMap.get(text);
	//	if(memo<count){
	//		return;
	//	}
	// }
	// System.out.println(text+","+count);
	if(count==3){
	    judge(text);
	    return;
	}
	if(standby2(text, count)) return;
	int l = text.length();
	for(int i=0;i<l-1;i++){
	    for(int j=i+1;j<l;j++){
		if(text.charAt(i)!=text.charAt(j)){
		    if(text.charAt(i)==target.charAt(i)) continue;
		    if(text.charAt(j)==target.charAt(j)) continue;
		}
		String next = swap(text, i, j);
		// System.out.println("swap: "+text+"=>"+next+" i="+i+", j="+j);
		run(next, count+1);
		// if(speedMap.containsKey(next)){
		//	int memo = speedMap.get(next);
		//	if(memo>count+1){
		//		speedMap.put(text, count+1);
		//	}
		// }
		// else{
		//	speedMap.put(text, count+1);
		// }
	    }
	}
    }

    private static boolean standby2(String text, int now) {
	if(text.equals(target)){
	    if(now%2==1||text.length()>26){
		System.out.println("YES");
		System.exit(0);
	    }
	}
	int ato = 6-(now*2);
	int count = 0;
	for(int i=0;i<text.length();i++){
	    if(text.charAt(i)!=target.charAt(i)){
		count++;
		if(count>ato) return true;
	    }
	}
	return false;
    }

    private static void judge(String text) {
	if(text.equals(target)){
	    // System.out.println(System.currentTimeMillis()-start);
	    System.out.println("YES");
	    System.exit(0);
	}
    }

    private static String swap(String text, int i, int j) {
	// if(speedSwap.containsKey(i*10000+j)){
	//	HashMap<String, String> map = new HashMap<String, String>();
	//	if(map.containsKey(text)){
	//		return map.get(text);
	//	}
	// }
	String before = String.valueOf(text.charAt(i));
	String after = String.valueOf(text.charAt(j));
	if(before.equals(after)) return text;
	StringBuilder sb = new StringBuilder();
	for(int x=0;x<text.length();x++){
	    if(x==i){
		sb.append(after);
	    }
	    else if(x==j){
		sb.append(before);
	    }
	    else{
		sb.append(text.charAt(x));
	    }
	}
	String next = sb.toString();
	// HashMap<String, String> map = new HashMap<String, String>();
	// if(speedSwap.containsKey(text)){
	//	map = speedSwap.get(text);
	// }
	// map.put(text, next);
	// speedSwap.put(i*10000+j, map);
	return next;
    }
}
