import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static int n, m;
	static int[][] arr;
	static int maxSum;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// Scanner sc = new Scanner(System.in);
		int t = Integer.parseInt(br.readLine());
		for (int tt = 1; tt <= t; tt++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			arr = new int[n + 1][n + 1];
			int[][] d = new int[n + 1][n + 1];

			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = 1; j <= n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					d[i][j] = d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1] + arr[i][j];
				}
			}

			int maxNum = -1;
			for (int x1 = 1; x1 <= n; x1++) {
				for (int y1 = 1; y1 <= n; y1++) {

					int x2 = x1 + m - 1;
					int y2 = y1 + m - 1;
					if (x2 > n || y2 > n)
						continue;
					int num = d[x2][y2] - d[x1 - 1][y2] - d[x2][y1 - 1] + d[x1 - 1][y1 - 1];
					maxNum = Math.max(num, maxNum);
				}
			}
			System.out.println("#"+ tt+ " " + maxNum);
		}
	}

}
