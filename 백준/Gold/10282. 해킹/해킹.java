import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int n, d, c;

    public static StringTokenizer st = null;

    public static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public static class Cor {
        int to;
        int time;

        public Cor(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    // 다익스트라
    public static int[] dikstra(int start) {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        PriorityQueue<Cor> pq = new PriorityQueue<>(new Comparator<Cor>() {
            @Override
            public int compare(Cor o1, Cor o2) {
                return Integer.compare(o1.time, o2.time);
            }
        });
        pq.add(new Cor(start, 0));
        int cnt = 0;
        while (!pq.isEmpty()) {
            Cor cur = pq.poll();
            if (visited[cur.to]) continue;
            if (cnt == n) break;
            visited[cur.to] = true;
            cnt++;
            for (int i = 0; i < graph.get(cur.to).size(); i++) {
                int next = graph.get(cur.to).get(i).to;
                if (dist[next] > dist[cur.to] + graph.get(cur.to).get(i).time) {
                    dist[next] = dist[cur.to] + graph.get(cur.to).get(i).time;
                    pq.add(new Cor(next, dist[next]));
                }
            }
        }
        return dist;
    }

    public static ArrayList<ArrayList<Cor>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int tt = 1; tt <= t; tt++) {
            st = new StringTokenizer(br.readLine());
            graph = new ArrayList<>();
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Cor(a, s));
            }


            int[] dist = dikstra(c);
            int maxNum = 0;
            int cnt = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != Integer.MAX_VALUE) {
                    cnt++;
                    maxNum = Math.max(maxNum, dist[i]);
                }
            }
            System.out.println(cnt+ " " + maxNum);
        }
    }
}