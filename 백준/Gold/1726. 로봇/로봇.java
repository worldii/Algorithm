import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import javax.swing.plaf.PanelUI;

public class Main {

    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n, m;
    public static int[][] map = null;

    static class Cor {

        int x;
        int y;
        int dir;
        int cnt;

        public Cor(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = 0;
        }

        public Cor(int x, int y, int dir, int cnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cnt = cnt;
        }
        
        public int left (){
            if (this.dir ==0 ) return 3;
            if (this.dir == 1) return 2;
            if (this.dir == 2) return 0;
            if (this.dir == 3) return 1;
            return -1;
        }
        public int right () {
            if (this.dir ==0 ) return 2;
            if (this.dir == 1) return 3;
            if (this.dir == 2) return 1;
            if (this.dir == 3) return 0;
            return -1;
        }
    }
    
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, -0, 0};


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Cor start = null;
        Cor end = null;
        st = new StringTokenizer(br.readLine());

        start = new Cor(Integer.parseInt(st.nextToken()) - 1,
            Integer.parseInt(st.nextToken()) - 1,
            Integer.parseInt(st.nextToken()) - 1);
        st = new StringTokenizer(br.readLine());
        end = new Cor(Integer.parseInt(st.nextToken()) - 1,
            Integer.parseInt(st.nextToken()) - 1,
            Integer.parseInt(st.nextToken()) - 1);

        PriorityQueue<Cor> pq = new PriorityQueue<>((a, b) -> a.cnt - b.cnt);
        boolean[][][] check = new boolean[m][n][4];
        check[start.x][start.y][start.dir] = true;
        pq.add(start);
        while (!pq.isEmpty()) {
            Cor temp = pq.poll();
            if (temp.x == end.x && temp.y == end.y && temp.dir == end.dir) {
                System.out.println(temp.cnt);
                return;
            }
            if (!check[temp.x][temp.y][temp.left()]) {
                check[temp.x][temp.y][temp.left()] =true;
                pq.add(new Cor(temp.x, temp.y, temp.left(), temp.cnt+1));
            }
            if (!check[temp.x][temp.y][temp.right()]) {
                check[temp.x][temp.y][temp.right()] =true;
                pq.add(new Cor(temp.x, temp.y, temp.right(), temp.cnt+1));
            }
            for (int t = 1; t <= 3; t++) {
                int nextX = temp.x + dx[temp.dir] * t;
                int nextY = temp.y + dy[temp.dir] * t;
                if (0 > nextY || 0 > nextX || nextX >= m || nextY >= n) {
                    continue;
                }
                if (map[nextX][nextY] == 1) {
                    break;
                }
                if (check[nextX][nextY][temp.dir]) {
                    continue;
                }
                check[nextX][nextY][temp.dir] = true;
                pq.add(new Cor(nextX, nextY, temp.dir, temp.cnt + 1));
            }
        }
    }


}