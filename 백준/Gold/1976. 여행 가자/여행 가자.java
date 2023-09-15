import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n, m;
    public static int []parent;
    public static int findParent(int a, int[]parent) {
        if (parent[a] == a) return a;
        parent[a] = findParent(parent[a], parent);
         return parent[a];
    }
    public static boolean union (int a, int b) {
        a= findParent(a, parent);
        b= findParent(b, parent);
        if (a!=b) {
            parent[a] = b;
            return true;
        } else {
            return false;
        }
    }
    public static int[][] arr = new int[1001][1001];
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        for (int i = 0 ; i< n ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j< n ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());

        ArrayList<Integer> b = new ArrayList<>();
        for (int i = 0 ; i< m ; i++) {
            b.add(Integer.parseInt(st.nextToken())-1);
        }
        parent = new int[n];

        for (int i = 0 ; i< n ; i++) {
            parent[i] = i;
        }

        for (int i = 0 ; i< n ; i++) {
            for (int j = 0 ; j< n ; j++) {
                if (arr[i][j] == 1) union(i, j);
            }
        }
        boolean isPossible = true;
        for (int i = 0 ; i<  m ; i++) {
            if (findParent(b.get(i), parent) != findParent(b.get(0), parent)) {
                isPossible = false;
                break;
            }
        }
        System.out.println(isPossible ? "YES" : "NO");
    }
}