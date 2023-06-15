import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static char[][] arr;

    public static class Cor {
        int x;
        int y;

        Cor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static ArrayList<Cor> stuList = new ArrayList<>();
    public static ArrayList<Cor> teaList = new ArrayList<>();
    public static boolean flag = false;
    public static int dx[] = {-1, 1, 0, 0};
    public static int dy[] = {0, 0, -1, 1};

    public static void recur(int cnt, int end, boolean[] check, int[] arr2, int start) {
        if (cnt == end) {
            boolean[] wallCheck = new boolean[n*n+1];

            for (int t = 0 ; t<3 ; t++) {
                wallCheck[arr2[t]] = true;
            }

            for (int i = 0; i < teaList.size(); i++) {
                for (int j = 0; j < 4; j++) {
                    boolean wallFlag = false;
                    int newX = dx[j] + teaList.get(i).x;
                    int newY = dy[j] + teaList.get(i).y;
                    while (0 <= newX && newX < n && 0 <= newY && newY < n) {
                        if (wallCheck[newX*n + newY]) wallFlag =true;
                        if (!wallFlag && arr[newX][newY]== 'S') return;
                        newX = dx[j] + newX;
                        newY = dy[j] + newY;
                    }
                }
            }

            flag = true;

            return;
        }
        for (int i = start; i < idxArr.size(); i++) {
            if (check[i]) continue;
            check[i] = true;
            arr2[cnt] = idxArr.get(i);
            recur(cnt + 1, end, check, arr2, i + 1);
            check[i] = false;
        }
    }

    public static ArrayList<Integer> idxArr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = st.nextToken().charAt(0);
                if (arr[i][j] == 'X') idxArr.add(i * n + j);
                else if (arr[i][j] == 'T') teaList.add(new Cor(i, j));
                else stuList.add(new Cor(i, j));
            }
        }

        boolean[] check = new boolean[n * n + 1];
        int[] arr = new int[n * n + 1];

        recur(0, 3, check, arr, 0);

        if (flag) System.out.println("YES");
        else System.out.println("NO");

    }
}
