import java.util.Scanner;

// WA
public class ABC001_D {
    
    public static void main(String[] args) {
	Scanner stdIn = new Scanner(System.in);
	
	// input
	int n = stdIn.nextInt();
	int[] start = new int[n];
	int[] stop = new int[n];
	for(int i=0;i<n;i++){
	    String line = stdIn.next();
	    String[] split = line.split("-");
	    start[i] = Integer.valueOf(split[0]);
	    stop[i] = Integer.valueOf(split[1]);
	}
	// round start
	for(int i=0;i<n;i++){
	    for(;;){
		if(start[i]%5!=0){
		    start[i]--;
		}
		else{
		    break;
		}
	    }
	}
	// round stop
	for(int i=0;i<n;i++){
	    for(;;){
		if(stop[i]%5!=0){
		    stop[i]++;
		}
		else{
		    break;
		}
	    }
	}
	// sort before debug output
	//		for(int i=0;i<n;i++){
	//			System.err.println(start[i]+"-"+stop[i]);
	//		}
	//		System.err.println("----------------");
	// sort
	for(int i=0;i<n-1;i++){
	    int min = start[i];
	    int z = i;
	    for(int j=i+1;j<n;j++){
		if(min>start[j]){
		    min = start[j];
		    z = j;
		}
	    }
	    // swap
	    // 1. start
	    int temp = start[i];
	    start[i] = start[z];
	    start[z] = temp;
	    // 2. stop
	    temp = stop[i];
	    stop[i] = stop[z];
	    stop[z] = temp;
	}
	// sort anfter debug output
	//		for(int i=0;i<n;i++){
	//			System.err.println(start[i]+"-"+stop[i]);
	//		}
	// ans create
	int[] ansStart = new int[n];
	int[] ansStop = new int[n];
	int index = 0;
	int max = -1;
	A:{
	    for(int i=0;i<n;){
		//			System.err.println("start="+start[i]);
		ansStart[index] = start[i];
		max = -1;
		if(i+1>=n){
		    //				System.err.println("stop="+stop[i]);
		    ansStop[index++] = stop[i];
		    break;
		}
		if(stop[i]<start[i+1]){
		    //				System.err.println("stop="+stop[i]);
		    ansStop[index++] = stop[i];
		    i++;
		}
		else{
		    max = stop[i];
		    for(;;){
			i++;
			
			// RE
			if(i+1>=n){
			    //						System.err.println("stop="+stop[i]);
			    ansStop[index++] = Math.max(max, stop[i]);
			    break A;
			}
			
			if(stop[i]<start[i+1]){
			    //						System.err.println("stop="+stop[i]);
			    ansStop[index++] = Math.max(max, stop[i]);
			    i++;
			    break;
			}
			else{
			    max = Math.max(max, stop[i]);
			}
		    }
		}
	    }
	}
	// output
	for(int i=0;i<index;i++){
	    System.out.printf("%04d",ansStart[i]);
	    System.out.print("-");
	    System.out.printf("%04d\n",ansStop[i]);
	}
    }
}
