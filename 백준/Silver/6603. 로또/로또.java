import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int[] arr = new int[10];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static boolean[][] checked = null;

    // 항상 최선의 선택을 해야함.

    public static void recur(int cnt, int n, int[] arr, int start, boolean[] checked, int [] arr2) {
        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(arr2[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = start; i < n; i++) {
            if (checked[i]) continue;
            arr2[cnt] = arr[i];
            checked[i] = true;
            recur(cnt + 1, n, arr, i + 1, checked, arr2);
            checked[i] = false;
            arr2[cnt] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            int[] arr = new int[n];
            int[] arr2= new int[n];

            boolean [] checked = new boolean[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            recur(0, n, arr, 0,checked, arr2);
            System.out.println();

        }

    }
}