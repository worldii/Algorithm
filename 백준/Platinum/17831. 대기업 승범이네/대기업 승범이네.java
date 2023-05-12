import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int [] cost ;
    public static long [][] dp ;

    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    public static boolean [] check;
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        for (int i = 0 ; i<= n; i++) {
            graph.add(new ArrayList<Integer>());
        }
        // dp [1~n][0:선택x, 1:선택o]
        dp = new long[n+1][2];
        check = new boolean[n+1];
        cost = new int [n+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 2 ; i<= n ; i++) {
            int a= Integer.parseInt(st.nextToken());
            graph.get(a).add(i);
        }

        st= new StringTokenizer(br.readLine());
        for (int i = 1; i<= n ; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1);
        System.out.println(Math.max(dp[1][0], dp[1][1]));

    }
    public static void dfs(int start)
    {
        check[start ] = true;

        // 자식 노드 리프 노드일때
        if (graph.get(start).size() == 0) {
            // 선택을 당연히 못함
            dp[start][0] = 0;
            return;
        }

        // 초기화 해줌
        for (int i = 0 ; i< graph.get(start).size() ; i++) {
            int child = graph.get(start).get(i);
            if (graph.get(child).size() == 0) {
                dp[start][1] = Math.max(dp[start][1], dp[child][0] + cost[start] * cost[child]);
            }
        }

        // 자식 노드가 리프 노드가 아닐때
        for (int i = 0 ; i< graph.get(start).size() ; i++) {
            int child = graph.get(start).get(i);
            dfs(child);
            // start 가 선택이 안될 경우,
            // child 가 선택이 됐을 경우, 안됐을 경우의 최대값이 dp[start][0] 임
            dp[start][0] += Math.max(dp[child][1], dp[child][0]);

        }

        for (int i = 0 ; i< graph.get(start).size() ; i++) {
            // start 가 선택이 될 경우
            // child 의 cost * start 의 cost 가 dp[start][1] 에 더해짐
            int child = graph.get(start).get(i);
            dp[start][1] = Math.max(dp[start][1], dp[start][0]  + dp[child][0] - Math.max(dp[child][1],dp[child][0]) + cost[child] * cost[start]);
        }
    }

}
