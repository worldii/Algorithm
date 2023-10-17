import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());
            int[][] dp = new int[n + 1][m + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    dp[i][j] = 0;
                    if (dp[i - 1][j] > 0) {
                        dp[i][j] += dp[i - 1][j];
                    }
                    for (int tt = arr[i]; tt <= j; tt += arr[i]) {
                        if (j >= tt && dp[i - 1][j - tt] > 0) {
                            dp[i][j] += dp[i - 1][j - tt];
                        }
                    }
                }
            }
            System.out.println(dp[n][m]);
        }
        //1 5 10
        // 20
        // dp[2][20]
        // dp[2][10], dp[2][0],
    }

}