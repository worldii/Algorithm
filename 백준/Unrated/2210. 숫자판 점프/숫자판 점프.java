import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int [][] arr = new int [5][5];
    public static int [] dx = { -1,1,0,0};
    public static int [] dy = {0,0, -1,1};
    public static int sum = 0;
    public static void dfs(int startX, int startY, int cnt, String str  ) {
        if (cnt == 5) {
            if (check[Integer.parseInt(str)]++ ==0 ) {
                //System.out.println(str);
                sum++;
            }
            return ;
        }
        for (int i = 0 ; i< 4 ; i++) {
            int newX = startX + dx[i];
            int newY = startY  + dy[i];
            if (0<= newX && newX < 5 && 0<= newY && newY < 5) {
                dfs(newX, newY, cnt+1, arr[newX][newY] + "" + str );
            }
        }

    }
    public static int []check = new int [1000001];
    public static void main(String[] args) throws IOException {
        for (int i = 0 ; i< 5 ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j< 5 ; j++) {

                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0 ; i< 5 ; i++) {
            for (int j = 0 ; j< 5 ; j++) {
                dfs(i,j, 0, arr[i][j] + "");
            }
        }
        System.out.println(sum);
    }
}