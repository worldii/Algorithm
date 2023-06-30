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
    static class Node {
        int memo;
        int cost;
        Node(int memo, int cost) {
            this.memo = memo;
            this.cost = cost;
        }
    }

    public static long minCost =Integer.MAX_VALUE;
    public static void recur (int cnt, int end, long wei, long cost) {
        if (wei >= m)  {minCost = Math.min(minCost, cost);
        return;}
        if (cnt >= n) return;
        // 선택 한다.
        recur (cnt+1, end, wei+ a[cnt], c[cnt] + cost);

        recur(cnt+1, end, wei, cost);
    }
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

        // i까지 했을 때 현재 비용이 j
        long [][] dp = new long[n+1][10001];
        for (int i = 1 ; i<= n ; i++) {
            // dp[i][j] = dp[i-1][j-c[i]] + m[i];
            // 최대값
            for (int j = 0 ; j<= 10000 ; j++) {
                dp[i][j] = dp[i-1][j];
                if (j>= c[i]) dp[i][j] = Math.max(dp[i-1][j-c[i]] + a[i], dp[i][j]);
            }
        }
        for (int j = 0 ; j<= 10000 ; j++) {
            if (dp[n][j] >= m) {
                System.out.println(j);
                break;
            }
        }
    }
}