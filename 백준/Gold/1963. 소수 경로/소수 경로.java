import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static boolean [] numArray = new boolean [10000];

    public static  void bfs(int start, int end ) {
        boolean [] check = new boolean[10000];
        check[start] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start,0});
        while (!q.isEmpty()) {
            int num = q.peek()[0];
            int cnt = q.peek()[1];
            if (num == end) {
                System.out.println(cnt);
                return ;
            }
            q.poll();
            //
            String str= Integer.toString(num);
            for (int i = 0 ; i< str.length() ; i++) {
                // i번째를 바꿈
                // 그리고
                for (int  j  = 0 ; j<= 9 ; j++) {

                    StringBuilder sb = new StringBuilder();
                    sb.append(str.substring(0,i));
                    sb.append(j);
                    sb.append(str.substring(i+1, str.length()));
                   // System.out.println(sb.toString());

                    int nextNum = Integer.parseInt(sb.toString());
                    if (check[nextNum]) continue;
                    if (numArray[nextNum]) continue;
                    check[nextNum] = true;
                    q.add(new int[]{Integer.parseInt(sb.toString()), cnt+1});
                }
            }

        }
        System.out.println("Impossible");
    }

    public static void main(String[] args) throws IOException {

        st= new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // 에라 토스 테네스 체로 구해놈일단 소수인지
        // 0~9999 까지
        // 탐색 돌림
        for (int i = 2 ; i<= 9999 ; i++) {
            if (!numArray[i]) {
                int t= i;
                for (int j = 2* t ; j<= 9999; j+=t) {
                    numArray[j] = true;
                }
            }
        }

        for (int i = 0 ; i< n ; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bfs(start, end);
        }
    }


}
