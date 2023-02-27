
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int total = -1;

	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	static int n;

	public static int[][] copy(int[][] arr) {
		int[][] arr2 = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr2[i][j] = arr[i][j];
			}
		}
		return arr2;
	}

	public static int move(int[][] arr, int dirNum, int[][] arr2) {
		// view 를 보고 arr에 담음.
		// dirNum 이 2또는 3일 경우 -> 좌우로 움직임

		if (dirNum == 2 || dirNum == 3) {
			for (int i = 0; i < n; i++) {
				int[] temp = new int[n];
				int idx = 0;
				if (dirNum == 2) {
					for (int j = 0; j < n; j++) {
						if (arr[i][j] == 0) {
							continue;
						}
						temp[idx++] = arr[i][j];
					}

				} else {
					for (int j = n - 1; j >= 0; j--) {
						if (arr[i][j] == 0) {
							continue;
						}
						temp[idx++] = arr[i][j];
					}
				}
				int cur = 0;
				int arr2Idx = 0;
				while (cur < idx) {
					if (cur == idx - 1) {
						arr2[i][arr2Idx++] = temp[cur];
						cur = cur + 1;

					} else if (temp[cur] == temp[cur + 1]) {
						arr2[i][arr2Idx++] = temp[cur] * 2;
						cur = cur + 2;
					} else {
						arr2[i][arr2Idx++] = temp[cur];
						cur = cur + 1;
					}
				}
				while (arr2Idx < n) {
					arr2[i][arr2Idx++] = 0;
				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				int[] temp = new int[n];
				int idx = 0;
				if (dirNum == 0) {
					for (int j = 0; j < n; j++) {
						if (arr[j][i] == 0) {
							continue;
						}
						temp[idx++] = arr[j][i];
					}

				} else {
					for (int j = n - 1; j >= 0; j--) {
						if (arr[j][i] == 0) {
							continue;
						}
						temp[idx++] = arr[j][i];
					}
				}



				int cur = 0;
				int arr2Idx = 0;
				while (cur < idx) {
					if (cur == idx - 1) {
						arr2[arr2Idx++][i] = temp[cur];
						cur = cur + 1;

					} else if (temp[cur] == temp[cur + 1]) {
						arr2[arr2Idx++][i] = temp[cur] * 2;
						cur = cur + 2;

					} else {
						arr2[arr2Idx++][i] = temp[cur];
						cur = cur + 1;
					}

				}
				while (arr2Idx < n) {
					arr2[arr2Idx++][i] = 0;
				}
			}

		}
		int cnt = -1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cnt = Math.max(cnt, arr2[i][j]);
				//System.out.print(arr2[i][j] + " ");
			}
		//	System.out.println();
		}
		//System.out.println();
		return cnt;
	}

	public static void recur(int cnt, int sum, int[] dir, int[][] arr) {
		total = Math.max(sum, total);
		if (cnt == 5)
			return;

		for (int i = 0; i < 4; i++) {
			dir[cnt] = i;
			int[][] arr2 = copy(arr);
			int[][] nextArr = new int[n][n];
			int tempSum = move(arr2, i, nextArr);
			recur(cnt + 1, tempSum, dir, nextArr);
			dir[cnt] = 0;
		}

	}

	public static void main(String[] args) throws IOException {

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] dir = new int[5];
		recur(0, 0, dir, arr);
		System.out.println(total);

	}

}
