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


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

//        int [] arr =new int [n];
//        for (int i = 0 ; i< n ; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }

        // 졸면 true
        int[] checked = new int[n + 3];
        int[] subset = new int[n + 3];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            int t = Integer.parseInt(st.nextToken());
            checked[t] = 2;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            int t = Integer.parseInt(st.nextToken());
            if (checked[t] == 2) continue;
            for (int j = t; j <= n + 2; j += t) {
                if (checked[j] == 2) continue;
                checked[j] = 1;

            }
        }


        for (int i = 3 ; i<= n+2 ; i++) {
            subset[i] = subset[i-1] ;
            if (checked[i] !=1) subset[i]++;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            System.out.println(subset[end]-subset[start-1]);

        }
    }
}

