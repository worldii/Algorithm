import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int n, m;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static int [][] copyArr (int [][] a) {
        int [][]newArr =new int[n][m];
        for (int i = 0 ; i< n ; i++) {
            for (int j = 0 ; j< m ; j++) {
                newArr[i][j] = a[i][j];
            }
        }
        return newArr;
    }
    public static int findNumByBfs(int[][] a) {
        boolean[][] checked = new boolean[n][m];
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] != 0 && !checked[i][j]) {
                    bfs(checked, i, j, a);
                    num++;
                }
            }
        }
        return num;
    }

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void bfs(boolean[][] check, int startX, int startY, int[][] a) {
        check[startX][startY] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});

        while (!q.isEmpty()) {
            int[] temp = q.peek();
            q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + temp[0];
                int nextY = dy[i] + temp[1];
                if (0 <= nextY && nextY < m && 0 <= nextX && nextX < n) {
                    if (check[nextX][nextY]) continue;
                    if (a[nextX][nextY] == 0) continue;
                    check[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY});
                }
            }
        }
    }

    public static void decreaseArr(int[][] a) {
       int [][] b= copyArr(a);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (b[i][j] > 0) {
                    int num = zeroNum(i, j,b);
                    a[i][j] -= num;
                    if (a[i][j] <0) a[i][j] = 0;
                }
            }
        }
    }

    public static boolean checkTwoSeom(int[][] a) {
        int num = findNumByBfs(a);
        return num >= 2;
    }

    public static boolean checkEmptySeom(int[][] a) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] != 0) return false;
            }
        }
        return true;
    }

    public static int zeroNum(int startX, int startY, int [][] a) {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = startX + dx[i];
            int nextY = startY + dy[i];
            if (0 <= nextY && nextY < m && 0 <= nextX && nextX < n) {
                if (a[nextX][nextY] == 0) num++;
            }
        }
        return num;
    }

    public static int[][] a;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        while (!checkEmptySeom(a)) {
            if (checkTwoSeom(a)) {
                System.out.println(sum);
                return;
            }
            decreaseArr(a);
            sum++;
            //printArr(a);
        }
        System.out.println(0);
    }

    public static void printArr(int[][] a) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(a[i][j]+ " ");
            }
            System.out.println();
        }
    }
}