import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Cor {
	int x;
	int y;
	int cnt;

	Cor(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class Main {

	static List<Integer> list = new ArrayList<>();
	static int[][] arr = new int[201][201];
	static int[] dx = { -2, -2, -1, 1, 2, 2, 1, -1 };
	static int[] dy = { -1, 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] dx1 = { -1, 1, 0, 0 };
	static int[] dy1 = { 0, 0, 1, -1 };
	static int R, C;
	static int[][][] dist = new int[201][201][31];
	final static int INF = Integer.MAX_VALUE;
	static int[][][] checked = new int[201][201][31];

	public static void bfs(int startx, int starty, int cnt) {

		Queue<Cor> q = new LinkedList<>();
		q.add(new Cor(0, 0, 0));

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int k = 0; k <= cnt; k++) {
					dist[i][j][k] = -1;
				}
			}
		}
		checked[0][0][0] = 1;
		while (!q.isEmpty()) {
			Cor temp = q.peek();
			int tempCnt = q.peek().cnt;
			q.poll();
			// System.out.println(temp.x + " " + temp.y + " " + tempCnt);
			for (int i = 0; i < 4; i++) {
				int newx = dx1[i] + temp.x;
				int newy = dy1[i] + temp.y;
				if (0 <= newx && newx < R && 0 <= newy && newy < C) {
					if (checked[newx][newy][tempCnt] == 1)
						continue;
					if (arr[newx][newy] == 1)
						continue;

					checked[newx][newy][tempCnt] = 1;

					q.add(new Cor(newx, newy, tempCnt));

					dist[newx][newy][tempCnt] = dist[temp.x][temp.y][tempCnt] + 1;

				}
			}

			for (int i = 0; i < 8; i++) {
				int newx = dx[i] + temp.x;
				int newy = dy[i] + temp.y;
				if (0 <= newx && newx < R && 0 <= newy && newy < C) {

					if (arr[newx][newy] == 1)
						continue;

					if (tempCnt + 1 <= cnt) {
						if (checked[newx][newy][tempCnt + 1] == 1)
							continue;
						q.add(new Cor(newx, newy, tempCnt + 1));

						checked[newx][newy][tempCnt + 1] = 1;

						dist[newx][newy][tempCnt + 1] = dist[temp.x][temp.y][tempCnt] + 1;

						// }
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		C = sc.nextInt();
		R = sc.nextInt();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				arr[i][j] = sc.nextInt();
			}
		}

		bfs(0, 0, t);
		if (R == 1  && C== 1 ) {
			System.out.print(0);
			return ;
		}
		int minNum = -1;
		for (int i = 0; i <= t; i++) {
			// System.out.println(dist[R - 1][C - 1][i]);
			if (dist[R - 1][C - 1][i] != -1) {
				if (minNum == -1 || dist[R - 1][C - 1][i] < minNum)
					minNum = dist[R - 1][C - 1][i];
			}
		}
		if (minNum != -1)
			minNum += 1;
		System.out.print(minNum);

	}
}
