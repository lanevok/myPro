import java.util.*;
 
public class Main {
 
//AOJ1144

    boolean[][] map;        // true --> 障害物
    Pos s, g;
    int h, w, ans;
     
    class Pos{
        int x;
        int y;
        Pos(int _x, int _y){
            x = _x;
            y = _y;
        }
    }
     
    // p座標からmap内にいるか返す
    boolean Inbox(Pos p){
        if(p.x<0||p.y<0||p.x>=h||p.y>=w){
            return false;
        }
        else{
            return true;
        }
    }
     
    // q座標から直接ゴール地点g座標に到達できるか
    boolean check(Pos q){
        Pos p = new Pos(0,0);
        p.x = q.x;
        p.y = q.y;
        Pos gt = new Pos(0,0);
        gt.x = g.x;
        gt.y = g.y;
        // 判定方法は、xかy方向でp.dirとg.dirの間に障害物がないか
        if(p.y==g.y){
            if(p.x>gt.x){
                int tmp = p.x;
                p.x = gt.x;
                gt.x = tmp;
            }
            for(int i=p.x;i<=gt.x;i++){
                if(map[i][g.y]){
                    return false;
                }
            }
        }
        else if(p.x==g.x){
            if(p.y>gt.y){
                int tmp = p.y;
                p.y = gt.y;
                gt.y = tmp;
            }
            for(int i=p.y;i<=gt.y;i++){
                if(map[g.x][i]){
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }
     
    // q座標からdir方向に移動させ、止まった座標を返す
    Pos proceed(Pos q, int dir){
        Pos p = new Pos(0,0);
        p.x = q.x;
        p.y = q.y;
//      System.out.println("   proceed call ("+p.x+","+p.y+") "+dir);
        while(true){
            if(dir==0){
                p.x++;
            }
            else if(dir==1){
                p.x--;
            }
            else if(dir==2){
                p.y++;
            }
            else if(dir==3){
                p.y--;
            }
            if(Inbox(p)){
                if(!map[p.x][p.y]){             
                    continue;
                }
                else{
                    if(dir==0){
                        p.x--;
                    }
                    else if(dir==1){
                        p.x++;
                    }
                    else if(dir==2){
                        p.y--;
                    }
                    else if(dir==3){
                        p.y++;
                    }
                    break;
                }
            }
            else{
                return null;
            }
        }
//      System.out.println("   proceed return ("+p.x+","+p.y+")");
        return p;
    }
     
    // p座標から1つi方向のマスの障害物を破壊する
    // 止まったp座標のdir方向次のマスの障害物を破壊する
    void Attack(Pos p, int i, boolean flag){
        if(i==0){
            map[p.x+1][p.y] = flag;
        }
        else if(i==1){
            map[p.x-1][p.y] = flag; 
        }
        else if(i==2){
            map[p.x][p.y+1] = flag;
        }
        else if(i==3){
            map[p.x][p.y-1] = flag;
        }
    }
     
    // バックトラック。引数は、p座標でcnt回目を動かす階層を表現
    void Search(Pos p, int cnt){
//      System.out.println("search ("+p.x+","+p.y+") "+cnt+"回目");
        // 現在のp座標から直接ゴール地点g座標に到達できるか
        if(check(p)){
//          System.out.println("pからゴール到達");
            if(ans==-1||ans>cnt){
                ans = cnt;
            }
        }
        // 移動回数限界内で
        else if(cnt++<10){
            // 現在のp座標から4方向に移動
            for(int i=0;i<4;i++){
                // 現在のp座標からi方向に移動させ、止まったq座標を求める
                Pos q = proceed(p,i);
                // 止まった座標がmapの外に出た もしくはの方向には動かせない
                if(q==null||(p.x==q.x&&p.y==q.y)){
                    continue;
                }
//              System.out.println(" "+cnt+" moved ("+q.x+","+q.y+")");
                // 障害物の破壊
                Attack(q,i,false);
                // q座標の再帰
                Search(q,cnt);
                // 障害物の修復
                Attack(q,i,true);
//              System.out.println("  repair");
            }
        }
    }
 
    void run(){
        Scanner stdIn = new Scanner(System.in);
        while(true){
            w = stdIn.nextInt();
            h = stdIn.nextInt();
            if((w|h)==0){
                return;
            }
            map = new boolean[h][w];
            s = new Pos(0,0);
            g = new Pos(0,0);
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    int tmp = stdIn.nextInt();
                    if(tmp==1){
                        map[i][j] = true;
                    }
                    else if(tmp==2){
                        s.x = i;
                        s.y = j;
                    }
                    else if(tmp==3){
                        g.x = i;
                        g.y = j;
                    }
                }
            }
            ans = -1;
            Search(s,1);
            System.out.println(ans);
        }
    }
     
    public static void main(String[] args) {
        new Main().run();
    }
}