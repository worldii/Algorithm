import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static boolean[][] checked = null;

    // 항상 최선의 선택을 해야함.


    public static int[] dx = {0, 0, 1, 1};
    public static int[] dy = {0, 1, 0, 1};

    public static int maxSum = 0;

    public static int[] arr;

    public static int n, m;

    public static boolean check(int mid) {
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + arr[i] > mid) {
                cnt++;
                sum = 0;
                sum += arr[i];
                if (cnt == m) return false;
            } else sum += arr[i];

        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[n];

        int maxNum = -1;
        //int end = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxNum = Math.max(maxNum, arr[i]);
        }

        int end = 1000000000;
        int start = maxNum;

        //   check(15);
        int mid = 0;

        int oldmid = 0;
        oldmid = (start + end) / 2;
        while (start <= end) {
            mid = (start + end) / 2;
            if (start == end) break;
               //System.out.println(mid);
            if (!check(mid)) {
                start = mid+1;
            }
            else end = mid;
        }
      System.out.print(start);
    }
}

