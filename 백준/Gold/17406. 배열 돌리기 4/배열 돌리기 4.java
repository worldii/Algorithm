import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

class Rotate {
    int r;
    int c;
    int s;

    Rotate(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}

public class Main {
    public static int[][] arr;
    public static int sum=Integer.MAX_VALUE;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int k, n, m;
    public static boolean[] checked = null;
    public static List<Rotate> list = new ArrayList<>();

    public static void rotate(int num, int[][] arr) {
        int r = list.get(num).r - 1;
        int c = list.get(num).c - 1;
        int s = list.get(num).s;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        for (int t = 1; t <= s; t++) {
            int[] tempArr = new int[n * 4];
            int idx = 0;
            int width = t * 2 + 1;
            int curX = r - t;
            int curY = c - t;
            int cnt = 0;
            for (int i = 1; i <= (width - 1) * 4; i++) {
                tempArr[idx++] = arr[curX][curY];
                curX = dx[cnt] + curX;
                curY = dy[cnt] + curY;
                if (i % (width - 1) == 0) cnt = (cnt + 1) % 4;
            }

            curX = r-t;
            curY = c-t+1;
            cnt = 0;
            int tempidx = 0;
            for (int i = 2; i <= (width - 1) * 4; i++) {
                arr[curX][curY] =  tempArr[tempidx++];
                curX = dx[cnt] + curX;
                curY = dy[cnt] + curY;
                if (i % (width - 1) == 0) cnt = (cnt + 1) % 4;
            }
            arr[r-t][c-t] = tempArr[idx-1];

        }
    }

    public static int [][] copy (int [][] arr) {
        int [][]copy =new int [arr.length][];
        for (int i = 0 ;i < arr.length ; i++) {
            copy[i] = new int [arr[i].length];
            for (int j = 0 ; j< arr[i].length; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    public static void recur(int cnt, int[] temp, boolean[] checked) {
        if (cnt == k) {
            int [][] arr2 =copy(arr);

            for (int i = 0; i < k; i++) {
                rotate(temp[i], arr2);
            }
            int maxNum = Integer.MAX_VALUE;
            for (int i = 0 ; i< n ; i++ ) {
                int sum = 0;
                for (int j = 0 ; j< m ; j++) {
                    sum += arr2[i][j];
                }
                if (maxNum >sum) maxNum = sum;

            }
            sum = Math.min(sum, maxNum);
            return;
        }
        for (int i = 0; i < k; i++) {
            if (checked[i]) continue;

            checked[i] = true;
            temp[cnt] = i;
            recur(cnt + 1, temp, checked);
            temp[cnt] = 0;
            checked[i] = false;

        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            list.add(new Rotate(r, c, s));
        }
        int[] temp = new int[k];
        checked = new boolean[k];

        recur(0, temp, checked);
        System.out.println(sum);
    }
}