import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[][] arr = new int[101][101];
		for (int i = 1; i <= n; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}

		int d[][] = new int[101][100001];

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				// d[i][j] = d[i-1][j] //선택하지 않음
				// d[i][j] = d[i-1][j-wi] + a[i] 선택함
				d[i][j] = d[i - 1][j];
				if (j >= arr[i][0])
					d[i][j] = Math.max(d[i - 1][j - arr[i][0]] + arr[i][1], d[i][j]);
			}
		}
		System.out.println(d[n][k]);

	}
}
