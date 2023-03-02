import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    static class Vertex implements Comparable<Vertex> {
        int node;
        int cost;

        Vertex(int end, int cost) {
            this.node = end;
            this.cost = cost;
        }

        public int compareTo(Vertex vertex) {
            return Integer.compare(this.cost, vertex.cost);
        }
    }

    public static int[] dis;

    public static void dikstra(int start) {
        dis = new int[V+1];
        // 무한대로 초기화
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        boolean [] check = new boolean[V+1];
        for (int i = 0; i < graph.get(start).size(); i++) {
            dis[graph.get(start).get(i).node] = graph.get(start).get(i).cost;
        }
        PriorityQueue<Vertex> q = new PriorityQueue<>();
        q.add(new Vertex(start, dis[start]));

        int vCount = 0;

        while (!q.isEmpty()) {
            Vertex temp = q.peek();
            q.poll();

            if (check[temp.node]) continue;
            check[temp.node] = true;
            if (++vCount == V) break;
            for (int i = 0; i < graph.get(temp.node).size(); i++) {

                int next = graph.get(temp.node).get(i).node;
                int cost = graph.get(temp.node).get(i).cost;
                if (dis[next] > dis[temp.node] + cost) {
                    dis[next] = dis[temp.node] + cost;

                }
                q.add(new Vertex(next, dis[next]));
            }


        }

    }

    public static int V, E;
    public static ArrayList<ArrayList<Vertex>> graph = new ArrayList<>();

    public static void main(String[] args) throws NumberFormatException, IOException {


        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {

            st = new StringTokenizer(br.readLine());
            int u, v, e;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Vertex(v, e));
        }
        // dikstra
        dikstra(start);
        for (int i = 1; i <= V; i++) {
            if (dis[i] == Integer.MAX_VALUE) System.out.println("INF");
            else System.out.println(dis[i]);
        }
    }

}
