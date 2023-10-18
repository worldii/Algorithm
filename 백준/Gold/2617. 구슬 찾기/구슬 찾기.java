import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /*
     *
     * 첫 줄은 구슬의 개수를 나타내는 정수 N(1 ≤ N ≤ 99)과 저울에 올려 본 쌍의 개수 M(1 ≤ M ≤ N(N-1)/2)이 주어진다.
     *  그 다음 M 개의 줄은 각 줄마다 두 개의 구슬 번호가 주어지는데, 앞 번호의 구슬이 뒤 번호의 구슬보다 무겁다는 것을 뜻한다.
     * */

    public static StringTokenizer st = null;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int n;
    public static int m;
    public static ArrayList<ArrayList<Integer>> minGraph = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> maxGraph = new ArrayList<>();


    public static int bfs(int start, ArrayList<ArrayList<Integer>> graph) {
        int count = 0;
        boolean[] check = new boolean[n + 1];
        check[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int temp = q.poll();
            for (int i = 0; i < graph.get(temp).size(); i++) {
                int next = graph.get(temp).get(i);
                if (check[next]) {
                    continue;
                }
                check[next] = true;
                q.add(next);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            minGraph.add(new ArrayList<>());
            maxGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            minGraph.get(a).add(b);
            maxGraph.get(b).add(a);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = bfs(i, minGraph);
            if (checkCount(cnt)) {
                count++;
                continue;
            }
            cnt = bfs(i, maxGraph);
            if (checkCount(cnt)) {
                count++;
            }
        }
        System.out.println(count);

    }
    public static boolean checkCount(int cnt) {
        if (cnt >n/2) return true;
        return false;
    }
}