import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Cor {
    int x;
    int y;

    Cor(int x, int y) {
        this.x = x;
        this.y = y;

    }
}


public class Main {

    public static int getSubSum(int x1, int y1, int x2, int y2) {
        // System.out.println(subSum[x2][y2] - subSum[x1-1][y2] - subSum[x2][y1-1] + subSum[x1-1][y1-1]);
        return (subSum[x2][y2] - subSum[x1 - 1][y2] - subSum[x2][y1 - 1] + subSum[x1 - 1][y1 - 1]);
    }

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    public static int n, m, H, W;

    public static int[][] dist;

    public static int[][] subSum;
    public static int[][] checked;

    public static void bfs(Cor start, Cor end) {
        checked[start.x][start.y] = 1;
        Queue<Cor> q = new LinkedList<>();
        q.add(start);

        dist[start.x][start.y] = 0;
        checked[start.x][start.y] = 1;
        while (!q.isEmpty()) {
            Cor temp = q.peek();
            q.poll();
            if (temp.x == end.x && temp.y == end.y) break;
            for (int i = 0; i < 4; i++) {
                int nextX = temp.x + dx[i];
                int nextY = temp.y + dy[i];
                int nextX2 = nextX + H - 1;
                int nextY2 = nextY + W - 1;
                if (1 <= nextX && nextX <= n && 1 <= nextY && nextY <= m) {
                    if (1 <= nextX2 && nextX2 <= n && 1 <= nextY2 && nextY2 <= m) {
                        int tempSubSum = getSubSum(nextX, nextY, nextX2, nextY2);
                        if (checked[nextX][nextY] == 0 && tempSubSum == 0) {
                            checked[nextX][nextY] = 1;
                            dist[nextX][nextY ] = dist[temp.x] [temp.y] +1;
                            q.add(new Cor(nextX, nextY));
                        }
                    }

                }

            }

        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {


        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][m + 1];
        checked = new int[n + 1][m + 1];

        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Cor start = null, end = null;

        for (int i = 0; i < 2; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (i == 0) start = new Cor(x, y);
            else end = new Cor(x, y);
        }
        subSum = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                subSum[i][j] = subSum[i - 1][j] + subSum[i][j - 1] - subSum[i - 1][j - 1] + arr[i][j];
            }
        }

//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= m; j++) {
//                System.out.print(subSum[i][j]);
//            }
//            System.out.println();
//        }
        bfs(start, end);
        int x1, x2, y1, y2;
        x1 = 2;
        y1 = 3;
        x2 = 3;
        y2 = 4;
        // 2,3 ~ 3,4  잡고 싶다
        if (checked[end.x][end.y] ==0) { System.out.println(-1);}
        else System.out.println(dist[end.x][end.y]);


    }
}