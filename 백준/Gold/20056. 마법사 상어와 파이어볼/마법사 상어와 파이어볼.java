import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Fireball {
    int r;
    int c;
    int m;
    int s;
    int d;

    @Override
    public String toString() {
        return "Fireball{" +
                "r=" + r +
                ", c=" + c +
                ", m=" + m +
                ", s=" + s +
                ", d=" + d +
                '}';
    }

    Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }

}

public class Main {


    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;

    public static ArrayList<ArrayList<ArrayList<Fireball>>> fireBallMap = new ArrayList<>();

    public static ArrayList<ArrayList<ArrayList<Fireball>>> nextFireBallMap = new ArrayList<>();

    public static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static int n, m, k;


    public static void move() {
        // fireball map 에 담음 => new fireballmap 에다가 옮겨 담음 (move)

        init(nextFireBallMap);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fireBallMap.get(i).get(j).size() > 0) {
                    for (int t = 0; t < fireBallMap.get(i).get(j).size(); t++) {
                        int nextX = (n+(n+fireBallMap.get(i).get(j).get(t).r + dx[fireBallMap.get(i).get(j).get(t).d] * fireBallMap.get(i).get(j).get(t).s) % n)%n ;
                        int nextY = (n + (n+ fireBallMap.get(i).get(j).get(t).c + dy[fireBallMap.get(i).get(j).get(t).d] * fireBallMap.get(i).get(j).get(t).s) % n)%n ;
                       // System.out.println(nextX + " "+ nextY);
                        nextFireBallMap.get(nextX).get(nextY).add(new Fireball(nextX, nextY, fireBallMap.get(i).get(j).get(t).m, fireBallMap.get(i).get(j).get(t).s, fireBallMap.get(i).get(j).get(t).d));

                    }
                }
            }
        }

    }

    public static void init(ArrayList<ArrayList<ArrayList<Fireball>>> arr) {
        arr.clear();
        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<ArrayList<Fireball>>());
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                arr.get(i).add(new ArrayList<Fireball>());
            }
        }
    }

    public static void moveAfter() {
        // new fireball map 을 보고 합쳐지는 것을 firemap 에 담음

       // printArr(nextFireBallMap);
        init(fireBallMap);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nextFireBallMap.get(i).get(j).size() == 0) continue;
                if (nextFireBallMap.get(i).get(j).size() >= 2) {
                    ArrayList<Fireball> temp = nextFireBallMap.get(i).get(j);
                    int weiTotal = 0;
                    int speTotal = 0;
                    int dir = (nextFireBallMap.get(i).get(j).get(0).d) % 2;
                    boolean dirFlag = true;
                    // dir 이 0 이면 짝수
                    for (int t = 0; t < temp.size(); t++) {

                        weiTotal += temp.get(t).m;
                        speTotal += temp.get(t).s;
                        if (dir != temp.get(t).d % 2) dirFlag = false;

                    }
                    weiTotal /= 5;
                    speTotal /= nextFireBallMap.get(i).get(j).size();
                    if (weiTotal == 0) continue;
                    if (dirFlag) {
                        for (int ii = 0; ii <= 6; ii += 2) {

                            fireBallMap.get(i).get(j).add(new Fireball(i, j, weiTotal, speTotal, ii));

                        }
                    } else {
                        for (int ii = 1; ii <= 7; ii += 2) {
                            fireBallMap.get(i).get(j).add(new Fireball(i, j, weiTotal, speTotal, ii));
                        }
                    }

                } else {
                    if (nextFireBallMap.get(i).get(j).get(0).m <= 0) continue;
                    fireBallMap.get(i).get(j).add(nextFireBallMap.get(i).get(j).get(0));
                }
            }
        }
       // printArr(fireBallMap);

    }

    public static void printArr(ArrayList<ArrayList<ArrayList<Fireball>>> arr) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr.get(i).get(j).size() > 0)  {
                    for (int t= 0 ;t< arr.get(i).get(j).size() ;t++) {
                        System.out.print(arr.get(i).get(j).get(t));
                    }

                }
                else {
                    System.out.print("EMPTY");
                }

            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {


        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        init(fireBallMap);
        init(nextFireBallMap);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r, c, m, s, d;
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            fireBallMap.get(r).get(c).add(new Fireball(r, c, m, s, d));
        }
        for (int i = 0; i < k; i++) {
            move();
            moveAfter();
        }
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (fireBallMap.get(i).get(j).size() > 0) {
                    for (int t = 0; t < fireBallMap.get(i).get(j).size(); t++) {
                        cnt += fireBallMap.get(i).get(j).get(t).m;
                    }
                }
            }
        }
        System.out.println(cnt);
    }

}
