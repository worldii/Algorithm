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

    public static int n;
    public static long x ;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Long.parseLong(st.nextToken());

        long  [] arr = new long  [n];
        st =new StringTokenizer(br.readLine());

        for (int i = 0 ; i< n ; i++) {
            arr [i] = Long.parseLong(st.nextToken());
        }

        int [] checked = new int [n];

        Arrays.sort(arr);

        long sum =0;
        int end = 0;
        for (end = n-1 ; end>= 0 ; end-- ) {
            if (arr[end] >=x) {
                checked[end] = 1;
                sum++;
            }
            else {
                break;
            }
        }

        int start = 0;
        while (start< end ) {
           // System.out.println(start+ " " + end );
            if (2 * (arr[start] + arr[end]) + x < 2 * x ) {start++;}
            else if (2* (arr[start] + arr[end] ) + x >= 2* x)
            {
                checked[end] = 1;
                checked[start] = 1;
                start++;
                end--;
                sum++;
            }

        }

//        if (start>= end ) {
//            System.out.println(sum);
//            return ;
//        }
        long  cnt =  0;

        for (int i = 0 ; i< n ; i++) {
            if (checked[i] ==0) cnt++;
          //  System.out.print(checked[i]+ " ");
        }
       // cnt = end - start+1;

        sum += (cnt/3);
        System.out.println(sum);

    }
}