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
    public static int[][] arr = new int[1001][1001];
    public static boolean checkIsPossible(int start, int end) {
        boolean[]visited = new boolean[n];
        Queue<Integer> q= new LinkedList<>();

        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()){
            Integer temp = q.peek();
            q.poll();
            if (temp == end) return true;
            for (int i = 0 ; i< n ; i++) {
                if (arr[temp][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        return false;
    }
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

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0 ; i< m ; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        boolean isPossible = true;
        for (int i = 0 ; i< m-1 ; i++) {
            if (!checkIsPossible(arr.get(i)-1, arr.get(i+1)-1)) {
                isPossible = false;
                break;
            }
        }
        if (isPossible) System.out.println("YES");
        else System.out.println("NO");

    }
}