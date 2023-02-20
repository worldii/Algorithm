import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int[] arr = new int[10];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static boolean[][] checked = null;

    // 항상 최선의 선택을 해야함.


    public static int[] dx = {0, 0, 1, 1};
    public static int[] dy = {0, 1, 0, 1};

    public static int maxSum = 0;

    public static void recur(int row, int col, int size, int startx, int starty) {
        if (size == 2) {
            for (int i = 0; i < 4; i++) {
                int newx = startx + dx[i];
                int newy = starty + dy[i];
                if (newx == row && newy == col) {
                    maxSum += (i + 1);
                    System.out.println(maxSum-1);
                    System.exit(0);
                }

            }
            maxSum += 4;
            return ;
        }

        if (startx <= row && row < startx + size/2 && starty <= col && col <starty + size/2)
            recur(row, col, size / 2, startx, starty);
        else maxSum += size/2* size/2;
        if (startx <= row && row < startx + size/2 && starty +size/2 <= col && col <starty + size)
            recur(row, col, size / 2, startx, starty + size / 2);
        else maxSum += size/2* size/2;
        if (startx + size/2 <= row && row < startx + size && starty <= col && col <starty + size/2)
            recur(row, col, size / 2, startx + size / 2, starty);
        else maxSum += size/2* size/2;
        if (startx +size/2<= row && row < startx + size && starty +size/2<= col && col <starty + size)
            recur(row, col, size / 2, startx + size / 2, starty + size / 2);
        else maxSum += size/2* size/2;

    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());


        recur(r, c, (int)Math.pow(2,n), 0, 0);
        System.out.println(maxSum-1);

    }
}

