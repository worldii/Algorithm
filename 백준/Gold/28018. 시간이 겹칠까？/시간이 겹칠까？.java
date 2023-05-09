import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static StringTokenizer st = null;

    public static int[] start = new int [1000001];
    public static int[] end = new int [1000001];
    public static int [] dp = new int [1000001];
    public static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        for (int i = 0 ; i< n ; i++) {
            st = new StringTokenizer(br.readLine());
            int startA = Integer.parseInt(st.nextToken());
            int endA = Integer.parseInt(st.nextToken());
            start[startA]++;
            end[endA]++;
        }
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int cnt = 0;
        for (int i = 0 ; i < 1000001; i++) {
            if (start[i]> 0) { cnt += start[i];
            }
            dp[i] = cnt;
            if (end[i]>0) cnt -= end[i];

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i< m ; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a > 1000000) sb.append(0).append("\n");
            else sb.append(dp[a]).append("\n");
        }


        System.out.println(sb);




    }
}