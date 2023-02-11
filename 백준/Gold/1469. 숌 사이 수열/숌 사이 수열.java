
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		checked = new int[17];
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
	public static int[] temp;

	public static boolean check(int[] temp, int num, int cnt) {
		// cnt 에서 num 떨어진 거리에 있어야 num 이 있어야 함
		// cnt 에서 num 보다 작을때 num 이 있으면 안됨.

		for (int i = 0; i <= cnt; i++) {
			int tempNum = temp[i];
			int flag = 0;
			if (checked[tempNum] == 2) {
				if (i + tempNum + 1 <= cnt && temp[i + tempNum + 1] == tempNum) {
					flag = 1;
				} else if (i - tempNum - 1 >= 0 && temp[i - tempNum - 1] == tempNum) {
					flag = 1;
				}
				if (flag == 0)
					return false;
			}
		}
		return true;
	}

	public static void dfs(int cnt, int[] temp, int[] checked) {
		if (cnt == 2 * n) {
			for (int i = 0; i < 2 * n; i++) {
				System.out.print(temp[i] + " ");
			}
			System.exit(0);
		}
		// 백트래킹
		for (int i = 0; i < n; i++) {
			// arr[i] 가 들어 갈 수 있는지 확인
			if (checked[arr[i]] < 2) {
				temp[cnt] = arr[i];
				checked[arr[i]]++;
				if (check(temp, arr[i], cnt)) {
					// 된다면 넣어주고 다음꺼!
					dfs(cnt + 1, temp, checked);
				}
				checked[arr[i]]--;
				temp[cnt] = 0;
			}
		}
	}

}
