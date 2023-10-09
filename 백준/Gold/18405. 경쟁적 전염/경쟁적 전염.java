import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import javax.swing.text.Style;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n, k;
    public static int[][] arr;
    public static int x, y, s;

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static Queue<int[]> q = new LinkedList<>();
    static class Node {
        int x;
        int y;
        int dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    public static PriorityQueue<Node>pq = new PriorityQueue<>(Comparator.comparingInt(o-> o.dist));
    public static void main(String[] args) throws IOException {
        /*
         * 첫째 줄에 자연수 N, K가 공백을 기준으로 구분되어 주어진다. (
         * 1 ≤ N ≤ 200, 1 ≤ K ≤ 1,000) 둘째 줄부터 N개의 줄에 걸쳐서 시험관의 정보가 주어진다.
         * 각 행은 N개의 원소로 구성되며, 해당 위치에 존재하는 바이러스의 번호가 공백을 기준으로 구분되어 주어진다.
         * 단, 해당 위치에 바이러스가 존재하지 않는 경우 0이 주어진다. 또한 모든 바이러스의 번호는 K이하의 자연수로만 주어진다.
         * N+2번째 줄에는 S, X, Y가 공백을 기준으로 구분되어 주어진다. (0 ≤ S ≤ 10,000, 1 ≤ X, Y ≤ N)
         * */
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j]!=0) pq.add(new Node(i,j,arr[i][j]));
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            q.add(new int[]{node.x, node.y});
        }
        for (int i = 0; i < s; i++) {
            int qSize = q.size();
            for (int t = 0; t < qSize; t++) {
                int [] temp = q.poll();

                for (int j= 0 ; j< 4 ; j++) {
                    int nextX = dx[j] + temp[0];
                    int nextY = dy[j] + temp[1];

                    if (0<=nextY && nextX <n && 0<= nextX && nextY <n) {

                        if (arr[nextX][nextY] !=0) continue;
                        arr[nextX][nextY] = arr[temp[0]][temp[1]];
                        q.add(new int[]{nextX,nextY});
                    }
                }
            }

        }
        System.out.println(arr[x - 1][y - 1]);
    }


}