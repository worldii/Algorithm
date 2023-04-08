import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public static StringTokenizer st = null;
    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int [][] arr= new int [n][n];
        for (int i = 0 ; i< n  ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j< n ; j++) {

                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // dp[i][j][k] // k 0일때 가로, 1일때 세로, 2일때 대각선

        int [][][]dp = new int [n][n][3];


        // 끝점 0, 1 이고 가로로 생긴거 1임
        dp[0][1][0] = 1;
        for (int i = 0 ; i< n ; i++){
            for (int j = 0 ; j< n ; j++) {
                if (arr[i][j] == 1) continue;

                if (j>=1) {
                dp[i][j][0] +=dp[i][j-1][0];
                dp[i][j][0] += dp[i][j-1][2];
                }

                if (i>=1 ) {
                dp[i][j][1]+=dp[i-1][j][1];
                dp[i][j][1]+=dp[i-1][j][2];}

                if (i>=1 && j>=1 )
                {
                    if (arr[i][j-1] == 0 && arr[i-1][j] ==0 )
                    {
                        dp[i][j][2] += dp[i-1][j-1][0];
                        dp[i][j][2] += dp[i-1][j-1][2];
                        dp[i][j][2] += dp[i-1][j-1][1];
                    }
                }

            }
        }

        System.out.println(dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2]);
    }
}