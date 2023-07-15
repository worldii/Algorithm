import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;


    public static int n, m, r;

    static class Node {
        int num;
        int dis;

        Node(int num, int dis) {
            this.num = num;
            this.dis = dis;
        }
    }

    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }
        int findMaxNum = 0;
        for (int i = 1; i <= n; i++) {
            findMaxNum = Math.max(findMaxNum(i, m), findMaxNum);
        }
        System.out.println(findMaxNum);
    }

    public static int[] arr;

    private static int findMaxNum(int num, int maxPath) {
        boolean[] check = new boolean[n + 1];
        Queue<Node> q = new ArrayDeque<>();
        int total = 0;
        q.add(new Node(num, 0));
        while (!q.isEmpty()) {
            Node temp = q.poll();
            check[temp.num] = true;
            for (int i = 0; i < graph.get(temp.num).size(); i++) {
                int next = graph.get(temp.num).get(i).num;
                int dis = graph.get(temp.num).get(i).dis;
                if (temp.dis + dis > maxPath) continue;
                q.add(new Node(next, temp.dis + dis));
            }
        }
        for (int i = 1; i <= n; i++) {
            if (check[i]) total += arr[i];
        }
        return total;
    }
}