import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int n, m,x;
    public static StringTokenizer st = null;


    public static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    public static int dikstra (int start, int end) {
        int[] dist = new int[n+1];
        boolean [] check = new boolean[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Cor> pq = new PriorityQueue<>(new Comparator<Cor>() {
            @Override
            public int compare(Cor o1, Cor o2) {
                return Integer.compare(o1.cost, o2.cost);
            };
        });
        pq.add(new Cor(start,0));
        while (!pq.isEmpty()) {
            Cor cur = pq.poll();
            if (cur.dest == end) return dist[end];
            if (check[cur.dest]) continue;
            check[cur.dest] = true;
            for (int i = 0 ; i< graph.get(cur.dest).size() ; i++) {
                int next = graph.get(cur.dest).get(i).dest;
                if (dist[next]  > dist[cur.dest] + graph.get(cur.dest).get(i).cost) {
                    dist[next] = dist[cur.dest] + graph.get(cur.dest).get(i).cost;
                    pq.add(new Cor(next, dist[next]));
                }
            }
        }
        return dist[end];
    }

    static class Cor {
        int dest;
        int cost;
        public Cor(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
    public static ArrayList<ArrayList<Cor>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        for (int i = 0  ;i<= n ;i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0 ; i< m ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Cor(b,cost));
        }

        int [] stu = new int[n+1];
        for (int i = 1 ; i<= n ; i++) {
            stu[i] = dikstra(i,x);
            stu[i] += dikstra(x,i);
        }

        int max = 0;
        for (int i = 1 ; i<= n ; i++) {
            max = Math.max(max, stu[i]);
        }
        System.out.println(max);
    }
}