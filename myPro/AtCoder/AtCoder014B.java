import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AtCoder014B {

    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	String[] say = new String[n];
	for(int i=0;i<n;i++){
	    say[i] = stdIn.next();
	}
	Set<String> said = new HashSet<String>();
	int i = 0;
	String last = say[0].substring(0, 1);
	for(;i<n;i++){
	    if(said.contains(say[i])){
		break;
	    }
	    if(!last.equals(say[i].substring(0,1))){
		break;
	    }
	    said.add(say[i]);
	    last = say[i].substring(say[i].length()-1);
	}
	if(n==i){
	    System.out.println("DRAW");
	    return;
	}
	if(i%2==1){
	    System.out.println("WIN");
	}
	else{
	    System.out.println("LOSE");
	}
    }
}
