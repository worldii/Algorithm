import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

/**
 * [키워드]
 * <p>
 * [풀이과정]
 * <p>
 * [입력]
 * [출력]
 *
 * @author SSAFY
 * @performance
 * @category #그래프, #BFS
 * @see
 */

public class Main {

    public static int l, c;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static char [] arr ;
    public static void recur(int cnt, char[] arr2, boolean[] check, int start) {
        if (cnt == l) {
            int ja = 0;
            int mo = 0 ;
            for (int i = 0 ; i< l; i++) {
                if (arr2[i] == 'a' || arr2[i] == 'e' || arr2[i] == 'i' || arr2[i] == 'o' || arr2[i] == 'u') {
                    mo++;
                }
                else ja++;
            }
            if (ja < 2 || mo < 1 ) return;
            for (int i = 0; i < l; i++) {
                System.out.print(arr2[i]);
            }
            System.out.println();
            return ;
        }

        for (int i = start ; i < c ; i++ ) {
            if (!check[i]) {
                check[i] = true;
                arr2[cnt] =arr[i];
                recur(cnt+1, arr2, check, i+1);
                check[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        l= Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        char[] arr2 = new char[l];
        boolean[] check = new boolean[c];

        recur(0, arr2, check, 0);
    }
}