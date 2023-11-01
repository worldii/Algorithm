import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n, m;

    public static int[][] map = null;
    public static List<Cor> cors = new ArrayList<>();

    public static class Cor {

        int x;
        int y;

        public Cor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int zCount;
    public static void main(String[] args) throws IOException {
        /*
         *
         * 첫째 줄에 연구소의 크기 N(4 ≤ N ≤ 50), 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)이 주어진다.
         * 둘째 줄부터 N개의 줄에 연구소의 상태가 주어진다. 0은 빈 칸, 1은 벽, 2는 비활성 바이러스의 위치이다.
         *  2의 개수는 M보다 크거나 같고, 10보다 작거나 같은 자연수이다.
         * */
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    cors.add(new Cor(i, j));
                }

                if (map[i][j] == 1) map[i][j] = -1;
                if (map[i][j] == 0) zCount++;
            }
        }

        int[] arr = new int[m];
        recur(0, m, arr, 0);
        if (maxNum== Integer.MAX_VALUE) maxNum=-1;
        System.out.println(maxNum);
    }

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static int bfs(int[] arr) {
        Queue<int[]> curQ = new LinkedList<>();
        boolean[][] check = new boolean[n][n];

        int[][] arr2 =  new int [n][n];
        for (int i = 0 ; i< arr2.length ; i++) {
            for (int j = 0 ; j< arr2.length ; j++) {
                arr2[i][j] = Integer.MAX_VALUE;
            }
        }
        int tempZ= zCount;
        for (int i = 0; i < arr.length; i++) {
            int idx = arr[i];
            int x = cors.get(idx).x;
            int y = cors.get(idx).y;
            check[x][y] = true;
            curQ.add(new int[]{x, y, 0});
            arr2[x][y] = 0;
        }

        while (!curQ.isEmpty()) {
            int[] temp = curQ.poll();
            if (map[temp[0]][temp[1]] == 0) tempZ--;

            if (tempZ == 0) return temp[2];

            for (int j = 0; j < 4; j++) {
                int nextX = temp[0] + dx[j];
                int nextY = temp[1] + dy[j];
                if (0 > nextX || nextX >= n || nextY >= n || 0 > nextY) {
                    continue;
                }
                if (check[nextX][nextY]) {
                    continue;
                }
                if (map[nextX][nextY] == -1) {
                    continue;
                }
                curQ.add(new int[]{nextX, nextY, temp[2] + 1});
                check[nextX][nextY] = true;
                arr2[nextX][nextY] = temp[2] + 1;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static int maxNum = Integer.MAX_VALUE;

    public static void recur(int idx, int end, int[] arr, int start) {
        if (idx == end) {
            // recur
            maxNum = Math.min(maxNum, bfs(arr));
            return;
        }
        for (int i = start; i < cors.size(); i++) {
            arr[idx] = i;
            recur(idx + 1, end, arr, i + 1);
        }
    }

}