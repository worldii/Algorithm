
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		String str = br.readLine();
		 ori = Integer.parseInt(str);
		n = str.length();

		arr = new int[n];
		checked = new int[n];
		temp = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = str.charAt(i) - '0';
		}
		Arrays.sort(arr);
		dfs(0, temp, checked);
		System.out.println(0);
	}

	public static int n, m;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;
	public static int[] arr;
	public static int[] checked;
	public static int[] temp;
public static int ori;
	public static void dfs(int cnt, int[] temp, int[] checked) {
		if (cnt == n) {
			String str = "";
			for (int i = 0; i < n; i++) {
				str += temp[i];
			}
			if (Integer.parseInt(str) > ori) {System.out.println(str);
			System.exit(0);}
			return;
		}

		for (int i = 0; i < n; i++) {
			if (checked[i] == 1)
				continue;
			checked[i] = 1;
			temp[cnt] = arr[i];
			dfs(cnt + 1, temp, checked);
			checked[i] = 0;
			temp[cnt] = 0;
		}
	}

}
