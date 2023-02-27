import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Cor {
    int x ;
    int y ;
    int nextX;
    int nextY;
    Cor (int x, int y ) {
        this.x = x;
        this.y = y;

    }
}


public class Main {
    public static int [] dx = {-1,1,0,0};
    public static int [] dy = {0,0,-1,1};


    public static int cnt = 0;
    public static boolean[][] checked = new boolean[102][102];

    // 0을 만났을 때 경계선에 도달하는지  계산
    public static void bfs(int startx, int starty ) {
        // 벽을 체크 해줌.
        // 뚫려 있다면 무조건 경계점을 만날 것이다.
        Queue<Cor> q= new LinkedList<>();
        checked[startx][starty ] = true;
        q.add(new Cor(startx, starty)) ;
        while (!q.isEmpty()) {
            Cor temp = q.peek();
            q.poll();
            for (int i = 0 ; i< 4 ; i++) {
                int nextX= dx[i] + temp.x;
                int nextY  = dy[i] + temp.y;
                if ( 0<=nextX && nextX <= 100 && 0<= nextY && nextY <= 100) {
                    if ( map[nextX][nextY] == 1 ) {
                        checked[nextX][nextY] =true;
                        cnt++;
                    }
                    else if (map[nextX][nextY] == 0 && !checked[nextX][nextY]) {
                        checked[nextX][nextY] =true;
                        q.add(new Cor(nextX, nextY));
                    }


                }
            }



        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int [][] map = new int[102][102];
    public static List<Cor> list = new ArrayList<>();
    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        int n = Integer.parseInt(br.readLine());

        // 둘레를 1로 , 아니면 테두리 안에는  2로 채움
        for (int i = 0 ; i< n ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int nextX = x +9;
            int nextY = y +9;
            for (int j = x ; j<= nextX ; j++) {
                for (int jj = y ;jj <=nextY ; jj++) {
                    map[j][jj] = 1;
                }
            }


        }

        // 0이 있을 때 주위에 벽이 있는지 검사.
        for (int i = 0 ; i<= 101 ; i++) {
            for (int j = 0 ; j<= 101 ; j++) {
                if (map[i][j] == 0&& !checked[i][j])
                {
                    bfs(i,j);
                }
            }
        }
        System.out.println(cnt);
    }

}
