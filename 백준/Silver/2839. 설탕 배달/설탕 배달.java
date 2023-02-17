import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int minNum = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(br.readLine());

        int [] dp = new int [n+1];

        // dp[i] i까지 갔을 떄의 최소 양9

        // dp[i] = min (dp[i-3], dp[i-5]) +1;
        dp[0] = 0;
        for (int i= 1 ; i<= n ; i++) {
            dp[i] = Integer.MAX_VALUE;
            if (i>=5 && dp[i-5] != Integer.MAX_VALUE) dp[i] = dp[i-5] +1;
            if (i>=3 && dp[i-3] != Integer.MAX_VALUE) dp[i] = Math.min( dp[i], dp[i-3]+1);
        }
        if (dp[n] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(dp[n]);

    }
}
