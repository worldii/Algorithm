import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		numChecked = new int[17];
		checked = new int[2 * n];
		temp = new int[2 * n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		dfs(0, temp, checked);
		System.out.println(-1);
	}

	public static int n, m;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;
	public static int[] arr;
	public static int[] checked;
	public static int[] numChecked;
	public static int[] temp;

	public static void dfs(int cnt, int[] temp, int[] checked) {

		int loc = cnt;
		while (loc < 2 * n && checked[loc] == 1) {
			loc++;
		}
		if (loc == 2 * n) {
			for (int i = 0; i < 2 * n; i++) {
				System.out.print(temp[i] + " ");
			}
			System.exit(0);
			;
		}

		// 백트래킹
		for (int i = 0; i < n; i++) {
			// arr[i] 가 들어 갈 수 있는지 확인
			if (numChecked[arr[i]] == 0 && loc + arr[i] + 1 < 2 * n && checked[loc] == 0
					&& checked[arr[i] + loc + 1] == 0) {
				temp[loc] = arr[i];
				temp[loc + arr[i] + 1] = arr[i];

				numChecked[arr[i]] = 1;

				checked[loc] = 1;
				checked[arr[i] + loc + 1] = 1;

				dfs(cnt + 1, temp, checked);

				checked[loc] = 0;
				checked[arr[i] + loc + 1] = 0;

				numChecked[arr[i]] = 0;

				temp[loc] = 0;
				temp[arr[i] + loc + 1] = 0;
			}
		}

	}

}
