
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] arr = new int[101];
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws NumberFormatException, IOException {

		for (int tt = 1; tt <= 10; tt++) {
			int t = Integer.parseInt(br.readLine());
			int[] arr = new int[101];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				arr[Integer.parseInt(st.nextToken())]++;
			}

			int start = 0;
			int end = 100;
			System.out.print("#" + tt + " ");

			for (int k = 0; k <= t; k++) {
				// find start, end
				start = 0;
				end = 100;
				while (arr[start] <= 0)
					start++;
				while (arr[end] <= 0)
					end--;
				if (k == t) {
					System.out.println(end - start);
					break;
				}
				arr[start]--;
				arr[end]--;
				arr[start + 1]++;
				arr[end - 1]++;
			}

		}
	}

}
