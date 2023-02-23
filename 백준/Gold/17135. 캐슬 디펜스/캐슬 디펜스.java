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

    public static int[][] copy(int[][] arr) {
        int[][] newArr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }

    public static void downArr(int[][] arr) {
        int[][] newArr = copy(arr);
        // 다운 !!
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = newArr[i - 1][j];
            }
        }
        for (int i = 0; i < m; i++) {
            arr[0][i] = 0;
        }

    }

    public static int shoot(int[] loc) {
        Comparator<Cor> c = new Comparator<Cor>() {
            @Override
            public int compare(Cor o1, Cor o2) {
                if (o1.dis != o2.dis) return Integer.compare(o1.dis, o2.dis);
                else return Integer.compare(o1.y, o2.y);
            }
        };

        PriorityQueue<Cor> q3 = new PriorityQueue<>(c);
        PriorityQueue<Cor> q1 = new PriorityQueue<>(c);
        PriorityQueue<Cor> q2 = new PriorityQueue<>(c);

        int[][] newArr = copy(arr);
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int personX = n;
            // 궁수 1,2,3;
            int person1Y = loc[0];
            int person2Y = loc[1];
            int person3Y = loc[2];
            boolean[][] checked = new boolean[n][m];
            // 탐색하여 q에 가능한 것 다 넣음
            q1.clear();
            q2.clear();
            q3.clear();

            // 궁수 1
            for (int t = 0; t < n; t++) {
                for (int tt = 0; tt < m; tt++) {
                    if (newArr[t][tt] == 1) {
                        int dis = getDis(personX, person1Y, t, tt);
                        if (dis <= d) {
                            q1.add(new Cor(t, tt, dis));
                        }
                        dis = getDis(personX, person2Y, t, tt);
                        if (dis <= d) {
                            q2.add(new Cor(t, tt, dis));
                        }
                        dis = getDis(personX, person3Y, t, tt);
                        if (dis <= d) {
                            q3.add(new Cor(t, tt, dis));
                        }
                    }
                }
            }

            if (!q1.isEmpty()) {
                Cor temp = q1.peek();
                if (!checked[temp.x][temp.y]) {
                    checked[temp.x][temp.y] = true;
                    sum++;
                    newArr[temp.x][temp.y] = 0;
                }
            }

            if (!q2.isEmpty()) {
                Cor temp = q2.peek();
                if (!checked[temp.x][temp.y]) {
                    checked[temp.x][temp.y] = true;
                    sum++;
                    newArr[temp.x][temp.y] = 0;
                }
            }

            if (!q3.isEmpty()) {
                Cor temp = q3.peek();
                if (!checked[temp.x][temp.y]) {
                    checked[temp.x][temp.y] = true;
                    sum++;
                    newArr[temp.x][temp.y] = 0;
                }
            }
            // array 를 다운시켜줌
             downArr(newArr);
//                System.out.println("ARRAY DOWN");
//             System.out.println(sum);
        }

        return sum;
    }

    public static void makeCombination(int cnt, int end, int start, int[] arr, boolean[] checked) {
        if (cnt == end) {
//            for (int i = 0; i < cnt; i++) {
//                System.out.print(arr[i] + " ");
//            }
            int num = shoot(arr);
        //    System.out.println(num);
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