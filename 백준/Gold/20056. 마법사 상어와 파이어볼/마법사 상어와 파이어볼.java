import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st = null;
    public static int n, m, k;

    static class Fireball {
        public final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        public final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
        private int r;
        private int c;
        private int m;
        private int s;
        private int d;
        private int cnt;
        private boolean dirFlag;

        Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
            this.cnt = 1;
            dirFlag = true;
        }

        public void increaseCnt() {
            this.cnt++;
        }

        @Override
        public String toString() {
            return "Fireball{" +
                    ", r=" + r +
                    ", c=" + c +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    ", cnt=" + cnt +
                    ", dirFlag=" + dirFlag +
                    '}';
        }

        public int getNextR() {
            return ((this.r + this.s * this.dx[this.d]) % n + n) % n;
        }

        public int getNextC() {
            return ((this.c + this.s * this.dy[this.d]) % n + n) % n;
        }


    }

    static class FireBallController {
        private Queue<Fireball> fireballs;
        private Fireball[][] space;

        FireBallController(Queue<Fireball> fireballs) {
            this.fireballs = fireballs;
            this.space = new Fireball[n][n];
        }

        public void start() {
            for (int i = 0; i < k; i++) {
                move();
                moveAfter();
            }
            calSum();
        }

        public void move() {
            // fireball Queue 를 보고 map 에 옮겨줌.
            while (!fireballs.isEmpty()) {
                Fireball cur = fireballs.poll();
                int nextR = cur.getNextR();
                int nextC = cur.getNextC();
                if (space[nextR][nextC] == null) {
                    space[nextR][nextC] = new Fireball(nextR, nextC, cur.m, cur.s, cur.d);
                } else {
                    space[nextR][nextC].increaseCnt();
                    space[nextR][nextC].m += cur.m;
                    space[nextR][nextC].s += cur.s;
                    // 방향은 처음에는 true 만약 안맞으면 false 로 바꿔줌
                    if (space[nextR][nextC].dirFlag) {
                        if (space[nextR][nextC].d % 2 != cur.d % 2) space[nextR][nextC].dirFlag = false;
                    }
                }


            }

        }


        public void moveAfter() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (space[i][j] == null) continue;
                    else if (space[i][j].cnt == 1) {
                        if (space[i][j].m > 0) {
                            // 그냥 fireball 리스트에 추가하면 됨 -> divide 가 안된다.
                            fireballs.add(space[i][j]);
                            space[i][j] = null;
                        }
                    } else {
                        // divide 되는 경우가 있다.
                        int newWei = space[i][j].m / 5;
                        int newSpe = space[i][j].s / space[i][j].cnt;
                        if (newWei > 0) {
                            if (space[i][j].dirFlag) {
                                for (int ii = 0; ii <= 6; ii += 2) {
                                    fireballs.add(new Fireball(i, j, newWei, newSpe, ii));
                                }
                            } else {
                                for (int ii = 1; ii <= 7; ii += 2) {
                                    fireballs.add(new Fireball(i, j, newWei, newSpe, ii));
                                }
                            }
                        }
                        space[i][j] = null;
                    }
                }
            }
        }

        public void calSum() {
            long cnt = 0;
            while (!fireballs.isEmpty()) {
                cnt += fireballs.peek().m;
                fireballs.poll();
            }
            System.out.println(cnt);
        }

    }


    public static void main(String[] args) throws NumberFormatException, IOException {


        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Queue<Fireball> fireballs = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r, c, m, s, d;
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, s, d));
        }
        FireBallController fireBallController = new FireBallController(fireballs);
        fireBallController.start();
    }

}
