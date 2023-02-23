import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {



        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // o(N^2) 로 해결이 안됨.

        long  [] arr = new long [n+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1 ; i<= n ; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long [] dp = new long [n+1];
        long []rem = new long [m];

        for (int i = 1 ; i<= n ; i++) {
            dp[i] = (dp[i-1] + arr[i] ) %  m;
            rem[(int)dp[i]]++;
        }
//        for (int i = 0 ; i< m ; i++) {
//            System.out.println(rem[i]);
//        }

        rem[0] ++;
        long sum = 0;
        for (int i = 0 ; i< m ; i++) {
            long num  = rem[i];
            sum += (num * (num-1))/2;
        }
        System.out.println(sum);


    }
}