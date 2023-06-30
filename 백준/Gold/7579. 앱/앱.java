import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n,m;

    public static int[] a;
    public static int[] c;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        a = new int[n + 1];
        for (int i =1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        c = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        long [] dp = new long[10001]; // 비용이 i 일 때 최대 메모리 수
        // N번을 돌림 (모든 수 고려)
        for (int  i = 1; i<= n ; i++) {
            //c[i] <= 비용이 (j) <= 10000 는 되어야 함.
            for (int j  = 10000 ; j>=0 ; j--) {
                if (j>= c[i]) dp[j] = Math.max(dp[j],  dp[j-c[i]] +a[i]);
            }
        }
        for (int i = 0 ; i<= 10000 ; i++) {
            if (dp[i] >= m )
            {
                System.out.println(i);
                break;
            }
        }

    }
}