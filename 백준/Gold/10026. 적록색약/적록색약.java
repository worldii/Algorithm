import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [키워드]
 * <p>
 * [풀이과정]
 * <p>
 * [입력]
 * [출력]
 *
 * @author SSAFY
 * @performance
 * @category #그래프, #BFS
 * @see
 */

class Cor {
    int x;
    int y;

    Cor(int x, int y) {
        this.x = x;
        this.y = y;

    }
}

public class Main {

    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};
    public static int n;
    public static char[][] arr;

    public static Queue<Cor> q = new LinkedList<>();

    public static void bfs(int startx, int starty,char color, boolean[][] checked, char [][] arr) {

        checked[startx][starty]  = true;
        q.add(new Cor (startx, starty));
        while (!q.isEmpty()) {
            Cor temp = q.peek();
            q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + temp.x;
                int nextY = dy[i] + temp.y;
                if (0 <= nextY && nextY < n && 0 <= nextX && nextX < n) {
                    if (arr[nextX][nextY] == color & !checked[nextX][nextY]) {
                        checked[nextX][nextY] = true;
                        q.add(new Cor(nextX, nextY));
                    }
                }

            }
        }
        return;
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {


        //st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(br.readLine());
        char [][]arr2 = new char [n][n];
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
           arr[i] = br.readLine().toCharArray();

           for (int  j= 0  ;j < n ; j++) {
               arr2[i][j] = arr[i][j];
               if( arr[i][j] == 'G') arr2[i][j] = 'R';
           }
        }


        // 적록 구하기
        int sum1= 0;
        boolean [][] check = new boolean [n][n];
        for (int i = 0 ; i< n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (!check[i][j]) {
                    check[i][j] = true;
                    bfs(i,j, arr2[i][j], check, arr2);
                    sum1++;
                }
            }
        }

     //   System.out.println(sum1);
        q =new LinkedList<>();

        int sum2= 0;
        check = new boolean [n][n];
        for (int i = 0 ; i< n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (!check[i][j]) {
                    check[i][j] = true;
                    bfs(i,j, arr[i][j], check, arr);
                    sum2++;
                }
            }
        }
        System.out.println(sum2+ " "+ sum1);
    }
}