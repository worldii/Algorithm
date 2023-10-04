import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    // MST 를 만든다. -> 크루스칼을 이용한다.
    static class Edges {

        int a;
        int b;
        double weight;

        public Edges(int a, int b, double weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    public static PriorityQueue<Edges> edges = new PriorityQueue<>(new Comparator<Edges>() {
        @Override
        public int compare(Edges o1, Edges o2) {
            if (o1.weight > o2.weight) {
                return 1;
            } else {
                return -1;
            }
        }
    });

    public static List<int[]> cor = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            cor.add(new int[]{x, y});
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                edges.add(new Edges(i, j, calculate(i, j)));
            }
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            // union 합치기
            union(a, b);
        }
        kruskal(n, m);

        System.out.println(String.format("%.2f",(double)Math.round(total*100)/100));
    }

    public static double total = 0;

    public static boolean union(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if (a == b) {
            return false;
        }
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        return true;
    }

    public static int[] parent;

    public static int getParent(int a) {
        if (parent[a] == a) {
            return a;
        }
        parent[a] = getParent(parent[a]);
        return parent[a];
    }

    private static void kruskal(int n, int m) {
        while ( !edges.isEmpty()) {
            Edges edges1 = edges.poll();
            int a = edges1.a;
            int b = edges1.b;
            if (union(a, b)) {
                total+= edges1.weight;
            }
        }
    }


    public static double calculate(int a, int b) {
        long ax = cor.get(a)[0];
        long ay = cor.get(a)[1];
        long bx = cor.get(b)[0];
        long by = cor.get(b)[1];
        return Math.sqrt((ax - bx) * (ax - bx) + (ay - by) * (ay - by));
    }

}