import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Cor {
	int x;
	int y;
	int depth;

	Cor(int x, int y, int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;

	}
}

public class Solution {
	static int[][] arr;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] checked;
	static int n;
	static int mid;

	static void bfs(int x, int y) {
		Queue<Cor> q = new LinkedList<>();
		q.add(new Cor(x, y, 0));
		checked[x][y] = 1;
		while (!q.isEmpty()) {
			Cor temp = q.poll();
			int tmpDepth = temp.depth;
			for (int i = 0; i < 4; i++) {
				int newx = dx[i] + temp.x;
				int newy = dy[i] + temp.y;

				if (0 <= newx && newx < n && 0 <= newy && newy < n && tmpDepth + 1 <= mid) {
					if (checked[newx][newy] == 0) {
						checked[newx][newy] = 1;
						q.add(new Cor(newx, newy, tmpDepth + 1));
					}
				}

			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tt = 1; tt <= t; tt++) {
			n = sc.nextInt();
			arr = new int[n + 1][n + 1];
			checked = new int[n + 1][n + 1];

			sc.nextLine();
			for (int i = 0; i < n; i++) {
				String str = sc.nextLine();
				for (int j = 0; j < n; j++) {

					arr[i][j] = str.charAt(j) - '0';
				}
			}
			mid = n / 2;
		
			int sum = 0;
			bfs(mid, mid);

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (checked[i][j] == 1) {
						sum += arr[i][j];
					}
				}
			}
			System.out.println("#" + tt + " " + sum);
		}
	}

}
