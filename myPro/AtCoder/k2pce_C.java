mport java.util.Scanner;
 
public class Main {
 
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		// ii番目の成分、jj番目の成分
		int ii = stdIn.nextInt();
		int jj = stdIn.nextInt();
		
		// 群数列で考える　i成分とj成分の和
		// ii番目がおよそ何群目にあるか
		int start = (int) Math.ceil(Math.sqrt(ii));
		int tmp = 0;
		int before = tmp;
		int index = 0;
		// 何群目か正確に探索する
		for(int i=start;;i++){
			// 等差数列の和の公式を利用
			tmp = (((i-1)*i)/2);
			if(tmp>=ii){
				break;
			}
			// 結果の保存
			before = tmp;
			index = i;
		}
		// 判定した群からその何番目かを調べる
		int sa = ii-before-1;
		int hidari = index-sa;
		int migi = index+1-hidari;
		// ii番目のi成分とj成分を得る
		int p1i = hidari;
		int p1j = migi;
		
		// 以下同様にjj番目も
		start = (int) Math.ceil(Math.sqrt(jj));
		tmp = 0;
		before = tmp;
		index = 0;
		for(int j=start;;j++){
			tmp = (((j-1)*j)/2);
			if(tmp>=jj){
				break;
			}
			before = tmp;
			index = j;
		}
		sa = jj-before-1;
		hidari = index-sa;
		migi = index+1-hidari;
		int p2i = hidari;
		int p2j = migi;
		
		// 成分の和を取る
		int p3i = p1i+p2i;
		int p3j = p1j+p2j;
		int p3 = p3i+p3j-1;
		// 第p3-1群まで何項あるか
		int mae = ((p3-1)*p3)/2;
		int amari = p3-p3i+1;
		// 最終的な結果
		System.out.println(mae+amari);
	}
}