import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static final int REMAINDER =  10007;
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int n =Integer.parseInt(st.nextToken());
        int []dp = new int [n+1];
        dp[1] = 0;
        for (int i = 2; i<= n ; i++) {
            dp[i] = dp[i-1];
            if (i%3 ==0 ) dp[i] = Math.min (dp[i] , dp[i/3]);
            if (i%2 ==0) dp[i] = Math.min(dp[i], dp[i/2]);
            dp[i]++;

        }
      
        System.out.println(dp[n]);


    }

}
