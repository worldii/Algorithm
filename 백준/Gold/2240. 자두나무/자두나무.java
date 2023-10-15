import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int t, w;

    public static int[] arr;
    public static int[][][] dp;

    public static int maxSum;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        dp = new int[t][w + 1][2];
        arr = new int[t];

        for (int i = 0; i < t; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;
        }

        if (arr[0] == 0) {
            dp[0][0][0] = 1;
            dp[0][1][1] = 0;
        } else {
            // arr[0] == 1
            dp[0][1][1] = 1;
            dp[0][0][0] = 0;
        }
        // 000
        // 001
        // 100
        // 101
        // 110
        // 111
        // 010
        // 011
        for (int i = 1; i < t; i++) {
            for (int j = 0; j <= w; j++) {
                if (arr[i] == 1) {
                    dp[i][j][1] = dp[i - 1][j][1] + 1;
                    dp[i][j][0] = dp[i - 1][j][0];
                    if (j >= 1) {
                        dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] + 1, dp[i][j][1]);
                        dp[i][j][0] = Math.max(dp[i - 1][j - 1][1], dp[i][j][0]);
                    }
                } else {
                    dp[i][j][0] = dp[i - 1][j][0] + 1;
                    dp[i][j][1] = dp[i - 1][j][1];
                    if (j >= 1) {
                        dp[i][j][0] = Math.max(dp[i - 1][j - 1][1] + 1, dp[i][j][0]);
                        dp[i][j][1] = Math.max(dp[i - 1][j - 1][0], dp[i][j][1]);
                    }
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= w; j++) {
                maxSum = Math.max(maxSum, dp[t - 1][j][i]);
               // System.out.println(dp[t - 1][j][i]);
            }
        }
        System.out.println(maxSum);
    }

}
