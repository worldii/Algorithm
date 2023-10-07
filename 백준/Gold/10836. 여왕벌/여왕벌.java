import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n, m;
    public static int[][] grow;
    public static int[][] originalArr;
    public static int[][] nextArr;
    public static int[] dx = {-1, -1, 0};
    public static int[] dy = {0, -1, -1};

    public static int getDiff(int x, int y) {
        int maxDiff = 0;
        for (int i = 0; i < 3; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            int diff = Math.abs(originalArr[nextX][nextY] - nextArr[nextX][nextY]);
            maxDiff = Math.max(diff, maxDiff);
        }
        return maxDiff;
    }

    private static void growFirst(int day) {
        int idx = 0;
        for (int i = m - 1; i >= 0; i--) {
            nextArr[i][0] = originalArr[i][0] + grow[day][idx];
            idx++;
        }
        for (int i = 1; i < m; i++) {
            nextArr[0][i] = originalArr[0][i] + grow[day][idx];
            idx++;
        }
    }

    private static void growSecond() {
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                int diff = getDiff(i, j);
                nextArr[i][j] = originalArr[i][j] + diff;
            }
        }
    }

    public static void swap() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                originalArr[i][j] = nextArr[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        originalArr = new int[m][m];
        nextArr = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                originalArr[i][j] = 1;
            }
        }

        grow = new int[n][2 * m - 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int zeroNum =Integer.parseInt(st.nextToken());
            int idx =0;
            for (int j = 0 ; j< zeroNum ; j++) {
                grow[i][idx++] = 0;
            }
            int firstNum= Integer.parseInt(st.nextToken());
            for (int j = 0 ; j< firstNum ; j++) {
                grow[i][idx++] = 1;
            }
            int secondNum= Integer.parseInt(st.nextToken());
            for (int j = 0 ; j< secondNum ; j++) {
                grow[i][idx++] = 2;
            }

            // grow[i][j] = Integer.parseInt(st.nextToken());

        }

        for (int i = 0; i < n; i++) {
            growFirst(i);
            growSecond();
            swap();
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(originalArr[i][j]+ " ");
            }
            System.out.println();
        }
    }


}
