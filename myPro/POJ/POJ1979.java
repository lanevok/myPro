import java.io.*;
import java.util.Scanner;
 
public class Main {
 
//AOJ1130

    private static int h;
    private static int w;
    static boolean[][] access;
    static boolean[][] map;
    static int cnt;
     
    static void Search(int x, int y){
        if(!access[x][y]){
            cnt++;
            access[x][y]=true;
            if(x+1<h&&map[x+1][y]){
                Search(x+1,y);
            }
            if(y+1<w&&map[x][y+1]){
                Search(x,y+1);
            }
            if(x-1>=0&&map[x-1][y]){
                Search(x-1,y);
            }
            if(y-1>=0&&map[x][y-1]){
                Search(x,y-1);
            }
        }
    }
     
    public static void main(String[] args) throws IOException {
        Scanner stdIn = new Scanner(System.in);
 
        while(true){
            w = stdIn.nextInt();
            h = stdIn.nextInt();
            if(w==0&&h==0){
                return;
            }
            access = new boolean[h][w];
            map = new boolean[h][w];
            int x = -1, y = -1;
            for(int i=0;i<h;i++){
                char[] tmp = stdIn.next().toCharArray();
                for(int j=0;j<w;j++){
                    if(tmp[j]=='.'){
                        map[i][j]=true;
                    }
                    else if(tmp[j]=='@'){
                        x = i;
                        y = j;
                    }
                }
            }
            cnt = 0;
            Search(x,y);
            System.out.println(cnt);
        }
    }
}