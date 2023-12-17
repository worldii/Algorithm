import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.StringTokenizer;

public class Main {

    public static char[][] map;

    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static List<int[]> door = new ArrayList<>();

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == '#') {
                    door.add(new int[]{i, j});
                }
            }
        }
        System.out.print(
            extracted(n, door.get(0)[0], door.get(0)[1], door.get(1)[0], door.get(1)[1]));
    }

    private static int extracted(int n, int startX, int startY, int endX, int endY) {
        boolean[][][] visit = new boolean[n][n][4];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (a[3] - b[3]));

        for (int i = 0; i < 4; i++) {
            q.add(new int[]{startX, startY, i, 0});
            visit[startX][startY][i] = true;
        }

        while (!q.isEmpty()) {
            int[] poll = q.poll();

            if (poll[0] == endX && poll[1] == endY) {
                return poll[3];
            }

            int nextX = poll[0] + dx[poll[2]];
            int nextY = poll[1] + dy[poll[2]];
            if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map.length) {
                continue;
            }

            if (map[nextX][nextY] == '*') {
                continue;
            }

            if (visit[nextX][nextY][poll[2]]) {
                continue;
            }
            visit[nextX][nextY][poll[2]] = true;
            q.add(new int[]{nextX, nextY, poll[2], poll[3]});

            if (map[nextX][nextY] == '!') {
                for (int i = 0; i < 4; i++) {
                    if (i % 2 == 0) {
                        continue;
                    }
                    int dir = (poll[2] + i) % 4;

                    if (visit[nextX][nextY][dir]) {
                        continue;
                    }
                    visit[nextX][nextY][dir] = true;
                    q.add(new int[]{nextX, nextY, dir, poll[3] + 1});
                }
            }

        }
        return Integer.MAX_VALUE;
    }

}