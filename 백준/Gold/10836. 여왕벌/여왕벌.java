import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n, m;
    public static int[] grow;
    public static int[][] originalArr;
    public static int[][] nextArr;
    public static int[] dx = {-1, -1, 0};
    public static int[] dy = {0, -1, -1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        originalArr = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                originalArr[i][j] = 1;
            }
        }

        grow = new int[2 * m - 1];
        Arrays.fill(grow, 1);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int zeroNum =Integer.parseInt(st.nextToken());
            int idx =0;
            for (int j = 0 ; j< zeroNum ; j++) {
                grow[idx++] += 0;
            }
            int firstNum= Integer.parseInt(st.nextToken());
            for (int j = 0 ; j< firstNum ; j++) {
                grow[idx++] += 1;
            }
            int secondNum= Integer.parseInt(st.nextToken());
            for (int j = 0 ; j< secondNum ; j++) {
                grow[idx++] += 2;
            }
        }

        int [] row = new int [m+1];
        int [] col = new int [m+1];
        int idx =0;
        for (int i = m-1; i>=0 ;i--) {
            col[i] = grow[idx++];
        }
        idx--;
        for (int i = 0; i< m ;i++) {
            row[i] =grow[idx++];
            System.out.print(row[i]+" ");
        }System.out.println();

        for (int i = 1; i<m ;i++) {
            System.out.print(col[i] +" ");
            for (int j = 1; j< m ;j++) {
                System.out.print(row[j]+ " ");
            }
            System.out.println();
        }

    }



}