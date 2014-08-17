import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * POJ2919 Traveling Queen Problem
 *
 * @author (TAT)chaN
 * @since 2014.6.30
 * @date 2014.8.18 (Accepted: 76)
 *
 * �y���T�v�z
 * �܂��C�`�F�X���C���[�W����D�`�F�X�̔ՖʂƃR�}�̈����͓����D
 * �����ł́C1�̃N�C�[��(Q)���Ֆʂ��ړ����邱�Ƃ��l����D
 * �N�C�[�����Ֆʂɔz�u����Ă��邻�ꂼ��̃i�C�g(N, ����2�`14)��K�₷��K�v������C
 * �K��Ƃ́C�i�C�g�̗אڃ}�X(��{�I�Ɏ���8�}�X)�̉��ꂩ�ɃN�C�[�����Œ�1�x�~�܂邱�Ƃ��w���D
 * �N�C�[�����Ֆʂɔz�u����Ă���S�Ẵi�C�g��K�₵���̂��C�r�V���b�v(B)��K�₷��o�H���l����D
 * ���́C���̌o�H�ɂ��āC�N�C�[���̈ړ��񐔂��ŏ��C
 * �܂����̒��Ōo�H(�R�}�̈ړ�����)���������ő�(�A���t�@�x�b�g���̐擪)�Ȃ��̂����߂�D
 * �����C�o�H������Ȃ��ꍇ�́uimpossible�v�Əo�͂���D
 *
 * �y��@�z
 * ���D��T��(BFS)�D�N�C�[��(�X�^�[�g�n�_)����1��ł�����}�X��􂢏o���Ă����Ɠ����ɁC
 * �􂢏o���ꂽ�}�X�ɂ����āC�i�C�g��K�₷�邱�ƂɂȂ������𔻒肷��D
 * �}�X�̍��W(64�}�X)�Ƃǂ̃N�C�[����K�₵����(�ő�ł�2^14�p�^�[��)����ԂƂ��āC�ێ�������D
 * �T�����������ɂ��邱�ƂŁC�ŏ��Ɍ��������o�H�𓚂��Ƃ��邱�Ƃ��ł���D
 * �Ȃ̂ŁC1��ňړ��ł���}�X�ɂ��ă\�[�g�������Ă����D
 * �����}�X�ɖ߂��Ă���ꍇ�́C��Ԑ����������C�������[�v������̂ŁC���o�̏�Ԃ����������疳������D
 * (�����ł̏�Ԃ����C�����}�X�ł��C�i�C�g�̖K��󋵂��قȂ�΁C��Ԃ͕ʂ���)
 * NG��@�Ƃ��āu���I�v��@�v�C�u�������ċA(cf. �\�[�X�� p2)(�[���D��T�� DFS)�v�ɂ����g�񂾁D
 * ���ɁC�������ċA�̐[���D��T���ł́C�ŒZ�X�e�b�v���������킩��Ȃ���ԂŁC
 * �o�H�̎��������ӎ����Ȃ��Ƃ����Ȃ��̂ŁC�S�T�����K�v�ƂȂ�C�}��������Ă����s���Ԃ��ǂ��t���Ȃ��D
 *
 * �y�|�C���g�z
 * �o�͂̓V�i���I���Ƃɋ�s�����ށD
 * ����}�X�ɖK�₵���ہC�����̃i�C�g��K�₷�邱�ƂɂȂ�\�����l������K�v������D
 * �܂��C�����̃}�X�ɃN�C�[�������鎞�_�ŁC�i�C�g��K�₷�邱�Ƃ�����΁C�K��ς݂ɂ��Ă����K�v������D
 *
 * �y�������z
 * POJ��Time Limit��5�b�ɐݒ肳��Ă��邪�CN=14�̃f�[�^1��1.3�b�œ����������Ă��C
 * TLE�ƂȂ��Ă���D�ŏI�I�ɂ�0.3�b�܂ō���������Accept����ꂽ�D
 * 1. ����}�X����1��łǂ̃}�X�ɍs���邩�́C�ǂ�ȏ�Ԃł���ӂɒ�܂�D
 * �@�}�X��64�����Ȃ����߁C��x�v�Z������ۑ����C�����v�Z���K�v�ɂȂ�΁C���p���邱�Ƃō���������D
 * �@(moveFasterMap)
 * 2. �ǂ̃}�X�ɃN�C�[��������΁C�ǂ̃i�C�g��K�₷�邱�ƂɂȂ邩�C���O�Ɍv�Z�����D
 * �@(neighboorKnightSerial)
 * 3. ���o�ȏ�ԃ`�F�b�N�̂��߁C�^�U�z���p�ӁD��Ԃ�8*8*(2^14)=��100�����x�����Ȃ��̂�
 * �@�񎟌��z��[x*8+y][1<<14]�Ƃ���΁CO(1)�ŊǗ��\
 * �@(publishedSuper)
 * 4. �i�C�g�������K�˂����𖈉�i�C�g�K��󋵔z��(acKnight)�𒲂ׂĂ͎��Ԃ�������̂ŁC
 * �@goBishop�Ńi�C�g�K�␔���Ǘ��C�i�C�g�o������goBishop���r���邱�ƂŁC���Ƃ̓r�V���b�v������������
 * 5. ��L3�ɂ����āCO(1)�Ŏ擾�ł���悤�C�i�C�g�K��󋵔z��̃p�^�[����ޔԍ��ɓ�������̂�
 * �@�ێ����Ă����Ƃ悢(visitKnightNumber) �p�^�[����2^14=2���サ���Ȃ��D
 *
 * ���e�X�g�� POJ2919test.txt, POJ2919ans.txt ���Q�ƁD
 *
 */
public class POJ2919 {

    /**
     * neighboorKnightSerial�����̂��߂̃N���X
     * �K��ł���i�C�g�̊Ǘ��ԍ���ێ�������D
     */
    class Nei{
	ArrayList<Integer> knightNo;

	public Nei() {
	    this.knightNo = new ArrayList<Integer>();
	}

	/**
	 * �K��ł���i�C�g��ǉ�����
	 * @param n �ǉ�����i�C�g�̊Ǘ��ԍ�
	 */
	public void add(int n){
	    this.knightNo.add(n);
	}
    }

    /**
     * ���W�܂���Ԃ̃N���X
     */
    class Point implements Comparable<Point>{
	int id;	         	        // ��Ԃ�ID
	int x, y;
	int parent;	        	// ���̏�Ԃ̐e(��������ID)
	boolean[] acKnight;		// �i�C�g�̖K��󋵔z��
	int goBishop;			// �i�C�g�̖K�␔
	int visitKnightNumber;		// �i�C�g�̖K��󋵃p�^�[���ԍ�

	/**
	 * ���W�̐����R���X�g���N�^
	 * @param _x
	 * @param _y
	 */
	Point(int _x, int _y){
	    this.x = _x;
	    this.y = _y;
	}

	/**
	 * ���W�Ə�Ԃ̐����R���X�g���N�^
	 * @param _x
	 * @param _y
	 * @param _parent
	 * @param _acKnight
	 * @param _goBishop
	 * @param _visitKnightNumber
	 */
	Point(int _x, int _y, int _parent, boolean[] _acKnight, int _goBishop, int _visitKnightNumber){
	    this.id = -100;	// ���K��ID��U��Ȃ�
	    this.x = _x;
	    this.y = _y;
	    this.parent = _parent;
	    this.goBishop = _goBishop;
	    this.visitKnightNumber = _visitKnightNumber;
	    this.acKnight = new boolean[knightNum];
	    if(_acKnight!=null){
		for(int i=0;i<knightNum;i++){
		    this.acKnight[i] = _acKnight[i];
		}
	    }
	}

	/**
	 * �w�肵���i�C�g��K��ɂ���(�K��ɂ��ĂĂ������)
	 * ����Ɠ����ɁC�i�C�g�̖K�␔�ƃi�C�g�̖K��󋵃p�^�[�����X�V����D
	 * @param checkKnight �K�₷��i�C�g�̊Ǘ��ԍ�
	 */
	public void checkKnight(int checkKnight){
	    if(!this.acKnight[checkKnight]){
		this.acKnight[checkKnight] = true;
		this.goBishop++;
		this.visitKnightNumber += Math.pow(2, checkKnight);
	    }
	}

	/**
	 * �V�K�̏�ԂƂ��Đ��K��ID�𔭍s����D
	 * �܂��C�o�H�̋t�������ł���悤��Ԃ̕ۑ��C��Ԃ����o�Ƃ��Ēǉ������D
	 */
	public void doPublish(){
	    this.id = createID++;
	    published.put(this.id, this);
	    publishedSuper[this.x*MAT+this.y][this.visitKnightNumber] = true;
	}

	//	public String toString() {
	//	    String[] c = {"a","b","c","d","e","f","g","h"};
	//	    return "("+c[y]+(8-x)+")";
	//	}

	//	public String accessKnightString(){
	//		StringBuilder sb = new StringBuilder();
	//		for(int i=0;i<knightNum;i++){
	//			if(this.acKnight[i])
	//				sb.append("o");
	//			else
	//				sb.append("x");
	//		}
	//		return sb.toString();
	//	}

	/**
	 * compareTo�����D�}�X�̎������\�[�g�p
	 */
	@Override
	    public int compareTo(Point o) {
	    return (((8-this.x)+this.y*8)-((8-o.x)+o.y*8));
	}

	/**
	 * �}�X�̖��̂𕶎���Ŏ擾����
	 * @return �}�X�̖��̕�����
	 */
	public String getStr() {
	    String[] c = {"a","b","c","d","e","f","g","h"};
	    return c[this.y]+(8-this.x);
	}
    }

    static int knightNum;	        		// �i�C�g�̐�
    static final int MAT = 8;
    static int[][] field;				// �Ֆʂ̏�
    static HashMap<Integer, Nei> neighboorKnightSerial;	// �e�}�X�ɂ��Ẵi�C�g�K��������X�g
    static List<Point> neighboorBishop;                 // �r�V���b�v�̗אڃ}�X���X�g
    static Point start;					// �����N�C�[��(�X�^�[�g�n�_)
    static final int PAWN = -40;
    static final int QUEEN = -10;
    static final int KNIGHT = -20;
    static final int BISHOP = -30;
    static int createID;				// ��Ԃ̃V���A��ID
    static Map<Integer, Point> published;	        // �o�H�t�����p�̏�ԕۑ�
    static boolean[][] publishedSuper;			// ���o��Ԕ���p
    static HashMap<Integer, List<Point>> moveFasterMap;	// 1��ړ��\�}�X�̕ۑ��p

    public static void main(String[] args)  {
	// System.setOut(new PrintStream(new File("koike.txt")));
	new POJ2919().run();
    }

    /**
     * ���͂Ǝ��s�Ăяo���̐e���\�b�h
     */
    private void run() {
	Scanner stdIn = new Scanner(System.in);
	int d = Integer.valueOf(stdIn.nextLine());
	for(int z=0;z<d;z++){
	    if(z!=0) stdIn.nextLine();
	    field = new int[MAT][MAT];
	    knightNum = 0;
	    for(int i=0;i<MAT;i++){
		char[] line = stdIn.nextLine().toCharArray();
		for(int j=0;j<MAT;j++){
		    if(line[j]=='Q'){
			field[i][j] = QUEEN;
			start = new Point(i, j);
		    }
		    else if(line[j]=='P') field[i][j] = PAWN;
		    else if(line[j]=='N'){
			knightNum++;
			field[i][j] = KNIGHT;
		    }
		    else if(line[j]=='B') field[i][j] = BISHOP;
		}
	    }
	    start = new Point(start.x, start.y, -1, null, 0, 0);
	    standby();
	    System.out.println("Scenario #"+(z+1)+":");
	    // long start = System.currentTimeMillis();
	    solve();
	    // System.out.println((System.currentTimeMillis()-start)*1.0/1000+" sec");
	    System.out.println();
	}
    }

    /**
     * ��v�Z
     */
    private void solve(){
	// ������
	createID = 0;
	published = new HashMap<Integer, Point>();
	publishedSuper = new boolean[MAT*MAT][1<<14];
	moveFasterMap =	new HashMap<Integer,List<Point>>();
	for(int i=0;i<MAT*MAT;i++){
	    publishedSuper[i] = new boolean[1<<14];
	}
	List<Point> nextList = new ArrayList<Point>();

	// �N�C�[��������Ԃł̃i�C�g(�r�V���b�v)�K��Ɋւ��鏈��
	Nei nowAccessKnight2 = neighboorKnightSerial.get(start.x*MAT+start.y);
	Point check2 = new Point(start.x, start.y, start.id,
				 start.acKnight, start.goBishop, start.visitKnightNumber);
	for(Integer nei: nowAccessKnight2.knightNo){
	    check2.checkKnight(nei);
	}
	if(check2.goBishop==knightNum){
	    for(int k=0;k<neighboorBishop.size();k++){
		Point nei = neighboorBishop.get(k);
		if(nei.x==check2.x&&nei.y==check2.y){
		    check2.doPublish();
		    route(check2.id);
		    return;
		}
	    }
	}
	nextList.add(check2);
	check2.doPublish();

	// �����N�C�[��(�X�^�[�g)�n�_���珇�ɏ���
	// int cnt = 0;
	while(true){
	    //	cnt++;
	    //	System.out.println("===== "+cnt+" ("+nextList.size()+") =====");
	    // ���������Ԃ��Ȃ��Ȃ�΁C�o�H�Ȃ��̖ړI�B���s��
	    if(nextList.size()==0){
		System.out.println("impossible");
		return;
	    }
	    // �������X�g�̏�����
	    List<Point> list = null;
	    // �����X�g�𑖍����X�g�փR�s�[
	    list = new ArrayList<Point>(nextList);
	    // �����X�g�̏�����
	    nextList = null;
	    nextList = new ArrayList<Point>();
	    // �������X�g���珇�Ƀ}�X��Ԃ��擾
	    for(Point nowPoint : list){
		// ���}�X�̎擾
		List<Point> possiblePoint = possibleMove(nowPoint);
		// ���}�X�����ɏ���
		for(Point nextPoint : possiblePoint){
		    // ���}�X�Ńi�C�g�K�������
		    Nei nowAccessKnight = neighboorKnightSerial.get(nextPoint.x*MAT+nextPoint.y);
		    Point check = new Point(nextPoint.x, nextPoint.y, nowPoint.id,
					    nowPoint.acKnight, nowPoint.goBishop, nowPoint.visitKnightNumber);
		    for(Integer nei: nowAccessKnight.knightNo){
			check.checkKnight(nei);
		    }
		    // �S�Ẵi�C�g�K�₪�������C�r�V���b�v���B���肪�K�v���m�F
		    if(check.goBishop==knightNum){
			for(int k=0;k<neighboorBishop.size();k++){
			    Point nei = neighboorBishop.get(k);
			    if(nei.x==check.x&&nei.y==check.y){
				// �ړI�B�����̔���
				check.doPublish();
				route(check.id);
				return;
			    }
			}
		    }
		    // ���o��Ԃ��m�F
		    if(!alreadyPublished(check)){
			nextList.add(check);
			check.doPublish();
		    }
		}
	    }
	}
    }

    /**
     * �r�V���b�v(�S�[���}�X)�̗אڃ}�X(�ړI�B���ŏI�}�X)����X�^�[�g(�����N�C�[��)��
     * �k��Ȃ���C�ړ��}�X(�o�H)�̕����񐶐����o�͂���D
     * @param id �r�V���b�v�̗אڃ}�X
     */
    private static void route(int id) {
	String s = "";
	while(id!=0){
	    s = published.get(id).getStr()+s;
	    id = published.get(id).parent;
	}
	s = published.get(id).getStr()+s;
	System.out.println(s);
    }

    /**
     * ���̏�Ԃ����łɊ��o�ł��邩�ǂ������肷��
     * @param target ���肷����Point�N���X
     * @return ���o�ł���
     */
    private static boolean alreadyPublished(Point target){
	if(publishedSuper[target.x*MAT+target.y][target.visitKnightNumber]) return true;
	return false;
    }

    /**
     * ����}�X����1��ňړ��ł���}�X�����X�g�ŕԂ�
     * @param locS ��̃}�X
     * @return �ړ��ł���}�X�̃��X�g
     */
    private List<Point> possibleMove(Point locS) {
	// ���Ɍv�Z�������m�F����
	List<Point> temp = moveFasterMap.get(locS.x*MAT+locS.y);
	if(temp!=null&&temp.size()!=0){
	    // �v�Z�ς݌��ʂ�Ԃ�
	    return temp;
	}
	// �V�K�v�Z�D�㉺���E�C�΂߂̕����Ōv8���
	List<Point> result = new ArrayList<Point>();
	for(int i=locS.x-1;i>=0;i--)
	    if(field[i][locS.y]>-15) result.add(new Point(i, locS.y));
	    else break;
	for(int i=locS.x+1;i<MAT;i++)
	    if(field[i][locS.y]>-15) result.add(new Point(i, locS.y));
	    else break;
	for(int i=locS.y-1;i>=0;i--)
	    if(field[locS.x][i]>-15) result.add(new Point(locS.x, i));
	    else break;
	for(int i=locS.y+1;i<MAT;i++)
	    if(field[locS.x][i]>-15) result.add(new Point(locS.x, i));
	    else break;
	for(int i=1;i<MAT;i++)
	    if(inMat(locS.x+i, locS.y+i)&&field[locS.x+i][locS.y+i]>-15) result.add(new Point(locS.x+i, locS.y+i));
	    else break;
	for(int i=1;i<MAT;i++)
	    if(inMat(locS.x+i, locS.y-i)&&field[locS.x+i][locS.y-i]>-15) result.add(new Point(locS.x+i, locS.y-i));
	    else break;
	for(int i=1;i<MAT;i++)
	    if(inMat(locS.x-i, locS.y+i)&&field[locS.x-i][locS.y+i]>-15) result.add(new Point(locS.x-i, locS.y+i));
	    else break;
	for(int i=1;i<MAT;i++)
	    if(inMat(locS.x-i, locS.y-i)&&field[locS.x-i][locS.y-i]>-15) result.add(new Point(locS.x-i, locS.y-i));
	    else break;

	// �ړ��\�}�X�̃\�[�g
	Collections.sort(result);
	// �v�Z���ʂ̕ۑ�
	moveFasterMap.put(locS.x*MAT+locS.y, result);
	return result;
    }

    /**
     * �r�V���b�v�̗אڃ}�X���X�g�����@�Ɓ@
     * �@�e�}�X�ɂ��Ẵi�C�g�K��������X�g����
     */
    private void standby() {
	// ������
	neighboorBishop = new ArrayList<Point>();
	neighboorKnightSerial = new HashMap<Integer, Nei>();
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++){
		Nei nei = new Nei();
		neighboorKnightSerial.put(i*MAT+j, nei);
	    }
	}

	int cnt = -1;
	for(int i=0;i<MAT;i++){
	    for(int j=0;j<MAT;j++){
		if(field[i][j]==KNIGHT){
		    cnt++;	// �i�C�g�̊Ǘ��ԍ�
		    for(int x=-1;x<2;x++)
			for(int y=-1;y<2;y++)
			    if(inMat(i+x, j+y)&&!(x==0&&y==0)&&field[i+x][j+y]!=PAWN){
				Nei nei = neighboorKnightSerial.get((i+x)*MAT+(j+y));
				nei.add(cnt);
				neighboorKnightSerial.put((i+x)*MAT+(j+y), nei);
			    }
		}
		else if(field[i][j]==BISHOP){
		    for(int x=-1;x<2;x++)
			for(int y=-1;y<2;y++)
			    if(inMat(i+x, j+y)&&!(x==0&&y==0))
				neighboorBishop.add(new Point(i+x, j+y));
		}
	    }
	}
    }

    /**
     * ���W���Ֆʓ��ł��邩
     * @param i x���W
     * @param j y���W
     * @return �Ֆʓ��ł���
     */
    private static boolean inMat(int i, int j) {
	if(i>=0&&i<MAT&&j>=0&&j<MAT) return true;
	return false;
    }
}
