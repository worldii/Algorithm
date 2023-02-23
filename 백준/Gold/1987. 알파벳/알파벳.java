import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Cor {
    int x;
    int y;
    boolean[] route;

    Cor(int x, int y, boolean[] route) {
        this.x = x;
        this.y = y;
        this.route = route;
    }


}

public class Main {

    public static int R, C;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static char[][] arr;
    public static boolean[] alph;
    public static int[][] dist;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};

    public static int minNum = -1;

    public static void recur (int startX, int startY, int cnt) {
        minNum = Math.max(cnt, minNum);
        for (int i = 0 ;  i< 4;  i++) {
            int nextX = dx[i] + startX ;
            int nextY = dy[i] + startY;
            if (0<= nextX && nextX < R && 0<= nextY && nextY <C) {
                if (! alph[arr[nextX][nextY] - 'A']) {
                    alph[arr[nextX][nextY]-'A'] = true;
                    recur(nextX, nextY, cnt+1);
                    alph[arr[nextX][nextY]-'A'] = false;

                }
            }


        }
    }
    public static void main(String[] args) throws IOException {


        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alph = new boolean[30];
        dist = new int[R][C];
        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        alph[arr[0][0]-'A'] = true;
        recur(0,0,1);
        System.out.println(minNum);





    }
}