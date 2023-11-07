import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n, m;
    public static int[][] a;
    public static int[][] dp;
    public static int[][] before;

    public static void main(String[] args) throws IOException {

        /**
         * 첫째 줄에 투자 금액 N과 투자 가능한 기업들의 개수 M이 주어진다. (1 ≤ N ≤ 300, 1 ≤ M ≤ 20)
         * 둘째 줄부터 N개의 줄에 투자액수와 각 기업이 투자가에게 주는 이익이 주어진다.
         * 투자 금액은 항상 1보다 크거나 같고, N보다 작거나 같고, 같은 투자 금액이 두 번 이상 주어지는 경우는 없다.
         * 즉, i번 줄에 주어지는 투자 금액은 i-1만원이다.
         */
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new int[n + 1][m + 1];
        dp = new int[m + 1][n + 1];
        before = new int[m+1][n+1];
        for (int i = 0 ; i<= m ; i++) {
            for (int j = 0 ; j<= n ; j++) {
                before[i][j] = -1;
            }
        }
        // dp[i][j]= i번쨰 기업을 봤을 때, j 투자금액이 있을 때 최대 금액
        // DP[I-1][J], DP[I-1][J-K] + a[k][i];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= m; j++) {
                a[cnt][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 0; k <= n; k++) {
                    if (j < k) {
                        continue;
                    }
                    if (dp[i][j] <= dp[i - 1][j - k] + a[k][i]) {
                        dp[i][j] = dp[i - 1][j - k] + a[k][i];
                        before[i][j] = k;
                    }
                }
            }
        }

        System.out.println(dp[m][n]);
        // before [i][j] =k i번째 기업이 j 라는 투자금액 이 있을 때 k 금액을 선택했다.
        // before[i-1][j-k]
        int maxM= m;
        int maxN = n;
        int [] sum = new int[m+1];
        while (maxM>= 1 && maxN>=1){
            int k = before[maxM][maxN];
            sum[maxM] += k;
            maxM = maxM-1;
            maxN = maxN-k;
        }
        for (int i = 1 ; i<= m ; i++) {
            System.out.print(sum[i]+ " ");
        }

    }


}