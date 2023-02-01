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
	static String[] arr;
	static int[] dx = { -1, 1, 0, 0, };
	static int[] dy = { 0, 0, 1, -1 };
	static int vCnt = 0;
	static int kCnt = 0;

	public static void bfs(int startx, int starty) {
		checked[startx][starty] = 1;
		int tempkCnt = 0;
		int tempvCnt = 0;

		Queue<Cor> q = new LinkedList<>();
		q.add(new Cor(startx, starty));

		while (!q.isEmpty()) {
			Cor temp = q.poll();
			if (arr[temp.x].charAt(temp.y) == 'v')
				tempvCnt++;
			else if (arr[temp.x].charAt(temp.y) == 'k')
				tempkCnt++;
			for (int i = 0; i < 4; i++) {

				int newx = dx[i] + temp.x;
				int newy = dy[i] + temp.y;

				if (0 <= newx && newx < m && 0 <= newy && newy < n) {
					if (arr[newx].charAt(newy) != '#' && checked[newx][newy] == 0) {
						// System.out.print(arr[newx].charAt(newy));
						checked[newx][newy] = 1;

						q.add(new Cor(newx, newy));
					}
				}
			}
		}
		// System.out.println(tempkCnt + " " + tempvCnt);

		if (tempkCnt > tempvCnt) {
			tempvCnt = 0;
		} else {

			tempkCnt = 0;
		}
		kCnt += tempkCnt;
		vCnt += tempvCnt;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();

		arr = new String[m];
		checked = new int[m][n];
		for (int i = 0; i < m; i++) {
			arr[i] = sc.next();

		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (checked[i][j] == 0 && arr[i].charAt(j) != '#') {
					//System.out.println(arr[i].charAt(j));
					bfs(i, j);
				}
			}
		}
		System.out.print(kCnt + " " + vCnt);
	}

}
