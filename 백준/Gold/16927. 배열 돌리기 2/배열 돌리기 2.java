import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static int n, m;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;
	public static int[][] arr = null;
	public static int[][] checked = null;
	public static int[] dx = { 0, 1, 0, -1 };
	public static int[] dy = { 1, 0, -1, 0 };
	public static ArrayDeque<Integer> queue = null;
	static StringBuilder sb = new StringBuilder();

	public static void insertQueue(int x, int y, int row, int col) {
		int curX = x;
		int curY = y;
		int cnt = 0;
		int beforeX =x;
		int beforeY =y;
		int temp = arr[curX][curY];
		
		
		for (int i = 0; i < col - 1; i++) {
			curX = dx[cnt] + beforeX;
			curY = dy[cnt] + beforeY;
			arr[beforeX][beforeY] = arr[curX][curY];
			beforeX = curX ;
			beforeY  =curY;
		}
		cnt++;

		for (int i = 0; i < row - 1; i++) {
			curX = dx[cnt] + curX;
			curY = dy[cnt] + curY;
			arr[beforeX][beforeY] = arr[curX][curY];
			beforeX = curX ;
			beforeY  =curY;

		}

		cnt++;

		for (int i = 0; i < col - 1; i++) {
			curX = dx[cnt] + curX;
			curY = dy[cnt] + curY;
			arr[beforeX][beforeY] = arr[curX][curY];
			beforeX = curX ;
			beforeY  =curY;

		}

		cnt++;

		for (int i = 0; i < row - 2; i++) {
			curX = dx[cnt] + curX;
			curY = dy[cnt] + curY;
			arr[beforeX][beforeY] = arr[curX][curY];
			beforeX = curX ;
			beforeY  =curY;

		}
		arr[beforeX][beforeY] = temp;
		cnt++;
	}

//	public static void insertArr(int x, int y, int row, int col) {
//		int curX = x;
//		int curY = y;
//		int cnt = 0;
//		for (int i = 0; i < col - 1; i++) {
//			arr[curX][curY] = queue.poll();
//			curX = dx[cnt] + curX;
//			curY = dy[cnt] + curY;
//		}
//		cnt++;
//		for (int i = 0; i < row - 1; i++) {
//
//			arr[curX][curY] = queue.poll();
//			curX = dx[cnt] + curX;
//			curY = dy[cnt] + curY;
//
//		}
//		cnt++;
//
//		for (int i = 0; i < col - 1; i++) {
//
//			arr[curX][curY] = queue.poll();
//			curX = dx[cnt] + curX;
//			curY = dy[cnt] + curY;
//		}
//		cnt++;
//
//		for (int i = 0; i < row - 2; i++) {
//			arr[curX][curY] = queue.poll();
//
//			curX = dx[cnt] + curX;
//			curY = dy[cnt] + curY;
//		}
//		arr[curX][curY] = queue.poll();
//	}

	public static void rotate(int x, int y, int row, int col) {
		// int row = 5 , col = 4
		insertQueue(x, y, row, col);
	//	insertArr(x, y, row, col);

	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		checked = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int curx = 0;
		int cury = 0;
		int curRow = n;
		int curCol = m;
		while (curRow > 0 && curCol > 0) {
			int t = r % ((curRow - 1) * 2 + (curCol - 1) * 2);
			for (int i = 0; i < t; i++) {
				rotate(curx, cury, curRow, curCol);
			}
			curx = curx + 1;
			cury = cury + 1;
			curRow -= 2;
			curCol -= 2;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
