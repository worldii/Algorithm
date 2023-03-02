import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
	public static int n;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;

	static class Tornado {

		private final int[] dx = {0, 1, 0, -1};
		private final int[] dy = {-1, 0, 1, 0};

		private int x;
		private int y;
		private int dir;

		private int tornadoSize;
		private int repeatCnt;

		Tornado(int n) {
			this.x = n / 2;
			this.y = n / 2;

			tornadoSize = 1;
			repeatCnt = 0;
		}

		public int getNx(int x) {
			return x + dx[dir];
		}

		public int getNy(int y) {
			return y + dy[dir];
		}

		public void move() {
			this.x = getNx(this.x);
			this.y = getNy(this.y);

			repeatCnt++;
			if (repeatCnt % tornadoSize == 0) {
				dir = (dir + 1) % 4;
			}

			if (repeatCnt == tornadoSize * 2) {
				repeatCnt = 0;
				tornadoSize++;
			}

		}

		public boolean IsMove() {
			return getNx(this.x) == 0 && getNy(this.y) == -1;
		}
	}

	static class Sand {
		public static int[][] arr;
		public int n;
		public int sum;

		// 왼 (0) , 아래 (1) , 오른(2), 위( 3)
		private final int[][] dirX = {
			{-1, 1, -2, -1, 1, 2, -1, 1, 0},
			{0, 0, 1, 1, 1, 1, 2, 2, 3},
			{-1, 1, -2, -1, 1, 2, -1, 1, 0},
			{0, 0, -1, -1, -1, -1, -2, -2, -3}
		};
		private final int[][] dirY = {
			{0, 0, -1, -1, -1, -1, -2, -2, -3},
			{-1, 1, -2, -1, 1, 2, -1, 1, 0},
			{0, 0, 1, 1, 1, 1, 2, 2, 3},
			{-1, 1, -2, -1, 1, 2, -1, 1, 0}
		};

		private final int[] amount =
			{1, 1, 2, 7, 7, 2, 10, 10, 5};

		Sand(int[][] arr, int n) {
			this.arr = arr;
			this.n = n;
			this.sum = 0;
		}

		public void move(Tornado tornado) {
			int curX = tornado.x;
			int curY = tornado.y;

			int nextX = tornado.getNx(curX);
			int nextY = tornado.getNy(curY);

			int aX = tornado.getNx(nextX);
			int aY = tornado.getNy(nextY);

			// 이건 격자 밖으로 안나감
			int sand = arr[nextX][nextY];

			if (sand > 0) {
				int tempSand = sand;
				for (int i = 0; i < 9; i++) {
					int sandX = curX + dirX[tornado.dir][i];
					int sandY = curY + dirY[tornado.dir][i];
					int sandAmount = (int)Math.floor((sand * (double)amount[i]) / 100);
					tempSand -= sandAmount;

					if (0 <= sandX && sandX < n && 0 <= sandY && sandY < n) {
						arr[sandX][sandY] += sandAmount;
					} else {
						sum += sandAmount;
					}

				}

				if (0 <= aX && aX < n && 0 <= aY && aY < n) {
					arr[aX][aY] += tempSand;
				} else {
					sum += tempSand;
				}

				arr[nextX][nextY] = 0;

			}
			tornado.move();
		}

		public void calculate() {
			System.out.println(sum);
		}

	}

	static class TornadoController {
		private Tornado tornado;
		private Sand sand;

		TornadoController(Tornado tornado, Sand sand) {
			this.tornado = tornado;
			this.sand = sand;
		}

		public void start() {
			do {
				sand.move(tornado);
			} while (!tornado.IsMove());
			sand.calculate();
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		TornadoController tornadoController = new TornadoController(new Tornado(n), new Sand(arr, n));
		tornadoController.start();
	}
}