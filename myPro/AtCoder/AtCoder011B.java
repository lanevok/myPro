import java.util.Scanner;

public class AtCoder011B {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	int n = stdIn.nextInt();
	char[][] res = new char[n][40];
	for(int i=0;i<n;i++){
	    char[] w = stdIn.next().toCharArray();
	    int z = 0;  // result index
	    for(int j=0;j<w.length;j++){
		String tmp = String.valueOf(w[j]);
		tmp = tmp.toLowerCase();
		w[j] =  tmp.charAt(0);
		if(w[j]=='b'||w[j]=='c'){
		    res[i][z++]= '1';
		}
		else if(w[j]=='t'||w[j]=='j'){
		    res[i][z++]= '3';
		}
		else if(w[j]=='l'||w[j]=='v'){
		    res[i][z++]= '5';
		}
		else if(w[j]=='p'||w[j]=='m'){
		    res[i][z++]= '7';
		}
		else if(w[j]=='n'||w[j]=='g'){
		    res[i][z++]= '9';
		}
		else if(w[j]=='d'||w[j]=='w'){
		    res[i][z++]= '2';
		}
		else if(w[j]=='f'||w[j]=='q'){
		    res[i][z++]= '4';
		}
		else if(w[j]=='s'||w[j]=='x'){
		    res[i][z++]= '6';
		}
		else if(w[j]=='h'||w[j]=='k'){
		    res[i][z++]= '8';
		}
		else if(w[j]=='z'||w[j]=='r'){
		    res[i][z++]= '0';
		}
	    }
	}
	boolean flag = false;
	StringBuffer r = new StringBuffer();
	for(int i=0;i<n;i++){
	    if(flag) r.append(" ");
	    flag = false;
	    for(int j=0;j<40;j++){
		if(res[i][j]>='0'&&res[i][j]<='9'){
		    flag = true;
		    r.append(res[i][j]);
		}
	    }
	}
	String ans;
	if(r.length()==0){
	    System.out.println();
	    return;
	}
	if(r.charAt(r.length()-1)==' '){
	    ans = r.substring(0, r.length()-1);
	}
	else{
	    ans = r.toString();
	}
	System.out.println(ans);
    }
}
