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

public class Solution {
    public static int n, h, w;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int[][] arr;
    public static int maxCnt = Integer.MAX_VALUE;

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};


    static class Util {
        public static int[][] copy() {
            int[][] newArr = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    newArr[i][j] = 0;
                }
            }
            return newArr;
        }

        public static int[][] getArr(int[][] arr) {
            int[][] newArr = new int[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    newArr[i][j] = arr[i][j];
                }
            }
            return newArr;
        }
    }
    static class Wall {
        public static int breakWall(int curX, int curY, int[][] arr) {
            int num = arr[curX][curY];
            int sum = 1;
            arr[curX][curY] = 0;
            for (int i = 0; i < 4; i++) {
                for (int t = 1; t <= num - 1; t++) {
                    int nextX = curX + dx[i] * t;
                    int nextY = curY + dy[i] * t;
                    if (0 <= nextY && nextY < w && 0 <= nextX && nextX < h) {
                        if (arr[nextX][nextY] > 0) {
                            sum += breakWall(nextX, nextY, arr);
                        }
                    }
                }
            }
            return sum;
        }

        public static void fallWall(int[][] arr, int[][] newArr) {
            for (int i = 0; i < w; i++) {
                Queue<Integer> q = new LinkedList<>();
                for (int j = h - 1; j >= 0; j--) {
                    if (arr[j][i] > 0) q.add(arr[j][i]);
                }
                int idx = h - 1;
                while (!q.isEmpty()) {
                    newArr[idx--][i] = q.poll();
                }
                while (idx >= 0) {
                    newArr[idx--][i] = 0;
                }
            }

        }
    }


    public static void recur(int cnt, int end, int[][] arr, int sum) {

        if (sum == w * h) {
            maxCnt = 0;
            return;
        }
        if (cnt == end) {
            int sum2 = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (arr[i][j] > 0) sum2++;
                }
            }
            maxCnt = Math.min(maxCnt, sum2);
            return;
        }

        for (int i = 0; i < w; i++) {
            int curH = 0;
            while (curH < h && arr[curH][i] == 0) {
                curH++;
            }
            if (curH == h) continue;
            int[][] newArr = Util.getArr(arr);
            // 벽돌 깨기
            int newSum = Wall.breakWall(curH, i, newArr);
            int[][] newArr2 = Util.copy();
            Wall.fallWall(newArr, newArr2);
            // 결과 newArr 에 담기
            recur(cnt + 1, end, newArr2, sum + newSum);
        }

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int tt = 1; tt <= t; tt++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            arr = new int[h][w];
            maxCnt = Integer.MAX_VALUE;
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            recur(0, n, arr, 0);
            System.out.println("#" + tt + " " + maxCnt);
        }
    }
}