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

	public Cor(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int[][] checked = new int[51][51];
	static int[][] arr = new int[51][51];

	static int n, m;

	// 나이트
	public static void bfs(Cor start) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		Queue<Cor> q = new LinkedList<>();
		q.add(start);
		checked[start.x][start.y] = 1;
		while (!q.isEmpty()) {
			Cor temp = q.peek();

			q.poll();
			for (int i = 0; i < 4; i++) {
				int newx = temp.x + dx[i];
				int newy = temp.y + dy[i];
				if (0 <= newx && newx < m) {
					if (0 <= newy && newy < n) {
						if (checked[newx][newy] == 0 && arr[newx][newy] == 1) {
							checked[newx][newy] = 1;
							q.add(new Cor(newx, newy));
						}
					}
				}
			}
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-->0) {
			m = sc.nextInt();
			n = sc.nextInt();
			int k = sc.nextInt();
			arr = new int[51][51];
			checked = new int[51][51];
			for (int i = 0; i < k; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				arr[x][y] = 1;

			}
		
			int cnt = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == 1 && checked[i][j] == 0) {
						bfs(new Cor(i, j));
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
