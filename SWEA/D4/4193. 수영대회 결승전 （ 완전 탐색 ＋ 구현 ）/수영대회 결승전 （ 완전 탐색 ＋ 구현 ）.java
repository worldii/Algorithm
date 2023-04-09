import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static StringTokenizer st = null;
    public static int[][] arr;

    public static class Cor {
        int x;
        int y;

        Cor(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }

    public static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int testcase = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= testcase; tc++) {
            ArrayList<Cor> obstacle = new ArrayList<>();
            int n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) arr[i][j] = -1;
                    else if (arr[i][j] == 2) obstacle.add(new Cor(i, j));
                }
            }

            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            Cor start = new Cor(startX, startY);

            st = new StringTokenizer(br.readLine());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            Cor end = new Cor(endX, endY);

            Queue<Cor> q = new LinkedList<>();
            q.add(start);
            boolean[][] check = new boolean[n][n];
            check[start.x][start.y] = true;
            Queue<Cor> nextQ = new LinkedList<>();
            int cnt = 0;
            outer: while (true)
            {
                while (!q.isEmpty()) {
                    Cor temp = q.peek();
                    q.poll();
                    if (temp.x == end.x && temp.y == end.y) {
                        break outer;
                    }
                    for (int i = 0; i < 4; i++) {
                        int nextX = dx[i] + temp.x;
                        int nextY = dy[i] + temp.y;
                        if (0 <= nextX && 0 <= nextY && nextY < n && nextX < n) {
                            if (check[nextX][nextY]) continue;
                            if (arr[nextX][nextY] == -1) continue;
                            if (arr[nextX][nextY] == 0) {
                                nextQ.add(new Cor(nextX, nextY));
                                check[nextX][nextY] = true;
                            }
                            if (arr[nextX][nextY] > 0) {
                                nextQ.add(temp);
                            }
                        }
                    }
                }
                if (nextQ.isEmpty()) {
                    cnt = -1;
                    break outer;
                }
                q = nextQ;
                nextQ = new LinkedList<>();
                for (int i = 0; i < obstacle.size(); i++) {
                    if (arr[obstacle.get(i).x][obstacle.get(i).y] == 0) {
                        arr[obstacle.get(i).x][obstacle.get(i).y] = 2;
                    } else {
                        arr[obstacle.get(i).x][obstacle.get(i).y]--;
                    }
                }

                cnt++;
            }

            System.out.println("#"+tc+ " " + cnt);
        }
    }

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
}
