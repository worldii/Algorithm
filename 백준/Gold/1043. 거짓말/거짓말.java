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
        //System.out.println("Hello World!");
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int knowPerson = Integer.parseInt(st.nextToken());
        boolean[] knowPersons = new boolean[n + 1];
        for (int i = 0; i < knowPerson; i++) {
            int personNum = Integer.parseInt(st.nextToken());
            knowPersons[personNum] = true;
        }

        ArrayList<ArrayList<Integer>> party = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < t; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr.add(num);
            }
            party.add(arr);
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            ArrayList<Integer> temp = party.get(i);
            for (int j = 0; j < temp.size(); j++) {
                for (int jj = 0; jj < temp.size(); jj++) {
                    union(temp.get(j), temp.get(jj));
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            parent[i] = getParent(i);
            if (knowPersons[i]) knowPersons[parent[i]] = true;
            //System.out.print(parent[i]);
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            boolean flag = false;
            ArrayList<Integer> temp = party.get(i);
            for (int j = 0; j < temp.size(); j++) {
                if (knowPersons[parent[temp.get(j)]]) flag = true;
            }
            if (!flag) cnt++;
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