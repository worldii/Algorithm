import com.sun.org.apache.xerces.internal.impl.dv.xs.SchemaDVFactoryImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.lang.reflect.Array;
import java.net.Inet4Address;
import java.util.*;


class Fireball {
    int r;
    int c;
    int m;
    int s;
    int d;

    Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}


class FireBallController {
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    private static ArrayList<Fireball>[][] fireballs = null;

    private static ArrayList<Fireball> fireballArrayList = null;
    private static int n;

    private ArrayList[][] init() {
        ArrayList[][] temp = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = new ArrayList<Fireball>();
            }
        }
        return temp;
    }

    FireBallController(ArrayList<Fireball> f, int n) {
        this.n = n;

        fireballs = this.init();
        this.fireballArrayList = f;
    }

    public void move() {

        for (int i = 0; i < fireballArrayList.size(); i++) {
            int nextX = (fireballArrayList.get(i).r + fireballArrayList.get(i).s * fireballArrayList.get(i).d) % n;
            int nextY = (fireballArrayList.get(i).c + fireballArrayList.get(i).s * fireballArrayList.get(i).d) % n;
            fireballArrayList.set(i, new Fireball(nextX, nextY, fireballArrayList.get(i).m, fireballArrayList.get(i).s, fireballArrayList.get(i).d));
        }
        preCalculateMap();
    }

    public void preCalculateMap() {
        for (int i = 0; i < fireballArrayList.size(); i++) {

            fireballs[fireballArrayList.get(i).r][fireballArrayList.get(i).c].add(fireballArrayList.get(i));
        }
    }


    public void moveAfter() {
        ArrayList[][] newMap = this.init();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fireballs[i][j].size() >= 2) {
                    ArrayList<Fireball> temp = fireballs[i][j];
                    int totalWei = 0;
                    int totalSpe = 0;
                    int dirFlag = temp.get(0).d;
                    boolean sameDir = true;
                    if (dirFlag % 2 == 0) {
                        dirFlag = 0;

                    } else {
                        dirFlag = 1;
                    }
                    for (int t = 0; t < temp.size(); t++) {
                        totalWei += temp.get(t).m;
                        totalSpe += temp.get(t).s;
                        if (dirFlag == 0 && temp.get(t).d % 2 == 1) sameDir = false;
                        if (dirFlag == 1 && temp.get(t).d % 2 == 0) sameDir = false;
                    }

                    int tempWei = totalWei / 5;
                    int tempSpe = totalSpe / fireballs[i][j].size();

                    if (tempWei <= 0) continue;
                    if (!sameDir) {
                        // 0 2 4 6
                        for (int t = 0; t < 4; t++) {
                            int newDir = t * 2;

                            newMap[i][j].add(new Fireball(i, j, tempWei, tempSpe, newDir));
                        }
                    } else {
                        // 1 3 5 6
                        for (int t = 0; t < 4; t++) {
                            int newDir = t * 2 + 1;
                            newMap[i][j].add(new Fireball(i, j, tempWei, tempSpe, newDir));
                        }
                    }

                } else {
                    newMap[i][j].add(fireballs[i][j].get(0));
                }
            }
        }

        this.fireballs = newMap;
        updateArrayList();
    }

    private void updateArrayList() {
        this.fireballArrayList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<Fireball> temp = fireballs[i][j];
                for (int t = 0; t < temp.size(); t++) {
                    fireballArrayList.add(temp.get(t));
                }
            }
        }
    }

    public int calculateSum() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ArrayList<Fireball> temp = fireballs[i][j];
                for (int t = 0; t < temp.size(); t++) {
                    sum += temp.get(t).m;
                }
            }
        }
        return sum;
    }
}


public class Main {

    public static int n, m, k;

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void bfs(boolean[] check, int start, int [] group) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        check[start] = true;

        while (!queue.isEmpty()) {
            int temp = queue.peek();
            queue.poll();
            for (int i = 0; i < graph.get(temp).size(); i++) {
                if (!check[graph.get(temp).get(i)] && group[start] == group[graph.get(temp).get(i)]) {
                    check[graph.get(temp).get(i)] = true;
                    queue.add(graph.get(temp).get(i));
                }
            }
        }
    }

    public static boolean start(int[] group, int n) {

        boolean flag = true;
        boolean[] checked = new boolean[n+1];
        // 그룹의 개수 구하기
        int cnt1 = 0;
        for (int i = 1; i <= n; i++) {
            if (!checked[i] && group[i] == 1) {
                cnt1++;
                bfs(checked, i, group);
            }
        }
        if (cnt1 != 1) flag = false;
        int  cnt2 = 0;
        for (int i = 1; i <= n; i++) {

            if (!checked[i] && group[i] == 2) {
                cnt2++;
                bfs(checked, i, group);
            }
        }

        if (cnt2 != 1) flag = false;
      //  System.out.println("cnt : " + cnt1 + " " + cnt2 + " ");
        return flag;
    }

    public static int minNum = Integer.MAX_VALUE;

    public static void recur(int cnt, int n, int[] group) {

        if (cnt == n + 1) {
            if (start(group, n)) {
                int sum = 0;
                for (int i = 1; i <= n; i++) {
                    if (group[i] == 1) {
                        sum += peo[i];
                    }
                }
//                for (int i = 1 ; i<= n ; i++) {
//                    System.out.print(group[i]+ " ");
//                }
               // System.out.println(peoTotal-sum + " " + sum);
                minNum = Math.min(Math.abs((peoTotal-sum) - sum), minNum);
                // System.out.println(minNum);
            }
            return;
        }


        // cnt 번째를 yes로 선택
        group[cnt] = 1;
        recur(cnt + 1, n, group);

        group[cnt] = 2;
        recur(cnt + 1, n, group);


    }

    public static int peoTotal = 0;
    public static int[] peo ;

    public static void main(String[] args) throws NumberFormatException, IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        peo = new int [n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            peo[i] = Integer.parseInt(st.nextToken());
            peoTotal += peo[i];
        }

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= cnt; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        int[] check = new int[n + 1];
        recur(1, n, check);
        if (minNum == Integer.MAX_VALUE) minNum = -1;
        System.out.println(minNum);
    }

}
