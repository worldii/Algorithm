import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Cor {
	int idx;
	int num;

	Cor(int idx, int num) {
		this.idx = idx;
		this.num = num;
	}
}

public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st = null;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Deque<Cor> stack = new ArrayDeque<>();
		int[] checked = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n; i++) {
			checked[i] = -1;
		}

		for (int i = 0; i < n; i++) {
			if (!stack.isEmpty()) {
				while (!stack.isEmpty() && stack.peek().num <= arr[i]) {
					stack.pop();
				}
				if (!stack.isEmpty()) {
					checked[i] = stack.peek().idx;
				}
			}

			stack.offerFirst(new Cor(i, arr[i]));

		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(checked[i]+1 + " ");
		}
		System.out.println(sb);
	}

}
