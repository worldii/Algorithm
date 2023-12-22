import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n;
    public static int[] arr;
    public static long total;
    public static long[][] dp;

    public static void main(String[] args) throws IOException {

        /*
         *
         * 상근이가 1학년 때, 덧셈, 뺄셈을 매우 좋아했다.
         * 상근이는 숫자가 줄 지어있는 것을 보기만 하면, 마지막 두 숫자 사이에 '='을 넣고,
         * 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 등식을 만들며 놀고 있다.
         * 예를 들어, "8 3 2 4 8 7 2 4 0 8 8"에서 등식 "8+3-2-4+8-7-2-4-0+8=8"을 만들 수 있다.
         * 상근이는 올바른 등식을 만들려고 한다.
         * 상근이는 아직 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다.
         * 따라서, 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다.
         *  예를 들어, "8+3+2-4-8-7+2+4+0+8=8"은 올바른 등식이지만, 8+3+2-4-8-7이 음수이기 때문에,
         * 상근이가 만들 수 없는 등식이다.
         * 숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성하시오.
         */

        n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][j] j는 합이고 i 는 자리수인데 이의 경우의 수야
        // dp[i][j] = dp[i-1][j+arr[i-1]] + dp[i-1][j-arr[i-1]];

        dp = new long[n + 1][21];
        dp[0][arr[0]] = 1L;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                int tempSum = j - arr[i];
                if (0 <= tempSum && tempSum <= 20) {
                    dp[i][j] += dp[i - 1][tempSum];
                }
                tempSum = j + arr[i];
                if (0 <= tempSum && tempSum <= 20) {
                    dp[i][j] += dp[i - 1][tempSum];
                }
            }
        }

        System.out.println(dp[n - 2][arr[n - 1]]);

        //3 1 2
        // 0 1 2
        // dp[2][arr[2]] = dp[1][arr[2]-arr[1]]  + dp[1][arr[2]+ arr[1]]
        // dp[1][1] = dp[0][1+3];
        // dp[1][3] = dp[0][0] + dp[0][3+3];
        //

    }

    public static void dfs(int start, int end, int sum, int answer) {
        if (start == end) {
            if (sum == answer) {
                total++;
            }
            return;
        }
        int tempSum = sum + arr[start];
        if (0 <= tempSum && tempSum <= 20) {
            dfs(start + 1, end, tempSum, answer);
        }
        tempSum = sum - arr[start];
        if (0 <= tempSum && tempSum <= 20) {
            dfs(start + 1, end, tempSum, answer);
        }
    }

}