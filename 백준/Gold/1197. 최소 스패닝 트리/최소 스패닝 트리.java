import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        @Override
        public String toString() {
            return "Edge{" +
                    "start=" + start +
                    ", end=" + end +
                    ", cost=" + cost +
                    '}';
        }

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Edge vertex) {
            return Integer.compare(this.cost, vertex.cost);
        }
    }

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

    public static long total = 0;

    public static void prim(int start) {

        // start check 한다.

        boolean[] ch = new boolean[V + 1];
        // pq
        // start 와 연결되어 있는것다 넣음

        PriorityQueue<Vertex> q = new PriorityQueue<>();
        q.add(new Vertex(start, 0));


        int vCount = 0;

        while (!q.isEmpty()) {
            Vertex temp = q.peek();
            q.poll();

            if (ch[temp.node]) continue;
            ;
            ch[temp.node] = true;

            total += temp.cost;
            if (++vCount == V) break;
            for (int i = 0; i < graph[temp.node].size(); i++) {
                // 연결 되어 있는 간선 노드 다 넣음.
                q.add(graph[temp.node].get(i));
            }

        }
    }

    public static int[] parent;

    public static int getParent(int a) {
        if (parent[a] == a) return a;
        parent[a] = getParent(parent[a]);
        return parent[a];
    }

    public static boolean IsSameParent(int a, int b) {
        if (getParent(a) == getParent(b)) {
            return true;
        } else return false;
    }

    public static void union(int a, int b) {
        int aParent = getParent(a);
        int bParent = getParent(b);
        if (aParent != bParent) {
            parent[bParent] = aParent;
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {


        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());


        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u, v, e;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, e));
        }

        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
        while (!edges.isEmpty()) {
            Edge temp = edges.peek();
            edges.poll();
           // System.out.println(temp);

            if (!IsSameParent(temp.end, temp.start)) {
                union(temp.end, temp.start);
                total += temp.cost;
            }
        }
        System.out.println(total);

    }

}
