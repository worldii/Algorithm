import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n;
    public static int m;
	public  static  ArrayList<int[]> cor =new ArrayList<>();

    static class Cor {
        int x;
        int y ;
        Cor (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
	public static  int [][] arr;

    public static int [] dx = {-1,1,0,0};
    public static int [] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {


        st =new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [][ ]arr = new int [n][m];
        boolean[][] check = new boolean[n][m];

        Queue<Cor> q= new LinkedList<>();
        for (int i = 0 ; i< n ; i++) {
            st=new StringTokenizer(br.readLine());
            for (int j = 0 ; j< m ; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (i==0 || i==n-1 || j==0 || j== m-1)
                {
                    q.add(new Cor(i,j));
                    check[i][j] = true;
                }
            }
        }

        Queue<Cor> nextQ = new LinkedList<>();

        int cnt = 0;

        while (true) {
            int beforeNum = q.size();
            while (!q.isEmpty()) {
                Cor temp = q.peek();
                q.poll();
                for (int i = 0; i < 4; i++) {
                    int nextX = temp.x + dx[i];
                    int nextY = temp.y + dy[i];
                    if (0 <= nextX && nextY >= 0 && nextY < m && nextX < n) {
                        if (check[nextX][nextY]) continue;
                        check[nextX][nextY] = true;
                        if (arr[nextX][nextY] == 1)
                        {
                            nextQ.add(new Cor(nextX, nextY));
                        }
                        else if (arr[nextX][nextY] == 0) {
                            q.add(new Cor(nextX, nextY));
                        }
                    }
                }
            }

            q = nextQ;
            nextQ = new LinkedList<>();
            if (q.isEmpty()) {
                System.out.println(cnt);
                System.out.println(beforeNum);
                return ;
            }
            cnt++;
        }


    }
}
