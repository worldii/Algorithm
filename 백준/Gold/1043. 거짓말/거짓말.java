import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int n;
    public static int m;
    public static int[] arr;
    public static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int knowPerson = Integer.parseInt(st.nextToken());

        ArrayList<Integer> knows = new ArrayList<>();
        for (int i = 0; i < knowPerson; i++) {
            int personNum = Integer.parseInt(st.nextToken());
            knows.add(personNum);
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int[] partyArr = new int[m + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int num = 0;
            for (int j = 0; j < t; j++) {
                if (j == 0) num = getParent(Integer.parseInt(st.nextToken()));
                else {
                    union(num, Integer.parseInt(st.nextToken()));
                }
            }
            partyArr[i] = num;
        }

      


        int cnt = 0;

        for (int i = 0; i < m; i++) {
            boolean flag = false;
            for (int j = 0; j < knows.size(); j++) {
                if (getParent(partyArr[i]) == getParent(knows.get(j))) {
                    flag = true;
                }
            }
            if (!flag) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static boolean union(int a, int b) {
        a = getParent(a);
        b = getParent(b);
        if (a != b) {

            parent[b] = a;
            return true;
        }
        return false;
    }

    public static int[] parent;

    public static int getParent(int a) {

        if (a == parent[a]) return a;
        parent[a] = getParent(parent[a]);
        return parent[a];
    }
}