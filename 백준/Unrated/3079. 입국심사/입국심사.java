import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;


    public static long n,m;
    public static void main(String[] args) throws IOException {

        st= new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());

         long [] a= new long [n];

         Arrays.sort(a);

         long maxNum = Integer.MAX_VALUE;
         for (int i = 0 ; i< n  ; i ++) {
             a[i] = Long.parseLong(br.readLine());
             maxNum = Math.min(maxNum, a[i]);
         }
         maxNum = maxNum*m;
        long start = 0;
        long end = maxNum;
         while (start< end) {
             long mid = (start+end)/2;
            // System.out.println(mid);
             //시간 체크 ;
             long sum = 0;

             for (int i = 0 ; i< n ; i++) {
                 sum += mid/a[i];
             }
             if (sum >= m) {
                 // 괜찮음
                 end = mid;
             }
             else {
                 // 안됨 더 늘려야 함
                 start = mid+1;
             }
         }

         //하한선을 구함
        System.out.println(start);
    }


}
