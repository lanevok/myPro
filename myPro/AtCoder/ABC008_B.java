import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ABC008_B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	Map<String, Integer> select = new HashMap<String, Integer>();
	
	int n = stdIn.nextInt();
	for(int i=0;i<n;i++){
	    String input = stdIn.next();
	    if(select.containsKey(input)){
		int value = select.get(input);
		select.put(input, ++value);
	    }
	    else{
		select.put(input, 1);
	    }
	}
	String maxUser = "";
	int max = 0;
	for(String user : select.keySet()){
	    int value = select.get(user);
	    if(value>max){
		maxUser = user;
		max = value;
	    }
	}
	System.out.println(maxUser);
    }
}
