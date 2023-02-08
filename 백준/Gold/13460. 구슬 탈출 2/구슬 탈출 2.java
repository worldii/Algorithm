import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

class Cor {
	int x;
	int y;

	Cor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Cor [x=" + x + ", y=" + y + "]";
	}
}

public class Main {
	static char[][] arr;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][][][] checked;
	static int n, m;

	static Cor[] canGo(Cor blue, Cor red, int dirnum) {
		int curBlueX = blue.x;
		int curBlueY = blue.y;
		while (true) {
			int nextBlueX = curBlueX + dx[dirnum];
			int nextBlueY = curBlueY + dy[dirnum];
			if (arr[nextBlueX][nextBlueY] == '#')
				break;
			curBlueX = nextBlueX;
			curBlueY = nextBlueY;
			if (arr[curBlueX][curBlueY] == 'O')
				break;
		}
		int curRedX = red.x;
		int curRedY = red.y;
		while (true) {
			int nextRedX = curRedX + dx[dirnum];
			int nextRedY = curRedY + dy[dirnum];
			if (arr[nextRedX][nextRedY] == '#')
				break;
			curRedX = nextRedX;
			curRedY = nextRedY;
			if (arr[curRedX][curRedY] == 'O')
				break;
		}
		if (!(curRedX == curBlueX && curRedY == curBlueY)) {
			if (arr[curBlueX][curBlueY] == 'O')
				return null;
			return new Cor[] { new Cor(curBlueX, curBlueY), new Cor(curRedX, curRedY) };
		} else {
			if (arr[curRedX][curRedY] == 'O')
				return null;
			else {

				// 위치가 같은 경우
				// 그럼 한칸 옮길 곳을 고름.
				// 만약 blue 가 가다가 RED 를 만나면 블루가 더 후순위 -> 방향 한칸 띄워줌.

				int flag = 1;
				int tempX = blue.x;
				int tempY = blue.y;
				while (arr[tempX][tempY] != '#') {
					if (tempX == red.x && tempY == red.y)
						flag = 2;
					tempX = tempX + dx[dirnum];
					tempY = tempY + dy[dirnum];
				}
				if (flag == 2) {
					// 블루후순위
					curBlueX = curBlueX - dx[dirnum];
					curBlueY = curBlueY - dy[dirnum];
				} else {
					curRedX = curRedX - dx[dirnum];
					curRedY = curRedY - dy[dirnum];
				}
				return new Cor[] { new Cor(curBlueX, curBlueY), new Cor(curRedX, curRedY) };
			}
		}
	}

	static int bfs(Cor blue, Cor red) {

		Queue<Cor[]> q = new LinkedList<>();
		checked = new int[11][11][11][11];
		int[][][][] dist = new int[11][11][11][11];

		q.add(new Cor[] { blue, red });
		checked[blue.x][blue.y][red.x][red.y] = 1;
		dist[blue.x][blue.y][red.x][red.y] = 0;
		while (!q.isEmpty()) {
			Cor curRed = q.peek()[1];
			Cor curBlue = q.peek()[0];
			if (arr[curRed.x][curRed.y] == 'O')
				return dist[curBlue.x][curBlue.y][curRed.x][curRed.y];
			q.poll();
			for (int i = 0; i < 4; i++) {
				Cor[] temp = canGo(curBlue, curRed, i);
				// next 로 가는데
				if (temp == null)
					continue;
				if (checked[temp[0].x][temp[0].y][temp[1].x][temp[1].y] == 0) {
					checked[temp[0].x][temp[0].y][temp[1].x][temp[1].y] = 1;
					q.add(temp);
					dist[temp[0].x][temp[0].y][temp[1].x][temp[1].y] = dist[curBlue.x][curBlue.y][curRed.x][curRed.y]
							+ 1;

				}

			}
		}

		return -1;
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		Cor blue = null, red = null;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 'B') {
					blue = new Cor(i, j);
				} else if (arr[i][j] == 'R') {
					red = new Cor(i, j);
				}
				;
			}
		}

		int cnt = bfs(blue, red);
		if (cnt > 10 || cnt == -1)
			System.out.println(-1);
		else
			System.out.println(cnt);
	}

}
