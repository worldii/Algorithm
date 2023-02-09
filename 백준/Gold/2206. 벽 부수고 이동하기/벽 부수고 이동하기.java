import java.util.LinkedList;
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

	@Override
	public String toString() {
		return "Cor [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
	}
}

public class Main {

	static int cnt = 1;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][][] checked;
	static int[][][] dist;
	static int[][] arr;
	static int n, m;

	public static int bfs(int startX, int startY, int endX, int endY) {
		checked =new int [n][m][2];
		dist =new int [n][m][2];

		Queue<Cor> q = new LinkedList<>();
		q.add(new Cor(startX, startY, 0));

		for (int i = 0  ; i< n ; i++) {
			for (int j = 0 ; j< m ; j++) {
				dist[i][j][0] = Integer.MAX_VALUE;
				dist[i][j][1] = Integer.MAX_VALUE;

			}
		}
		checked[startX][startY][0] = 1;
		dist[startX][startY][0] = 1;
		while (!q.isEmpty()) {
			Cor temp = q.peek();
			q.poll();
			//System.out.println(temp);
			if (temp.x == endX && temp.y == endY)
				return Math.min(dist[endX][endY][0], dist[endX][endY][1]);
			for (int i = 0; i <4; i++) {
				int newX = dx[i] + temp.x;
				int newY = dy[i] + temp.y;
				if (0 <= newX && newX < n && 0 <= newY && newY < m) {
					if (arr[newX][newY] == 0) {
					
						if (checked[newX][newY][temp.cnt] == 0) {
							checked[newX][newY][temp.cnt] = 1;
							dist[newX][newY][temp.cnt] = dist[temp.x][temp.y][temp.cnt] + 1;
							q.add(new Cor(newX, newY, temp.cnt));
						}
					} else {
					
						if (temp.cnt == 0) {
							if (checked[newX][newY][temp.cnt + 1] == 0) {
								checked[newX][newY][temp.cnt + 1] = 1;
								dist[newX][newY][temp.cnt + 1] = dist[temp.x][temp.y][temp.cnt] + 1;
								q.add(new Cor(newX, newY, temp.cnt + 1));
							}
						}
					}
				}

			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n][m];
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			String str = sc.nextLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

	

		System.out.println(bfs(0, 0, n - 1, m - 1));

	}
}
