import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n;
    public static long []a;
    public static long []d;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        d= new long[n+1];
        a= new long[n+1];
        for (int i = 1 ; i<=n ; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        for (int i = 1 ; i<= n ; i++) {
            for (int j = 1 ; j* j<= i ; j++) {
                if (i%j == 0) {
                    d[j] += a[i];
                    if (j*j != i) d[i/j]+= a[i];
                }
            }
        }
        long maxDepth = 0;
        int maxidx = 0;
        for (int i = 1 ; i<= n ; i++) {
            if (d[i] > maxDepth) {
                maxDepth = d[i];
                maxidx = i;
            }
            //System.out.println(d[i]);
        }
        System.out.println(maxidx+" " +maxDepth);
    }

}