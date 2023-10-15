import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static int n;
    public static int m;

    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static boolean[][] isTurn = new boolean[n][n];

    public static void main(String[] args) throws IOException {

        /*
        * 첫 번째 줄에는 N(2 ≤ N ≤ 100)과, M(1 ≤ M ≤ 20,000)이 정수로 주어진다.

        다음 M줄에는 네 개의 정수 x, y, a, b가 주어진다.
        * (x, y)방에서 (a, b)방의 불을 켜고 끌 수 있다는 의미이다.
        * 한 방에 여러개의 스위치가 있을 수 있고,
        *  하나의 불을 조절하는 스위치 역시 여러개 있을 수 있다.
        * */

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n * n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        isTurn = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get((x) * n + y).add((a) * n + b);
        }
        System.out.println(getCnt());
    }

    private static int getCnt() {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0});
        boolean[][] check = new boolean[n][n];
        boolean[][] movable = new boolean[n][n];

        check[0][0] = true;
        movable[0][0] = true;
        isTurn[0][0] = true;
        int cnt = 1;
        while (!deque.isEmpty()) {
            int[] temp = deque.poll();
            if (!isTurn[temp[0]][temp[1]]) continue;
            for (int i = 0; i < graph.get(temp[0] * n + temp[1]).size(); i++) {
                int num = graph.get(temp[0] * n + temp[1]).get(i);
                int nextX = num / n;
                int nextY = num % n;
                if (!isTurn[nextX][nextY]) {
                    isTurn[nextX][nextY] = true;
                    cnt++;
                    if (movable[nextX][nextY] && check[nextX][nextY]) {
                        deque.addFirst(new int[]{nextX, nextY});
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + temp[0];
                int nextY = dy[i] + temp[1];
                if (0 > nextX || nextX >= n || nextY < 0 || nextY >= n) {
                    continue;
                }
                movable[nextX][nextY] = true;
                if (!check[nextX][nextY] && isTurn[nextX][nextY]) {
                    check[nextX][nextY] = true;
                    deque.addFirst(new int[]{nextX,nextY});
                }
                else if (!check[nextX][nextY] && !isTurn[nextX][nextY]){
                    check[nextX][nextY] = true;
                    deque.addLast(new int[] {nextX, nextY});
                }
            }
        }
        return cnt;
    }

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
}