import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int t =Integer.parseInt(st.nextToken());
        for (int i = 0 ; i< t ; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            int[][] dp = new int [k+1][n+1];


            dp[0][0] = 0;
            for (int j = 1 ; j<= n ; j++) {
                dp[0][j] = j;
            }


            for (int tt = 1 ; tt<= k ; tt++) {

                for (int jj = 1 ; jj<= n ; jj++) {
                    dp[tt][jj] = dp[tt-1][jj] + dp[tt][jj-1];
                }

            }

            
            // 0 층부터 있음
            // 0 층   1 2 3
            // 1 층   1 3 6
            // 2 층   1 4 10
            System.out.println(dp[k][n]);
        }

    }

}
