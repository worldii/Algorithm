import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static BufferedReader
            br = new BufferedReader(new InputStreamReader(System.in));
    private static int n;
    private static char[][] arr;
    public static int [] dx = {-1,1,0,0};
    public static int [] dy = {0,0,-1,1};
    static class Cor {
        int x ;
        int y ;
        Cor (int x, int y) {
            this.x= x;
            this.y = y;
        }
    }
    public static int R, C;

    private static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C =Integer.parseInt(st.nextToken());

        int idx = 0;
        Cor [] backCor = new Cor[2];
        boolean[][] check = new boolean[R][C];

        arr = new char [R][C];

        Queue<Cor> back = new ArrayDeque<>();
        Queue<Cor> nextBack = new ArrayDeque<>();
        Queue<Cor> water = new ArrayDeque<>();
        Queue<Cor> waterNextQ= new ArrayDeque<>();

        for (int i = 0 ; i< R ; i++)
        {
            arr[i] = br.readLine().toCharArray();
            for (int j = 0 ; j< C ; j++)
            {
                if (arr[i][j] == 'L')
                {
                    backCor[idx++] = new Cor(i,j);
                    water.add(new Cor(i,j));
                }
                else if (arr[i][j] == '.')
                {
                    water.add(new Cor(i,j));
                }
            }
        }

        back.add(backCor[0]);
        check[backCor[0].x][backCor[0].y] = true;
        int cnt = 0;
        while (true)
        {
            while (!back.isEmpty())
            {
                Cor temp = back.peek();
                back.poll();
               // System.out.println(temp.x + " " + temp.y);

                for (int i = 0 ; i< 4;  i++) {

                    int nextX = dx[i] + temp.x;
                    int nextY = dy[i] + temp.y;

                    if (0<= nextY && nextY < C && 0<= nextX && nextX < R) {
                        if (check[nextX][nextY]) continue;
                        else if (arr[nextX][nextY] == 'X')
                        {
                            nextBack.add(new Cor(nextX, nextY));

                            check[nextX][nextY]= true;

                        }
                        else if (arr[nextX][nextY] == '.') {
                            check[nextX][nextY]= true;
                            back.add(new Cor(nextX, nextY));
                        }
                        else if (arr[nextX][nextY] == 'L') {
                            check[nextX][nextY]= true;
                            System.out.println(cnt);
                            return;
                        }
                    }
                }
            }

            while (!water.isEmpty()) {
                Cor temp = water.peek();
                water.poll();
                for (int i = 0 ; i< 4 ; i++)
                {
                    int nextX = dx[i] + temp.x;
                    int nextY = dy[i] + temp.y;
                    if (0<= nextY && nextY < C && 0<= nextX && nextX < R) {
                        if (arr[nextX][nextY] == 'X') {
                            waterNextQ.add(new Cor(nextX, nextY));
                            arr[nextX][nextY] = '.';
                        }
                    }
                }
            }

//            for (int i =0  ; i< R ; i++) {
//                for (int j = 0 ; j< C; j++) {
//                    System.out.print(arr[i][j]);
//                }System.out.println();
//            }System.out.println();

            water= waterNextQ;
            waterNextQ = new ArrayDeque<>();
            cnt++;
            back = nextBack;
            nextBack = new ArrayDeque<>();
            if (water.isEmpty()) {
                break;
            }
        }

    }
}