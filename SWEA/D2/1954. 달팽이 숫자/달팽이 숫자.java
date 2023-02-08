
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Cor {
	int x;
	int y;

	Cor(int x, int y) {
		this.x = x;
		this.y = y;

	}
}

public class Solution {

	static boolean[][] check;
	static int n;
	static int[][] arr;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int cnt;

	static void bfs(int startx, int starty) {

		Queue<Cor> q = new LinkedList<>();
		check[startx][startx] = true;
		q.add(new Cor(startx, starty));
		int dir = 0;
		arr[startx][starty] = cnt++;
		if (n ==1) return;
		while (!q.isEmpty()) {
			Cor temp = q.peek();
			q.poll();
			int newx = temp.x + dx[dir];
			int newy = temp.y + dy[dir];
			if (0 <= newx && newx < n && 0 <= newy && newy < n) {
				if (!check[newx][newy]) {
					check[newx][newy] = true;
					q.add(new Cor(newx, newy));
					arr[newx][newy] = cnt++;
				} else if (check[newx][newy] && cnt != n * n + 1) {
					q.add(new Cor(temp.x, temp.y));
					dir = (dir + 1) % 4;

				}
			} else {
				dir = (dir + 1) % 4;
				q.add(new Cor(temp.x, temp.y));

			}
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int tt = 1; tt <= t; tt++) {
			n = sc.nextInt();

			System.out.println("#" + tt);
			arr = new int[n][n];
			check = new boolean[n][n];
			cnt = 1;
			bfs(0, 0);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(arr[i][j]+ " ");
				}
				System.out.println();
			}
		}
	}

}
