import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Cor {
    int x;
    int y;

    Cor(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String toString() {
        return this.x+ " " + this.y;
    }
}

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int minNum = Integer.MAX_VALUE;

    public static List<Cor> chicken = new ArrayList<>();
    public static List<Cor> home = new ArrayList<>();

    public static int n,m;

    public static  boolean []checked ;

    public static  int calculateDis(boolean [] checked)
    {
        List<Cor> list =new ArrayList<>();
        for (int i = 0  ; i< chicken.size(); i++) {
            if (checked[i]) list.add(chicken.get(i));
        }
        int dis = 0;
        for (int i = 0 ; i< home.size() ; i++) {
            Cor tempHome = home.get(i);
            int minDis = Integer.MAX_VALUE;
            for (int Chickenidx = 0 ; Chickenidx< list.size() ; Chickenidx++) {
                int tempDis = Math.abs(tempHome.x - list.get(Chickenidx).x) + Math.abs(tempHome.y - list.get(Chickenidx).y);
                minDis = Math.min(minDis,tempDis);

            }
            dis += minDis;
        }
        return dis;
    }
    public  static int maxNum = Integer.MAX_VALUE;
    public static void recur (int cnt, int start, boolean [] checked ) {
        if (cnt== m ) {
//            for (int i = 0  ; i< chicken.size(); i++) {
//                if (checked[i]) System.out.print(i+" ");
//            }
            int sum =calculateDis(checked);
            maxNum= Math.min(maxNum, sum);

            return;

        }

        for (int i = start ; i< chicken.size() ; i++) {
            if (checked[i]) continue;
            checked[i] = true;
            recur (cnt+1, i+1, checked);
            checked[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    chicken.add(new Cor(i, j));
                }
                else if (arr[i][j] == 1 ) {
                    home.add(new Cor(i,j));
                }
            }
        }

        checked = new boolean[n*n];
        recur (0,0, checked);
        System.out.print(maxNum);

    }
}
