import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[] arr;
	static int maxSum;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static void recur(int choose, int start, int[] arr2) {
		if (choose == 3) {
			int sum = 0;
			for (int i = 0; i < 3; i++) {
				sum += arr2[i];
			}
		//	System.out.println(sum);
			if (sum <= m) {
				maxSum = Math.max(sum, maxSum);
			}
			return;
		}
		if (start >= n)
			return;

		// 선택
		arr2[choose] = arr[start];
		recur(choose + 1, start + 1, arr2);

		// 선택 x ,
		arr2[choose] = 0;
		recur(choose, start + 1, arr2);
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		int[] arr2 = new int[m];
		recur(0, 0, arr2);
		System.out.println(maxSum);
	}

}
