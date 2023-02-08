import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] arr;
	static int maxSum;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

	
		arr = new int[n+1][n+1];
		int [][] d = new int [n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				d[i][j] = d[i-1][j] + d[i][j-1] - d[i-1][j-1] + arr[i][j];
			}
		}
	
		for (int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());

			int x1, x2, y1, y2;
			x1 =  Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			System.out.println(d[x2][y2] - d[x1-1][y2] - d[x2][y1-1] + d[x1-1][y1-1]);
		}
	}

}
