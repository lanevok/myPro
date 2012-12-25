import java.util.Scanner;
 
public class Main {
 
//AOJ1136

    static int n;           // 探す折れ線の本数
    static int m0;      // 基準の折れ線頂点数
    static int mt;      // 対象の折れ線頂点数
    static int[][] p0;  // 基準の折れ線座標
    static int[][] pt;  // 対象の折れ線座標
    static int[] l0;        // 基準の折れ線を生成する長さ
    static int[] lt;        // 対象の降り戦を生成する長さ
    static int[] v0;    // 基準の折れ線を生成するベクトル(向きのみ)
    static int[] vt;        // 対象の折れ線を生成するベクトル(向きのみ)
     
    static int length(int x1, int y1, int x2, int y2){
        if(y1==y2){
            // 上下方向の直線
            if(x1>x2){
                // 上方向
                return x1-x2;
            }
            else{
                // 下方向
                return x2-x1;
            }
        }
        else{
            if(y1>y2){
                // 左方向
                return y1-y2;
            }
            else{
                // 右方向
                return y2-y1;
            }
        }
    }
     
    static int vector(int x1, int y1, int x2, int y2){
        if(y1==y2){
            // 上下方向の直線
            if(x1>x2){
                // 上方向
                return 2;
            }
            else{
                // 下方向
                return 4;
            }
        }
        else{
            if(y1>y2){
                // 左方向
                return 1;
            }
            else{
                // 右方向
                return 3;
            }
        }
    }
     
    static void len_vecMake(int f){
        if(f==0){
            l0 = new int[m0-1];
            v0 = new int[m0-1];
            for(int i=0;i<m0-1;i++){
                l0[i] = length(p0[i][0],p0[i][1],p0[i+1][0],p0[i+1][1]);
                v0[i] = vector(p0[i][0],p0[i][1],p0[i+1][0],p0[i+1][1]);
            }
        }
        else{
            lt = new int[mt-1];
            vt = new int[mt-1];
            for(int i=0;i<mt-1;i++){
                lt[i] = length(pt[i][0],pt[i][1],pt[i+1][0],pt[i+1][1]);
                vt[i] = vector(pt[i][0],pt[i][1],pt[i+1][0],pt[i+1][1]);
            }
        }
    }
     
    static boolean vecMatch(){
        for(int i=0;i<m0-i;i++){
            if(v0[i]!=vt[i]){
                return false;
            }
        }
        return true;
    }
     
    static void turnVec(){
        for(int i=0;i<m0-1;i++){
            vt[i]++;
            if(vt[i]==5){
                vt[i] = 1;
            }
        }
        return;
    }
     
    static void reverse(){
        for(int i=0;i<mt-1;i++){
            vt[i]+=2;
            if(vt[i]==5){
                vt[i] = 1;
            }
            else if(vt[i]==6){
                vt[i] = 2;
            }
        }
        int[] tmp = new int[mt-1];
        for(int i=0;i<mt-1;i++){
            tmp[i] = vt[i];
        }
        for(int i=0;i<mt-1;i++){
            vt[i] = tmp[mt-2-i];
        }
    }
     
    static boolean judgeMatch(){
        // 折り曲げる回数(正式には折れ線頂点数)の不一致
        if(m0!=mt){
//          System.out.println("折り曲げ回数不一致");
            return false;
        }
         
        // 折り曲げる個々の箇所までの長さ不一致
        boolean flag = true;        // 端点両方チェック用
        for(int i=0;i<m0-1;i++){
            if(l0[i]!=lt[i]){
                flag = false;
//              System.out.println("順方向長さ不一致(フラグ立つ)");
                break;
            }
        }
        if(flag){
            // 順方向での長さ一致、ベクトル(向き)の確認
//          System.out.println("順方向で長さ一致、向き探索開始");
            int i = 0;
            while(true){
                boolean tmp = vecMatch();
                if(tmp){
                    // 答えとしての一致
//                  System.out.println("長さ向き一致");
                    return true;
                }
                else if(i==3){      // 回転は3回試す
                    break;
                }
                else{
                    i++;
//                  System.out.print("回転なう1");
                    turnVec();
//                  System.out.print(" 対象の向きは ");
//                  for(int z=0;z<mt-1;z++){
//                      System.out.print(vt[z]+" ");
//                  }
//                  System.out.println();
                }
            }
        }
        // 一方の端点長さで不一致(折り曲げる長さ)
        flag = true;        // なくても1
        for(int i=0;i<m0-1;i++){
            if(l0[i]!=lt[m0-2-i]){
//              System.out.println("二(逆)方向長さ不一致return");
                return false;
            }
        }
        if(flag){       // なくても2
            // 逆方向での長さ一致、ベクトル(向き)の確認
//          System.out.println("逆方向で長さ一致、向き探索開始");
             
//          System.out.print("リバース前 ");
//          for(int z=0;z<mt-1;z++){
//              System.out.print(vt[z]+" ");
//          }
//          System.out.println();
             
            reverse();      // 逆順に並べ替えておく(turnVecに依存の為)
             
//          System.out.print("リバース後 ");
//          for(int z=0;z<mt-1;z++){
//              System.out.print(vt[z]+" ");
//          }
//          System.out.println();
             
            int i = 0;
            while(true){
                boolean tmp = vecMatch();
                if(tmp){
                    // 答えとしての一致
//                  System.out.println("逆方向向き一致(答え)");
                    return true;
                }
                else if(i==3){      // 回転は3回試す
                    return false;
                }
                else{
                    i++;
                    turnVec();
//                  System.out.print("回転なう2");
//                  System.out.print(" 対象の向きは ");
//                  for(int z=0;z<mt-1;z++){
//                      System.out.print(vt[z]+" ");
//                  }
//                  System.out.println();
                }
            }
        }
//      System.out.println("Error");
        return false;
    }
     
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
         
        while(true){
            n = stdIn.nextInt();
            if(n==0){
                return;
            }
             
            // 基準0の処理
            m0 = stdIn.nextInt();
            p0 = new int[m0][2];
            for(int i=0;i<m0;i++){
                for(int j=0;j<2;j++){
                    p0[i][j] = stdIn.nextInt();
                }
            }
            len_vecMake(0);
             
//          System.out.println();
//          System.out.print("基準の向きは ");
//          for(int z=0;z<m0-1;z++){
//              System.out.print(v0[z]+" ");
//          }
//          System.out.println();
             
            // 対象targetの処理
            for(int i=1;i<=n;i++){
                mt = stdIn.nextInt();
                pt = new int[mt][2];
                for(int j=0;j<mt;j++){
                    for(int k=0;k<2;k++){
                        pt[j][k] = stdIn.nextInt();
                    }
                }
                len_vecMake(1);
                 
//              System.out.print("対象 "+i+" の向きは ");
//              for(int z=0;z<mt-1;z++){
//                  System.out.print(vt[z]+" ");
//              }
//              System.out.println();
                 
                boolean ans = judgeMatch();
//              System.out.println("Judge Fin");
                if(ans){
//                  System.out.print("OK ----------------------------> ");
                    System.out.println(i);
                }
            }
            System.out.println("+++++");
        }
    }
}