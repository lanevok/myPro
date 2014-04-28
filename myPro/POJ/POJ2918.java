import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 数独。ナンバープレイス。
 * 必ず一意に定まる問題が出題される。
 * 空白セルに入るべき値を入れるという問題。
 *
 * ここまで複雑なデータ構造も用いなくても解ける。
 * 
 */
public class POJ2918 {
	Cell cell[][];		// セル型セル盤面
	Constraint pRow[], pCalm[], pBlock[];	// 9個ずつのCellを管理するConstraint

	// セルクラス
	class Cell{
		int value;		// セルの値
		Constraint inRow, inCalm, inBlock;		// 属するConstraint制約

		// コンストラクタ
		public Cell(int _value, Constraint _row, Constraint _calm, Constraint _block) {
			this.value = _value;
			this.inRow = _row;
			this.inCalm = _calm;
			this.inBlock = _block;
		}
	}

	// Constraint制約クラス
	class Constraint implements Comparable<Constraint>{
		int cnt;		// 埋まっていない(0の)個数

		// Comparable実装
		public int compareTo(Constraint o) {
			return this.cnt - o.cnt;
		}
	}

	// 入力時のセルに値を設定するメソッド
	void makeCell(int i, int j, int setValue) {
		// 大域Constraintを持たせる
		cell[i][j] = new Cell(setValue, pRow[i], pCalm[j], pBlock[i/3*3+j/3]);
		// 空白セルの場合の処理．
		if (setValue==0) {
			// 該当の大域Constraintをインクリメントする
			pRow[i].cnt++;
			pCalm[j].cnt++;
			pBlock[i/3*3+j/3].cnt++;
		}
	}

	// ある1Constraint制約(行,列,ブロック)のCell配列を抽出する
	Cell[] search(Constraint targetConst) {
		Cell targetCell[] = new Cell[9];	// 抽出するCell配列
		int idx = 0;	// 抽出するCell配列で用いるインデックス
		// 盤面を走査
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				// 現在のcell[i][j]がtargetConstの1Constraint制約であるかの判定
				if (cell[i][j].inRow==targetConst||cell[i][j].inCalm==targetConst||cell[i][j].inBlock==targetConst) {
					// 1Constraint制約内のセルであれば，targetCelとして抽出する
					targetCell[idx++] = cell[i][j];
				}
			}
		}
		return targetCell;
	}

	// targetCellの空白セルに値を設定する
	void settting(Cell[] targetCell) {
		boolean[] flag = new boolean[9];		// 出現する値
		int idx = 0;	// 空白セルのインデックス
		for (int i=0;i<9;i++){
			if (targetCell[i].value==0){
				idx = i;
			}
			else{
				flag[targetCell[i].value-1] = true;
			}
		}
		// 出現しない値を走査しインデックスに格納
		for (int i=0;i<9;i++){
			if (!flag[i]){
				targetCell[idx].value = i+1;
			}
		}
		// 空白セルにおける各制約内の空白セルのデクリメント
		targetCell[idx].inRow.cnt--;
		targetCell[idx].inCalm.cnt--;
		targetCell[idx].inBlock.cnt--;
	}

	void run(){
		Scanner stdIn = new Scanner(System.in);
		cell = new Cell[9][9];
		pRow = new Constraint[9];
		pCalm = new Constraint[9];
		pBlock = new Constraint[9];

		// シナリオ
		int sNo = stdIn.nextInt();
		for (int sNow=1;sNow<=sNo;sNow++) {
			// 初期化(シナリオごとに必要)
			for (int i=0;i<9;i++){
				pRow[i] = new Constraint();
				pCalm[i] = new Constraint();
				pBlock[i] = new Constraint();
			}
			// 入力
			for (int i=0;i<9;i++){
				char[] inputLine = stdIn.next().toCharArray();
				for (int j=0;j<9;j++){
					// セルの作成
					makeCell(i, j, inputLine[j]-'0');
				}
			}
			// cntを持ったrow,calm,blockそれぞれ9個ずつの
			// ConstraintをQueueに入れる
			PriorityQueue<Constraint> queue = new PriorityQueue<Constraint>();
			for (int i = 0; i < 9; i++){
				queue.offer(pRow[i]);
				queue.offer(pCalm[i]);
				queue.offer(pBlock[i]);
			}
			// Queueに対して操作
			while (!queue.isEmpty()){
				Constraint targetConst = queue.poll();	// 取り出す
				if (targetConst.cnt==0) continue;	// 0であれば次を取り出す
				Cell targetCell[] = search(targetConst);		// 1Constraint制約からtargetCell取り出す
				settting(targetCell);	// targetCellに値を設定する

				// キューの更新作業
				PriorityQueue<Constraint> updateQueue = new PriorityQueue<Constraint>();
				while (!queue.isEmpty()){
					updateQueue.offer(queue.poll());
				}
				queue = updateQueue;
			}

			// 出力
			System.out.println("Scenario #" + sNow + ":");
			for (int i=0;i<9;i++){
				for (int j=0;j<9;j++){
					System.out.print(cell[i][j].value);
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		new POJ2918().run();
	}
}