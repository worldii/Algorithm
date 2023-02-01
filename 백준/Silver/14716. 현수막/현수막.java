import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
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

public class Main {
	static int m;
	static int n;
	static int[][] checked;
	static int[][] arr;
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void bfs(int startx, int starty) {
		checked[startx][starty] = 1;
		Queue<Cor> q = new LinkedList<>();
		q.add(new Cor(startx, starty));
		while (!q.isEmpty()) {
			Cor temp = q.poll();
			for (int i = 0; i < 8; i++) {
				int newx = dx[i] + temp.x;
				int newy = dy[i] + temp.y;
				if (0 <= newx && newx < m && 0 <= newy && newy < n) {
					if (arr[newx][newy] == 1 && checked[newx][newy] == 0) {
						checked[newx][newy] = 1;
						q.add(new Cor(newx, newy));
					}
				}
			}
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();

		arr = new int[m][n];
		checked = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int cnt = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (checked[i][j] == 0 && arr[i][j] == 1) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		System.out.print(cnt);
	}

}
