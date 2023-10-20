import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[][] dp;
    public static final int REMAINDER = 9901;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][3];
        // 0 ,1,2 => 0은 없을떄, 1은 1에 넣을때 2는 2에 넣을 때
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;
        for (int i = 2; i <= n; i++) {

            dp[i][0] =
                (dp[i - 1][1] % REMAINDER + dp[i - 1][2] % REMAINDER + dp[i - 1][0] % REMAINDER)
                    % REMAINDER;
            dp[i][1] = (dp[i - 1][2] % REMAINDER + dp[i - 1][0] % REMAINDER) % REMAINDER;
            dp[i][2] = (dp[i - 1][1] % REMAINDER + dp[i - 1][0] % REMAINDER) % REMAINDER;
        }
        System.out.println(((dp[n][0] + dp[n][1]) % REMAINDER + dp[n][2]) % REMAINDER);
    }

}