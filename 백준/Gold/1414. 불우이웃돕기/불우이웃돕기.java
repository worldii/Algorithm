import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n;
    public static int INT_MAX = 987654321;
    public static int[][] arr;
    public static int[][] dis;

    public static int changeToNum(final char a) {
        if (a == '0') {
            return INT_MAX;
        }
        if ('a' <= a && a <= 'z') {
            return a - 'a' + 1;
        } else {
            return a - 'A' + 27;
        }
    }
    static class Edge {
        int a;
        int b;
        int dist;

        public Edge(int a, int b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }
    public static int []parent;

    public static int getParent(int a) {
        if (parent[a] == a) return a;
        parent[a] = getParent(parent[a]);
        return parent[a];
    }
    public static boolean union (int a, int b ) {
        a= getParent(a);
        b= getParent(b);
        if (a==b) {
            return false;
        }
        parent[b] = a;
        return true;
    }

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dis = new int[n][n];
        parent = new int[n];

        for (int i = 0 ; i< n ; i++ ) {
            parent[i] = i;
        }

        PriorityQueue<Edge> pq= new PriorityQueue<>((a,b)-> a.dist-b.dist);
        long totalSum = 0;
        for (int i = 0; i < n; i++) {
            char[] charArray = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                arr[i][j] = changeToNum(charArray[j]);
                if (arr[i][j] == INT_MAX) continue;
                totalSum += arr[i][j];
                pq.add(new Edge(i, j, arr[i][j]));
            }
        }


        int nodeCount = 0;
        int sum= 0;
        // 최소 스패닝 트리를 구한다.
        while (!pq.isEmpty()  &&   nodeCount < n-1) {
            Edge edge = pq.poll();
            if (union(edge.a, edge.b)) {
                nodeCount++;
                sum +=edge.dist;
            }
        }
        if (nodeCount != n-1) System.out.println(-1);
        else System.out.println(totalSum-sum);


    }
}