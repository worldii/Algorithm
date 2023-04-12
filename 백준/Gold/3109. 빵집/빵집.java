import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static StringTokenizer st = null;
    public static char[][] arr;
    public static int R,C;

//    5 5
//.xx..
//..x..
//.....
//...x.
//...x.
    public static BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        R= Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for (int i = 0 ; i< R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        boolean [][]check = new boolean[R][C];
        int cnt = 0;
        for (int i = 0 ; i< R ; i++) {
            check[i][0] = true;
            if(dfs(i,0,check)) cnt++;

//
//            for (int ii = 0 ; ii< R ; ii++) {
//                for (int j = 0 ; j< C ; j++) {
//                    System.out.print(check[ii][j]);
//                }System.out.println();
//            }
//            System.out.println();

        }
        System.out.println(cnt);
    }

    public static int [] dx = {-1,0,1};
    public static int []dy = {1,1,1};
    private static boolean dfs(int row, int col,boolean[][]check) {
        if (col == C-1) {
            return  true;
        }
        boolean flag =false;
        outer : for (int i = 0 ; i<3 ; i++) {
            int nextX = dx[i] + row;
            int nextY = dy[i] + col;
            if (0<=nextY && 0<=nextX && nextY < C && nextX <R) {
                if (check[nextX][nextY]) continue;
                if(arr[nextX][nextY] =='x') continue ;
                check[nextX][nextY] = true;

                flag = dfs(nextX, nextY, check);
                if (flag ) break outer;
            }
        }
        if(flag) return  true;
        return false;
    }
}
