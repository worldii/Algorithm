import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
    public static int n;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    static class FireStorm {
        private int[][] arr;
        private int[][] arrCopy;
        private int size;
        private final int[] dx = {-1, 1, 0, 0};
        private final int[] dy = {0, 0, -1, 1};

        public void printArr(int[][] arr) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }

        FireStorm(int[][] arr, int size) {
            this.arr = arr;
            this.size = size;
            this.arrCopy = new int[size][size];
            for (int i = 0; i < size; i++) {
                arrCopy[i] = Arrays.copyOf(arr[i], size);
            }
        }

        public void copy() {
            this.arrCopy = new int[size][size];
            for (int i = 0; i < size; i++) {
                arrCopy[i] = Arrays.copyOf(arr[i], size);
            }
        }

        public void move(int lev) {
            int level = (int) Math.pow(2, lev);
            rotate(0, 0, size, level);
            decreaseIce();
            copy();
        }

        public void rotate(int x, int y, int len, int level) {
            // 종료 조건
            if (len == 0) return;
            else if (len == level) {
                for (int i = x; i < x + level; i++) {
                    for (int j = y; j < y + level; j++) {
                        int startX = i-x;
                        int startY  = j-y;
                        arr[startX + x][startY+y] = arrCopy[level-startY -1 + x][startX+ y];
                    }
                }
                return ;
            }
            int half = len / 2;
            rotate(x, y, half, level);
            rotate(x, y + half, half, level);
            rotate(x + half, y, half, level);
            rotate(x + half, y + half, half, level);
        }

        public void decreaseIce() {
            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int cnt = 0;
                    for (int t = 0; t < 4; t++) {
                        int nx = i + dx[t];
                        int ny = j + dy[t];
                        if (0 <= nx && nx < size && 0 <= ny && ny < size) {
                            if (arr[nx][ny] > 0)
                            {
                                cnt++;
                            }
                        }
                    }
                   // System.out.println(i + " " +j + cnt );

                    if (cnt < 3) {
                        queue.add(new int[]{i, j});
                    }
                }
            }
            while (!queue.isEmpty()) {
                int[] temp = queue.peek();
                if (arr[temp[0]][temp[1]] > 0) arr[temp[0]][temp[1]]--;
                queue.poll();
            }
        }

        public void calculateSum() {
            int sum = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    sum += arr[i][j];
                }
            }
            System.out.println(sum);
        }

        public int bfs(boolean[][] check, int startX, int startY) {
            check[startX][startY] = true;

            Queue<int[]> q = new LinkedList<>();

            q.add(new int[]{startX, startY});
            int ice = 1;

            while (!q.isEmpty()) {
                int[] temp = q.peek();
                q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + temp[0];
                    int ny = dy[i] + temp[1];

                    if (0 <= nx && nx < size && 0 <= ny && ny < size) {
                        if (!check[nx][ny] && arr[nx][ny] > 0) {
                            check[nx][ny] = true;
                            ice++;
                            q.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            return ice;
        }

        public void calculateCnt() {
            int maxIce = 0;
            boolean[][] check = new boolean[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (!check[i][j] && arr[i][j] > 0) maxIce = Math.max(maxIce, bfs(check, i, j));
                }
            }
            System.out.println(maxIce);
        }

        // 완료
        public void calculate() {
            calculateSum();
            calculateCnt();
        }

    }

    static class FireStormController {
        private FireStorm fireStorm;
        private int[] lev;

        FireStormController(FireStorm fireStorm, int[] lev) {
            this.fireStorm = fireStorm;
            this.lev = lev;
        }

        public void start() {
            for (int i = 0; i < lev.length; i++) {
                fireStorm.move(lev[i]);
            }
            fireStorm.calculate();
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int Q = Integer.parseInt(st.nextToken());

        int realN = (int) Math.pow(2, n);
        int[][] arr = new int[realN][realN];
        for (int i = 0; i < realN; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < realN; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] lev = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {

            lev[i] = Integer.parseInt(st.nextToken());
        }
        FireStormController fireStormController = new FireStormController(new FireStorm(arr, realN), lev);

        fireStormController.start();
    }
}