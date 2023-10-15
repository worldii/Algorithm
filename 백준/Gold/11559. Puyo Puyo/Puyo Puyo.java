import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import javax.swing.plaf.SplitPaneUI;

public class Main {

    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static char[][] puyo = new char[12][6];
    public static boolean[][] check = new boolean[12][6];
    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static char[][] copy() {
        char[][] arr = new char[12][6];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = puyo[i][j];
            }
        }
        return arr;
    }

    public static int bfs(int x, int y, char[][] arr) {
        char color = puyo[x][y];
        int cnt = 1;
        check[x][y] = true;
        arr[x][y] = 'C';
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + temp[0];
                int nextY = dy[i] + temp[1];
                if (nextY < 0 || nextY >= 6 || nextX < 0 || nextX >= 12) {
                    continue;
                }
                if (color != puyo[nextX][nextY]) {
                    continue;
                }
                if (check[nextX][nextY]) {
                    continue;
                }
                check[nextX][nextY] = true;
                arr[nextX][nextY] = 'C';
                q.add(new int[]{nextX, nextY});
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 12; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                puyo[i][j] = charArray[j];
            }
        }

        int sum = 0;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!check[i][j] && puyo[i][j] != '.') {
                        char[][] newArr = copy();
                        int cnt = bfs(i, j, newArr);
                        if (cnt >= 4) {
                            flag = true;
                            puyo = newArr;
                     //         printArr();
                        }
                    }
                }
            }
            if (!flag) {
                break;
            } else {
                sum++;
                gravity(puyo);
                check = new boolean[12][6];
            }
        }

        System.out.println(sum);
    }

    private static void printArr() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(puyo[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

    private static void gravity(char[][] puyo) {

        for (int i = 0; i < 6; i++) {
            int startIdx = findIdx(i, puyo);
            while (startIdx != -1) {

                int endIdx = findEndIdx(i, puyo, startIdx);
                int size = startIdx - endIdx;
                for (int t = endIdx; t >= 0; t--) {
                    puyo[t + size][i] = puyo[t][i];
                    puyo[t][i] = '.';
                }
                 //printArr();
                if (endIdx == -1) {
                    for (int t = startIdx ; t>= 0 ; t--) {
                        puyo[t][i] = '.';
                    }
                }
                //System.out.println(startIdx + " " + i + " " + endIdx);
                startIdx = findIdx(i, puyo);
            }
            // 이걸 반복해야함 없을 때까지
            // 11 부터 시작하여, 만약 char[i][j] = 'C' 이면
            // 중력이 발동되어야 한다.
            // 'C' 가 아닐 때까지 끝점을 위로 찾고
            // 끝점을 찾으면 옮겨버림
        }
    }

    public static int findIdx(int col, char[][] puyo) {
        int startIdx = 11;
        while (startIdx >= 0) {
            if (puyo[startIdx][col] == 'C') {
                return startIdx;
            }
            startIdx--;
        }
        return -1;
    }

    public static int findEndIdx(int col, char[][] puyo, int startIdx) {
        int endIdx = startIdx;
        char color = puyo[startIdx][col];
        while (endIdx >= 0 && color == puyo[endIdx][col]) {
            endIdx--;
        }
        return endIdx;
    }
}