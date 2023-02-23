import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Cor {
    int dis;
    int x;
    int y;

    Cor(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }

    public String toString() {
        return " X : " + this.x + "Y : " + this.y + " DIS : " + this.dis;
    }
}

public class Main {

    public static int n, m, d;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static int maxNum = -1;
    public static int[][] arr;

    public static int getDis(int x, int y, int x2, int y2) {
        return Math.abs(x - x2) + Math.abs(y - y2);
    }
    public static void killEne(int [][] arr ) {
        for (int i = 0 ; i< n ; i++) {
            for (int j = 0 ; j< m ; j++) {
                if (arr[i][j] == 2) {
                    arr[i][j]  =0;
                }
            }
        }
    }
    public static Cor selectEne(int x, int y, int[][]arr) {
        Cor temp = null;
        for (int i = 0 ; i<x ; i++) {
            for (int j = 0  ; j<  m ; j++) {
                if (arr[i][j] ==1 ) {
                    int dis = getDis(i,j,x, y) ;
                    if (dis <= d) {

                        if (temp == null) temp = new Cor(i,j, dis);
                        if (temp.dis > dis) temp = new Cor(i,j,dis);
                        if (temp.dis == dis && temp.y > j) temp = new Cor(i,j, dis );
                    }
                }
            }
        }
        return temp;
    }
    public static int shoot(int[] loc) {
      //  System.out.println("LOC");
        int [][] newArr = new int [n][m];
        for (int i = 0 ; i< n ; i++) {
            for (int j = 0 ; j< m ; j++) {
                newArr[i][j] = arr[i][j] ;
            }
        }

        int sum = 0;
        boolean [][] checked = new boolean [n] [m] ;
        int row = n;
        for (int i = 0; i < n; i++) {
            // 궁수 1,2,3;
            List<Cor> enemy = new ArrayList<>();

            for (int t = 0 ;t<3 ; t++) {
                Cor selectEnemy =selectEne(row, loc[t],newArr);
                if (selectEnemy == null ) continue;
                enemy.add(selectEnemy);
            }

            for (int t = 0 ; t< enemy.size() ; t++) {
                Cor selectEnemy = enemy.get(t);
                //System.out.println("ENE"+selectEnemy);
                if (!checked[selectEnemy.x][selectEnemy.y]) {
                    checked[selectEnemy.x][selectEnemy.y] = true;
                    sum++;
                    newArr[selectEnemy.x][selectEnemy.y] = 0;
                }
            }
          //  System.out.println(sum+" ");
            row--;

        }


        return sum;
    }

    public static void makeCombination(int cnt, int end, int start, int[] arr, boolean[] checked) {
        if (cnt == end) {
            int num = shoot(arr);
            maxNum = Math.max(num, maxNum);
            return;
        }
        for (int i = start; i < m; i++) {
            if (checked[i]) continue;
            checked[i] = true;
            arr[cnt] = i;

            makeCombination(cnt + 1, end, i + 1, arr, checked);
            arr[cnt] = 0;
            checked[i] = false;

        }
    }

    public static void main(String[] args) throws IOException {


        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] loc = new int[3];

        boolean[] checked = new boolean[m + 1];
        makeCombination(0, 3, 0, loc, checked);
        System.out.println(maxNum);


    }
}