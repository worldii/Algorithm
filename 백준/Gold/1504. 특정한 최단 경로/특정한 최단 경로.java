import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int n, e;
    public static StringTokenizer st = null;
    public static int[][] arr;

    static class Cor {
        int dest;
        int cost;
        public Cor(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }
    public static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
    public static ArrayList<ArrayList<Cor>> graph = new ArrayList<>();
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
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        for (int i = 0  ;i<= n ;i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0 ; i< e ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Cor(b,c));
            graph.get(b).add(new Cor(a,c));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        // 다익스트라 1 -> v1 -> v2 -> n
        int dist1 = dikstra(1,v1);
        int dist2 = dikstra(v1,v2);
        int dist3 = dikstra(v2,n);
        int maxNum = Integer.MAX_VALUE;
        if (!(dist1 == Integer.MAX_VALUE || dist2 == Integer.MAX_VALUE || dist3 == Integer.MAX_VALUE)) {
            maxNum = Math.min(maxNum, dist1 + dist2 + dist3);
        }

        //System.out.println(dist1+ " " + dist2 + " " + dist3);
        // 다익스트라 1 -> v2 -> v1 -> n
        int dist4 = dikstra(1,v2);
        int dist5 = dikstra(v2,v1);
        int dist6 = dikstra(v1,n);
        //System.out.println(dist4+ " " + dist5 + " " + dist6);
        if (!(dist4 == Integer.MAX_VALUE || dist5 == Integer.MAX_VALUE || dist6 == Integer.MAX_VALUE)) {
            maxNum = Math.min(maxNum, dist4 + dist5 + dist6);
        }
        if (maxNum == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(maxNum);

    }
}