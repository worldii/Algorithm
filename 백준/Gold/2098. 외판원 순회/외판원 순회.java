import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n;

    public static int [][]dp ;
    public static final int MAX_INT = Integer.MAX_VALUE-1;
    public static int [][] arr;

    public static int dfs(int cnt, int visited , boolean[][]check) {

        if (check[cnt][visited]) return dp[cnt][visited];

        if (visited == (1<< n)-1)
        {
            if (arr[cnt][0] !=0 ) return arr[cnt][0];
            else return -1;
        }

        for (int i = 0 ; i< n ; i++) {
            if ((visited & (1<<i)) == 0)
            {
                // 방문한 적이 없으면,// 방문했다고 체크한다.
                int nextVisit = visited | (1<<i);
                if ( arr[cnt][i] ==0 ) continue;
                int temp = dfs(i, nextVisit, check);
              //  System.out.println(temp);
                if (temp!= -1)
                {
                    dp[cnt][visited] = Math.min(dp[cnt][visited] , arr[cnt][i] + temp);
                }
            }
        }

        if (dp[cnt][visited] == MAX_INT) dp[cnt][visited] = -1;
        check[cnt][visited] = true;
        return dp[cnt][visited];
    }

    public static void main(String[] args) throws IOException {


        st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr =new int [n][n];
        dp = new int [n][1 <<n];
        boolean[][] check = new boolean[n][1<<n];
        for (int i = 0 ; i< n ; i++) {
            st= new StringTokenizer(br.readLine());
            for (int j =0 ;  j< n ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i<n ; i++) {
            Arrays.fill(dp[i], MAX_INT);
        }

        // 0번째만 방문했다고 체크
        System.out.println(dfs(0, 1, check));


    }
}
