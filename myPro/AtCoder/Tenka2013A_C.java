import java.util.Scanner;

public class Main {
    static int[][] map;
    static int m,n;
    static int cnt;
    
    public static void main(String[] args) {
	input();
	process();
	//	System.out.println("cnt: "+cnt);
	System.out.println(cnt);
    }
    
    private static void process() {
	//	System.out.println("call");
	// �Z���o��
	//	output();
	// �󂫏ꏊ������
	int i=-1, j=-1;
	boolean flag = true;
	A:
	for(i=0;i<m;i++){
	    for(j=0;j<n;j++){
		if(map[i][j]==0){
		    flag = false;
		    break A;
		}
	    }
	}
	// �󂫏ꏊ�Ȃ�
	if(flag){
	    //	System.out.println("cnt++&return");
	    cnt++;
	    return;
	}
	// ���߂�
	for(int x=1;x<=3;x++){
	    //	System.out.println("map["+i+"]["+j+"] = "+x);
	    map[i][j] = x;
	    // �G���[�`�F�b�N��
	    if(errorJudge(i,j)){
		continue;
	    }
	    // �ċA
	    process();
	}
	// �ċA������ۂɌ��ɖ߂��@����
	map[i][j] = 0;
    }
    
    private static void output() {
	System.out.println("---start--------------------------");
	for(int i=0;i<m;i++){
	    for(int j=0;j<n;j++){
		System.out.print(map[i][j]+" ");
	    }
	    System.out.println();
	}
	System.out.println("---end--------------------------");
    }
    
    private static boolean errorJudge(int i, int j) {
	//	for(int i=0;i<m;i++){
	//		for(int j=0;j<n;j++){
	int target = map[i][j];
	for(int x=i-target;x<=i+target;x++){
	    int y = j;
	    // target�Z�����O
	    if(x==i&&y==j){
		continue;
	    }
	    // �z��O�Q��
	    if(x<0||x>=m){
		continue;
	    }
	    // �͈͓����ꐔ���ݒ蔻��
	    if(target==map[x][y]&&map[x][y]!=0){
		//	System.out.println("error");
		return true;	//�G���[
	    }
	}
	for(int y=j-target;y<=j+target;y++){
	    int x = i;
	    // target�Z�����O
	    if(x==i&&y==j){
		continue;
	    }
	    // �z��O�Q��
	    if(y<0||y>=n){
		continue;
	    }
	    // �͈͓����ꐔ���ݒ蔻��
	    if(target==map[x][y]&&map[x][y]!=0){
		//	System.out.println("error");
		return true;	//�G���[
	    }
	}
	//		}
	//	}
	//	System.out.println("ok");
	return false;
    }
    
    private static void input() {
	Scanner stdIn = new Scanner(System.in);
	m = stdIn.nextInt();
	n = stdIn.nextInt();
	map = new int[m][n];
	for(int i=0;i<map.length;i++){
	    map[i] = new int[n];
	}
    }
}