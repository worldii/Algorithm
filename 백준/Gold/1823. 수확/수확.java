import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int [] arr = new int [n];
        int [][] dp = new int [n][n];
        for (int i = 0 ; i< n ;  i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        // dp[start][end] = Math.max(dp[start+1][end] + cnt * a[start] ,  dp[start][end-1] + cnt * a[end]) ;
        // 크기 순으로 0 - n-1 까지
        for (int i = 0 ; i< n ; i++) {
            dp[i][i] = arr[i] * n;
        }
        for (int size = 1 ; size <= n; size++){
            for (int start = 0 ; start < n ; start++) {
                int end = start + size;
                if (end >= n) continue;
                int cnt = n- (end-start);
                dp[start][end] = Math.max(dp[start+1][end] + cnt * arr[start], dp[start][end-1] + cnt * arr[end]);
            }
        }

        System.out.println(dp[0][n-1]);
    }

}