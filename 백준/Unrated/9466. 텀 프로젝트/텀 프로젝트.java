import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static StringTokenizer st = null;

    public static int[] arr;
    public static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public static int dfs(int start, int cur, int cnt, boolean[] visit) {
        visit[start] = true;

        // 팀에 들어가 있는지 확인해 본다
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();
        set.add(cur);
        deque.add(cur);
        int next =arr[cur];
        while (true)
        {
            if (set.contains(next)) {
                // 사이클인 경우
                // 1 3
                // 3 4
                // 4 7
                // 7 3
                int size = set.size();
            //    System.out.println(size+ " next " + next);
                while (!deque.isEmpty() && deque.peek() != next) {
                    deque.pollFirst();
                    size--;
                }

                // 사이클인 경우임
                return size;
            }

            if (visit[next]) return 0;
            visit[next]= true;
            // System.out.println("ne" + " " + next);

            // 사이클인경우
            //cur next
            // 4 7
            // 7 6
            // 6 4
            // 4 7
            set.add(next);
            deque.addLast(next);
            // 3 3
            // 사이클이 아닌 경우
            // 1 3
            // 3 3

            // 2 1
            // 1 3
            // 3 3
            next = arr[next];
        }

    }

    public static void main(String[] args) throws IOException {

        int testcase = Integer.parseInt(br.readLine());
        for (int j = 1; j <= testcase; j++) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int sum = 0;
            boolean[] visit = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
            //           System.out.println("num" + i);
                    int t;
                    if (arr[i] == i) { t= 1 ; visit[i] = true;}
                    else  t = dfs(i, i, 0, visit);
                    //  System.out.println(i+ " " + t);
                    sum+=t;
                }

            }
            System.out.println(n-sum);
        }
    }
}