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
        dis = new int[V + 1];
        // 무한대로 초기화
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;

        // boolean[] check = new boolean[V + 1];
        PriorityQueue<Vertex> q = new PriorityQueue<>();
        q.add(new Vertex(start, dis[start]));

        while (!q.isEmpty()) {
            Vertex temp = q.poll();
            // 방문하지 않은 노드 중 가장 비용이 적은 노드를 선택함.
            if (dis[temp.node] < temp.cost) continue;

            // 인접한 것들 중에서 갱신할 것 있는지 봄.
            for (int i = 0; i < graph[temp.node].size(); i++) {
                int next = graph[temp.node].get(i).node;
                int cost = graph[temp.node].get(i).cost;
                if (dis[next] > dis[temp.node] + cost) {
                    dis[next] = dis[temp.node] + cost;
                    q.add(new Vertex(next, dis[next]));

                }
            }
        }

    }

    public static int V, E;
    public static ArrayList<Vertex>[] graph;

    public static int total = 0;
    public static void prim(int start) {

        // start check 한다.

        boolean [] ch = new boolean[V+1];
        // pq
        // start 와 연결되어 있는것다 넣음

        PriorityQueue<Vertex > q = new PriorityQueue<>();
        q.add(new Vertex(start, 0));


        int vCount=0;

        while (!q.isEmpty()) {
            Vertex temp = q.peek();
            q.poll();

            if (ch[temp.node]) continue;;
            ch[temp.node] = true;

            total += temp.cost;
            if (++vCount == V)  break;
            for (int i = 0 ; i< graph[temp.node].size() ; i++) {
                // 연결 되어 있는 간선 노드 다 넣음.
                q.add(graph[temp.node].get(i));
            }

        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException {


        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V + 1];

        for (int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++)
        {
            st = new StringTokenizer(br.readLine());
            int u, v, e;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            graph[u].add(new Vertex(v, e));
            graph[v].add(new Vertex(u, e));
        }
        prim(1) ;

        System.out.println(total);
//        // dikstra
//        dikstra(start);
//        for (int i = 1; i <= V; i++) {
//            if (dis[i] == Integer.MAX_VALUE) System.out.println("INF");
//            else System.out.println(dis[i]);
//        }
    }

}
