import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static int LOW_LIMIT = 1;
    public static int HIGH_LIMIT;

    public static double dx[] = {-2, 2, 0.5, -0.5};

    public static void dfs(int cnt, int end, boolean[] check, int[] diff, int sum, int start) {
        if (cnt == end) {
            if (sum + diff[cnt-1] >n || sum + diff[cnt-1] <1) return;
            if (check[sum+diff[cnt-1]]) return ;
            System.out.println("YES");
            int temp = start;
            System.out.print(temp + " ");
            for (int j = 0; j < end; j++) {
                temp += diff[j];
                System.out.print(temp + " ");
            }
            System.exit(0);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int num = Math.abs((int)((double)diff[cnt - 1] * dx[i]));

            if (1 <= num && num <= HIGH_LIMIT) {
                diff[cnt] = (int)((double)diff[cnt - 1] * dx[i]);
                if ((sum + diff[cnt-1]) >n || (sum + diff[cnt-1]) <1) continue;
                if (check[sum+diff[cnt-1]]) continue;;
                check[sum+diff[cnt-1]] = true;
                dfs(cnt + 1, end, check, diff, sum+ diff[cnt-1], start);
                check[sum+diff[cnt-1]] = false;
            }
        }
    }

    public static int n;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        int maxNum = n - 1;
        int digit = 1;
        ArrayList<Integer> arr2 = new ArrayList<>();
        while (digit <= maxNum) {
            arr2.add(digit);
            arr2.add(-1 * digit);
            digit = 2 * digit;
        }

        HIGH_LIMIT = digit / 2;
        boolean[] check = new boolean[n + 1];
        int[] diff = new int[n  +1];
        for (int j = 1; j <= n; j++) {
            for (int i = 0; i < arr2.size(); i++) {
                diff[0] = arr2.get(i);
                check[j] = true;
                dfs(1, n-1 , check, diff, j, j);
                check[j] = false;
            }
        }
        System.out.println("NO");
    }
}
