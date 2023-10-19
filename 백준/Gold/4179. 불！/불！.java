import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static StringTokenizer st = null;
    public static int R, C;
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};
    /*
    * 입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.
    * 다음 입력으로 R줄동안 각각의 미로 행이 주어진다.
    * 각각의 문자들은 다음을 뜻한다.
        #: 벽
        .: 지나갈 수 있는 공간
        J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
        F: 불이 난 공간
        J는 입력에서 하나만 주어진다.
    *
    * */
    public static char[][] arr;
    public static int[][] jihunMiro;
    public static int[][] fireMiro;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void bfs(int[][] miro, ArrayList<int[]> cor) {
        boolean[][] check = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < cor.size(); i++) {
            check[cor.get(i)[0]][cor.get(i)[1]] = true;
            q.add(new int[]{cor.get(i)[0], cor.get(i)[1]});
            miro[cor.get(i)[0]][cor.get(i)[1]] = 1;
        }
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = temp[0] + dx[i];
                int nextY = temp[1] + dy[i];
                if (0 > nextY || nextY >= C || 0 > nextX || nextX >= R) {
                    continue;
                }
                if (check[nextX][nextY]) {
                    continue;
                }
                if (arr[nextX][nextY] == '#') {
                    continue;
                }
                check[nextX][nextY] = true;
                miro[nextX][nextY] = miro[temp[0]][temp[1]] + 1;
                q.add(new int[]{nextX, nextY});
            }
        }
    }

    public static ArrayList<int[]> jihun = new ArrayList<>();
    public static ArrayList<int[]> fire = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        jihunMiro = new int[R][C];
        fireMiro = new int[R][C];
        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                if (arr[i][j] == 'J') {
                    jihun.add(new int[]{i, j});
                    arr[i][j] = '.';
                } else if (arr[i][j] == 'F') {
                    fire.add(new int[]{i, j});
                    arr[i][j] = '.';
                }
            }
        }

        for (int i = 0 ; i< R; i++) {
            for (int j = 0 ; j< C; j++) {
                fireMiro[i][j] = Integer.MAX_VALUE;
                jihunMiro[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs(fireMiro, fire);
        bfs(jihunMiro, jihun);
      //  printArr(jihunMiro);
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < C; i++) {
            if (jihunMiro[0][i] < fireMiro[0][i]) {
                minNum = Math.min(minNum, jihunMiro[0][i]);
            }
            if (jihunMiro[R - 1][i] < fireMiro[R - 1][i]) {
                minNum = Math.min(minNum, jihunMiro[R - 1][i]);
            }
        }
        for (int i = 0; i < R; i++) {
            if (jihunMiro[i][0] < fireMiro[i][0]) {
                minNum = Math.min(minNum, jihunMiro[i][0]);
            }
            if (jihunMiro[i][C - 1] < fireMiro[i][C - 1]) {
                minNum = Math.min(minNum, jihunMiro[i][C-1]);
            }
        }

        if (minNum == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(minNum);
        }

    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

}