import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /*
     * 첫째 줄에 V와 E가 빈칸을 사이에 두고 주어진다. (2 ≤ V ≤ 400, 0 ≤ E ≤ V(V-1))
     * 다음 E개의 줄에는 각각 세 개의 정수 a, b, c가 주어진다.
     * a번 마을에서 b번 마을로 가는 거리가 c인 도로가 있다는 의미이다.
     * (a → b임에 주의) 거리는 10,000 이하의 자연수이다.
     *  (a, b) 쌍이 같은 도로가 여러 번 주어지지 않는다.
     * */
    public static int V, E;

    static class Edge {

        int b;
        long cost;

        public Edge(int b, long cost) {
            this.cost = cost;
            this.b = b;
        }
    }

    public static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    public static void dikstra(int start, long[] dist) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.cost));
        pq.add(new Edge(start, 0));
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        while (!pq.isEmpty()) {
            int num = pq.peek().b;
            long cc =pq.peek().cost;
            pq.poll();
            if (dist[num] < cc ) continue;;
            for (int i = 0; i < graph.get(num).size(); i++) {
                int next = graph.get(num).get(i).b;
                long cost = graph.get(num).get(i).cost;
                if (dist[next] > dist[num] + cost) {
                    dist[next] = dist[num] + cost;
                    pq.add(new Edge(next, dist[next]));
                }
            }
        }
    }

    public static long[][] dist;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, c));
        }

        dist = new long[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            dikstra(i, dist[i]);
        }

        for (int i = 1; i <= V; i++) {
            for (int j = i + 1; j <= V; j++) {
                if (dist[i][j] == Long.MAX_VALUE|| dist[j][i] == Long.MAX_VALUE) {
                    continue;
                }
                sum = Math.min(dist[i][j] + dist[j][i], sum);
            }
        }
        if (sum > Integer.MAX_VALUE) {
            sum = -1L;
        }

        System.out.println(sum);
    }

    public static long sum = Long.MAX_VALUE;

}